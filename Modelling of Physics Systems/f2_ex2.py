import matplotlib.pyplot as plt
import numpy as np
import sympy as sym

""" Cálculo da Primeira Derivada de yt
t = sym.Symbol("t", real=True, positive=True)
x = sym.Symbol("x", real=True, positive=True)
vt = sym.Symbol("vt", real=True, positive=True)
g = sym.Symbol("g", real=True, positive=True)
f = sym.Symbol("'f", real=True, positive=True)
D = sym.Symbol("D", real=True, positive=True)

D = sym.Derivative(vt**2/g*sym.log(sym.cosh(g/vt*t)), t, evaluate=True) # Derivada de yt
print("d/dt(log(cosh(gt))=", sym.simplify(D))
"""

""" Cálculo da Primeira Derivada de yvt
t = sym.Symbol("t", real=True, positive=True)
vt = sym.Symbol("vt", real=True, positive=True)
g = sym.Symbol("g", real=True, positive=True)
D = sym.Symbol("D", real=True, positive=True)

D = sym.Derivative(vt*sym.tanh(g*t/vt), t, evaluate=True)
print("d/dt(vt*tanh(g*t/vt))=", sym.simplify(D))
"""

# a)
g = 9.8 # acelaração gravítica
vT = 6.80 # velocidade terminal
tempo = np.linspace(0,4,1000) # criação de um vetor do tempo
yt = ((vT**2)/g)*np.log(np.cosh((g*tempo)/vT)) # movimento do volante

fig, ax = plt.subplots(1)
ax.set_xlabel( 'tempo (s)' )
ax.set_ylabel( 'x (m)' )
ax.plot(tempo, yt, label="Volante")
ax.set_title('Movimento de um volante')
plt.legend()
plt.grid()
plt.show()

# b)
yvt = vT*np.tanh(g*tempo/vT)  # Velocidade Instantânea
fig, ax = plt.subplots(1)
ax.set_xlabel("tempo (s)")
ax.set_ylabel("x(m)")
ax.plot(tempo, yvt, label="Volante")
ax.set_title('Velocidade de um volante')
plt.legend()
plt.grid()
plt.show()


# c)
yat = g/np.cosh(g*tempo/vT)**2 # Aceleração Instantânea
fig, ax = plt.subplots(1)
ax.set_xlabel("tempo (s)")
ax.set_ylabel("x(m)")
ax.plot(tempo, yat, label="Volante")
ax.set_title('Aceleração de um volante')
plt.legend()
plt.grid()
plt.show()