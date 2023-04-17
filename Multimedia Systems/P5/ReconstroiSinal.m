function [xi, ti] = ReconstroiSinal(x,Ta)
    % constants
    N = length(x);
    T = N*Ta;
    t = (0:(N-1))*Ta;
    fa = 1/Ta;
    Tai = Ta/100;
    ti = 0:Tai:T-Tai;
    Ni = length(ti);
    
    xi = zeros(1, Ni);
    for n=1:N
        sinc_n = sinc(ti*fa - t(n)*fa);
        xi = xi + x(n)*sinc_n;
    end
end