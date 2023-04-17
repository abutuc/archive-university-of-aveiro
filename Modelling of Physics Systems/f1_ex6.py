import matplotlib.pyplot as plt
import numpy as np
import cheats as ch

def main():
    data = [(0, 0.00), (1, 0.735), (2, 1.363), (3, 1.739), (4, 2.805), (5, 3.814), (6, 4.458), (7, 4.335), (8, 5.666), (9, 5.329)]
    x = [coord[0] for coord in data]
    y = [coord[1] for coord in data]

    # a) Sim, a distância percorrida parece ser linear, apesar de existir alguns desvios.
    plt.plot(x, y, 'o')
    plt.xlabel("min")
    plt.ylabel("km")
    plt.show()

    # b) Sim, sendo o coeficiente de correlação superior a 0.90 posso concluir que o ciclista conseguiu manter uma velocidade uniforme durante o percurso.
    m = ch.calc_m(data)
    b = ch.calc_b(data)
    r2 = ch.calc_r2(data)
    deltaM = ch.calc_deltaM(m, r2, len(data))
    deltaB = ch.calc_deltaB(deltaM, data)
    print("m={}\tdeltaM={}\nb={}\tdeltaB={}\nr2={}".format(m, deltaM, b, deltaB, r2))

    # c)
    c1 = np.polyfit(x, y, 1)

    print("Velocidade = {} km/min".format(c1[0]))

    # d) km/m, 1m = 1/60 horas 
    print("Velociade = {} km/h".format(c1[0] * 60))


main()
