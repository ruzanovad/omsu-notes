import warnings
import numpy as np

warnings.filterwarnings("ignore")

EPS = 1e-5
NUMBER_OF_ITERATIONS = 1000


def gauss_seidel(A, b):
    n = len(A)
    x = np.zeros(n) 

    converge = False
    eps_conv = 1e-7
    
    cnt = 0
    while not converge and cnt < NUMBER_OF_ITERATIONS:
        x_new = np.copy(x)
        for i in range(n):
            s1 = sum(A[i][j] * x_new[j] for j in range(i)) 
            # ignore i coordinate 
            s2 = sum(A[i][j] * x[j] for j in range(i + 1, n))
            x_new[i] = (b[i] - s1 - s2) / A[i][i]

        converge = norm(x_new - x) <= eps_conv
        x = x_new
        cnt+=1
    if converge:
        return x
    else:
        # print("Method does not converge")
        return None
    


def get_sign(sign_rule, i):
    if sign_rule % 2:
        return 1 if i % 2 == 0 else -1
    else:
        return -1 if i % 2 == 0 else 1

# alpha beta 
def first_class(n, alpha, beta, inv=False, sign_rule=1):
    Q = np.zeros((n, n))
    J = np.zeros((n, n))
    return_value = np.zeros((n, n))

    if not inv:
    for i in range(n):
        sign = 1
        gamma = np.sqrt((i + 0.) / (n - 1))

        if i == 0:
            J[0, 0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta))
        else:
            J[i, i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta))
        if i != n-1:
            for j in range(i+1):
                Q[j, i] = 1./np.sqrt((i+1)*(i+2))
            Q[i+1, i] = -np.sqrt(float(i+1)/(i+2))
        else: 
            for j in range(i+1):
                Q[j, i] = 1./np.sqrt(n)

    return_value = np.matmul(np.matmul(Q, J), Q.T)
    print(Q)
    print(J)
    print(Q.T)
    return return_value


def second_class(n, alpha, beta, inv=False, sign_rule=1):
    T = np.zeros((n, n))
    T_inv = np.zeros((n, n))
    J = np.zeros((n, n))
    return_value = np.zeros((n, n))

    T[0, 0] = 1
    T_inv[0, 0] = n

    gamma = 0
    sign = 1

    if not inv:
        J[0, 0] = sign * ((1 - gamma) * alpha + gamma * beta)
        for i in range(1, n):
            T[i-1, i] = T[i, i-1] = -1

            T_inv[i, i] = T_inv[0, i] = T_inv[i, 0] = n - i

            sign = 1
            gamma = np.sqrt((i + 0.) / (n - 1))
            J[i, i] = sign * ((1 - gamma) * alpha + gamma * beta)
        J[0, 1] = 1  # Set the (0, 1) element to 1
    else:
        J[0, 0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta))
        J[0, 1] = -J[0, 0] * J[0, 0]  # Set the (0, 1) element

        for i in range(1, n):
            T[i-1, i] = T[i, i-1] = -1

            T_inv[i, i] = T_inv[0, i] = T_inv[i, 0] = n - i

            sign = 1
            gamma = np.sqrt((i + 0.) / (n - 1))
            J[i, i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta))

    return_value = np.matmul(np.matmul(T, J), T_inv)
    return return_value


def norm(x):
    if isinstance(x, np.ndarray):
        return np.linalg.norm(x, np.inf)
    else:
        raise ValueError("Input must be a numpy array.")
    

def print_results(A, b, expected_x, log=False):
    if log:
        print("Matrix A:")
        print(A)
        print("Right-hand side vector:")
        print(b)

    A_copy = A.copy()
    b_copy = b.copy()
    actual_x = gauss_seidel(A, b)
    if isinstance(actual_x, np.ndarray):
        print("Expected solution:")
        print(expected_x)
        print("Computed solution:")
        print(actual_x)
        z = expected_x-actual_x

        print("Error:", norm(z))
        print("Relative error:", norm(z) / norm(expected_x))

        mul = np.matmul(A_copy, actual_x)
        r = mul- b_copy

        print("Residual:", norm(r))
        print("Relative residual:", norm(r) / norm(b_copy))

        assert norm(z) < EPS
    else:
        print(f"Method does not converge, the maximal number of iterations = {NUMBER_OF_ITERATIONS} is exceeded")
    


def generate_actual_solution(n, alpha=1, beta=1):
    return_value = np.zeros(n)
    for i in range(n):
        pi_half = np.pi * 0.5
        gamma = np.sin(pi_half * ((i + 0.0) / (n - 1)))
        sign = get_sign(2, i)
        return_value[i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta))
    return return_value

