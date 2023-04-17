import matplotlib.pyplot as plt
import numpy as np
import cheats as ch
import math as m
raw_data = [(6.37, 9.8), (7.02, 8.0), (7.61,6.6), (8.02, 6.3), (8.43, 5.5), (8.92, 5.1), (9.31, 4.6), (9.78, 4.1), (10.25, 3.8), (10.74, 3.6)]

pro_data = [(1/m.pow(coord[0],2), coord[1]) for coord in raw_data]
x = [coord[0] for coord in pro_data]
y = [coord[1] for coord in pro_data]

k = ch.calc_m(pro_data)
print(k)