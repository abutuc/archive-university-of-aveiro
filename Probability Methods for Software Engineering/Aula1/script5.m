clear;
close all;
clc;

%% Cálculo analítico de probabilidade em séries experiências de Bernoulli
p = 0.5;
k = 6;
n = 15;
prob= ProbTeorica(k,n,p); % nchoosek(n,k)= n!/(n-k)!/k!