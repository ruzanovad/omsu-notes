import glob
from operator import mul
import numpy as np
from scipy.linalg import solve
from scipy.integrate import quad
import matplotlib.pyplot as plt
import pandas as pd
import lab1

QUADRATURE = 2  # Global variable to set the quadrature degree
NODES_3 = np.array([-np.sqrt(3 / 5), 0, np.sqrt(3 / 5)])
NODES_2 = np.array([-np.sqrt(1 / 3), np.sqrt(1 / 3)])
WEIGHTS_3 = np.array([5 / 9, 8 / 9, 5 / 9])
WEIGHTS_2 = np.array([1, 1])


def get_weights_nodes(a, b):
    if QUADRATURE == 2:
        nodes = 0.5 * (b + a) + (b - a) * 0.5 * NODES_2
        weights = WEIGHTS_2 * (b - a) * 0.5
    else:
        nodes = 0.5 * (b + a) + (b - a) * 0.5 * NODES_3
        weights = WEIGHTS_3 * (b - a) * 0.5
    return weights, nodes


def gauss_quadrature_3rd_order(f, a, b):
    """
    Computes the integral of a function `f` over [a, b] using 3rd-order Gauss quadrature
    by dividing [a, b] into smaller intervals.
    """
    n_intervals = 1000  # Number of intervals
    # Nodes and weights for [-1, 1]
    nodes = np.array([-np.sqrt(3 / 5), 0, np.sqrt(3 / 5)])
    weights = np.array([5 / 9, 8 / 9, 5 / 9])

    # Step size for intervals
    h = (b - a) / n_intervals
    integral = 0.0

    for i in range(n_intervals):
        sub_a = a + i * h
        sub_b = sub_a + h

        # Transform nodes and weights for the current subinterval
        transformed_nodes = 0.5 * (sub_b - sub_a) * nodes + 0.5 * (sub_a + sub_b)
        transformed_weights = 0.5 * (sub_b - sub_a) * weights

        # Compute the integral over the current subinterval
        integral += np.sum(transformed_weights * f(transformed_nodes))

    return integral


def gauss_quadrature_2nd_order(f, a, b):
    """
    Computes the integral of a function `f` over [a, b] using 2nd-order Gauss quadrature
    by dividing [a, b] into smaller intervals.
    """
    n_intervals = 1000  # Number of intervals
    # Nodes and weights for [-1, 1]
    nodes = np.array([-np.sqrt(1 / 3), np.sqrt(1 / 3)])
    weights = np.array([1, 1])

    # Step size for intervals
    h = (b - a) / n_intervals
    integral = 0.0

    for i in range(n_intervals):
        sub_a = a + i * h
        sub_b = sub_a + h

        # Transform nodes and weights for the current subinterval
        transformed_nodes = 0.5 * (sub_b - sub_a) * nodes + 0.5 * (sub_a + sub_b)
        transformed_weights = 0.5 * (sub_b - sub_a) * weights

        # Compute the integral over the current subinterval
        integral += np.sum(transformed_weights * f(transformed_nodes))

    return integral


def integral(f, a, b):
    """
    Wrapper function to compute integrals using the appropriate quadrature degree.
    """
    if QUADRATURE == 3:
        return gauss_quadrature_3rd_order(f, a, b)
    else:
        return gauss_quadrature_2nd_order(f, a, b)


def plot_results(
    x,
    y,
    x_hat,
    fun,
    b,
    beta,
    dots,
    real_label="Real Function",
    approx_label="Approximation",
):
    """
    Plots the real function and the approximate solution.

    Parameters:
        x (np.ndarray): The x-coordinates for the real function points (discretized nodes).
        y (np.ndarray): The y-coordinates for the real function values at x (real function values).
        x_hat (np.ndarray): A dense set of x-coordinates for the approximation.
        fun (function): The approximation function (e.g., piecewise approximation).
        b (np.ndarray): The breakpoints or coefficients for the approximation function.
        beta (np.ndarray): The weights or parameters for the approximation function.
        real_label (str): Label for the real function in the plot.
        approx_label (str): Label for the approximation function in the plot.
    """
    # Compute the approximated y values for the dense x_hat
    y_hat = np.zeros(len(x_hat))
    for i in range(len(x_hat)):
        y_hat[i] = fun(x_hat[i], b, beta)

    # Plot the real function (discretized points) and the approximation
    plt.figure(figsize=(10, 6))
    plt.plot(
        x, y, "o", label=real_label, markersize=5, alpha=0.8
    )  # Discretized real function
    plt.plot(
        x_hat, y_hat, "-", label=approx_label, linewidth=2
    )  # Approximated function

    plt.scatter(
        dots[0], dots[1], linewidth=2, label="Y", color="green"
    )  # results of sle
    plt.grid()
    plt.xlabel("x")
    plt.ylabel("y")
    plt.legend(loc="best")
    plt.title("Real Function vs Approximation")
    plt.show()


