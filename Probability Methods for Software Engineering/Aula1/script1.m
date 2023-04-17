clear;
close all;
clc;

%% Código 1 - versão 1
experiencias = rand(3,10000);
lancamentos = experiencias < 0.5;
resultados = sum(lancamentos);
sucessos = resultados == 2;
probSimulacao1 = sum(sucessos)/10000;

%% Código 1 - versão 2

N = 1e5; % numero de vezes que repete-se a experiência
p = 0.5;  % probabilidade de sucesso
k = 2;  % numero de sucesso
n = 3; % numero de casos possiveis
lancamentos = rand(n,N) < p;
sucessos = sum(lancamentos) == k;
probSimulacao = sum(sucessos)/N;