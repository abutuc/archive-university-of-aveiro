clear;
close all;
clc;


%% Código 1 - versão 1
experiencias = rand(15,10000);
lancamentos = experiencias < 0.5;
resultados = sum(lancamentos);
sucessos = resultados >= 6;
probSimulacao1 = sum(sucessos)/10000;
%% Código 1 - versão 2
N = 1e5; % numero de vezes que repete-se a experiência
p = 0.5;  % probabilidade de sucesso
k = 6;  % numero de sucesso
n = 15; % numero de casos possiveis
lancamentos = rand(n,N) < p;
sucessos = sum(lancamentos) >= k;
probSimulacao2 = sum(sucessos)/N;