import matplotlib.pyplot as plt
import numpy as np


dt = 0.1
tf = 100
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)

x = np.empty(n)
v = np.empty(n)
a = np.empty(n)


x0 = 4
v0 = 0
w = 1

x[0] = x0
v[0] = v0


for i in range(n-1):
    a[i] = -w**2 * x[i]
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt

plt.plot(v, x, "-")
plt.xlabel("v(m/s)")
plt.ylabel("x(m)")
plt.title("Mola, v/t")
plt.show()

plt.plot(t, x, "-")
plt.xlabel("t(s)")
plt.ylabel("x(m)")
plt.title("Mola, x/t")
plt.show()


plt.plot(t, v, "-")
plt.xlabel("t(s)")
plt.ylabel("v(m/s)")
plt.title("Mola, v/t")
plt.show()