import matplotlib.pyplot as plt
import numpy as np
import cheats as ch

def main():
    data = [(200, 0.6950), (300, 4.363), (400, 15.53), (500, 38.74), (600, 75.08), (700, 125.2), (800, 257.9), (900, 344.1), (1000, 557.4), (1100, 690.7)]
    x = [coord[0] for coord in data]
    y = [coord[1] for coord in data]

    # a) Não é linear.
    plt.plot(x,y, 'o')
    plt.xlabel("T(K)")
    plt.ylabel("E(J)")
    plt.show()

    # b) A relação é exponencial.
    logx = np.log(x)
    logy = np.log(y)

    x = logx
    y = logy
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
    plt.xlabel("T(K)")
    plt.ylabel("E(J)")
    plt.show()
   



main()
