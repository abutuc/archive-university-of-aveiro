function [t,y] = Flanger(x, fa, max_delay, freq)
    Ta = 1/fa;
    N = length(x);
    t = (0:N-1)'* (1/fa);
    y = zeros(size(x));
    d = cos(2*pi*t*freq)*max_delay*fa;
    for k = 1:N
        dk = round(d(k));
        if (k>dk) & (k-dk<N)
            y(k)=x(k)+x(k-dk);
        else
            y(k) = x(k);
        end
    end
    px = Potencia(x, Ta, Ta*N);
    py = Potencia(y, Ta, Ta*N);
    y = y * sqrt(px/py);
end