import matplotlib.pyplot as plt
import numpy as np

dt = 0.001
tf = 1
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)



vx = np.empty(n)
vy = np.empty(n)
v = np.empty(n)
x = np.empty(n)
y = np.empty(n)
eC = np.empty(n)
eP = np.empty(n)
eM = np.empty(n)

v0 = 27.78

vx0 = np.cos(np.radians(10)) * v0
vy0 = np.sin(np.radians(10)) * v0
x0 = 0
y0 = 0
g = 9.8
m = 57/1000
eC0 = (1/2)*m*(v0**2)
eP0 = 0
eM0 = eC0 + eP0


v[0] = v0
vx[0] = vx0
vy[0] = vy0
x[0] = x0
y[0] = y0
eC[0] = eC0
eP[0] = eP0
eM[0] = eM0


for i in range(n-1):
    v[i+1] = np.sqrt(vx[i]**2 + vy[i]**2)
    vx[i+1] = vx[i]
    vy[i+1] = vy[i] - g*dt
    x[i+1] = x[i] + vx[i]*dt
    y[i+1] = y[i] + vy[i]*dt
    eC[i] = (1/2)*m*v[i]**2
    eP[i] = m*g*y[i]
    eM[i] = eC[i] + eP[i]

plt.plot(t, eM, '-')
plt.title("Em da bola em função do tempo sem resistência")
plt.xlabel("t(s)")
plt.ylabel("Em(J)")
plt.grid()
plt.show()

plt.plot(x, y, '-')
plt.title("Trajetória da bola sem resistência")
plt.xlabel("X(m))")
plt.ylabel("Y(m)")
plt.grid()
plt.show()