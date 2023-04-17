clear;
close all;
clc;

keys = 50;
N = 10000;
t = 100:100:1000;
p = zeros(size(t));
i = 1;
for T = t
    ls = randi([0 T-1], keys, N); % max valor int, n linhas, n colunas
    todos_diff = zeros(1,N);
    for col=1:N
        todos_diff(col) = length(unique(ls(:,col))) == keys; 
    end

    cfav = sum(todos_diff);
    p(i) = cfav / N;
    i = i + 1;
end

plot(t, p)