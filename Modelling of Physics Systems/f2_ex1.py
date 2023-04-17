import matplotlib.pyplot as plt
import numpy as np

# a)

ap = 2 # acelaração do carro patrulha
vx0a = 70 # velocidade inicial do carro A
vx0a = vx0a/3.6 # conversão da velocidade para m/s
vx0p = 0
tempo = np.linspace(0,30,1000) # criação de um vetor do tempo
xa = vx0a*tempo # movimento uniforme carro A
xp = 0.5*ap*(tempo**2) # movimento uniforme acelerado carro patrulha

fig, ax = plt.subplots(1)
ax.set_xlabel( 'tempo (s)' )
ax.set_ylabel( 'x (m)' )
ax.plot(tempo, xa, label="Carro A")
ax.plot(tempo, xp, label="Carro Patrulha")
ax.set_title('Dois Carros')
plt.legend()
plt.grid()
plt.show()

# b)
"""

x = 378m ; t= 19s

"""