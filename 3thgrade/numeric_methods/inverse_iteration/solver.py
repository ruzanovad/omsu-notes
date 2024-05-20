import warnings
import numpy as np

warnings.filterwarnings("ignore")

EPS = 1e-5
NUMBER_OF_ITERATIONS = 10000
ROUND_CONST = 3

num_of_iter = 0

def check_symmetric(a, rtol=1e-05, atol=1e-08):
    return np.allclose(a, a.T, rtol=rtol, atol=atol)

def reverse_iteration(A, epsilon=1e-8, max_iter=NUMBER_OF_ITERATIONS):
    n = A.shape[0]
    x = np.ones(n) # начальное значение
    global num_of_iter
    x /= norm(x)
    _lambda = 0
    for _ in range(max_iter):
        y = np.linalg.solve(A, x)   # TODO LU factor
        # y = gauss_seidel(A, x)  
        
        # converge?
        new_lambda = np.dot(A @ y, y) # scalar product
        
        y /= norm(y)
        
        if abs(new_lambda-_lambda) < epsilon:
            num_of_iter = _+1
            return y, 1.0 / new_lambda, True
        _lambda = new_lambda
        x = y

    print("Не достигнута сходимость за {} итераций".format(max_iter))
    return x, 1.0 / _lambda, False
    
    

def gauss_seidel(A, b, epsilon=1e-7):
    n = len(A)
    x = np.zeros(n) 
    global num_of_iter
    num_of_iter = 0
    converge = False
    # eps_conv = 1e-7
    
    # global epsilon
    
    cnt = 0
    while not converge and cnt < NUMBER_OF_ITERATIONS:
        x_new = np.copy(x)
        for i in range(n):
            s1 = sum(A[i][j] * x_new[j] for j in range(i)) 
            # ignore i coordinate 
            s2 = sum(A[i][j] * x[j] for j in range(i + 1, n))
            x_new[i] = (b[i] - s1 - s2) / A[i][i]
        # converge = norm(x_new - x) <= eps_conv
        converge = norm(x_new - x) <= epsilon
        x = x_new
        cnt+=1
    num_of_iter = cnt
    if converge:
        return x
    else:
        # print("Method does not converge")
        return x
    

def get_sign(sign_rule, i):
    if sign_rule % 2:
        return 1 if i % 2 == 0 else -1
    else:
        return -1 if i % 2 == 0 else 1

# alpha beta 
def first_class(n, alpha, beta, inv=False, sign_rule=1):
    Q = np.zeros((n, n))
    J = np.zeros((n, n))
    # J_inv = np.zeros((n, n))
    return_value = np.zeros((n, n))
    # inv of J is got correctly
    
    min_eigenvalue = 1. /alpha  if  inv else alpha
    for i in range(n):
        gamma = np.sqrt((i + 0.) / (n - 1))

        if inv:
        
            J[i, i] = 1. / (((1 - gamma) * alpha + gamma * beta))
        else:
            # print(((1 - gamma) * alpha + gamma * beta))
            J[i, i] = (((1 - gamma) * alpha + gamma * beta))
 
        if i != n-1:
            for j in range(i+1):
                Q[j, i] = 1./np.sqrt((i+1)*(i+2))
            Q[i+1, i] = -np.sqrt(float(i+1)/(i+2))
        else: 
            for j in range(i+1):
                Q[j, i] = 1./np.sqrt(n)
        min_eigenvalue = min(min_eigenvalue, J[i, i])
    # print(Q.T @ J_inv @ Q)    
    # print(Q @ J @ Q.T)
    # A^-1 @ A = E, но не наоборот(
    return_value = Q @ J @ Q.T
        
    # print((Q @ J @ Q.T) @ (Q @ J_inv @ Q.T))        
    return return_value, min_eigenvalue


def second_class(n, alpha, beta, inv=False, sign_rule=1):
    Q = np.zeros((n, n))
    J = np.zeros((n, n))
    return_value = np.zeros((n, n))

    gamma = 0

    if inv:
        J[0, 1] = -(1. / (((1 - gamma) * alpha + gamma * beta)))**2 # Set the (0, 1) element
    else:
        J[0, 1] = 1  # Set the (0, 1) element to 1
    min_eigenvalue = 1. /alpha  if  inv else alpha
    for i in range(0, n):

        if inv:
            J[i, i] = 1. / (((1 - gamma) * alpha + gamma * beta))
        else:
            J[i, i] = (((1 - gamma) * alpha + gamma * beta))
 
        if i != n-1:
            for j in range(i+1):
                Q[j, i] = 1./np.sqrt((i+1)*(i+2))
            Q[i+1, i] = -np.sqrt(float(i+1)/(i+2))
        else: 
            for j in range(i+1):
                Q[j, i] = 1./np.sqrt(n)
        gamma = np.sqrt((i + 0.) / (n - 1))
        min_eigenvalue = min(min_eigenvalue, J[i, i])
    
    return_value = Q @ J @ Q.T
    
    # print(return_value)        
    return return_value, min_eigenvalue


def norm(x):
    return np.linalg.norm(x)

    

