import copy

from fractions import Fraction
from tabulate import  tabulate

def print_(array):
    print(tabulate(array, tablefmt='grid', disable_numparse=True))


if __name__ == '__main__':
    with open("inputt.txt") as file:
        table = []
        cols = 8
        rows = 5
        for i in range(rows):
            table.append([Fraction(x) for x in file.readline().split()])
            assert len(table[-1]) == cols
        print_(table)
        while True:
            i, j = [int(x) for x in input().split()]
            tablele = copy.deepcopy(table)
            table[i][j] = 1 / tablele[i][j]
            for ind in range(rows):
                if ind != i:
                    table[ind][j] = -tablele[ind][j] / tablele[i][j]
            for ind in range(cols):
                if ind != j:
                    table[i][ind] = tablele[i][ind] / tablele[i][j]
            for ind1 in range(rows):
                for ind2 in range(cols):
                    if ind1 != i and ind2 != j:
                        table[ind1][ind2] = tablele[ind1][ind2] - tablele[ind1][j] * tablele[i][ind2] / tablele[i][j]
            print_(table)
