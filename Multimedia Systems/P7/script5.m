%% Clear
clear;
close all;
clc;
%%
load Guitar03.mat
Espetro(x, 1/fa, false, 3); 
[t,yc] = Flanger(x, fa, 0.02, 2);
figure(1)
plot(t, x);
figure(2)
plot(t, yc);
Espetro(yc, 1/fa, false, 4);