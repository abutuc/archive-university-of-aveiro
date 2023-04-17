clear;
close all;
clc;

%% Ex2 a)
N = 100;
n = 20;
m = 100;

ls = randi([1 m], n, N); % max valor int, n linhas, n colunas
todos_diff = zeros(1,N);
for col=1:N
    todos_diff(col) = length(unique(ls(:,col))) == n; 
end

cfav = sum(todos_diff);
p = cfav / N;

%% Ex2 b)

nao_diff = zeros(1,N);
for col=1:N
    nao_diff(col) = length(unique(ls(:,col))) <= n-1; 
end

cfav2 = sum(nao_diff);
p2 = cfav2/N;

%% Ex2 c)
N = 1e5;
m = 1000;
v = 1:10:200;

for i=1:length(v)
    n = v(i);
    ls = randi([1 m], n, N); % max valor int, n linhas, n colunas
    todos_diff = zeros(1,N);
    for col=1:N
        todos_diff(col) = length(unique(ls(:,col))) == n; 
    end
    
    cfav = sum(todos_diff);
    p(i) = cfav / N;
end

figure(1)
plot(v,p, 'r:*')