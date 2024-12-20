import numpy as np
import matplotlib.pyplot as plt
import csv

def solve_cauchy_second_order(f, y0, t0, t_end, h):
    """
    Solve the Cauchy problem for a first-order ODE using a second-order approximation scheme (Heun's method).

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

        # Второй порядок (метод Хойна)
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
    # Стартуем со второго порядка
    t_values, y_values = solve_cauchy_second_order(f, y0, t0, t_end, h)

    # Вносим поправку для третьего порядка
    y_corrected = y_values.copy()
    for i in range(1, len(t_values)):
        t = t_values[i - 1]
        y = y_values[i - 1]

        k1 = f(t, y)
        k2 = f(t + h, y + h * k1)
        residual = (h**2) * (k2 - k1) / 2

        y_corrected[i] = y_values[i] + residual

    return t_values, y_corrected


if __name__ == "__main__":

    # Определим набор тестовых задач в виде словаря
    # Каждая задача содержит:
    # f: функция правая часть ОДУ
    # analytical: аналитическое решение в виде функции
    # t0, t_end, y0: начальные условия
    # h_values: список шагов сетки для тестирования
    test_problems = {
        "problem1": {
            "f": lambda t, y: -2 * t * y,          # dy/dt = -2ty
            "analytical": lambda t: np.exp(-(t**2)), # y(t) = e^{-t^2}
            "t0": 0,
            "t_end": 2,
            "y0": 1,
            "h_values": [0.1, 0.05, 0.01, 0.001]
        },
        "problem2": {
            "f": lambda t, y: y,                   # dy/dt = y
            "analytical": lambda t: np.exp(t),
            "t0": 0,
            "t_end": 1,
            "y0": 1,
            "h_values": [0.1, 0.05, 0.01, 0.001]
        },
        "problem3": {
            "f": lambda t, y: np.sin(t),           # dy/dt = sin(t)
            "analytical": lambda t: 1 - np.cos(t), # Решение: y(t)=1 - cos(t), при y(0)=0
            "t0": 0,
            "t_end": 2*np.pi,
            "y0": 0,
            "h_values": [0.2, 0.1, 0.05, 0.01, 0.001]
        }
    }

    results = []

    for prob_name, prob_data in test_problems.items():
        f = prob_data["f"]
        analytical = prob_data["analytical"]
        t0 = prob_data["t0"]
        t_end = prob_data["t_end"]
        y0 = prob_data["y0"]

        for h in prob_data["h_values"]:
            # Решения численными методами
            t_values, y_second = solve_cauchy_second_order(f, y0, t0, t_end, h)
            _, y_third = improve_to_third_order(f, y0, t0, t_end, h)

            # Аналитическое решение
            y_analytic = analytical(t_values)

            # Оценим погрешности (максимальное отклонение)
            error_second = np.max(np.abs(y_second - y_analytic))
            error_third = np.max(np.abs(y_third - y_analytic))

            # Сохраняем результат в таблицу
            results.append([prob_name, h, error_second, error_third])

    # Сохраняем результаты в CSV файл
    filename = "results_table.csv"
    with open(filename, "w", newline="") as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(["Problem", "Step size (h)", "Max Error (2nd order)", "Max Error (3rd order)"])
        for row in results:
            writer.writerow(row)

    # Выводим таблицу на экран
    print("Results Table:")
    print("{:<10} {:<15} {:<20} {:<20}".format("Problem", "h", "Max Error (2nd)", "Max Error (3rd)"))
    for r in results:
        print("{:<10} {:<15} {:<20} {:<20}".format(r[0], r[1], r[2], r[3]))

    # При желании можно также построить графики для одной из проблем
    # Ниже пример для Problem1, h=0.1:
    problem_to_plot = "problem1"
    h_plot = 0.2
    p_data = test_problems[problem_to_plot]
    t_values, y_second = solve_cauchy_second_order(p_data["f"], p_data["y0"], p_data["t0"], p_data["t_end"], h_plot)
    _, y_third = improve_to_third_order(p_data["f"], p_data["y0"], p_data["t0"], p_data["t_end"], h_plot)
    y_analytic = p_data["analytical"](t_values)

    plt.figure(figsize=(10, 6))
    plt.plot(t_values, y_analytic, label="Analytical", linestyle="--")
    plt.plot(t_values, y_second, label="Second-order", marker="o")
    plt.plot(t_values, y_third, label="Third-order", marker="s")
    plt.xlabel("t")
    plt.ylabel("y")
    plt.title(f"Solution of {problem_to_plot} with h={h_plot}")
    plt.legend()
    plt.grid()
    plt.show()
