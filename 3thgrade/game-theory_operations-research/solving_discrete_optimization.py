import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import linprog, Bounds, LinearConstraint

# linear_constraint = LinearConstraint(
# ,
# (-1)*[10, 15, 20]

bounds = Bounds([0 for _ in range(16)], [None for _ in range(16)])

def f(x):
    '''Objective function'''
    return sum([x[i] for i in range(16)])

def df(x):
    '''Derivative function'''
    return [x[i] for i in range(16)]

start = np.array([0 for i in range(16)])


result = linprog(
    c=[1 for _ in range(16)], method='highs',
    A_ub=[list(map(lambda x: -x, [4, 3, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0])),
          list(map(lambda x: -x, [0, 2, 4, 2, 1,
               6, 4, 3, 1, 0, 5, 8, 6, 3, 2, 0])),
          list(map(lambda x: -x, [0, 0, 0, 1, 2, 0, 1, 2, 3, 4, 2, 0, 1, 3, 4, 5]))],
    b_ub=[-10, -15, -20],
    bounds=list(zip([0 for _ in range(16)], [None for _ in range(16)])), integrality=1)

print(result)
print(result.x)