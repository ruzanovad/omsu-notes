import numpy as np
from scipy.linalg import solve
from scipy.integrate import quad
import matplotlib.pyplot as plt
import pandas as pd
from functools import lru_cache



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


def fredholm_solver(kernel, g, exact_solution, a=0, b=1, n_intervals=100, m=3):
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

    #треугольная функция (треугольный импульс)

    # Дискретизация по x
    x_nodes = np.linspace(a, b, n_intervals)
    h = 1/n_intervals
    def V(x_i, x):
        if abs(x-x_i) <= h:
            return 1 - abs(x-x_i)/h
        else:
            return 0


r

    # Решение системы линейных уравнений A * beta = B с использованием наименьших квадратов
    beta = np.linalg.lstsq(A_matrix, B_vec, rcond=None)[0]

    # Аппроксимация f(t) на более плотной сетке для визуализации
    t_fine = np.linspace(a, b, 1000)
    f_approx_fine = fun(t_fine, b_breaks, beta)

    # Точное решение на плотной сетке
    f_exact_fine = exact_solution(t_fine)

    # Вычисление средней квадратичной ошибки
    error = np.max(np.abs((f_approx_fine - f_exact_fine)))
    print(f"Ошибка: {error:.6f}")

    # Визуализация результатов
    plot_results(t_fine, f_exact_fine, t_fine, f_approx_fine)

    return f_approx_fine, error, beta, b_breaks


# Пример использования функции
if __name__ == "__main__":
    # Определение точного решения
    def exact_solution(t):
        return -np.sin(2*t)

    # @lru_cache
    # Определение ядра интегрального уравнения
    def kernel(x, t):
        return np.cos(x - t)  # Без шума для детерминированных результатов

    @lru_cache
    # Определение правой части уравнения g(x) на основе точного решения
    def g(x):
        # Используем квадратуру Гаусса 3-го порядка для численного интегрирования
        integrand = lambda t: kernel(x, t) * exact_solution(t)
        return gauss_quadrature_3rd_order(integrand, 0, 1)

    # Параметры решения
    a, b = 0, 1  # Границы интегрирования
    n_intervals = 150  # Количество узлов для дискретизации x
    m = 5  # Количество сегментов для аппроксимации f(t)

    # Решение интегрального уравнения
    f_approx, error, beta, b_breaks = fredholm_solver(
        kernel, g, exact_solution, a, b, n_intervals, m
    )

    # Аппроксимация f(t) в узлах разбиения
    f_approx_nodes = fun(np.array(b_breaks), b_breaks, beta)
    print(f"Приближённые значения f(t) в узлах разбиения: {f_approx_nodes}")
