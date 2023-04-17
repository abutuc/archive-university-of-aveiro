function [x, Ta] = Reconstruct(X, f)

    N = length(X);
    fa = -f(1)*2;
    Ta = 1/fa;
    t = (0: (N-1))*Ta;
    x = ifft(ifftshift(X))*N;

    plot(t,x);
    xlabel("Tempo (seg)");
    ylabel("Sinal x(t)");

end