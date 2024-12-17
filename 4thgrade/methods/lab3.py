import numpy as np
import matplotlib.pyplot as plt


def solve_cauchy_second_order(f, y0, t0, t_end, h):
    """
    Solve the Cauchy problem for a first-order ODE using a second-order approximation scheme.

    f: function - the derivative function dy/dt = f(t, y)
    y0: float - initial condition y(t0) = y0
    t0: float - initial time
    t_end: float - final time
    h: float - step size

    Returns:
    t_values: np.array - array of time values
    y_values: np.array - array of solution values
    """
    t_values = np.arange(t0, t_end + h, h)
    y_values = np.zeros_like(t_values)
    y_values[0] = y0

    for i in range(1, len(t_values)):
        t = t_values[i - 1]
        y = y_values[i - 1]

        # Second-order approximation (Heun's method)
        k1 = f(t, y)
        k2 = f(t + h, y + h * k1)
        y_values[i] = y + (h / 2) * (k1 + k2)

    return t_values, y_values


def improve_to_third_order(f, y0, t0, t_end, h):
    """
    Improve the second-order approximation to third-order by adding corrections.

    f: function - the derivative function dy/dt = f(t, y)
    y0: float - initial condition y(t0) = y0
    t0: float - initial time
    t_end: float - final time
    h: float - step size

    Returns:
    t_values: np.array - array of time values
    y_values: np.array - array of solution values (third-order corrected)
    """
    # Start with the second-order solution
    t_values, y_values = solve_cauchy_second_order(f, y0, t0, t_end, h)

    # Compute corrections for third-order approximation
    y_corrected = y_values.copy()
    for i in range(1, len(t_values)):
        t = t_values[i - 1]
        y = y_values[i - 1]

        # Compute the second-order residual (h^2 * derivative)
        k1 = f(t, y)
        k2 = f(t + h, y + h * k1)
        residual = (h**2) * (k2 - k1) / 2

        # Apply the third-order correction
        y_corrected[i] = y_values[i] + residual

    return t_values, y_corrected


# Example usage
def example_function(t, y):
    return -2 * t * y  # Example: dy/dt = -2ty


if __name__ == "__main__":

    # Initial conditions and parameters
    t0 = 0
    t_end = 2
    y0 = 1
    h = 0.1

    # Solve with second-order method
    t_values, y_second_order = solve_cauchy_second_order(
        example_function, y0, t0, t_end, h
    )

    # Improve to third-order
    t_values, y_third_order = improve_to_third_order(example_function, y0, t0, t_end, h)

    # Analytical solution for comparison
    analytical_solution = np.exp(-(t_values**2))

    # Plot results
    plt.figure(figsize=(10, 6))
    plt.plot(t_values, analytical_solution, label="Analytical", linestyle="--")
    plt.plot(t_values, y_second_order, label="Second-order", marker="o")
    plt.plot(t_values, y_third_order, label="Third-order", marker="s")
    plt.xlabel("t")
    plt.ylabel("y")
    plt.title("Solution of ODE using Second and Third-Order Methods")
    plt.legend()
    plt.grid()
    plt.show()
