clear;
close all;
clc;
%% a)
N = 10e5;
n = 5;
p_elem = 0.3;
defeitos = rand(n, N) < p_elem;
defeitos_sum = sum(rand(n, N) < p_elem);
x = 0:5;
P = zeros(size(x));
for i=x
    P(i+1) = sum(defeitos_sum == i)/N;
end
% i)
figure(1)
stem(x,P)
axis([-1 n+1 -0.01 0.5])
title("Função Massa de Probabilidade de X")
xlabel("x")
ylabel("P(x)")

% ii)
figure(2)
P2 = [0 0 P 0 0];
acum = cumsum(P2);
x_acum = -1:8;
stairs(x_acum, acum);
axis([-1 6 -0.01 1.1])
title("Função Distribuição de Probabilidade de X")
xlabel("x")
ylabel("F(x)")

% iii)
probIII = sum(defeitos_sum <= 2)/N;


%% b)
% i)
px = zeros(size(x));
for i = x
    px(i+1)= nchoosek(n,i)*p_elem^i*(1-p_elem)^(n-i);
end
px2 = [0 0 px 0 0];
acum_px = cumsum(px2);
figure(3)
stairs(x_acum,acum_px);
axis([-1 6 -0.01 1.1])
title("Função Distribuição de Probabilidade de X (teórica)")
xlabel("x")
ylabel("F(x)")


% ii)
probII = px(1) + px(2) + px(3);