import matplotlib.pyplot as plt
import numpy as np

dt = 0.001
tf = 1
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n+1)



vx = np.empty(n+1)
vy = np.empty(n+1)
v = np.empty(n+1)
x = np.empty(n+1)
y = np.empty(n+1)
ax = np.empty(n+1)
ay = np.empty(n+1)
eC = np.empty(n+1)
eP = np.empty(n+1)
eM = np.empty(n+1)
fun = np.zeros(n+1)
int_trab_res = np.zeros(n+1)


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


for i in range(n):
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


    fun[i] = m * ax[i]*vx[i] + m*ay[i]*vy[i]
    int_trab_res[i] = dt*((fun[0]+fun[n])*0.5+np.sum(fun[1:n-1])) # integração

fun[i+1] = m * ax[i+1]*vx[i+1] + m*ay[i+1]*vy[i+1]
int_trab_res[i+1] = dt*((fun[0]+fun[n])*0.5+np.sum(fun[1:n-1])) # integração


plt.plot(t, int_trab_res)
plt.xlabel("t(s)")
plt.ylabel("W(J)")
plt.show()


print("t = 0")
for i in range(n):
    if t[i] > -0.001 and t[i+1] < 0.002:
        print(t[i+1], int_trab_res[i+1])
print("t = 0.4")
for f in range(n):
    if t[f] == 0.4:
        print(t[f], int_trab_res[f])

print("t = 0.8")
for s in range(n):
    if t[s] == 0.8:
        print(t[s], int_trab_res[s])
