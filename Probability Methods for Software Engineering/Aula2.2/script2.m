clear;
close all;
clc;

%% a)
% 90 notas de 5€, 9 notas de 50€, 1 nota de 100€
% Espaço de Amostragem consiste no espaço de amostragem {n1, n2, ... n100}
% A probabilidade elementar é 1/100;
n = 100;
Aamost = 1:n;
P = ones(size(Aamost))/n;
figure(1)
stem(Aamost,P)
axis([0 n -0.01 0.015])
title("Função Massa de Probabilidade de X")
xlabel("Aamost")
ylabel("P(Aamost)")

P2 = [0 0 P 0 0];
Aamost2 = -1:n+2;
Aacum = cumsum(P2);
figure(2)
stairs(Aamost2,Aacum)
axis([-1 n+1 -0.01 1.1])
title("Função Distribuição de Probabilidade de X")
xlabel("Aamost2")
ylabel("P(Aamost2)")


%% b)
% Amost = {5, 50, 100}
% Acontecimento A: Retirar nota 5€; 
% Acontecimento B: Retirar nota 50€ 
% Acontecimento C: Retirar nota 100€
% Total de Notas: 90+9+1=100 notas.
% P(A) = 90/100 = 9/10;
% P(B) = 9/100;
% P(C) = 1/100;

Bamost = [5 50 100];
P = [90 9 1]/100;
figure(3)
stem(Bamost,P)
axis([0 100 -0.01 1])
title("Função Massa de Probabilidade de X")
xlabel("Bamost")
ylabel("P(Bamost)")

P2 = [0 0 P 0 0];
Bamost2 = -1:5;
Bacum = cumsum(P2);
figure(4)
stairs(Bamost2, Bacum)
axis([-1 5 -0.01 1.1])
title("Função Distribuição de Probabilidade de X")
xlabel("Bamost2")
ylabel("P(Bamost2)")


