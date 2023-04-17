import matplotlib.pyplot as plt
import numpy as np


def maximo(xm1,xm2,xm3,ym1,ym2,ym3):  # máximo pleo polinómio de Lagrange
    xab=xm1-xm2
    xac=xm1-xm3
    xbc=xm2-xm3

    a=ym1/(xab*xac)
    b=-ym2/(xab*xbc)
    c=ym3/(xac*xbc)

    xmla=(b+c)*xm1+(a+c)*xm2+(a+b)*xm3
    xmax=0.5*xmla/(a+b+c)

    xta=xmax-xm1
    xtb=xmax-xm2
    xtc=xmax-xm3

    ymax=a*xtb*xtc+b*xta*xtc+c*xta*xtb
    return xmax, ymax


def acelera(t,x,vx):
    k = 1.0
    m=1.0
    alfa=0.002
    b=0.05
    f0 = 7.5
    omef=1.0
    ax=-k/m*x-b/m*vx+f0/m*np.cos(omef*t)
    en = 0.5*m*vx**2+0.5*k*x**2
    return ax, en


dt = 0.0001
tf = 1200
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)

x = np.empty(n)
v = np.empty(n)
a = np.empty(n)
eM = np.empty(n)

x0 = 4
v0 = 0


m = 1
k = 1
b = 0.05
f0 = 7.5
wf = 1
x[0] = x0
v[0] = v0


for i in range(n-1):
    a[i], eM[i] = acelera(t[i], x[i], v[i])
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt
    
a[i+1], eM[i+1] = acelera(t[i+1], x[i+1], v[i+1])

plt.plot(t, eM, "-")
plt.title("Oscilador Harmónico Forçado Met Eul")
plt.xlabel("t(s)")
plt.ylabel("Em(J)")
plt.show()