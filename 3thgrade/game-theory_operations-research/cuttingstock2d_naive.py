# рассматриваем лист фанеры 4x4 как граф 4x4
# содержащий циклы из 4 вершин, а также цепи из 3 и 2 вершин (просто ребра)
# ребрами соединять можно только "соседние" вершины
# сколько таких разных графов существует?
# количество компонент связности каждого типа дает нам паттерн (способ раскройки)

class Pattern:
    def __init__(self, graph, parameters) -> None:
        self.graph = graph
        self.parameters = parameters


# проверка, является ли i соседом j
def check(i, j):
    return ((i-1)//4 == (j-1)//4 and abs(i-j) == 1) or (i % 4 == j % 4 and abs(i-j) == 4)

# всего вариантов ребер 2^24


# 1 2 3 4
# 5 6 7 8
# 9 10 11 12
# 13 14 15 16
# 6 + 6 + 6 + 6
l = []
for i in range(4):
    l.append([i*4 + 1 + j for j in range(4)])
# print(*l)

# sample = [False for _ in range(16)]
all_patterns = []
for k in range(1 << 24):
    cnt = 1
    edges = {i: [] for i in range(1, 17)}

    for i in range(1, 17):
        for j in range(i+1, 17):
            if (check(i, j)):
                if k & cnt:
                    edges[i] += [j]
                    edges[j] += [i]
                cnt = cnt << 1
    visited = [False for _ in range(16)]
    cycles = 0
    only_edge = 0
    two_edges = 0
    error = False

    graph = [[' ' for i in range(4)] for j in range(4)]
    for i in range(1, 17):
        count_of_vertices = 0
        is_cycle = False
        is_only_edge = False
        is_two_edges = False

        path = [i]

        def dfs(vertex, p=-1):
            global is_cycle
            global count_of_vertices
            global edges
            if visited[vertex-1]:
                is_cycle = True
                return
            count_of_vertices += 1
            visited[vertex-1] = True
            for to in edges[vertex]:
                if to != p:
                    path.append(to)
                    dfs(to, vertex)
        if not visited[i-1]:
            dfs(i)
        else:
            continue
        if count_of_vertices == 1:
            continue
        is_only_edge = count_of_vertices == 2
        is_two_edges = count_of_vertices == 3 and not is_cycle
        is_cycle &= count_of_vertices == 4
        if not (is_cycle or is_only_edge or is_two_edges):
            error = True
            break
        else:
            if is_cycle:
                for x in path:
                    graph[(x-1)//4][(x-1) % 4] = '0'
            if is_two_edges:
                for x in path:
                    graph[(x-1)//4][(x-1) % 4] = '.'
            if is_only_edge:
                for x in path:
                    graph[(x-1)//4][(x-1) % 4] = 'x'
            only_edge += is_only_edge
            two_edges += is_two_edges
            cycles += is_cycle
    if not error and (only_edge > 0 or two_edges > 0 or cycles > 0):
        # print("Cycles: %d, edge: %d, two edges: %d" %
        #       (cycles, only_edge, two_edges))
        # for x in graph:
        #     print(*x)
        all_patterns.append(Pattern(graph, [cycles, only_edge, two_edges]))
# all_patterns.sort(key=lambda x: 16 -
#                   x.parameters[0]*4 - x.parameters[1]*2 - x.parameters[2]*3)
all_patterns.sort(key=lambda x:
                  [x.parameters[0], x.parameters[1], x.parameters[2]], reverse=True)
# with open("patterns.txt", "w") as file:
with open("patterns1.txt", "w") as file:

    for pattern in all_patterns:
        file.write("Cycles: %d, edge: %d, two edges %d, remainder: %d\n" %
                   (pattern.parameters[0], pattern.parameters[1], pattern.parameters[2],
                    16 - pattern.parameters[0]*4 - 2*pattern.parameters[1] - 3*pattern.parameters[2]))
        for x in pattern.graph:
            file.write(' '.join(x) + '\n')
            # print(*x)
