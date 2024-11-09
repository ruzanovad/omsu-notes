from copy import deepcopy


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
    closure = set(attributes)
    while True:
        new_elements = len(closure)
        for left, right in fds:
            if set(left).issubset(closure):
                closure.update(right)
        if len(closure) == new_elements:
            break
    return closure


def remove_redundant_fds(fds):
    """
    Удаляет избыточные зависимости
    """
    minimized_fds = deepcopy(fds)
    for fd in fds:
        minimized_fds.remove(fd)
        closure = find_closure(fd[0], minimized_fds)
        if not fd[1].issubset(closure):
            minimized_fds.append(fd)
        else:
            print("Удаляем ФЗ", fd[0], "->", fd[1])
    return minimized_fds


def remove_redundant_attributes(fds):
    """
    Удаляет избыточные атрибуты из левой части каждой зависимости
    """
    minimized_fds = []
    for left, right in fds:
        left = list(left)
        for attr in left:
            test_left = set(left)
            test_left.remove(attr)
            closure = find_closure(test_left, fds)
            if right.issubset(closure):
                left.remove(attr)
                print(
                    f"Удаляем избыточный атрибут '{attr}' из зависимости: {left | {attr}} -> {rights}"
                )
        minimized_fds.append((set(left), right))
    return minimized_fds


def minimal_cover(fds):
    """
    Строит минимальное покрытие для множества функциональных зависимостей
    """
    # decomposed_fds = decompose_fd(fds)
    without_redundant_fds = remove_redundant_fds(fds)
    # minimized_fds = remove_redundant_attributes(without_redundant_fds)
    return without_redundant_fds


def reading_function(file="decomposed-deps.md"):
    fds = []
    with open(file, "r") as f:
        lines = f.readlines()
        for line in lines:
            left, right = line.strip().split(" -> ")
            left_set = set(left.split(", "))
            right_set = set(right.split(", "))
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
        if len(left) != 1:
            raise ValueError(
                "Этот вариант функции предполагает, что левая часть содержит один атрибут."
            )
        key = next(iter(left))  # Извлекаем единственный элемент из множества
        if key in fd_map:
            fd_map[key].update(right)
        else:
            fd_map[key] = set(right)
    return fd_map


fds = reading_function()
print(convert_fds_list_to_map_single(fds))
min_cover = minimal_cover(fds)
print("Минимальное покрытие:", convert_fds_list_to_map_single(min_cover))
