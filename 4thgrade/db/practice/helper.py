from copy import deepcopy
from re import L
import pandas as pd
from typing import List, Set, Tuple


def has_lossless_join(
    F: List[Tuple[Set[str], str]], decomposition: List[Set[str]]
) -> bool:
    """
    Проверяет, обладает ли декомпозиция свойством соединения без потерь.

    :param F: Множество функциональных зависимостей в виде списка кортежей (X, Aj),
              где X — множество атрибутов, Aj — атрибут.
    :param decomposition: Список подмножеств атрибутов, представляющих декомпозицию.
    :return: True, если декомпозиция обладает свойством соединения без потерь, иначе False.
    """
    # Собираем все атрибуты
    attributes = set()
    for Ri in decomposition:
        attributes.update(Ri)
    sorted_attributes = sorted(list(attributes))

    # Создаем таблицу с "+" для атрибутов, присутствующих в каждом подмножестве
    sorted_decomposition = sorted([tuple(sorted(subset)) for subset in decomposition])

    # Создаём таблицу с "+" для атрибутов, присутствующих в каждом подмножестве
    table = pd.DataFrame(
        [
            ["+" if attr in subset else "" for attr in sorted_attributes]
            for subset in decomposition
        ],
        columns=sorted_attributes,
        index=sorted_decomposition,  # индексируем строки отсортированными кортежами
    )
    print(table)

    # Функция для проверки, содержит ли строка все атрибуты
    def is_full(row):
        return all(cell == "+" for cell in row)

    while True:
        changes = False
        for X, Aj in F:  # Для каждой ФЗ
            print("ФЗ", X, Aj)
            # Выбираем строки, содержащие все атрибуты из X
            mask = (table[list(X)] == "+").all(axis=1)  # по строкам
            selected_rows = table[mask]
            print(selected_rows)

            # Если ни одна строка не удовлетворяет, пропускаем
            if selected_rows.empty:
                continue

            # Добавляем Aj в строки, которые удовлетворяют X
            for idx in selected_rows.index:
                if table.at[idx, Aj] != "+":
                    print("+ на", idx, Aj)
                    
                    table.at[idx, Aj] = "+"  # Обновляем значение в таблице
                    # print(table)
                    changes = True

        # Проверяем, есть ли строка, полностью заполненная '+'
        if table.apply(is_full, axis=1).any():
            return True  # Свойство соединения без потерь выполняется
        print(table)
        if not changes:
            break  # Нет изменений, завершаем

    return False  # Свойство соединения без потерь отсутствует

def convert_min_cover_to_F(min_cover):
    """
    Преобразует минимальное покрытие в формат F = [({"A"}, "B"), ({"B"}, "C"), ...]
    :param min_cover: Список кортежей вида (set(left), set(right))
    :return: Список кортежей вида [({left}, "right")]
    """
    F = []
    mapping = convert_fds_list_to_map_single(min_cover)
    for left, right in mapping.items():
        for r in right: 
            F.append((left, r))
    return F

def decompose_fd(fds):
    """
    Разбивает зависимости с множеством атрибутов справа
    на несколько зависимостей с одним атрибутом справа
    """
    decomposed_fds = []
    for left, right in fds:
        for r in right:
            decomposed_fds.append((left, {r}))
    return decomposed_fds


def find_closure(attributes, fds):
    """
    Находит замыкание атрибутов по множеству зависимостей
    """
    closure = set(attributes)  # по множеству каких атрибутов строим
    while True:
        new_elements = len(closure)  # текущее количество атрибутов
        for left, right in fds:
            if set(left).issubset(closure):  # если левая часть текущей ФЗ
                # является подмножеством текущего множества атрибутов, добавляем туда
                # правую часть
                closure.update(right)
        if len(closure) == new_elements:  # если на текущем шаге не добавили ни одну...
            break
    return closure


def remove_redundant_fds(fds):
    """
    Удаляет избыточные зависимости
    """
    minimized_fds = deepcopy(fds)
    for fd in fds:
        minimized_fds.remove(fd)  # удаляем из множества ФЗ текущую
        closure = find_closure(
            fd[0], minimized_fds
        )  # строим замыкание на основе левой части

        if not fd[1].issubset(closure):  # если правую часть не удалось вывести
            # мы не можем ее удалить, добавляем обратно
            minimized_fds.append(fd)
        else:
            print("Удаляем ФЗ", fd[0], "->", fd[1])
    return minimized_fds


def remove_redundant_attributes(fds):
    """
    Удаляет избыточные атрибуты из левой части каждой зависимости
    """

    minimized_fds = deepcopy(fds)
    for i in range(len(minimized_fds)):
        left, right = minimized_fds[i]
        left = list(left)  # левые части
        flag = False
        if len(left) > 1:  # слева больше одного атрибута
            for attr in left:  # пытаемся удалить атрибут
                test_left = set(left)
                test_left.remove(attr)
                closure = find_closure(
                    test_left, minimized_fds
                )  # minimized fds обновляется
                # находим замыкание на основе оставшихся атрибутов
                if right.issubset(closure):
                    flag = True
                    left.remove(attr)
                    print(
                        f"Удаляем избыточный атрибут '{attr}' из зависимости: {left | {attr}} -> {right}"
                    )
        if flag:
            minimized_fds[i] = (set(left), right)
    return minimized_fds


def minimal_cover(fds):
    """
    Строит минимальное покрытие для множества функциональных зависимостей
    """
    decomposed_fds = decompose_fd(fds)
    without_redundant_fds = remove_redundant_fds(decomposed_fds)
    minimized_fds = remove_redundant_attributes(without_redundant_fds)
    return minimized_fds


def reading_function(file="dependencies.md"):
    fds = []
    with open(file, "r") as f:
        lines = f.readlines()
        for line in lines:
            left, right = line.strip().split(" -> ")
            left_set = set(left.split(", "))
            right_set = set(right[1:-1].split(", "))
            fds.append((left_set, right_set))
    return fds


# fds = {
#     ("A1",): {"A2", "A3", "A4", "A5", "A6", "A7", "A8"},
#     ("B1",): {"B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11"},
#     ("C1",): {"C2"},
#     ("D1",): {"D2"},
# }


def convert_fds_list_to_map_single(fds_list):
    """
    :param fds_list: Список кортежей вида (set(left), set(right))
    :return: Словарь вида {left: set(right)}
    """
    fd_map = {}
    for left, right in fds_list:
        left = frozenset(left)
        # right = frozenset(right)
        if left in fd_map.keys():
            fd_map[left] |= right
        else:
            fd_map[left] = right
    return fd_map


def printing_function(fds):
    converted = convert_fds_list_to_map_single(fds)
    for x, y in converted.items():
        print(set(x), ":", y)

if __name__ == "__main__":
    fds = reading_function()
    printing_function(fds)

    min_cover = minimal_cover(fds)
    print("Минимальное покрытие:")
    # print(type(min_cover[0]))
    printing_function(min_cover)

    # Определяем функциональные зависимости F
    F = convert_min_cover_to_F(min_cover)

    # Определяем декомпозицию ρ(R1, R2, ..., Rk)
    decomposition = []
    with open("decomposition.txt", "r") as f:
        for line in f.readlines():
            decomposition.append(line.strip().split(", "))

    # Проверяем свойство соединения без потерь
    if has_lossless_join(F, decomposition):
        print("Декомпозиция обладает свойством соединения без потерь.")
    else:
        print("Декомпозиция НЕ обладает свойством соединения без потерь.")

