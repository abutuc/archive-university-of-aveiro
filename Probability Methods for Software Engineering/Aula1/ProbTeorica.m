function prob = ProbTeorica(k, n, p)
% Calcula analiticamente a probabilidade.
    prob= nchoosek(n,k)*p^k*(1-p)^(n-k);