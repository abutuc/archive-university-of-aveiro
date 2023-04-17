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

dt = 0.0001

tf = 2
t0 = 0

n = np.int((tf-t0)/dt)

t = np.linspace(t0, tf, n)

x = np.empty(n)
vx = np.empty(n)
ax = np.empty(n)

y = np.empty(n)
ay= np.empty(n)
vy = np.empty(n)


z = np.empty(n)
az = np.empty(n)
vz = np.empty(n)

v0 = 36.11

x0 = -10
vx0 = v0 * np.cos(np.radians(10))
ax0 = 0

vT = 27.78
g = 9.80

y0 = 1
vy0 = v0 * np.sin(np.radians(10))
ay0 = 0

D = g / (vT**2)

x[0] = x0
vx[0] = vx0

y[0] = y0
vy[0] = vy0

r = 0.067/2
A = np.pi * r**2
massa = 0.057
den = 1.225
omega = -100
mag = (0.5*den*A*r)/massa




for i in range(n-1):
    vv=np.sqrt(vx[i]**2+vy[i]**2 + vz[i]**2)
    amx = mag*omega*vy[i]
    amy = -mag*omega*vx[i]
    ax[i] = -D*vv*vx[i] + amx
    ay[i] = -D*vv*vy[i] - g + amy
    az[i] = 0

    vx[i+1] = vx[i] + ax[i]*dt
    vy[i+1] = vy[i] + ay[i]*dt
    vz[i+1] = vz[i] + az[i]*dt

    x[i+1] = x[i] + vx[i]*dt
    y[i+1] = y[i] + vy[i]*dt
    z[i+1] = z[i] + vz[i]*dt

    if i>1 and y[i-1] < y[i] and  y[i+1] < y[i]  :
        print('sucess',i, y[i-1], y[i], y[i+1])
        maxx, maxy=maximo(x[i-1], x[i], x[i+1], y[i-1], y[i], y[i+1])
        print('maximo=',maxx,maxy)
    if i>1 and y[i-1]*y[i] < 0 :   # condição de passagem de y positivo para y negativo
        print('sucess solo:',i, t[i], ' alcance = ',x[i],'solo = ',y[i])
        zerxx, zeryy=zerosv(x[i-1], x[i], x[i+1], y[i-1], y[i], y[i+1])
        print('alcance =',zerxx,zeryy)


plt.plot(x, y)
plt.xlabel("x(m)")
plt.ylabel("y(m)")
plt.title("Movimento de x/y com rotação W(0,0,-100)")
plt.grid()
plt.show()