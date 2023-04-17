clear;
close all;
clc;
N = 1e6;
p = 0.3;  % probabilidade de sucesso
k = 0:5;  % numero de sucesso
n = 5; % numero de casos possiveis

prob_simu = zeros(1, length(k));


for i = k
    prob_simu(1,i+1) = ksucessos(i,n,p,N);
end

histogram(prob_simu)