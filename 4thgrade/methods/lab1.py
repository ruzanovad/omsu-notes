import numpy as np
import matplotlib.pyplot as plt
import pandas as pd


def generate_b(x, n, m):
    b = [0]
    for i in range(1, m):
        b.append(x[n * i // m])
    b.append(1)
    return b


def preprocess(x: np.array, y: np.array):
    assert len(x) == len(y)
    # Сортировка точек по возрастанию значений x
    sorted_indices = np.argsort(x)
    x_sorted = x[sorted_indices]
    y_sorted = y[sorted_indices]
    return x_sorted, y_sorted


def get_parameters(x, y, b: list):
    assert len(b) >= 2
    A = np.zeros((len(x), len(b)))
    for i in range(0, len(x)):
        A[i, 0] = 1.0
        A[i, 1] = x[i] - 0
    for i in range(0, len(x)):
        for j in range(1, len(b)):
            A[i, j] = (x[i] > b[j - 1]) * (x[i] - b[j - 1])
    beta = (np.linalg.inv(A.T @ A) @ A.T) @ y

    return beta


def fun(x, b, beta):
    assert x >= 0.0 and x <= 1.0

    def find_interval(arr, x):
        low, high = 0, len(arr) - 1

        while low <= high:
            mid = (low + high) // 2

            if arr[mid] <= x <= arr[mid + 1]:
                return mid  # Found the interval where b_i <= x <= b_i+1
            elif x < arr[mid]:
                high = mid - 1
            else:
                low = mid + 1

        return None  # This should not happen if the input list is valid and sorted

    b_i = find_interval(b, x)
    y = beta[0]
    for i in range(1, b_i + 2):
        y += beta[i] * (x - b[i - 1])
    return y


def calculate_error(x, y, b, beta):
    z = np.abs(y[0] - fun(x[0], b, beta))
    for i in range(1, len(y)):
        z = max(z, np.abs(y[i] - fun(x[i], b, beta)))
    return z


def plot_results(x, y, x_hat, fun, b, beta):
    y_hat = np.zeros(len(x_hat))
    for i in range(len(x_hat)):
        y_hat[i] = fun(x_hat[i], b, beta)

    plt.figure()
    plt.plot(x, y, "o")
    plt.plot(x_hat, y_hat, "-")
    plt.grid()
    plt.xlabel("x")
    plt.ylabel("y")
    plt.show()


def solve(x, y, n, m):
    x, y = preprocess(x, y)
    b = generate_b(x, n, m)

    beta = get_parameters(x, y, b)
    return fun(b=b, beta=beta)


def create_table(func_str, x, y, n, m_values):
    data = []

    for m in m_values:
        x, y = preprocess(x, y)
        b = generate_b(x, n, m)
        beta = get_parameters(x, y, b)
        error = calculate_error(x, y, b, beta)
        data.append([func_str, len(x), m, error])

    # Создание DataFrame для удобного вывода
    df = pd.DataFrame(
        data, columns=["Function", "Number of points", "Number of pieces (M)", "Error"]
    )
    return df


def create_table_improved(func_strs, steps, m_values):
    data = []

    for func_str_ in func_strs:
        func_str, fun = func_str_
        for step in steps:
            x = np.arange(0, 1, step)
            y = fun(x)
            n = len(x)
            for m in m_values:
                if n < m * 2:
                    continue
                x, y = preprocess(x, y)
                b = generate_b(x, n, m)
                beta = get_parameters(x, y, b)
                error = calculate_error(x, y, b, beta)
                data.append([func_str, len(x), m, error])

    # Создание DataFrame для удобного вывода
    df = pd.DataFrame(
        data, columns=["Function", "Number of points", "Number of pieces (M)", "Error"]
    )
    return df
