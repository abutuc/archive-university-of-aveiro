function [x, t] = GeraSinal(N, Ta)
    t = (0:N-1)*Ta;
    p1 = randn(1, N)*pi;
    p2 = randn(1, N)*pi;
    phi1 = cumtrapz(t,p1);
    phi2 = cumtrapz(t,p2);
    r = 0.5*sin(20*pi*t + 10*phi1) + 0.5*sin(24*pi*t + 10*phi2);
    x = sin(2*pi*t) + r;
    plot(t, x);