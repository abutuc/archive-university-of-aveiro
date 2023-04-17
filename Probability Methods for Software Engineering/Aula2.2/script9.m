clear;
close all;
clc;

N = 1e6;
m=14;
desvio_padrao = 2;
variancia = desvio_padrao^2;

X = randn(1,N)*desvio_padrao+m;

% a)

sucessos1 = (X > 12 & X < 16);
sum_sucessos1 = sum(sucessos1);
prob1 = sum(sum_sucessos1)/N;

pt1 = normcdf(16,14,2) - normcdf(12,14,2);
% b)

sucessos2 = (X > 10 & X < 18);
sum_sucessos2 = sum(sucessos2);
prob2 = sum(sum_sucessos2)/N;
pt2 = normcdf(18,14,2) - normcdf(10,14,2);
% c)

sucessos3 = (X >= 10);
sum_sucessos3 = sum(sucessos3);
prob3 = sum(sum_sucessos3)/N;
pt3 = normcdf(20,14,2) - normcdf(10,14,2);


