from fractions import *


with open("input.txt") as file:
    # Условное мат.ожидание
    xi = [Fraction(i) for i in file.readline().split()]
    eta = [Fraction(i) for i in file.readline().split()]

    # table = [[Fraction(1, 8), Fraction(1, 6), Fraction(5, 12)],
    #          [Fraction(0), Fraction(1, 12), Fraction(7, 48)],
    #          [Fraction(0), Fraction(0), Fraction(1, 16)]]
    table = []
    for i in range(len(xi)):
        # for j in range(len(eta)):
        table.append([Fraction(i) for i in file.readline().split()])

    # table = [[Fraction(1, 8), Fraction(1, 12), Fraction(7, 24)],
    #         [Fraction(2, 24), Fraction(1, 12), Fraction(1, 16)],
    #         [Fraction(3, 24), Fraction(1, 12), Fraction(1, 16)]]
    for i in range(3):
        g = Fraction(0)
        M = 0
        for j in range(len(xi)):
            g+=table[i][j]
            M+=xi[j]*table[i][j]

        # for j in range(len(eta)):
        #     g+=table[j][i]
        #     M+=eta[j]*table[j][i]

        # print(g)

        # print(M)
        print(M/g)
        print("---")

