import matplotlib.pyplot as plt
import numpy as np
import math as mt


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


dt = 0.0001
tf = 400
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)

x = np.empty(n)
v = np.empty(n)
a = np.empty(n)

x0 = 4
v0 = 0



m = 1
k = 1
b = 0.05
f0 = 7.5
wf = 1

x[0] = x0
v[0] = v0

countMaximos = 0
maxTotal = 0

maxTMax = []
deltas = []
for i in range(n-1):
    a[i] = (1/m)*(-k*x[i] - b*v[i] + f0*mt.cos(wf*t[i]))
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt

    if t[i]>390 and x[i-1] < x[i] and  x[i+1] < x[i]:
        #print('sucess',i, x[i-1], x[i], x[i+1])
        maxx, maxy=maximo(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1])
        maxTotal += maxy
        countMaximos += 1
        maxTMax.append(maxx)



print("Amplitude: ", maxTotal/countMaximos)

for d in range(len(maxTMax)-1):
    deltas.append(maxTMax[d+1] - maxTMax[d])


print("Período: ", sum(deltas)/len(deltas))
"""
plt.plot(t, x, "-")
plt.xlabel("t(s)")
plt.ylabel("x(m)")
plt.title("Oscilador Harmónico Forçado Met Eul")
plt.show()
"""

""" OUTPUT

Amplitude:  149.99216752883436
Período:  6.283183681901164

"""

