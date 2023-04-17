# -*- coding: utf-8 -*-
"""
Created on Wed Apr 21 17:00:09 2021

@author: cdazevedo
"""

#a)

import numpy as np
import matplotlib.pyplot as plt



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










dt=0.00001
tf=1.00
n=np.int(tf/dt+0.1)

t=np.linspace(0,tf,n)


g=9.80      # m/s**2
vt=100*1000/3600  # m/s
vel0=100*1000/3600  # m/s
theta=10*np.pi/180  # rad
v0x=vel0*np.cos(theta)
v0y=vel0*np.sin(theta)
x0=0
y0=0


vy=np.empty(n)
y=np.empty(n)
vx=np.empty(n)
x=np.empty(n)
ax=np.empty(n)
ay=np.empty(n)
vx[0]=v0x
vy[0]=v0y
x[0]=x0
y[0]=y0

D=g/vt**2

for i in range(0,n-1):
    vv=np.sqrt(vx[i]**2+vy[i]**2)
    ax[i]=-D*vv*vx[i]
    ay[i]=-D*vv*vy[i]-g
    vx[i+1]=vx[i]+ax[i]*dt
    vy[i+1]=vy[i]+ay[i]*dt
    x[i+1]=x[i]+vx[i]*dt
    y[i+1]=y[i]+vy[i]*dt
    if i>1 and y[i-1] < y[i] and  y[i+1] < y[i]  :
        print('sucess',i, y[i-1], y[i], y[i+1])
        maxx, maxy=maximo(x[i-1], x[i], x[i+1], y[i-1], y[i], y[i+1])
        print('maximo=',maxx,maxy)
    if i>1 and y[i-1]*y[i] < 0 :   # condição de passagem de y positivo para y negativo
        print('sucess solo:',i, t[i], ' alcance = ',x[i],'solo = ',y[i])
        zerxx, zeryy=zerosv(x[i-1], x[i], x[i+1], y[i-1], y[i], y[i+1])
        print('alcance =',zerxx,zeryy)

    

xs=x0+v0x*t
ys=y0+v0y*t-0.5*g*t**2

fig, ax1 = plt.subplots(1)
ax1.set_xlabel( 'x(t) (m)' )
ax1.set_ylabel( 'y(t) (m)' )
ax1.plot(xs,ys,label='sem resistência')
ax1.plot(x,y,'-',label='com resistência')
plt.legend()
plt.show()
