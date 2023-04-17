import matplotlib.pyplot as plt
import numpy as np
import cheats as ch


data = [(9.676, 0) , (6.355, 5), (4.261, 10), (2.729, 15), (1.862, 20), (1.184, 25), (0.7680, 30), (0.4883, 35), (0.3461, 40), (0.2119, 45)]
atividade = [coord[0] for coord in data]
tempo = [coord[1] for coord in data]

# a) Não é linear.
plt.plot(atividade, tempo, 'o')
plt.xlabel("Atividade (mCi)")
plt.ylabel("Tempo(dias)")
plt.show()


# b) É linear.
log_atividade = np.log(atividade)

atividade = log_atividade
new_data = []

for log_atividade1, tempo1 in zip(log_atividade, tempo):
    new_data.append((log_atividade1, tempo1))

m = ch.calc_m(new_data)
b = ch.calc_b(new_data)
r2 = ch.calc_r2(new_data)
deltaM = ch.calc_deltaM(m, r2, len(new_data))
deltaB = ch.calc_deltaB(deltaM, new_data)

print("m={}\tdeltaM={}\nb={}\tdeltaB={}\nr2={}".format(m, deltaM, b, deltaB, r2))

xmax = np.max(atividade)* 1.1
xmin = np.min(atividade) * 1.1
x1 = np.linspace(xmin,xmax, 1000)
y1 = m*x1+b

plt.plot(atividade, tempo, 'o', x1, y1)
plt.xlabel("Atividade (mCi)")
plt.ylabel("Tempo(dias)")
plt.show()