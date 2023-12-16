import fractions
import igraph


def is_graph_three_colorable(graph):
    num_vertices = len(graph)
    colors = [0] * num_vertices
    return color_graph(graph, colors, 0)


def color_graph(graph, colors, vertex):
    num_vertices = len(graph)
    if vertex == num_vertices:
        return True

    for color in [1, 2, 3]:
        if is_safe_color(graph, colors, vertex, color):
            colors[vertex] = color
            if color_graph(graph, colors, vertex + 1):
                return True
            colors[vertex] = 0

    return False


def is_safe_color(graph, colors, vertex, color):
    num_vertices = len(graph)
    for neighbor in range(num_vertices):
        if graph[vertex][neighbor] == 1 and colors[neighbor] == color:
            return False
    return True


def generate_adjacency_matrices(n):
    matrices = []
    for i in range(2 ** (n * (n - 1) // 2)):
        array = []
        j = i
        while (j > 0):
            array.append(j % 2)
            j //= 2
        array += [0] * (n * (n - 1) // 2 - len(array))
        matrix = [[0] * n for j in range(n)]
        cnt = 0
        for row in range(n - 1):
            for k in range(n - 1 - row):
                matrix[row][row + k + 1] = array[cnt]
                matrix[row + k + 1][row] = array[cnt]
                cnt += 1
        # print(cnt, len(array))
        matrices.append(matrix)
    return matrices


def generate_matrices_helper(matrix, row, matrices):
    n = len(matrix)

    if row == n:
        # Matrix is complete, add it to the list
        matrices.append(matrix[:])
        return

    for col in range(n):
        matrix[row][col] = 0  # Try 0
        generate_matrices_helper(matrix, row + 1, matrices)

        matrix[row][col] = 1  # Try 1
        generate_matrices_helper(matrix, row + 1, matrices)

        # Reset the value for backtracking
        matrix[row][col] = 0


# Example usage:
for n in range(5, 6):
    count = 0
    adjacency_matrices = generate_adjacency_matrices(n)
    # igraph.Graph.to_undirected()
    dictionary = {}
    for index, matrix in enumerate(adjacency_matrices):
        # print(*[' '.join(map(str, i)) for i in matrix])
        graph = igraph.Graph.Adjacency(matrix)
        graph.to_undirected()
        # igraph.plot(graph)

        # fig, axs = plt.subplots(64, 64, figsize=(64, 53))
        # igraph.plot(g, target=axs[0])
        # axs[1].bar(x=[0, 1, 2], height=[1, 5, 3], color='tomato')
        # igraph.Graph.get_edgelist().__len__()
        # print(graph.)
        l = graph.cliques(min=4, max=4)
        if len(l) > 0:
            # print(*l)
            count += 1
            if graph.get_edgelist().__len__() not in dictionary.keys():
                dictionary[graph.get_edgelist().__len__()] = [graph]
            else:
                flag = False
                for grp in dictionary[graph.get_edgelist().__len__()]:
                    flag = graph.isomorphic(grp)
                    if flag:
                        break
                if not flag:
                    dictionary[graph.get_edgelist().__len__()].append(graph)
            # fig, ax = plt.subplots()

            # plt.show()
            # print(matrix)
    for val in dictionary.values():
        for index, value in enumerate(val):
            igraph.plot(value, layout='circle', target='%d_clique_%d.png' % (value.get_edgelist().__len__(), index))
    print(count, 2 ** (n * (n - 1) // 2), count / 2 ** (n * (n - 1) // 2),
          fractions.Fraction(count, 2 ** (n * (n - 1) // 2)))
