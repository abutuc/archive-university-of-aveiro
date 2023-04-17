import matplotlib.pyplot as plt
import numpy as np

def abfourier(tp,xp,it0,it1,nf):
#
# cálculo dos coeficientes de Fourier a_nf e b_nf
#       a_nf = 2/T integral ( xp cos( nf w) ) dt   entre tp(it0) e tp(it1)
#       b_nf = 2/T integral ( xp sin( nf w) ) dt   entre tp(it0) e tp(it1)    
# integracao numerica pela aproximação trapezoidal
# input: matrizes tempo tp   (abcissas)
#                 posição xp (ordenadas) 
#       indices inicial it0
#               final   it1  (ao fim de um período)   
#       nf índice de Fourier
# output: af_bf e bf_nf  
# 
    dt=tp[1]-tp[0]
    per=tp[it1]-tp[it0]
    ome=2*np.pi/per

    s1=xp[it0]*np.cos(nf*ome*tp[it0])
    s2=xp[it1]*np.cos(nf*ome*tp[it1])
    st=xp[it0+1:it1]*np.cos(nf*ome*tp[it0+1:it1])
    soma=np.sum(st)
    
    q1=xp[it0]*np.sin(nf*ome*tp[it0])
    q2=xp[it1]*np.sin(nf*ome*tp[it1])
    qt=xp[it0+1:it1]*np.sin(nf*ome*tp[it0+1:it1])
    somq=np.sum(qt)
    
    intega=((s1+s2)/2+soma)*dt
    af=2/per*intega
    integq=((q1+q2)/2+somq)*dt
    bf=2/per*integq
    return af,bf


def acelera(t,x,vx):
    k = 1.0
    m=1.0
    alfa=0.002
    b=0.05
    f0 = 7.5
    omef=1.0
    ax=-k/m*x-b/m*vx+f0/m*np.cos(omef*t)
    en = 0.5*m*vx**2+0.5*k*x**2*(1+alfa*x**2)
    return ax, en

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

dt = 0.001
tf = 400
t0 = 0

n = np.int((tf-t0) / dt)

t = np.linspace(t0, tf, n)

x = np.empty(n)
v = np.empty(n)
a = np.empty(n)
eM = np.empty(n)

x0 = -2
v0 = -4


m = 1
k = 1
b = 0.05
f0 = 7.5
wf = 1
x[0] = x0
v[0] = v0
ampl = 0

# 15 é um número arbitrário e calcula as 15 primeiras frequências.
af0 = np.zeros(15)
bf0 = np.zeros(15)

ind = [0 for i in range(1000)]
countMaximos = 0
for i in range(n-1):
    a[i], eM[i] = acelera(t[i], x[i], v[i])
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt

    if t[i]>200 and x[i-1] < x[i] and  x[i+1] < x[i]:
        maxx, maxy=maximo(t[i-1], t[i], t[i+1], x[i-1], x[i], x[i+1])
        ampl = ampl + maxy
        countMaximos += 1 
        ind[countMaximos] = int(i) # tem de ser um int

t0 = ind[countMaximos-1]
t1 = ind[countMaximos]

for i in range(15):
    af, bf = abfourier(t, x, t0, t1, i)
    af0[i] = af
    bf0[i] = bf

    
li = np.linspace(0,14,15)
plt.figure()
plt.ylabel(" | b n |")
plt.xlabel("n")
plt.bar(li, np.abs(bf0))
plt.grid()
plt.show()

li = np.linspace(0,14,15)
plt.figure()
plt.ylabel(" | a n |")
plt.xlabel("n")
plt.bar(li, np.abs(af0))
plt.grid()
plt.show()