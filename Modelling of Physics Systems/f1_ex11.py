import matplotlib.pyplot as plt
import numpy as np
import cheats as ch
import math


fh = open("f1_ex11.txt", "r")
trial1 = []
trial2 = []
trial3 = []
trial4 = []
for line in fh:
    r_content = line.strip().split(" ")
    l_content = [float(elem) for elem in r_content]
    trial1.append((l_content[1], l_content[0]))
    trial2.append((l_content[2], l_content[0]))
    trial3.append((l_content[3], l_content[0]))
    trial4.append((l_content[4], l_content[0]))
    
fh.close()

# a) Não é linear.
t1_x = [coord[0] for coord in trial1]
t1_y = [coord[1] for coord in trial1]

plt.plot(t1_x, t1_y, "o", label= "Trial1")

t2_x = [coord[0] for coord in trial2]
t2_y = [coord[1] for coord in trial2]

plt.plot(t2_x, t2_y, 'o', label="Trial2")


t3_x = [coord[0] for coord in trial3]
t3_y = [coord[1] for coord in trial3]

plt.plot(t3_x, t3_y, 'o', label="Trial3")


t4_x = [coord[0] for coord in trial4]
t4_y = [coord[1] for coord in trial4]

plt.plot(t4_x, t4_y, 'o', label="Trial4")
plt.show()

# b) 

y = np.polyfit(t2_x, t2_y, 2)
print(y)
xmax = np.max(t2_x)* 1.1
xmin = np.min(t2_x) * 1.1
x1 = np.linspace(xmin,xmax, 1000)


plt.plot(t2_x, t2_y, 'o', x1, y)
plt.show()