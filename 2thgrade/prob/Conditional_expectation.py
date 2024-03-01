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

    xi_distribution = [(xi[i], sum(table[j][i] for j in range(len(eta)))) for i in range(len(xi))]
    eta_distribution = [(eta[i], sum(table[i][j] for j in range(len(xi)))) for i in range(len(eta))]
    print(',\t'.join(list(map(lambda x: "P{xi = " + str(x[0]) + "} = " + str(x[1]), xi_distribution))))
    print(',\t'.join(list(map(lambda x: "P{eta = " + str(x[0]) + "} = " + str(x[1]), eta_distribution))))
    print("xi|eta")
    for i in range(3):
        g = Fraction(0)
        M = 0
        # xi | eta
        for j in range(len(xi)):
            g += table[i][j]
            M += xi[j] * table[i][j]
        print("P(eta = %s) = %s" % (xi[i], g))
        print("M(xi I_{eta = %s}) = %s" % (xi[i], M))
        # eta | xi
        # for j in range(len(eta)):
        #     g+=table[j][i]
        #     M+=eta[j]*table[j][i]

        # print(g)

        # print(M)

        print("M(xi|eta)(%s) = %s" % (eta[i], M / g))
        print("---")

    print("eta|xi")
    for i in range(3):
        g = Fraction(0)
        M = 0
        # xi | eta
        # for j in range(len(xi)):
        #     g+=table[i][j]
        #     M+=xi[j]*table[i][j]

        # eta | xi
        for j in range(len(eta)):
            g += table[j][i]
            M += eta[j] * table[j][i]

        print("P(xi = %s) = %s" % (xi[i], g))
        print("M(eta I_{xi = %s}) = %s" % (xi[i], M))

        # print(g)

        # print(M)

        print("M(eta|xi)(%s) = %s" % (xi[i], M / g))
        print("---")
    Mxi = sum(xi_distribution[i][0] * xi_distribution[i][1] for i in range(len(xi_distribution)))
    Meta = sum(eta_distribution[i][0] * eta_distribution[i][1] for i in range(len(eta_distribution)))
    print("M xi = %s" % Mxi)
    print("M eta = %s" % Meta)

    Mxi_squared = sum(
        xi_distribution[i][0] * xi_distribution[i][0] * xi_distribution[i][1] for i in range(len(xi_distribution)))
    Meta_squared = sum(
        eta_distribution[i][0] * eta_distribution[i][0] * eta_distribution[i][1] for i in range(len(eta_distribution)))
    print("M xi ^ 2 = %s" % Mxi_squared)
    print("M eta ^ 2 = %s" % Meta_squared)
    print("D xi = %s" % (Mxi_squared - Mxi * Mxi))
    print("D eta = %s" % (Meta_squared - Meta * Meta))

    print("xi * eta")
    set_xi_eta = set()
    # [[xi[i] * eta[j] for i in range(len(xi))] for j in range(len(eta))]
    for j in range(len(eta)):
        for i in range(len(xi)):
            set_xi_eta.add(xi[i] * eta[j])
    set_xi_eta = sorted(list(set_xi_eta))
    xi_eta_distribution = [[set_xi_eta[i], 0] for i in range(len(set_xi_eta))]
    for i in range(len(xi)):
        for j in range(len(eta)):
            if xi[i] * eta[j] in set_xi_eta:
                xi_eta_distribution[set_xi_eta.index(xi[i] * eta[j])][1] += table[j][i]
    print(',\t'.join(list(map(lambda x: "P{xi*eta = " + str(x[0]) + "} = " + str(x[1]), xi_eta_distribution))))
    # построить табличку новую, прогнать

    print('-----')
    print("eta+xi")
    print("xi * eta")
    set_xi_p_eta = set()
    # [[xi[i] * eta[j] for i in range(len(xi))] for j in range(len(eta))]
    for j in range(len(eta)):
        for i in range(len(xi)):
            set_xi_p_eta.add(xi[i] + eta[j])
    set_xi_p_eta = sorted(list(set_xi_p_eta))
    xi_p_eta_distribution = [[set_xi_p_eta[i], 0] for i in range(len(set_xi_p_eta))]
    for i in range(len(xi)):
        for j in range(len(eta)):
            if xi[i] + eta[j] in set_xi_p_eta:
                xi_p_eta_distribution[set_xi_p_eta.index(xi[i] + eta[j])][1] += table[j][i]
    print(',\t'.join(list(map(lambda x: "P{xi+eta = " + str(x[0]) + "} = " + str(x[1]), xi_p_eta_distribution))))

    Mrasp = sum(xi_p_eta_distribution[i][0] * xi_p_eta_distribution[i][1] for i in range(len(xi_p_eta_distribution)))
    Mrasp_squared = sum(
        xi_p_eta_distribution[i][0] * xi_p_eta_distribution[i][0] * xi_p_eta_distribution[i][1] for i in
        range(len(xi_p_eta_distribution)))

    print("M(xi + eta) = %s" % Mrasp)
    print("M(xi + eta)^2 = %s" % Mrasp_squared)
    print("D(xi+eta) = M(xi + eta)^2 - (M(xi + eta))^2 = %s - %s = %s" % (
        Mrasp_squared, Mrasp * Mrasp, Mrasp_squared - Mrasp * Mrasp))

    print("eta|xi * eta")
