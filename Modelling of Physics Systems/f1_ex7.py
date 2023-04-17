import matplotlib.pyplot as plt
import numpy as np
import cheats as ch
import math

def main():
    data = [(0.15, 1,21), (0.20, 1.40), (0.16, 1.26), (0.11, 1.05), (0.25, 1.60), (0.32, 1.78), (0.40, 2.00), (0.45, 2.11), (0.50, 2.22), (0.55, 2.33)]
    x = [coord[0] for coord in data]
    y = [coord[1] for coord in data]

    # a) Não, apresenta quase como uma curva suave.
    plt.plot(x, y, 'o')
    plt.xlabel("m (kg)")
    plt.ylabel("T (s)")
    plt.show()

    # b) Parece ser linear.
    logx = np.log(x)
    logy = np.log(y)

    x = logx
    y = logy
    plt.plot(x, y, 'o')
    plt.xlabel("m (kg)")
    plt.ylabel("T (s)")
    plt.show()

    # c) É um bom ajuste, tem um coeficiente de correlação de aprox. 0.96

    new_data = []
    for x1, y1 in zip(x, y):
        new_data.append((x1,y1))

    m = ch.calc_m(new_data)
    b = ch.calc_b(new_data)
    r2 = ch.calc_r2(new_data)
    deltaM = ch.calc_deltaM(m, r2, len(new_data))
    deltaB = ch.calc_deltaB(deltaM, new_data)

    print("m={}\tdeltaM={}\nb={}\tdeltaB={}\nr2={}".format(m, deltaM, b, deltaB, r2))

    xmax = np.max(x)* 1.1
    xmin = np.min(x) * 0.9
    x1 = np.linspace(xmin,xmax, 1000)
    y1 = m*x1+b
    plt.plot(x, y, 'o', x1, y1)
    plt.xlabel("m (kg)")
    plt.ylabel("T (s)")
    plt.show()

    # d) k = 3.61

    x = [coord[0] for coord in data]
    y = [coord[1] for coord in data]
    sqrt_m = [np.sqrt(num) for num in x]
    k_data = []
    for m_sqrt, y1 in zip(sqrt_m, y):
        k_data.append((m_sqrt, y1))
    
    m_sqrt = ch.calc_m(k_data)
    K = ((2 * np.pi) * (1/m_sqrt))**2
    print("A constante elástica é igual {}".format(K))


main()