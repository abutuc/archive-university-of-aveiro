function [ck, integral_sign, integral] = CoefFourier(x,Ta,k)
    N = length(x);
    T0 = N*Ta;
    F0 = 1/T0;
    w0 = 2*pi*F0;
    t = 0:Ta:T0-Ta;
    integral_sign = zeros(1, length(t));
    for i=1:length(integral_sign)
        integral_sign(i) = x(i)*exp(-1j*k*w0*t(i));
    end
    area_sign = abs(integral_sign*Ta);
    integral = sum(area_sign);
    ck = integral*F0;
end