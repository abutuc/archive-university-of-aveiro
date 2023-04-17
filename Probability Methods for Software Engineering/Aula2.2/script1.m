clear;
close all;
clc;

%% a)

x = 1:6; % Valores Possiveis
P = ones(size(x))/6;
figure(1)
stem(x,P)
axis([0 7 -0.01 0.2])
title("Função Massa de Probabilidade de X")
xlabel("x")
ylabel("P(x)")
%% b
P2 = [0 0 P 0 0];
x2 = -1:8;
acum = cumsum(P2);
figure(2)
stairs(x2,acum);
axis([-1 8 -0.01 1.1])
title("Função Distribuição de Probabilidade de X")
xlabel("x")
ylabel("P(x)")