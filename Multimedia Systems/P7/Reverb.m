function [t,y]=Reverb(x, fa, Delay, Gain)
    Ta = 1/fa;
    N = length(x);
    t = (0:N-1)'* (1/fa);
    y = zeros(size(x));
    Delay = round(Delay)*fa;
    for k = 1:N
        if (k>Delay) & (k-Delay<N)
            y(k)=x(k)+Gain*x(k-Delay);
        else
            y(k) = x(k);
        end
    end
    px = Potencia(x, Ta, Ta*N);
    py = Potencia(y, Ta, Ta*N);
    y = y * sqrt(px/py);
end