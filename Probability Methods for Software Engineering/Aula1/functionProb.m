function pr = ksucessos(k, n, p, N)
    lancamentos = rand(n,N) > p;
    sucessos = sum(lancamentos) >= k;
    pr = sum(sucessos)/N;