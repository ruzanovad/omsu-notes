# # Import required libraries
# import numpy as np
# from scipy.optimize import minimize

# # Define the objective function to be maximized
# def objective(x):
#     return -(-4*x[0] + 8*x[1] - x[0]**2 - (3/2)*(x[1]**2) + 2 * x[0]*x[1])

# # Define the constraints
# def constraint1(x):
#     return -(3*x[0] + 5 * x[1] - 15)

# def constraint2(x):
#     return -(x[0] - x[1] - 1)

# # Define the bounds
# bounds = [(0, None), (0, None)]

# # Define the initial guess
# x0 = np.array([0, 0])

# # Define the constraints dictionary
# con1 = {'type': 'ineq', 'fun': constraint1}
# con2 = {'type': 'ineq', 'fun': constraint2}
# cons = [con1, con2]

# # Use the SLSQP solver to solve the problem
# sol = minimize(objective, x0, method='SLSQP', bounds=bounds, constraints=cons)

# # Print the solution
# print(sol)

# Import required libraries
# import numpy as np
# from scipy.optimize import minimize

# # Define the objective function to be maximized
# def objective(x):
#     return -(8*x[0] + 12*x[1] - x[0]**2 - (3/2)*x[1]**2)

# # Define the constraints
# def constraint1(x):
#     return -(-2*x[0] - x[1] + 4)

# def constraint2(x):
#     return -(2*x[0] + 5*x[1] - 10)

# # Define the bounds
# bounds = [(0, None), (0, None)]

# # Define the initial guess
# x0 = np.array([0, 0])

# # Define the constraints dictionary
# con1 = {'type': 'ineq', 'fun': constraint1}
# con2 = {'type': 'ineq', 'fun': constraint2}
# cons = [con1, con2]

# # Use the SLSQP solver to solve the problem
# sol = minimize(objective, x0, method='SLSQP', bounds=bounds, constraints=cons)

# # Print the solution
# print(sol)