clear;
close all;
clc;

%% Alínea a)
N = 10000;
n = 10;
m = 1000;

ls = randi([0 m-1], n, N); % max valor int, n linhas, n colunas
nao_diff = zeros(1,N);
for col=1:N
    nao_diff(col) = length(unique(ls(:,col))) <= n-1; 
end

cfav1 = sum(nao_diff);
p1 = cfav1/N;

%% Alínea b)
p = zeros(size(10:1:150));
i = 1;
for k=10:1:150
    l = randi([0 m-1], k, N); % max valor int, n linhas, n colunas
    nao_diff = zeros(1,N);
    for col=1:N
        nao_diff(col) = length(unique(l(:,col))) <= k-1; 
    end

    cfav = sum(nao_diff);
    p(i) = cfav/N;
    i = i +1;
end

plot(10:1:150, p);