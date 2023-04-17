from fractions import *

# Условное мат.ожидание
xi = [-1, 0, 1]
eta = [-1, 0, 1]
# table = [[Fraction(1, 8), Fraction(1, 6), Fraction(5, 12)],
#          [Fraction(0), Fraction(1, 12), Fraction(7, 48)],
#          [Fraction(0), Fraction(0), Fraction(1, 16)]]
table = [[Fraction(1, 8), Fraction(1, 12), Fraction(7, 24)],
         [Fraction(2, 24), Fraction(1, 12), Fraction(1, 16)],
         [Fraction(3, 24), Fraction(1, 12), Fraction(1, 16)]]
for i in range(3):
    g = Fraction(0)
    M = 0
    for j in range(3):
        g+=table[i][j]
        M+=xi[j]*table[i][j]

    # for j in range(3):
    #     g+=table[j][i]
    #     M+=xi[j]*table[j][i]

    print(g)

    print(M)
    print(M/g)
    print("---")