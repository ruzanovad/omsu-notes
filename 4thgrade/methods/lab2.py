import numpy as np
from regex import T
from scipy.linalg import solve
from scipy.integrate import quad
import matplotlib.pyplot as plt
import pandas as pd
from functools import lru_cache

QUADRATURE = 2


def gauss_quadrature_3rd_order(f, a, b):
    """
    Вычисляет интеграл функции f на интервале [a, b] с использованием квадратуры Гаусса третьего порядка,
    разделяя интервал [a, b] на n_intervals + 1 подинтервалов.
    """
    n_intervals = 10
    # Узлы и веса для интервала [-1, 1]
    nodes = np.array([-np.sqrt(3 / 5), 0, np.sqrt(3 / 5)])
    weights = np.array([5 / 9, 8 / 9, 5 / 9])

    # Шаг между узлами
    h = (b - a) / n_intervals
    integral = 0.0

    # Проходим по каждому подинтервалу
    for i in range(n_intervals):
        sub_a = a + i * h
        sub_b = sub_a + h

        # Масштабирование узлов и весов для текущего подинтервала
        transformed_nodes = 0.5 * (sub_b - sub_a) * nodes + 0.5 * (sub_a + sub_b)
        transformed_weights = 0.5 * (sub_b - sub_a) * weights

        # Вычисление интеграла на текущем подинтервале
        integral += np.sum(transformed_weights * f(transformed_nodes))
    
    return integral


def gauss_quadrature_2nd_order(f, a, b):
    """
    Вычисляет интеграл функции f на интервале [a, b] с использованием квадратуры Гаусса второго порядка,
    разделяя интервал [a, b] на n_intervals + 1 подинтервалов.
    """
    n_intervals = 10
    # Узлы и веса для интервала [-1, 1]
    nodes = np.array([-np.sqrt(1 / 3), np.sqrt(1 / 3)])
    weights = np.array([1, 1])

    # Шаг между узлами
    h = (b - a) / n_intervals
    integral = 0.0

    # Проходим по каждому подинтервалу
    for i in range(n_intervals):
        sub_a = a + i * h
        sub_b = sub_a + h

        # Масштабирование узлов и весов для текущего подинтервала
        transformed_nodes = 0.5 * (sub_b - sub_a) * nodes + 0.5 * (sub_a + sub_b)
        transformed_weights = 0.5 * (sub_b - sub_a) * weights

        # Вычисление интеграла на текущем подинтервале
        integral += np.sum(transformed_weights * f(transformed_nodes))
    
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
    A_matrix += 0.0001 * np.eye(n_intervals)
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


def create_error_dataframe(kernel, exact_solution, g, a=0, b=1, intervals_list=None, function_label="Unknown"):
    """
    Создаёт DataFrame, содержащий ошибки аппроксимации для разных n_intervals.
    
    Параметры:
        kernel (function): Ядро K(x, t).
        exact_solution (function): Точное решение f(t).
        g (function): Правая часть интегрального уравнения g(x).
        a (float): Левая граница интегрирования.
        b (float): Правая граница интегрирования.
        intervals_list (list): Список значений n_intervals для экспериментов.
        function_label (str): Строковое описание типа функции.
    
    Возвращает:
        pd.DataFrame: Таблица с колонками Function, n_intervals, Error.
    """
    if intervals_list is None:
        intervals_list = [10, 20, 50, 100]  # Default values if not provided
    
    results = []
    
    for n_intervals in intervals_list:
        # Решение уравнения Фредгольма для текущего n_intervals
        _, error, _, _ = fredholm_solver(
            kernel, g, exact_solution, a=a, b=b, n_intervals=n_intervals
        )
        # Запись результатов
        results.append({"Function": function_label, "n_intervals": n_intervals, "Error": error})
    
    # Преобразование в DataFrame
    return pd.DataFrame(results)

# Пример использования
if __name__ == "__main__":
    # Определение точного решения
    def exact_solution(t):
        return t  # Простое линейное решение

    # Определение ядра интегрального уравнения
    def kernel(x, t):
        return np.cos(x - t)  # Пример ядра

    # Определение правой части уравнения g(x) на основе точного решения
    def g(x):
        def integrand(t, x_i):
            return kernel(x_i, t) * exact_solution(t)
        return np.array([integral(lambda t: integrand(t, x_i), 0, 1) for x_i in x])

    # Параметры
    a, b = 0, 1  # Границы интегрирования
    intervals_list = [10, 20, 50, 100, 200, 500, 1000]  # Различные значения n_intervals
    # function_label = "Linear Function (t)"

    # # Создание DataFrame с ошибками
    # error_df = create_error_dataframe(kernel, exact_solution, g, a, b, intervals_list, function_label)

    # # Вывод таблицы
    # print(error_df)

    # # Сохранение в файл (опционально)
    # error_df.to_csv("error_results.csv", index=False)
    # ---
    def exact_solution(t):
        return np.exp(2*t)  # Простое линейное решение
    function_label = "exp (t)"

    # Создание DataFrame с ошибками
    error_df = create_error_dataframe(kernel, exact_solution, g, a, b, intervals_list, function_label)

    # Вывод таблицы
    print(error_df)

    # Сохранение в файл (опционально)
    error_df.to_csv("error_results_1.csv", index=False)

    # sns.lineplot(data=error_df, x="n_intervals", y="Error", marker="o")
    # plt.title("Error vs. n_intervals")
    # plt.xlabel("Number of Intervals (n_intervals)")
    # plt.ylabel("Error")
    # plt.grid(True)
    # plt.show()

