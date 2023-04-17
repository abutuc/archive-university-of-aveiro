import matplotlib.pyplot as plt
import numpy as np


dt = 0.0001
tf = 4
t0 = 0

n= np.int((tf-t0)/dt)

t = np.linspace(t0, tf, n)

v = np.zeros(n)
y = np.zeros(n)
a = np.zeros(n)

g = 9.80
vT = 27.8
d = g/((vT)**2)
v0 = 10
y0 = 0

v[0] = v0
y[0] = y0
for i in range(n-1):
    a[i] = -g - d*abs(v[i])*v[i]
    v[i+1] = v[i] + a[i]*h
    y[i+1] = y[i] + v[i]*h

print(np.where(y == max(y)))
#plt.plot(t, y)
#plt.plot(t, v)
#plt.plot(t, a)
#plt.show()
