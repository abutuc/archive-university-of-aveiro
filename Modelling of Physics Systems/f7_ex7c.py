import matplotlib.pyplot as plt
import numpy as np


dt = 0.001
tf = 100
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)

x = np.empty(n)
v = np.empty(n)
a = np.empty(n)
eM = np.empty(n)


x0 = 4
v0 = 0
w = 1

x[0] = x0
v[0] = v0


for i in range(n-1):
    a[i] = -w**2 * x[i]
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt
    eM[i] = (1/2)*x[i]**2 + (1/2)*v[i]**2

eM[i+1] = (1/2)*x[i+1]**2 + (1/2)*v[i+1]**2

plt.plot(t, eM, "-")
plt.xlabel("t(s)")
plt.ylabel("Em(J)")
plt.ylim(0,16)
plt.show()
