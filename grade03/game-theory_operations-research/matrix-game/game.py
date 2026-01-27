import fractions
import tabulate
class MatrixGame:
    def __init__(self, matrix: list[list[int]]):
        self.matrix = matrix

    def get_min_in_row(self, index):
        return min(self.matrix[index])

    def get_max_in_col(self, index):
        return max([self.matrix[i][index] for i in range(len(self.matrix[0]))])

    def get_alpha(self):
        return max([self.get_min_in_row(i) for i in range(len(self.matrix))])

    def get_beta(self):
        return min([self.get_max_in_col(j) for j in range(len(self.matrix[0]))])



def pure_strategy(game : MatrixGame)-> bool:
    print("Выясним, разрешима ли игра в чистых стратегиях:")
    mat = (game.matrix.copy()) # todo map to list[str]
    mat.append([game.get_max_in_col(i) for i in range(len(game.matrix[0]))]+[''])
    for i in range(game.matrix.__len__()):
        mat[i].append(game.get_min_in_row(i))
    mat[-1][-1] = "%d\%d" % (game.get_beta(), game.get_alpha())
    print(tabulate(mat, headers='firstrow',
                   tablefmt='grid', disable_numparse=True))

with open("input.txt") as file:
    matrix = []
    n = int(file.readline())
    for i in range(n):
        matrix.append([int(j) for j in file.readline().strip().split()])
    game = MatrixGame(matrix)

