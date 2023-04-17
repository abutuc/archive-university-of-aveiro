import matplotlib.pyplot as plt
import numpy as np
import cheats as ch

passos = [0.01, 0.001, 0.0001, 0.00001]
erros = []
for passo in passos:
    dx = passo
    xf = 2
    x0 = 0

    n = np.int((xf/dx) + 0.1)
    valorEsperado = 1
    x = np.linspace(x0, xf, n+1)

    fun = np.empty(n+1)

    for i in range(0,n):
        fun[i] = (x[i] ** 3) / 4

    integral = dx * ((fun[0] + fun[n])*0.5+np.sum(fun[1:n]))
    erros.append(abs(valorEsperado-integral))

data = []
for k, l in zip(passos, erros):
    data.append((k, l))

m = ch.calc_m(data)
b = ch.calc_b(data)

print(m)
print(b)