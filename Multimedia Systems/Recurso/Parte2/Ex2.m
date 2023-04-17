clear
close all
clc

Ta = 0.1;
t = 0:Ta:2;
y = cos(4*pi*t) + cos(16*pi*t - pi/3);
Espetro(y, Ta, 0);