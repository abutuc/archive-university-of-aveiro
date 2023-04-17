clear;
close all;
clc;
N = 1e6;
p = 0.3;  % probabilidade de sucesso
k = 3;  % numero de sucesso
n = 5; % numero de casos possiveis

prob_anal = ProbTeorica(k, n, p);

prob_simu = ksucessos(k,n,p,N);
