import copy
from enum import Enum
from fractions import Fraction

from tabulate import tabulate


def read(file):
    number_of_variables = int(file.readline())
    number_of_restrictions = int(file.readline())
    function = Function(file.readline().split())
    restrictions = []
    soft_restrictions = []
    for i in range(number_of_restrictions):
        restrictions.append(Restriction(file.readline().split()))
    for i in range(number_of_variables):
        soft_restrictions.append(Inequality.parseString(file.readline().split()[1]))
    return function, restrictions, soft_restrictions


class Function:
    def __init__(self, array) -> None:
        self.opt = False if array[-1] == 'min' else True
        self.array = [Fraction(i) for i in array[1:-1]]
        self.c = Fraction(array[0])

    def max(self) -> bool:
        return self.opt

    def min(self) -> bool:
        return not self.opt

    def value(self, vector: list[Fraction]) -> Fraction:
        assert len(vector) == len(self.array)
        ret = Fraction(0)
        for i in range(len(vector)):
            ret += self.array[i] * vector[i]
        return ret


class Inequality(Enum):
    EQUAL = 0
    # LESS = -2
    LESSEQ = -1
    # GREATER = 2
    GREATEREQ = 1
    NONE = 10

    def __str__(self):
        m = {Inequality.EQUAL: "=",
             Inequality.GREATEREQ: ">=",
             Inequality.LESSEQ: "<=",
             Inequality.NONE: "#"}
        return m[self]

    def parseString(value: str):
        m = {"=": Inequality.EQUAL,
             ">=": Inequality.GREATEREQ,
             "<=": Inequality.LESSEQ,
             "#": Inequality.NONE}
        return m[value]


# we assume that all variables are greater or equal to 0
class Restriction:
    def __init__(self, array: list[str]) -> None:
        # match (array[-2]):
        #     case
        self.inequal = Inequality.parseString(array[-2])
        self.coefs = [Fraction(i) for i in array[:-2]]
        self.b = Fraction(array[-1])


