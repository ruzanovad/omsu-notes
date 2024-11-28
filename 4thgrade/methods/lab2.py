import numpy as np
from regex import T
from scipy.linalg import solve
from scipy.integrate import quad
import matplotlib.pyplot as plt
import pandas as pd
from functools import lru_cache

QUADRATURE = 3


def gauss_quadrature_3rd_order(f, a, b):
    """
    Вычисляет интеграл функции f на интервале [a, b] с использованием квадратуры Гаусса третьего порядка.
    """
    # Узлы и веса для интервала [-1, 1]
    nodes = np.array([-np.sqrt(3 / 5), 0, np.sqrt(3 / 5)])
    weights = np.array([5 / 9, 8 / 9, 5 / 9])

    # Масштабирование узлов и весов для интервала [a, b]
    transformed_nodes = 0.5 * (b - a) * nodes + 0.5 * (a + b)
    transformed_weights = 0.5 * (b - a) * weights

    # Вычисление интеграла
    integral = np.sum(transformed_weights * f(transformed_nodes))
    return integral


def gauss_quadrature_2nd_order(f, a, b):
    """
    Вычисляет интеграл функции f на интервале [a, b] с использованием квадратуры Гаусса второго порядка
    """
    # Узлы и веса для интервала [-1, 1]
    nodes = np.array([-np.sqrt(1 / 3), np.sqrt(1 / 3)])
    weights = np.array([1, 1])

    # Масштабирование узлов и весов для интервала [a, b]
    transformed_nodes = 0.5 * (b - a) * nodes + 0.5 * (a + b)
    transformed_weights = 0.5 * (b - a) * weights

    # Вычисление интеграла
    integral = np.sum(transformed_weights * f(transformed_nodes))
    return integral


if QUADRATURE == 3:
    integral = gauss_quadrature_3rd_order
else:
    integral = gauss_quadrature_2nd_order


