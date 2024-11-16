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

    # Создаем таблицу с "+" для атрибутов, присутствующих в каждом подмножестве
    table = pd.DataFrame(
        [
            ["+" if attr in subset else "" for attr in attributes]
            for subset in decomposition
        ],
        columns=list(attributes),
        index=[
            frozenset(x) for x in decomposition
        ],  # индексируем строки подмножествами
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
                    # print("плюс на", idx, Aj)
                    
                    table.at[idx, Aj] = "+"  # Обновляем значение в таблице
                    print(table)
                    changes = True

        # Проверяем, есть ли строка, полностью заполненная '+'
        if table.apply(is_full, axis=1).any():
            return True  # Свойство соединения без потерь выполняется

        if not changes:
            break  # Нет изменений, завершаем

    return False  # Свойство соединения без потерь отсутствует


# Пример использования
if __name__ == "__main__":
    # Определяем функциональные зависимости F
    F = [({"A"}, "B"), ({"B"}, "C"), ({"A"}, "C"), ({"A", "D"}, "E")]

    # Определяем декомпозицию ρ(R1, R2, ..., Rk)
    decomposition = [{"A", "B"}, {"B", "C"}, {"E", "D"}]

    # Проверяем свойство соединения без потерь
    if has_lossless_join(F, decomposition):
        print("Декомпозиция обладает свойством соединения без потерь.")
    else:
        print("Декомпозиция НЕ обладает свойством соединения без потерь.")