class Problem:
    def __init__(self, fun: Function, restrictions: list[Restriction], soft_restrictions: list[Inequality]):
        self.variables = []
        self.function = fun
        self.restrictions = restrictions

        self.variables = ["x_%d" % (i + 1) for i in range(len(restrictions[0].coefs))]
        # for i in range(len(restrictions[0].coefs)):
        #     self.variables.append("x_%d" % (i + 1))
        self.soft_restrictions = soft_restrictions

        for i in range(len(restrictions)):
            assert len(restrictions[i].coefs) == len(self.function.array)

        assert len(self.soft_restrictions) == len(self.function.array)
        # self.number_of_variables = len(self.soft_restrictions)

    # soft restr - NONE -> x in R, LESSEQ  <= 0, GREATEREQ >=0

    def check_canon(self) -> bool:
        flag = True
        flag &= self.function.opt
        for restriction in self.restrictions:
            flag &= restriction.inequal == Inequality.EQUAL

        for soft_restriction in self.soft_restrictions:
            flag &= soft_restriction == Inequality.GREATEREQ
        return flag

    def make_canon(self, log: bool):
        if self.check_canon():
            return self

        # min to max

        if self.function.min():
            self.function.array = list(map(lambda x: -x, self.function.array))
            self.function.c *= -1
            self.function.opt = True

        count = len(self.variables) + 1
        index = 0
        while index < len(self.soft_restrictions):
            if self.soft_restrictions[index] == Inequality.LESSEQ:
                for i in range(len(self.restrictions)):
                    self.restrictions[i].coefs[index] *= -1
                self.function.array[index] *= -1
                self.soft_restrictions[index] = Inequality.GREATEREQ
                if log:
                    print("%d variable: %s -> %s, %s => -%s" %
                          (index + 1, Inequality.LESSEQ, Inequality.GREATEREQ,
                           self.variables[index], self.variables[index]))

            elif self.soft_restrictions[index] == Inequality.NONE:
                self.soft_restrictions.insert(index + 1, Inequality.GREATEREQ)
                for i in range(len(self.restrictions)):
                    self.restrictions[i].coefs.insert(index + 1, -self.restrictions[i].coefs[index])
                self.function.array.insert(index + 1, -self.function.array[index])
                # self.function.array[index] *= -1

                var = self.variables[index]

                self.soft_restrictions[index] = Inequality.GREATEREQ
                self.variables[index] = self.variables[index] + "'"
                self.variables.insert(index + 1, self.variables[index] + "'")
                if log:
                    print("%d variable: %s -> %s, %s => %s + %s, %s>=0 and %s>=0" %
                          (index + 1, Inequality.NONE, Inequality.GREATEREQ,
                           var, self.variables[index], self.variables[index + 1],
                           self.variables[index], self.variables[index + 1]))
                index += 1

            index += 1
        for index in range(len(problem.restrictions)):
            if self.restrictions[index].inequal == Inequality.LESSEQ:
                self.restrictions[index].inequal = Inequality.EQUAL
                self.soft_restrictions.append(Inequality.GREATEREQ)
                for jndex in range(len(problem.restrictions)):
                    if index != jndex:
                        self.restrictions[jndex].coefs.append(Fraction(0))
                    else:
                        self.restrictions[jndex].coefs.append(Fraction(1))
                self.function.array.append(Fraction(0))
                self.variables.append("x_%d" % count)
                # TODO log for <=
                count += 1
            elif self.restrictions[index].inequal == Inequality.GREATEREQ:
                self.restrictions[index].inequal = Inequality.EQUAL
                self.soft_restrictions.append(Inequality.GREATEREQ)
                for jndex in range(len(problem.restrictions)):
                    if index != jndex:
                        self.restrictions[jndex].coefs.append(Fraction(0))
                    else:
                        self.restrictions[jndex].coefs.append(Fraction(-1))
                self.function.array.append(Fraction(0))
                self.variables.append("x_%d" % count)
                # TODO log for >=
                count += 1

    def __str__(self):
        s = "f = "
        if (self.function.c != 0):
            s += "(" + str(self.function.c) + ")"
            if len(list(filter(lambda x: x != 0, self.function.array))) >= 1:
                s += " + "
            else:
                s += " "
        if len(self.function.array) >= 1:
            if self.function.array[0] == 0:
                s += " " * len("(" + str(self.function.array[0]) + ")*" + self.variables[0])
            else:
                s += "(" + str(self.function.array[0]) + ")*" + self.variables[0]

        for i in range(len(self.function.array) - 1):
            if self.function.array[i + 1] != 0:
                s += "+ (" + str(self.function.array[i + 1]) + ")*" + self.variables[i + 1]
            else:
                s += "   " + " " * len(str(self.function.array[i + 1]) + ")*" + self.variables[i + 1])
        s += " "
        if self.function.max():
            s += "-> max\n"
        else:
            s += "-> min\n"
        for i in range(len(self.restrictions)):
            if len(self.restrictions[i].coefs) >= 1:
                if self.restrictions[i].coefs[0] == 0:
                    s += " " * len("(" + str(self.restrictions[i].coefs[0]) + ")*" + self.variables[0])
                else:
                    s += "(" + str(self.restrictions[i].coefs[0]) + ")*" + self.variables[0]

            for j in range(1, len(self.restrictions[i].coefs)):
                if self.restrictions[i].coefs[j] != 0:
                    s += "+ (" + str(self.restrictions[i].coefs[j]) + ")*" + self.variables[j]
                else:
                    s += "   " + " " * len(str(self.restrictions[i].coefs[j]) + ")*" + self.variables[j])
            # s += ' + '.join(["(" + str(self.restrictions[i].coefs[j]) + ")*" + self.variables[j]
            #                  if self.restrictions[i].coefs[j] !=0 else
            #                  for j in range(len(self.restrictions[i].coefs))]) + " " + str(
            s += str(self.restrictions[i].inequal) + "\t"
            s += str(self.restrictions[i].b) + "\n"

        for i in range(len(self.soft_restrictions)):
            s += "%s %s 0\n" % (self.variables[i], self.soft_restrictions[i])
        return s

    def check_reduced(self):
        if not self.check_canon():
            return False
        # temp_list = copy.deepcopy(self.restrictions)
        list_of_basis = []
        # vector_of_nonbasis = function.array
        for i in range(len(self.function.array)):
            if self.function.array[i] == 0:
                null = 0
                basis = 0
                index_of_basis = -1
                for j in range(len(self.restrictions)):
                    null += self.restrictions[j].coefs[i] == 0
                    basis += self.restrictions[j].coefs[i] == 1
                    if self.restrictions[j].coefs[i] == 1:
                        index_of_basis = j
                if basis != 1 or null != len(self.restrictions) - 1:
                    return False
                list_of_basis.append(index_of_basis)  # iтая переменная, index oj basis - номер ограничения
        list_of_basis.sort()
        if list_of_basis[0] != 0 or list_of_basis[-1] != len(list_of_basis) - 1:
            return False
        return True

    def check_special(self):
        if not self.check_reduced():
            return False
        flag = True
        for i in range(len(self.restrictions)):
            flag &= self.restrictions[i].b >= 0
        return flag

    def get_basis(self):
        if not self.check_reduced():
            return []
        list_of_basis = []
        vector_of_nonbasis = list(map(lambda x: x != 0, self.function.array))
        for i in range(len(vector_of_nonbasis)):
            if not vector_of_nonbasis[i]:
                null = 0
                basis = 0
                index_of_basis = -1
                for j in range(len(self.restrictions)):
                    null += self.restrictions[j].coefs[i] == 0
                    basis += self.restrictions[j].coefs[i] == 1
                    if self.restrictions[j].coefs[i] == 1:
                        index_of_basis = j
                if basis != 1 or null != len(self.restrictions) - 1:
                    return False
                list_of_basis.append((i, index_of_basis))  # iтая переменная, index oj basis - номер ограничения
        list_of_basis.sort(key=lambda x: x[1])
        return list_of_basis


