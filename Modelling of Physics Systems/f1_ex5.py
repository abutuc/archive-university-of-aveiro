import numpy as np
import matplotlib.pyplot as plt
import cheats as ch
def main():
    # a) - d)
    data = [(222.0, 2.3), (207.5, 2.2), (194.0, 2.0), (171.5, 1.8), (153.0, 1.6), (133.0, 1.4), (113.0, 1.2), (92.0, 1.0)]
    L = [coord[0] for coord in data]
    X = [coord[1] for coord in data]

    m = ch.calc_m(data)
    b = ch.calc_b(data)
    r2 = ch.calc_r2(data)
    deltaM = ch.calc_deltaM(m, r2, len(data))
    deltaB = ch.calc_deltaB(deltaM, data)

    print("m={}\tdeltaM={}\nb={}\tdeltaB={}\nr2={}".format(m, deltaM, b, deltaB, r2))

    Lmax = np.max(L)* 1.1
    Lmin = np.min(L) * 0.9
    L1 = np.linspace(Lmin,Lmax, 1000)
    X1 = m*L1+b
    plt.plot(L,X,'o', L1, X1)
    plt.xlabel("L")
    plt.ylabel("X")
    plt.show()

    # e)
    print("Quando L = 165.0cm, X = {}".format(m*165.0+b))

    # f)
    data = [(222.0, 2.3), (207.5, 2.2), (194.0, 2.0), (171.5, 1.8), (153.0, 3.6), (133.0, 1.4), (113.0, 1.2), (92.0, 1.0)]
    L = [coord[0] for coord in data]
    X = [coord[1] for coord in data]

    m = ch.calc_m(data)
    b = ch.calc_b(data)
    r2 = ch.calc_r2(data)
    deltaM = ch.calc_deltaM(m, r2, len(data))
    deltaB = ch.calc_deltaB(deltaM, data)

    print("m={}\tdeltaM={}\nb={}\tdeltaB={}\nr2={}".format(m, deltaM, b, deltaB, r2))

    Lmax = np.max(L)* 1.1
    Lmin = np.min(L) * 0.9
    L1 = np.linspace(Lmin,Lmax, 1000)
    X1 = m*L1+b
    plt.plot(L,X,'o', L1, X1)
    plt.xlabel("L")
    plt.ylabel("X")
    plt.show()


main()
