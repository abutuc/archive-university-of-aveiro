function [a, b] = CoefAkBk(Ta, T0, x, K)
% Ta = Periodo de amostragem, em segundos.
% T0 = periodo do sinal, em segundos.
% x = vetor do sinal
% K = nr de frequências
    f0 = 1/T0;
    N = length(x);
    t = (0:Ta:(N-1)*Ta);
    a = zeros(K, 1);
    b = zeros(K, 1);
    for k = 1:K
        cos_k = cos(k*2*pi*f0*t);
        sin_k = sin(k*2*pi*f0*t);
        a(k) = 2*sum(x.*cos_k*Ta)/(N*Ta);
        b(k) = 2*sum(x.*sin_k*Ta)/(N*Ta);
    end
end