# class SoftRestriction():


class SimplexTable:
    @staticmethod
    def simplex_method(problem: Problem, full_table=True):
        if not problem.check_special():
            print("задача имеет отрицательные элементы в столбце правых частей")
            return
        table = []

        basis = problem.get_basis()
        if len(basis) == 0:
            print("Нет базиса")
            return
        if full_table:
            table.append(["B"] + ["x_0"] + problem.variables)
            # print(*table[0])
            table.append(["f", problem.function.c] + [-i for i in problem.function.array])
            for i in range(len(basis)):
                row = [problem.variables[basis[i][0]], problem.restrictions[i].b]
                row += problem.restrictions[i].coefs
                table.append(row)
                # print(row)
            count = 1
            while count <= 100:
                print(tabulate(table, headers='firstrow', tablefmt='grid', disable_numparse=True))
                print("Итерация №%d" % count)

                print("Проверка на оптимальность")
                if all([table[1][i + 2] >= 0 for i in range(len(problem.function.array))]):
                    d = {problem.variables[i]: 0 for i in range(len(problem.variables))}
                    for i in range(len(problem.restrictions)):
                        d[table[i + 2][0]] = table[i + 2][1]
                    # solution = [0 if table[0][i + 2] not in [table[j + 2][0] for j in range(len(problem.restrictions))] else
                    #         table[1][i + 2] for i in range(len(problem.variables))]
                    print("Базисное решение (%s) оптимально." % (', '.join([str(i) for i in list(d.values())])))
                    return list(d.values())
                print("Проверка на неразрешимость")
                if any([all([table[j + 1][i + 2] < 0 for j in range(len(problem.restrictions) + 1)]) for i in
                        range(len(problem.variables))]):
                    print("Задача ЛП неразрешима, т.к f(x) не ограничена сверху на множестве допустимых решений D_I")
                    return -1
                print("Выбор ведущего столбца")
                q = -1
                max_q = 0
                for i in range(len(problem.variables)):
                    if table[1][i + 2] < 0 and table[1][i + 2] < max_q:
                        max_q = table[1][i + 2]
                        q = i
                print("Столбец %s является ведущим" % problem.variables[q])

                print("Выбор ведущей строки")
                p = -1
                min_p = max([table[i + 2][1] for i in range(len(problem.restrictions))])
                possible_p = []
                for i in range(len(problem.restrictions)):
                    if table[i + 2][q + 2] > 0:
                        possible_p.append((table[i + 2][1] / table[i + 2][q + 2], i))
                possible_p.sort(key=lambda x: x[0])
                p = possible_p[0][1]
                print("Строка %s является ведущей" % problem.variables[basis[p][0]])
                print("Преобразование...")

                table[p + 2][0] = table[0][q + 2]
                old_table = copy.deepcopy(table)
                for i in range(len(problem.variables) + 1):
                    table[p + 2][i + 1] = old_table[p + 2][i + 1] / old_table[p + 2][q + 2]
                for i in range(len(problem.restrictions) + 1):
                    if i + 1 != p + 2:
                        for j in range(len(problem.variables) + 1):
                            table[i + 1][j + 1] = old_table[i + 1][j + 1] - \
                                                  old_table[i + 1][q + 2] / old_table[p + 2][q + 2] * old_table[p + 2][
                                                      j + 1]

                count += 1
            return -1
        else:
            map_basis = {problem.variables[i]: False for i in range(len(problem.variables))}
            nonbasis = copy.deepcopy(problem.variables)

            for i in basis:
                nonbasis.remove(problem.variables[i[0]])
                map_basis[problem.variables[i[0]]] = True
            table.append(["B"] + ["x_0"] + nonbasis)
            # print(*table[0])
            index_row = ["f", problem.function.c]
            for i in range(len(problem.function.array)):
                if not map_basis[problem.variables[i]]:
                    index_row.append(-problem.function.array[i])
            table.append(index_row)
            for i in range(len(basis)):
                row = [problem.variables[basis[i][0]], problem.restrictions[i].b]
                for j in range(len(problem.restrictions[i].coefs)):
                    if not map_basis[problem.variables[j]]:
                        row.append(problem.restrictions[i].coefs[j])
                table.append(row)
                # print(row)
            count = 1
            while count <= 100:
                print(tabulate(table, headers='firstrow', tablefmt='grid', disable_numparse=True))
                print("Итерация №%d" % count)

                print("Проверка на оптимальность")
                if all([table[1][i + 2] >= 0 for i in range(len(problem.function.array) - len(basis))]):
                    d = {problem.variables[i]: 0 for i in range(len(problem.variables))}
                    for i in range(len(problem.restrictions)):
                        d[table[i + 2][0]] = table[i + 2][1]
                    # solution = [0 if table[0][i + 2] not in [table[j + 2][0] for j in range(len(problem.restrictions))] else
                    #         table[1][i + 2] for i in range(len(problem.variables))]
                    print("Базисное решение (%s) оптимально." % (', '.join([str(i) for i in list(d.values())])))
                    assert table[1][1] == problem.function.value(list(d.values()))
                    return list(d.values()), table[1][1]
                print("Проверка на неразрешимость")
                if any([all([table[j + 1][i + 2] < 0 for j in range(len(problem.restrictions) + 1)]) for i in
                        range(len(problem.variables) - len(basis))]):
                    print("Задача ЛП неразрешима, т.к f(x) не ограничена сверху на множестве допустимых решений D_I")
                    return -1
                print("Выбор ведущего столбца")
                q = -1
                max_q = 0
                for i in range(len(problem.variables) - len(basis)):
                    if table[1][i + 2] < 0 and table[1][i + 2] < max_q:
                        max_q = table[1][i + 2]
                        q = i
                print("Столбец %s является ведущим" % problem.variables[q])

                print("Выбор ведущей строки")
                p = -1
                min_p = max([table[i + 2][1] for i in range(len(problem.restrictions))])
                possible_p = []
                for i in range(len(problem.restrictions)):
                    if table[i + 2][q + 2] > 0:
                        possible_p.append((table[i + 2][1] / table[i + 2][q + 2], i))
                possible_p.sort(key=lambda x: x[0])
                p = possible_p[0][1]
                print("Строка %s является ведущей" % problem.variables[basis[p][0]])
                print("Преобразование...")

                table[p + 2][0], table[0][q + 2] = table[0][q + 2], table[p + 2][0]
                old_table = copy.deepcopy(table)
                table[p + 2][q + 2] = 1 / old_table[p + 2][q + 2]
                for j in range(len(problem.variables) - len(basis) + 1):
                    if j + 1 != q + 2:
                        table[p + 2][j + 1] = old_table[p + 2][j + 1] / old_table[p + 2][q + 2]
                for i in range(len(problem.restrictions) + 1):
                    if i + 1 != p + 2:
                        table[i + 1][q + 2] = -old_table[i + 1][q + 2] / old_table[p + 2][q + 2]
                for i in range(len(problem.restrictions) + 1):
                    if i + 1 != p + 2:
                        for j in range(len(problem.variables) - len(basis) + 1):
                            if j + 1 != q + 2:
                                table[i + 1][j + 1] = old_table[i + 1][j + 1] - \
                                                      old_table[i + 1][q + 2] / old_table[p + 2][q + 2] * \
                                                      old_table[p + 2][
                                                          j + 1]

                count += 1
            return -1

    def artificial_basis(self, problem: Problem) -> Problem:
        if problem.check_special():
            return problem
        list_of_basis = set()
        vector_of_nonbasis = list(map(lambda x: x != 0, problem.function.array))
        for i in range(len(vector_of_nonbasis)):
            if not vector_of_nonbasis[i]:
                column = [problem.restrictions[j].coefs[i] for j in range(len(problem.restrictions))]
                if column.count(1) == 1 and column.count(0) == len(problem.restrictions)-1:
                    list_of_basis.add((column.index(1), i))



    def dual_simplex(self, problem: Problem):
        pass


# def artifictial_basis():


if __name__ == '__main__':
    with open("input.txt") as file:
        problem = Problem(*read(file))
        assert not problem.check_canon()
        print(problem)
        problem.make_canon(True)
        assert problem.check_canon()
        print(problem)
        assert not problem.check_reduced()
    with open("input1.txt") as file:
        problem = Problem(*read(file))
        assert not problem.check_canon()
        print(problem)
        problem.make_canon(False)
        assert problem.check_canon()
        print(problem)
        assert problem.check_reduced()
        print("Базис: %s" % (problem.get_basis()))
        assert SimplexTable.simplex_method(problem) == [2, 2, 0, 0, 4], 12
    with open("input2.txt") as file:
        problem = Problem(*read(file))
        assert not problem.check_canon()
        problem.make_canon(False)
        print(problem)
        assert problem.check_special()
        x, y = SimplexTable.simplex_method(problem, False)  # == [3, 2, 0, 0, 4, 2], 17
        # assert SimplexTable.simplex_method(problem, False) == [3, 2, 0, 0, 4, 2], 17

        assert x == [3, 2, 0, 0, 4, 2]
        assert y == 17
    with open("input3.txt") as file:
        problem = Problem(*read(file))

