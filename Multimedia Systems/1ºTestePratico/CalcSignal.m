function [x,t] = CalcSignal(Ck, fo)
    fa = 2*fo;
    T0 = 1/fo;
    Ta = 1/fa;
    N = 2;
    K = length(Ck);
    t = 0:Ta:N*T0;
    x = zeros(1, length(t));
    for i=1:length(t)
        for k=1:K
            x(i) = x(i) + Ck(k)*exp(1j*2*pi*(k-1-(K/2)*fo*t(i))); 
        end
    end
end