import matplotlib.pyplot as plt
import numpy as np
# a) A aceleração instantânea é igual à derivada da velocidade instantânea em ordem ao tempo.

# b) & e)
dt = 0.05
tf = 4.0
t0 = 0
n= np.int((tf-t0)/dt)

t = np.linspace(t0, tf, n)

vy = np.zeros(n)
y = np.zeros(n)

g = 9.80
v0y = 0
y0 = 0

vy[0] = v0y

for i in range(n-1):
    vy[i+1] = vy[i] + g*dt
    y[i+1] = y[i] + vy[i]*dt
    if (t[i] > 2.99 and t[i+1] < 3.1):
        print(vy[i])


#for i in range(n-1):
 #   print(str(t[i]) + " " + str(vy[i]))
plt.plot(t, y)
plt.show()

# c)
dt = 0.1
tf = 4.0
t0 = 0
n= np.int((tf-t0)/dt)

t = np.linspace(t0, tf, n)

vy = np.zeros(n)
g = 9.80
v0y = 0
y0 = 0
vy[0] = v0y

for i in range(n-1):
    vy[i+1] = vy[i] - g*dt

plt.plot(t, vy)
plt.show()

# d) É igual.

# g)