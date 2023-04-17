function F = Fasor(x, To, Np)
    A = max(x);
    t0 = x(1);
    fase = asin(t0);
    F = A*exp(1j*fase);
end