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

def zerosv(xm1,xm2,xm3,ym1,ym2,ym3):  # raiz pelo polinómio de Lagrange
    xab=xm1-xm2
    xac=xm1-xm3
    xbc=xm2-xm3

    a=ym1/(xab*xac)
    b=-ym2/(xab*xbc)
    c=ym3/(xac*xbc)

    am=a+b+c
    bm=a*(xm2+xm3)+b*(xm1+xm3)+c*(xm1+xm2)
    cm=a*xm2*xm3+b*xm1*xm3+c*xm1*xm2

    xzero=(bm+np.sqrt(bm*bm-4*am*cm))/(2*am)
    if xm3 > xm1 and (xzero < xm1 or xzero > xm3): 
        xzero=(bm-np.sqrt(bm*bm-4*am*cm))/(2*am)


    if xm1 > xm3 and (xzero < xm3 or xzero > xm1):
        xzero=(bm-np.sqrt(bm*bm-4*am*cm))/(2*am)

    xta=xzero-xm1
    xtb=xzero-xm2
    xtc=xzero-xm3
    yzero=a*xtb*xtc+b*xta*xtc+c*xta*xtb
    return xzero, yzero

dt = 0.001
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

countMaximos = 0
maxTotal = 0

maxTMax = []
deltas = []
for i in range(n-1):
    a[i] = -w**2 * x[i]
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt

    if i>1 and x[i-1] < x[i] and  x[i+1] < x[i]:
        #print('sucess',i, x[i-1], x[i], x[i+1])
        maxx, maxy=maximo(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1])
        maxTotal += maxy
        countMaximos += 1
        maxTMax.append(maxx)
        #print('maximo=',maxx,maxy)

    """
    if x[i] > x[i+1] and x[i-1] < x[i]:
        maxt.append(i)
        print(x[i])
        """

print("Amplitude: ", maxTotal/countMaximos)

for d in range(len(maxTMax)-1):
    deltas.append(maxTMax[d+1] - maxTMax[d])


print("Período: ", sum(deltas)/len(deltas))
# print((maxt[1] - maxt[0]) * dt)
"""
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
"""