def generate_b(x, n, m):
    """
    Генерирует точки разбиения для кусочно-линейной аппроксимации.

    Параметры:
        x (np.array): Массив узлов.
        n (int): Общее количество узлов.
        m (int): Количество сегментов (кусочков).

    Возвращает:
        b (list): Список точек разбиения.
    """
    b = [x[0]]
    for i in range(1, m):
        b.append(x[n * i // m])
    b.append(x[-1])
    return b


def preprocess(x: np.array, y: np.array):
    """
    Сортирует точки по возрастанию x.
    """
    assert len(x) == len(y), "Массивы x и y должны иметь одинаковую длину."
    sorted_indices = np.argsort(x)
    x_sorted = x[sorted_indices]
    y_sorted = y[sorted_indices]
    return x_sorted, y_sorted


def get_parameters(x, y, b: list):
    """
    Вычисляет коэффициенты кусочно-линейной аппроксимации.

    Параметры:
        x (np.array): Отсортированные значения x.
        y (np.array): Соответствующие значения y.
        b (list): Точки разбиения.

    Возвращает:
        beta (np.ndarray): Коэффициенты аппроксимации.
    """
    m = len(b) - 1  # Количество сегментов
    A = np.zeros((len(x), m + 1))  # +1 для постоянного члена

    # Постоянный член
    A[:, 0] = 1.0

    # Линейные члены для каждого сегмента
    for j in range(1, m + 1):
        A[:, j] = np.maximum(x - b[j - 1], 0)

    # Решение задачи наименьших квадратов
    beta, residuals, rank, s = np.linalg.lstsq(A, y, rcond=None)
    return beta


def fun(x, b, beta):
    """
    Кусочно-линейная аппроксимация функции f(t).

    Параметры:
        x (np.array): Точки, в которых вычисляется функция.
        b (list): Точки разбиения.
        beta (np.ndarray): Коэффициенты аппроксимации.

    Возвращает:
        y (np.array): Значения аппроксимированной функции.
    """
    m = len(b) - 1
    y = np.full_like(x, beta[0], dtype=np.float64)
    for j in range(1, m + 1):
        y += beta[j] * np.maximum(x - b[j - 1], 0)
    return y


def calculate_error(x, y, b, beta):
    """
    Вычисляет среднеквадратичную ошибку между точными и аппроксимированными значениями.
    """
    y_hat = fun(x, b, beta)
    error = np.max(np.abs((y_hat - y)))
    return error


def plot_results(x, y, x_hat, y_hat, func_label="Приближённое решение"):
    """
    Визуализирует точное и аппроксимированное решения.
    """
    plt.figure(figsize=(10, 6))
    plt.plot(x, y, "o", label="Точное решение")
    plt.plot(x_hat, y_hat, "-", label=func_label)
    plt.grid(True)
    plt.xlabel("t")
    plt.ylabel("f(t)")
    plt.legend()
    plt.title("Сравнение точного и аппроксимированного решений")
    plt.show()


def fredholm_solver(kernel, g, exact_solution, a=0, b=1, n_intervals=100):
    """
    Решает интегральное уравнение Фредгольма первого рода методом кусочно-линейной аппроксимации
    с использованием квадратурной формулы Гаусса 3-го порядка.

    Параметры:
        kernel (function): Функция ядра K(x, t).
        g (function): Функция правая часть уравнения g(x).
        exact_solution (function): Точная функция решения f(t).
        a (float): Левая граница интегрирования.
        b (float): Правая граница интегрирования.
        n_intervals (int): Количество узлов для дискретизации x.
        m (int): Количество сегментов (кусочков) для аппроксимации функции f(t).

    Возвращает:
        f_approx_fine (np.ndarray): Приближённое решение f(t) на плотной сетке.
        error (float): Среднеквадратичная ошибка по сравнению с точным решением.
        beta (np.ndarray): Коэффициенты аппроксимации.
        b_breaks (list): Точки разбиения.
    """

    # треугольная функция (треугольный импульс)

    # Дискретизация по x
    x_nodes = np.linspace(a, b, n_intervals)
    h = 1 / n_intervals

    def V(x_i, x):
        diff = np.abs(x - x_i)
        return np.where(diff <= h, 1 - diff / h, 0)

    A_matrix = np.zeros((n_intervals, n_intervals), dtype=float)
    B_vec = g(x_nodes)

    for i in range(n_intervals):
        for j in range(n_intervals):
            A_matrix[i, j] = integral(
                lambda s: kernel(x_nodes[i], s) * V(x_nodes[j], s), 0, 1
            )

    # print(A_matrix)
    # print(B_vec)
    A_matrix += 0.001 * np.eye(n_intervals)
    # Решение системы линейных уравнений A * beta = B
    beta = np.linalg.solve(A_matrix, B_vec)

    # Аппроксимация f(t) на более плотной сетке для визуализации
    t_fine = np.linspace(a, b, 1000)
    f_approx_fine = np.zeros_like(t_fine)
    for j in range(n_intervals):
        f_approx_fine += beta[j] * V(x_nodes[j], t_fine)

    # Точное решение на плотной сетке
    f_exact_fine = exact_solution(t_fine)

    # Вычисление максимальной абсолютной ошибки
    error = np.max(np.abs(f_approx_fine - f_exact_fine))
    print(f"Максимальная абсолютная ошибка: {error:.6f}")

    # Визуализация результатов
    plot_results(
        t_fine,
        f_exact_fine,
        t_fine,
        f_approx_fine,
        func_label="Аппроксимация (треугольники)",
    )

    return f_approx_fine, error, beta, x_nodes


# Пример использования функции
if __name__ == "__main__":

    # Определение точного решения
    def exact_solution(t):
        return t+0.5

    # @lru_cache
    # Определение ядра интегрального уравнения
    def kernel(x, t):
        # Ensure x and t are broadcasted to compatible shapes
        return np.cos(np.subtract.outer(x, t))

    # @lru_cache
    # Определение правой части уравнения g(x) на основе точного решения
    def g(x):
        # Define the integrand
        def integrand(t):
            # Kernel returns a 2D array; we need the corresponding exact_solution for broadcasting
            return kernel(x, t) * exact_solution(t)

        # Compute g(x) using Gauss quadrature for each x
        g_values = np.array(
            [integral(lambda t: integrand(t)[i], 0, 1) for i in range(len(x))]
        )
        return g_values

    # Параметры решения
    a, b = 0, 1  # Границы интегрирования
    n_intervals = 10  # Количество узлов для дискретизации x

    # Решение интегрального уравнения
    f_approx, error, beta, b_breaks = fredholm_solver(
        kernel, g, exact_solution, a, b, n_intervals
    )

    # Аппроксимация f(t) в узлах разбиения
    f_approx_nodes = fun(np.array(b_breaks), b_breaks, beta)
    print(f"Приближённые значения f(t) в узлах разбиения: {f_approx_nodes}")
