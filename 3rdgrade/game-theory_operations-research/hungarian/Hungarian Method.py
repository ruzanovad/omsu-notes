def print_mat(matrix):
    for a in matrix:
        print(*a)


def find_row_with_min_zeros(matrix, __paint, __del):
    cnt = len(matrix)
    index = -1
    for i in range(len(matrix)):
        zeros = 0
        for j in range(len(matrix[i])):
            if matrix[i][j] == 0 and (i, j) not in __paint and (i, j) not in __del:
                zeros += 1
        if zeros != 0:
            if cnt == -1 or zeros < cnt:
                cnt = zeros
                index = i
    return index


def find_first_zero_in_row(matrix, index, __paint, __del):
    for j in range(len(matrix[index])):
        if matrix[index][j] == 0 and (index, j) not in __paint and (index, j) not in __del:
            return j
    return -1


def delete_all_zeros(matrix, i, j, __paint, __del):
    for _ in range(len(matrix)):
        if (i, _) not in __del and _ != j and matrix[i][_] == 0:
            __del.append((i, _))
    for _ in range(len(matrix[0])):
        if (_, j) not in __del and _ != i and matrix[_][j] == 0:
            __del.append((_, j))


def highlight_cols(matrix, __high, __del, __cols):
    for i in __high:
        for j in range(len(matrix[i])):
            if j not in __cols and (i, j) in __del:
                __cols.append(j)


def highlight_new_rows(matrix, __painted, __cols, __high):
    for i in range(len(matrix)):
        if i not in __high:
            for j in __cols:
                if (i, j) in __painted:
                    __high.append(i)


def iteration(matrix) -> list[list, list]:
    highlighted_rows = []
    painted_zeros = []
    deleted_zeros = []

    index1 = find_row_with_min_zeros(matrix, painted_zeros, deleted_zeros)

    while index1 != -1:
        highlighted_rows.append(index1)
        painted_zeros.append((index1, find_first_zero_in_row(matrix, index1, painted_zeros, deleted_zeros)))
        delete_all_zeros(matrix, painted_zeros[-1][0], painted_zeros[-1][1], deleted_zeros, deleted_zeros)
        index1 = find_row_with_min_zeros(matrix, painted_zeros, deleted_zeros)
    real_highlighted_rows = list(filter(lambda x: x not in highlighted_rows, range(n)))
    print("Arrows:")
    print(*real_highlighted_rows)

    highlighted_cols = []
    highlight_cols(matrix, real_highlighted_rows, deleted_zeros, highlighted_cols)
    # bug
    cnt = 0
    while len(highlighted_cols) > cnt:
        cnt = len(highlighted_cols)
        highlight_new_rows(matrix, painted_zeros, highlighted_cols, real_highlighted_rows)
        highlight_cols(matrix, real_highlighted_rows, deleted_zeros, highlighted_cols)
    highlighted_rows_but_cols = list(filter(lambda x: x not in real_highlighted_rows, range(n)))

    print("New arrows:")
    print(*real_highlighted_rows)
    print("Painted zeros coordinates: ")
    print(*painted_zeros)
    print("Deleted zeros coordinates: ")
    print(*deleted_zeros)
    print("Deleted rows:")
    print(*highlighted_rows_but_cols)

    print("Deleted cols:")
    print(*highlighted_cols)

    return [highlighted_cols, highlighted_rows_but_cols]


with open("input1.txt") as file:
    matrix = []
    n = int(file.readline())
    for i in range(n):
        matrix.append([int(j) for j in file.readline().strip().split()])
    print("C=")
    print_mat(matrix)
    for i in range(n):
        min_elem = min(matrix[i])
        for j in range(n):
            matrix[i][j] -= min_elem
    print("\nC'=")
    print_mat(matrix)

    lst = matrix[0].copy()

    for i in range(n):
        for j in range(n):
            lst[i] = min(lst[i], matrix[j][i])
    for i in range(n):
        for j in range(n):
            matrix[j][i] -= lst[i]
    print("\nC'=")
    print_mat(matrix)

    highlighted_cols, highlighted_rows_but_cols = iteration(matrix)
    while len(highlighted_cols) + len(highlighted_rows_but_cols) != n:
        theta = min(map(lambda y: matrix[y//n][y %n],
                        filter(lambda x: x // n not in highlighted_rows_but_cols and
                                         x % n not in highlighted_cols,
                           [i for i in range(n * n)])))
        for i in range(n):
            for j in range(n):
                if i not in highlighted_rows_but_cols and j not in highlighted_cols:
                    matrix[i][j] -= theta
                elif i in highlighted_rows_but_cols and j in highlighted_cols:
                    matrix[i][j] += theta
        print("\nC=")
        print_mat(matrix)
        highlighted_cols, highlighted_rows_but_cols = iteration(matrix)

    print("OPTIMAL SOLUTION")
