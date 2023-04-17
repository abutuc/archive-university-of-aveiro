import matplotlib.pyplot as plt
import numpy as np
import math as mt


dt = 0.0001
tf = 200
t0 = 0

dt2 = 0.001
tf2 = 200
t02 = 0

n = np.int((tf-t0) / dt)
n2 = np.int((tf2-t02)/dt2)

t = np.linspace(t0, tf, n)
t2 = np.linspace(t02, tf2, n2)

x = np.empty(n)
v = np.empty(n)
a = np.empty(n)

x2 = np.empty(n2)
v2 = np.empty(n2)
a2 = np.empty(n2)

x0 = 4
v0 = 0

m = 1
k = 1
b = 0.05
f0 = 7.5
wf = 1

x[0] = x0
v[0] = v0

x2[0] = x0
v2[0] = v0

for i in range(n-1):
    a[i] = (1/m)*(-k*x[i] - b*v[i] + f0*mt.cos(wf*t[i]))
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt

for f in range(n2-1):
    a2[f] = (1/m)*(-k*x2[f] - b*v2[f] + f0*mt.cos(wf*t2[f]))
    v2[f+1] = v2[f] + a2[f]*dt2
    x2[f+1] = x2[f] + v2[f+1]*dt2

plt.plot(t, x, "-")
plt.plot(t2, x2, "-", alpha=0.3)
plt.xlabel("t(s)")
plt.ylabel("x(m)")
plt.title("Oscilador Harmónico Forçado Met Eul")
plt.show()

"""plt.plot(t, v, "-")
plt.xlabel("t(s)")
plt.ylabel("v(m/s)")
plt.title("v/t")
plt.show()
"""

# Resposta ao problema.
"""
Temos confiança, porque a lei do
movimento obtida por dois passos temporais diferentes é a mesma. Como também os
dois passo temporais produzem o mesmo resultado para o tempo final considerado
𝑡 = 300.0000 s 𝑥 = −149.881 m;
"""