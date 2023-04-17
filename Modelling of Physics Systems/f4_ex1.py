import matplotlib.pyplot as plt
import numpy as np

dt = 0.0001
tf = 1
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)

vx = np.empty(n)
vy = np.empty(n)
x = np.empty(n)
y = np.empty(n)
ax = np.empty(n)
ay = np.empty(n)

vx0 = 27.36
vy0 = 4.82
x0 = 0
y0 = 0
g = 9.8
vT = 27.78
D = g/(vT**2)


vx[0] = vx0
vy[0] = vy0
x[0] = x0
y[0] = y0
ax[0] = 0
ay[0] = 0

for i in range(n-1):
    vv = np.sqrt(vx[i]**2 + vy[i]**2)
    ax[i] = -D*vv*vx[i]
    ay[i] = -D*vv*vy[i] - g
    vx[i+1] = vx[i] + ax[i]*dt
    vy[i+1] = vy[i] + ay[i]*dt
    x[i+1] = x[i] + vx[i]*dt
    y[i+1] = y[i] + vy[i]*dt



plt.plot(x, y)
plt.grid()
plt.title("Versão de Euler")
plt.show()


"""
# Versão analítica

y_anal = vy0*t - (1/2)*g*t**2
x_anal = vx0*t
plt.plot(x_anal, y_anal)
plt.grid()
plt.title("Versão Analítica")
plt.show()
"""