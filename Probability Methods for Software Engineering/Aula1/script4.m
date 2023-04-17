clear;
close all;
clc;

N = 1e5; % numero de vezes que repete-se a experiência
p = 0.5;  % probabilidade de sucesso
n = 100;
probs = zeros(1,20);
    
for k = 0:n
    probs(1,k+1) = ksucessos(k,n,p,N);
end

stem(0:n, probs)
