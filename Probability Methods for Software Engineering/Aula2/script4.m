clear;
close all;
clc;

N = 1e4;
m = 365;
ns = 1:60;
n = 0;
p = 0;
probs = zeros(60);
while (n < 60)
    n = n + 1;
    amost = dardos(n, m, N);  
    nao_diff = zeros(1,N);
    for col=1:N
        nao_diff(col) = length(unique(amost(:,col))) <= n-1; 
    end
    cfav = sum(nao_diff);
    p = cfav/N;
    probs(n) = p;
end

figure(1)
plot(probs)
aux = ns(probs>0.99);
resposta = aux(1)