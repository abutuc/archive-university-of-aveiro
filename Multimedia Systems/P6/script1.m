%% Clear
clear;
close all;
clc;

%% GeraSinal

N = 2000;
Ta = 0.01;
[x, t] = GeraSinal(N, Ta);

%% Espetro
[X, f] = Espetro(x, Ta, 0);

%% Filtro Sinal

H = (f<4) & (f>-4);
hold on;
plot(f, H, 'LineWidth', 2);
hold off;

%% Filtragem Sinal

Y = X.*H;
figure(2);
y = real(Reconstruct(Y, f));
plot(t, y);

%% Filtragem Sinal Ruido
Hr = 1-H;
R = X .* Hr;
figure(3);
r = real(Reconstruct(R, f));
plot(t,r);
% ** to be completed **


%% 

% SNR = Ps / Pr 
% SNR_db = 10*log10(SNR).

Ps = Potencia(y, Ta, N*Ta);
Pr = Potencia(r, Ta, N*Ta);

SNR = Ps/Pr;
SNR_db = 10*log10(SNR);