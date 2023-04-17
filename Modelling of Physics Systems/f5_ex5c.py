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
ax = np.empty(n)
ay = np.empty(n)
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
vT = 27.78
D = g / (vT**2)
ax0 = 0
ay0 = 0


v[0] = v0
vx[0] = vx0
vy[0] = vy0
x[0] = x0
y[0] = y0
ax[0] = ax0
ay[0] = ay0
eC[0] = eC0
eP[0] = eP0
eM[0] = eM0


for i in range(n-1):
    vv=np.sqrt(vx[i]**2+vy[i]**2)
    
    ax[i] = -D*vv*vx[i]
    ay[i] = -D*vv*vy[i] - g 

    vx[i+1] = vx[i] + ax[i]*dt
    vy[i+1] = vy[i] + ay[i]*dt

    x[i+1] = x[i] + vx[i]*dt
    y[i+1] = y[i] + vy[i]*dt

    eC[i] = (1/2)*m*vv**2
    eP[i] = m*g*y[i]
    eM[i] = eC[i] + eP[i]


"""
fig, axs = plt.subplots(2)
axs[0].plot(t, eM, '-')
axs[1].plot(x, y, '-')
plt.show()
"""

print("t = 0")
for i in range(n-1):
    if t[i] > -0.001 and t[i+1] < 0.002:
        eMR0 = eM[i+1]
        print(t[i+1], eM[i+1])
print("t = 0.4")
for f in range(n-1):
    if t[f] > 0.399 and t[f+1] < 0.401:
        eM4 = eM[f+1]
        print(t[f+1], eM4)

print("t = 0.8")
for s in range(n-1):
    if t[s] > 0.799 and t[s+1] < 0.801:
        eM8 = eM[s+1]
        print(t[s+1], eM8)


print("0 : Fres = ", eMR0-eM0)
print("0.4 : Fres = ", eM4-eM0)
print("0.8 : Fres = ", eM8-eM0)
