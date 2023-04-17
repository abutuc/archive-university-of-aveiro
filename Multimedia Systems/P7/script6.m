%% Clear
clear;
close all;
clc;
%%
load Guitar03.mat
Espetro(x, 1/fa, false, 3); 
N = length(x);
t = (0:N-1)'* (1/fa);
yc = Fonseca_Reverb(x, fa, 1, 0.2);
figure(1)
hold on
plot(t, x);
plot(t, yc);
hold off;
sound(yc, fa);
Espetro(yc, 1/fa, false, 4);