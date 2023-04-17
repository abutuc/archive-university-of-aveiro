clear;
close all;
clc;

%% a) Por simulação
% N = 1e5;
% n = 8000;
% p_sucesso = 1/1000;
% defeitos = rand(n, N) < p_sucesso;
% defeitos_sum = sum(defeitos);
% p = sum(defeitos_sum==7)/N;

%% a) Valor Teórico
n = 8000;
k = 7;
p = 1/1000;

px = nchoosek(n, k)*(p^k)*(1-p)^(n-k);


%% b) Valor Teórico

k = 7;
lambda = n*p;

pk = (lambda^k / factorial(k)) * exp(-lambda);
