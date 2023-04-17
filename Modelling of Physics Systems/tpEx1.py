import matplotlib.pyplot as plt
import numpy as np
import math


def maximo(xm1, xm2, xm3, ym1, ym2, ym3):  # máximo pelo polinómio de Lagrange
    xab = xm1 - xm2
    xac = xm1 - xm3
    xbc = xm2 - xm3

    a = ym1 / (xab * xac)
    b = -ym2 / (xab * xbc)
    c = ym3 / (xac * xbc)

    xmla = (b + c) * xm1 + (a + c) * xm2 + (a + b) * xm3
    xmax = 0.5 * xmla / (a + b + c)

    xta = xmax - xm1
    xtb = xmax - xm2
    xtc = xmax - xm3

    ymax = a * xtb * xtc + b * xta * xtc + c * xta * xtb
    return xmax, ymax


def zerosv(xm1, xm2, xm3, ym1, ym2, ym3):  # raiz pelo polinómio de Lagrange
    xab = xm1 - xm2
    xac = xm1 - xm3
    xbc = xm2 - xm3

    a = ym1 / (xab * xac)
    b = -ym2 / (xab * xbc)
    c = ym3 / (xac * xbc)

    am = a + b + c
    bm = a * (xm2 + xm3) + b * (xm1 + xm3) + c * (xm1 + xm2)
    cm = a * xm2 * xm3 + b * xm1 * xm3 + c * xm1 * xm2

    xzero = (bm + np.sqrt(bm * bm - 4 * am * cm)) / (2 * am)
    if xm3 > xm1 and (xzero < xm1 or xzero > xm3):
        xzero = (bm - np.sqrt(bm * bm - 4 * am * cm)) / (2 * am)

    if xm1 > xm3 and (xzero < xm3 or xzero > xm1):
        xzero = (bm - np.sqrt(bm * bm - 4 * am * cm)) / (2 * am)

    xta = xzero - xm1
    xtb = xzero - xm2
    xtc = xzero - xm3
    yzero = a * xtb * xtc + b * xta * xtc + c * xta * xtb
    return xzero, yzero


dt = 0.001

tf = 30
ti = 0

n = np.int((tf - ti) / dt)
t = np.linspace(ti, tf, n)

k = 1
m = 1
w = math.sqrt(k / m)
alfa = -0.02

v = np.zeros(n)
v[0] = 0

x = np.zeros(n)
x[0] = 4

Ep = np.zeros(n)

a = np.zeros(n)

for i in range(n - 1):
    a[i] = -(w**2) * x[i] - 3*(alfa/m)*x[i]**2
    v[i + 1] = v[i] + a[i] * dt
    x[i + 1] = x[i] + v[i + 1] * dt
    Ep[i] = 0.5 * k * x[i]**2 + alfa * x[i]**3


Ep[i + 1] = 0.5 * k * x[i + 1]**2 + alfa * x[i + 1]**3

#plot dos gráficos pedidos
plt.plot(t, x, '-')
plt.ylabel("x(m)")
plt.xlabel("t(s)")
plt.show()

plt.plot(t, v, '-')
plt.ylabel("v(m/s)")
plt.xlabel("t(s)")
plt.show()