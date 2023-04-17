close all 
clc
clear

t =[0:0.01:10]';
x = sin(2*pi*(1+0.1.*t).*t);
plot(t, x);