def main():
    
    n = 5
    print(f"n = {n}")

    params_beta_increasing = [(1, 0.0000001),(1, 0.001),(1, 0.01), (1, 0.1), (1, 1), (1, 10), (1, 100), (1, 1000), (1, 10000000)]
    params_beta_increasing += [(42, 0.0000001),(42, 0.001),(42, 0.01), (42, 0.1), (42, 1), (42, 10), (42, 100), (42, 1000), (42, 10000000)]
    params_alpha_increasing = [y[::-1] for y in params_beta_increasing]

    print("First class: alpha_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho\tnum_of_iter")
    for alpha, beta in params_alpha_increasing:
        
        A, eigenvalue_starred = first_class(n, alpha, beta)
        assert check_symmetric(A)
        A_inv, __ = first_class(n, alpha, beta, inv=True)
        eigenvector_tilde, eigenvalue_tilde, result = reverse_iteration(A)
         
        # x_starred = generate_actual_solution(n, alpha, beta)
        f = A @ eigenvector_tilde
        # x_tilde = gauss_seidel(A, f)
        if result:   
            r = f - eigenvalue_tilde * eigenvector_tilde
            error = norm(eigenvalue_tilde- eigenvalue_starred)
            zeta = error / norm(eigenvalue_tilde)
            rho = norm(r) / norm(f)
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t{error:.{ROUND_CONST}e}\t{zeta:.{ROUND_CONST}e}\t{norm(r):.{ROUND_CONST}e}\t{rho:.{ROUND_CONST}e}\t{num_of_iter}")
        else:
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t-\t-\t-\t-")
            
    print("\nFirst class: beta_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho\tnum_of_iter")
    for alpha, beta in params_beta_increasing:
        A, eigenvalue_starred = first_class(n, alpha, beta)
        assert check_symmetric(A)
        A_inv, __ = first_class(n, alpha, beta, inv=True)
        eigenvector_tilde, eigenvalue_tilde, result = reverse_iteration(A)
         
        # x_starred = generate_actual_solution(n, alpha, beta)
        f = A @ eigenvector_tilde
        # x_tilde = gauss_seidel(A, f)
        if result:   
            r = f - eigenvalue_tilde * eigenvector_tilde
            error = norm(eigenvalue_tilde- eigenvalue_starred)
            zeta = error / norm(eigenvalue_tilde)
            rho = norm(r) / norm(f)
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t{error:.{ROUND_CONST}e}\t{zeta:.{ROUND_CONST}e}\t{norm(r):.{ROUND_CONST}e}\t{rho:.{ROUND_CONST}e}\t{num_of_iter}")
        else:
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t-\t-\t-\t-")

    print("\nSecond class: alpha_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho\tnum_of_iter")
    for alpha, beta in params_alpha_increasing:
        A, eigenvalue_starred = second_class(n, alpha, beta)
        A_inv, __ = second_class(n, alpha, beta, inv=True)
        eigenvector_tilde, eigenvalue_tilde, result = reverse_iteration(A)
         
        # x_starred = generate_actual_solution(n, alpha, beta)
        f = A @ eigenvector_tilde
        # x_tilde = gauss_seidel(A, f)
        if result:   
            r = f - eigenvalue_tilde * eigenvector_tilde
            error = norm(eigenvalue_tilde- eigenvalue_starred)
            zeta = error / norm(eigenvalue_tilde)
            rho = norm(r) / norm(f)
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t{error:.{ROUND_CONST}e}\t{zeta:.{ROUND_CONST}e}\t{norm(r):.{ROUND_CONST}e}\t{rho:.{ROUND_CONST}e}\t{num_of_iter}")
        else:
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t-\t-\t-\t-")
    print("\nSecond class: beta_increasing")
    print("alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho\tnum_of_iter")
    for alpha, beta in params_beta_increasing:
        A, eigenvalue_starred = second_class(n, alpha, beta)
        A_inv, __ = second_class(n, alpha, beta, inv=True)
        eigenvector_tilde, eigenvalue_tilde, result = reverse_iteration(A)
         
        # x_starred = generate_actual_solution(n, alpha, beta)
        f = A @ eigenvector_tilde
        # x_tilde = gauss_seidel(A, f)
        if result:   
            r = f - eigenvalue_tilde * eigenvector_tilde
            error = norm(eigenvalue_tilde- eigenvalue_starred)
            zeta = error / norm(eigenvalue_tilde)
            rho = norm(r) / norm(f)
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t{error:.{ROUND_CONST}e}\t{zeta:.{ROUND_CONST}e}\t{norm(r):.{ROUND_CONST}e}\t{rho:.{ROUND_CONST}e}\t{num_of_iter}")
        else:
            print(f"{alpha:.{ROUND_CONST}e}\t{beta:.{ROUND_CONST}e}\t{norm(A):.{ROUND_CONST}e}\t{norm(A_inv):.{ROUND_CONST}e}\t{norm(A) * norm(A_inv):.{ROUND_CONST}e}\t-\t-\t-\t-")

        
        
if __name__ == "__main__":
    main()
