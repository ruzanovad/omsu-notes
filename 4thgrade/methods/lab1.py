import numpy as np

def preprocess(x : np.array, y : np.array):
    assert len(x) == len(y)
    z = np.stack((x, y), axis=1)
    
    return z[z[:,0].argsort()]

def get_parameters(z, b : list):
    b.sort()
    b = [0.] + b + [1.]
    assert len(b) >= 2

    x = z[:, 0]
    y = z[:, 1]
    A = np.zeros((len(x), len(b)))
    for i in range(0, len(x)):
        A[i, 0] = 1.
        A[i, 1] = x[i] - 0
    for i in range(0, len(x)):
        for j in range(2, len(b)):
            A[i, j] = (x[i] > b[j-1])*(x[i]-b[j-1])

    beta = (np.linalg.inv(A.T @ A) @ A.T) @ y

    return beta

def fun(x, b, beta):
    assert x >= 0.0 and x <= 1.
    def find_interval(arr, x):
        low, high = 0, len(arr) - 1
        
        # Perform binary search
        while low <= high:
            mid = (low + high) // 2
            
            if arr[mid] <= x <= arr[mid + 1]:
                return mid  # Found the interval where b_i < x < b_i+1
            elif x < arr[mid]:
                high = mid - 1
            else:
                low = mid + 1
        
        return None  # This should not happen if the input list is valid and sorted
    b_i = find_interval(b, x)
    y = beta[0]
    for i in range(1, b_i+2):
        y+= beta[i]*(x-b[i-1])
    return y

