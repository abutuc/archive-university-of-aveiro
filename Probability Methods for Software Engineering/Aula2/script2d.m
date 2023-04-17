clear;
close all;
clc;

N = 100;
n = 20;
i = 1;
p = zeros(1, 9);
for m = [200 500 1000 2000 5000 10000 20000 50000 100000]
    ls = randi([1 m], n, N); % max valor int, n linhas, n colunas
    
    nao_diff = zeros(1,N);
    for col=1:N
        nao_diff(col) = length(unique(ls(:,col))) <= n-1; 
    end
    
    cfav2 = sum(nao_diff);
    p(i) = cfav2/N;
    i = i + 1;
end

plot(1:9, p)