def fredholm_solver(
    kernel, exact_solution, a=0, b=1, n_intervals=100, m=5, alpha=0.001
):
    """
    Solves Fredholm integral equation of the first kind using piecewise linear approximation
    with 2nd or 3rd order Gauss quadrature (controlled by QUADRATURE).

    Returns:
        f_approx_fine (np.ndarray): Approximate solution on a fine grid.
        error (float): Maximum absolute error compared to the exact solution.
        beta (np.ndarray): Approximation coefficients.
        x_nodes (list): Discretization points.
    """
    if m >= n_intervals:
        return None, None, None

    # Determine the multiplier based on QUADRATURE
    if QUADRATURE == 2:
        multiplier = 2
    elif QUADRATURE == 3:
        multiplier = 3
    else:
        raise ValueError("QUADRATURE must be 2 or 3.")

    n = n_intervals * multiplier
    x_nodes = np.linspace(a, b, n_intervals + 1)

    # Collect all quadrature nodes
    x_hat = []
    for j in range(n_intervals):
        _, nodes = get_weights_nodes(x_nodes[j], x_nodes[j + 1])
        x_hat.extend(nodes)
    x_hat = np.array(x_hat)
    x_nodes_f = x_hat.copy()

    def g(x):
        def integrand(t, x_i):
            return kernel(x_i, t) * exact_solution(t)

        results = []
        for x_i in x:
            integral_value = integral(lambda t: integrand(t, x_i), a, b)
            results.append(integral_value)
        return np.array(results)

    # Build matrix K_ij
    K = np.zeros((n, n))
    for i in range(n):
        for j in range(n_intervals):
            weights, nodes = get_weights_nodes(x_nodes[j], x_nodes[j + 1])
            for k in range(multiplier):
                idx = multiplier * j + k
                if idx >= n:
                    continue  # Avoid index out-of-bounds
                K[i, idx] = weights[k] * kernel(x_nodes_f[i], nodes[k])

    # Build the vector f_i using the selected quadrature
    f_vector = g(x_nodes_f)
    # print(f_vector)

    # Apply regularization to solve ill-posed systems
    A = K + alpha * np.eye(n)
    # print(A)
    Y = np.linalg.solve(A, f_vector)

    x_hat, Y = lab1.preprocess(x_hat, Y)
    b_mse = lab1.generate_b(x_hat, n, m)
    beta = lab1.get_parameters(x_hat, Y, b=b_mse)

    # Fine grid for visualization
    t_fine = np.linspace(a, b, num=1000)
    f_approx_fine = np.zeros((1000))
    for i in range(1000):
        f_approx_fine[i] = lab1.fun(t_fine[i], b_mse, beta)

    f_exact_fine = exact_solution(t_fine)

    # Compute the maximum absolute error
    error = np.max(np.abs(f_approx_fine - f_exact_fine))
    print(f"Maximum absolute error: {error:.2e}")

    plot_results(t_fine, f_exact_fine, t_fine, lab1.fun, b_mse, beta, (x_hat, Y))
    return f_approx_fine, error, x_hat


def create_error_dataframe(
    kernel_string,
    kernel,
    exact_solution,
    a,
    b,
    intervals_list,
    m_list,
    function_label,
    quadrature_degree,
):
    """
    Creates a DataFrame with approximation errors for different `n_intervals` and quadrature degrees.
    """
    results = []
    global QUADRATURE
    QUADRATURE = quadrature_degree  # Set quadrature degree globally

    for n_intervals in intervals_list:
        for m in m_list:
            _, error, _ = fredholm_solver(
                kernel, exact_solution, a=a, b=b, n_intervals=n_intervals, m=m
            )
            results.append(
                {
                    "Kernel": kernel_string,
                    "Function": function_label,
                    "n_intervals": n_intervals,
                    "Quadrature Degree": quadrature_degree,
                    "Error": error,
                    "m": m,
                }
            )

    return pd.DataFrame(results)


if __name__ == "__main__":
    pd.options.display.float_format = "{:.2e}".format

    # def kernel(x, t):
    #     return np.abs(x - t)

    a, b = 0, 1

    kernels = [
        ("np.abs(x-t)", lambda x, t: np.abs(x - t)),
        # ("2*np.sin(x - t)", lambda x, t: 2 * np.sin(x - t)),
    ]

    intervals_list = [20, 50, 200, 500]
    m_list = [4, 10]

    test_cases = [
        (lambda t: t, "Linear Function (t)", 2),
        (lambda t: t, "Linear Function (t)", 3),
        # (lambda t: np.exp(2 * t), "Exponential Function (exp(2*t))", 2),
        # (lambda t: np.exp(2 * t), "Exponential Function (exp(2*t))", 3),
        (lambda t: np.sin(6 * t), "Sine Function (sin(6*t))", 2),
        (lambda t: np.sin(6 * t), "Sine Function (sin(6*t))", 3),
    ]

    final_results = []
    for kern, kernel in kernels:
        for func, func_label, quadrature_degree in test_cases:
            exact_solution = func
            error_df = create_error_dataframe(
                kern,
                kernel,
                exact_solution,
                a,
                b,
                intervals_list,
                m_list,
                func_label,
                quadrature_degree,
            )
            final_results.append(error_df)

    combined_df = pd.concat(final_results, ignore_index=True)
    print(combined_df)
    # combined_df.to_csv("error_results_combined.csv", index=False)
