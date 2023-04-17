function pr = ksucessos(k, n, p, N)
% Estima a probabilidade por simulação.
    lancamentos = rand(n,N) < p;
    sucessos = sum(lancamentos) == k;
    pr = sum(sucessos)/N;