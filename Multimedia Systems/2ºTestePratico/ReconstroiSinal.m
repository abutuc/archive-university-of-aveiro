function [y, t] = ReconstroiSinal(x, Ta)
    N = length(x);  
    Fa = 1/Ta;

    Fs = 100*(1/Ta);
    Ts = 1/Fs;

    t = [0:(100*N-1)]*Ts;

    y = zeros(1, length(t));
    for i = 1:N
        y = y + x(i)*sinc(Fa*(t-(i-1)*Ta));
    end
end