%% Clear
clc
clear
close all

Ta = 0.01;

x1 = -5:Ta:5-Ta;
x2 = x1';

y = cos(2*pi*sqrt(x1.^2 + x2.^2));

Espetro2(y, 1, 1)
figure(2)

contourf(x1, x2, y);
xlabel("x1");
ylabel("x2");