def main():
    # Test cases
    test_cases = [
        {
            "A": np.array([[2, -1, -2], [-4, 6, 3], [-4, -2, 8]]),
            "b": np.array([-8, -11, -3]),
            "expected_x": np.array([-24.625, -127/12, -46/3])
        },
        {
            "A": np.array([[1, 2, 1], [2, 2, 1], [1, 1, 1]]),
            "b": np.array([2, 1, 1]),
            "expected_x": np.array([-1, 1, 1])
        },
        {
            "A": np.array([[1, 2.01, 1], [2, 2, 1], [1, 1, 1]]),
            "b": np.array([2, 1, 1]),
            "expected_x": np.array([-100/101, 100/101, 1])
        },
        {
            "A": np.array([[1, 2.01, 1], [2, 2, 1], [1, 1, 0.999]]),
            "b": np.array([2, 1, 1]),
            "expected_x": np.array([-99801/100798, 49850/50399, 500/499])
        },
        {
            "A": np.array([[1, 2.01, 1], [2, 2, 1], [1, 1, 0.999]]),
            "b": np.array([2, 1.0001, 1]),
            "expected_x": np.array([-997909201/1007980000, 9970001/10079800, 9999/9980])
        },
        {
            "A": np.array([[1, 2, 1.1], [2, 0, 1], [0, -2.02, 0]]),
            "b": np.array([0, 1, 1.00001]),
            "expected_x": np.array([11099/121200, -100001/202000, 49501/60600])
        },
        {
            "A": np.array([[1]]),
            "b": np.array([2]),
            "expected_x": np.array([2])
        }
    ]

    for test_case in test_cases:
        print_results(test_case["A"], test_case["b"], test_case["expected_x"])

    # Larger test cases
    n = 3
    print(f"n = {n}")

    params_beta_increasing = [(1, 0.0000001),(1, 0.001),(1, 0.01), (1, 0.1), (1, 1), (1, 10), (1, 100), (1, 1000), (1, 10000000)]
    params_alpha_increasing = [y[::-1] for y in params_beta_increasing]

    print("First class: alpha_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho")
    for alpha, beta in params_alpha_increasing:
        A = first_class(n, alpha, beta)
        A_inv = first_class(n, alpha, beta, inv=True)
        x_starred = generate_actual_solution(n, alpha, beta)
        f = np.matmul(A, x_starred)
        x_tilde = gauss_seidel(A, f)
        if isinstance(x_tilde, np.ndarray):   
            r = np.matmul(A, x_tilde)- f
            error = norm(x_tilde- x_starred)
            zeta = error / norm(x_starred)
            rho = norm(r) / norm(f)
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t{error:.2e}\t{zeta:.2e}\t{norm(r):.2e}\t{rho:.2e}")
        else:
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t-\t-\t-\t-")
            
    print("\nFirst class: beta_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho")
    for alpha, beta in params_beta_increasing:
        A = first_class(n, alpha, beta)
        A_inv = first_class(n, alpha, beta, inv=True)
        x_starred = generate_actual_solution(n, alpha, beta)
        f = np.matmul(A, x_starred)
        x_tilde = gauss_seidel(A, f)
        if isinstance(x_tilde, np.ndarray):   
            r = np.matmul(A, x_tilde)- f
            error = norm(x_tilde- x_starred)
            zeta = error / norm(x_starred)
            rho = norm(r) / norm(f)
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t{error:.2e}\t{zeta:.2e}\t{norm(r):.2e}\t{rho:.2e}")
        else:
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t-\t-\t-\t-")

    print("\nSecond class: alpha_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho")
    for alpha, beta in params_alpha_increasing:
        A = second_class(n, alpha, beta)
        A_inv = second_class(n, alpha, beta, inv=True)
        x_starred = generate_actual_solution(n, alpha, beta)
        f = np.matmul(A, x_starred)
        x_tilde = gauss_seidel(A, f)
        if isinstance(x_tilde, np.ndarray):   
            r = np.matmul(A, x_tilde)- f
            error = norm(x_tilde-x_starred)
            zeta = error / norm(x_starred)
            rho = norm(r) / norm(f)
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t{error:.2e}\t{zeta:.2e}\t{norm(r):.2e}\t{rho:.2e}")
        else:
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t-\t-\t-\t-")
    print("\nSecond class: beta_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho")
    for alpha, beta in params_beta_increasing:
        A = second_class(n, alpha, beta)
        A_inv = second_class(n, alpha, beta, inv=True)
        x_starred = generate_actual_solution(n, alpha, beta)
        f = np.matmul(A, x_starred)
        x_tilde = gauss_seidel(A, f)
        if isinstance(x_tilde, np.ndarray):   
            r = np.matmul(A, x_tilde)- f
            error = norm(x_tilde-x_starred)
            zeta = error / norm(x_starred)
            rho = norm(r) / norm(f)
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t{error:.2e}\t{zeta:.2e}\t{norm(r):.2e}\t{rho:.2e}")
        else:
            print(f"{alpha:.2e}\t{beta:.2e}\t{norm(A):.2e}\t{norm(A_inv):.2e}\t{norm(A) * norm(A_inv):.2e}\t-\t-\t-\t-")
if __name__ == "__main__":
    main()
