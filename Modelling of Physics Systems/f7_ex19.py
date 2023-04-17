import matplotlib.pyplot as plt
import numpy as np


def rk4(t,x,vx,acelera,dt):
    """
    Integração numérica de equação diferencial de 2ª ordem respeitante ao movimento
    acelera=dvx/dt=Força(t,x,vx)/massa      com vx=dx/dt   (acelera é uma função)
    input:  t = instante de tempo
            x(t) = posição
            vx(t) = velocidade
            dt = passo temporal 
    output: x(t+dt),vx(t+dt)
    """
    ax1=acelera(t,x,vx)
    c1v=ax1*dt
    c1x=vx*dt
    ax2=acelera(t+dt/2.,x+c1x/2.,vx+c1v/2.)
    c2v=ax2*dt
    c2x=(vx+c1v/2.)*dt			# predicto:  vx(t+dt) * dt
    ax3=acelera(t+dt/2.,x+c2x/2.,vx+c2v/2.)
    c3v=ax3*dt
    c3x=(vx+c2v/2.)*dt
    ax4=acelera(t+dt,x+c3x,vx+c3v)
    c4v=ax4*dt
    c4x=(vx+c3v)*dt
      
    xp=x+(c1x+2.*c2x+2.*c3x+c4x)/6.
    vxp=vx+(c1v+2.*c2v+2.*c3v+c4v)/6.
    return xp,vxp


def acelera(t,x,vx):
    g = 9.80 
    vt = 6.80
    ay = g - (g/vt**2)*np.abs(vx)*vx
    return ay



dt = 0.1
tf = 2
t0 = 0

n = np.int((tf-t0) / dt) + 1


t = np.linspace(t0, tf, n)

y = np.empty(n)
vy = np.empty(n)

y0 = 0
v0 = 0
tem0 = t[0]

y[0] = y0
vy[0] = v0

xet = y0
vye = v0
tem = tem0

for i in range(n-1):
    xet, vye = rk4(i,xet, vye, acelera, dt)
    tem = tem + dt
    t[i+1] = tem
    vy[i+1] = vye
    y[i+1] = xet


plt.plot(t, vy, "-")
plt.xlabel("t(s)")
plt.ylabel("vy(m/s)")
plt.grid()
plt.show()

print(vy[-1])