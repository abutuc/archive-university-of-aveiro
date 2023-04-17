%% Clear
clear;
close all;
clc;

%% 

Ta = 0.01;
t = 0:Ta:10-Ta;
x = sin(2*pi*t)+sin(2*pi*t)+sin(2*pi*t);
N = length(x); 
k = 2;
T0 = N*Ta;
F0 = 1/T0;
w0 = 2*pi*F0;
[coef, integral_sign, integral] = CoefFourier(x, Ta,k);
plot(t, integral_sign);

%%
cmap_a = colormap("gray");
figure(1)
mesh(cmap_a); 
view(2);
figure(2)
cmap_b = colormap("cool");
mesh(cmap_b); 
view(2);

prod = ImagemProduto(cmap_a, cmap_b);