clear;
close all;
clc;

%% a)

% 4 lançamentos, Duas possíbilidades= {1, 0}
N = 10e5;
n = 4;
p_elem = 0.5;
lancamentos = rand(n, N) < p_elem;
lancamentos_sum = sum(rand(n, N) < p_elem);
x = 0:4;
P = zeros(size(x));
for i=x
    P(i+1) = sum(lancamentos_sum == i)/N;
end

figure(1)
stem(x,P)
axis([-1 n+1 -0.01 0.5])
title("Função Massa de Probabilidade de X")
xlabel("x")
ylabel("P(x)")


%% b)

% E[X] = Sum (x_i * P(X=x_i))
EX = sum(x .* P);
% EX2 = x*P';
EX2 = sum(x.^2 .* P);
%var = EX[X^2] - EX[X]^2
VarX = EX2 - EX^2;

% Distribuição Binomial 

%% c)

% px(K) = Pr(X=K) = nchosek(n,k)p^k*(1-p)^(n-p)

px = zeros(size(x));
for i=x
    px(i+1)= nchoosek(n,i)*p_elem^i*(1-p_elem)^(n-i);
end
figure(2)
stem(x,px)
axis([-1 n+1 -0.01 0.5])
title("Função Massa de Probabilidade de X (teórica)")
xlabel("x")
ylabel("P(x)")


%% e)
EX_teorico = n * p_elem;
VarX_teorico = n * p_elem * (1-p_elem);


%% f
% i)
probI = px(3)+px(4)+px(5);
% ii)
probII = px(1)+px(2);
% iii)
probIII = px(2)+px(3)+px(4);

