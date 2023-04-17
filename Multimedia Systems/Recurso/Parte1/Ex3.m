close all
clear
clc

T = 5;
Ta = 0.001;
t = 0:Ta:T;

x = sawtooth(2*pi*t + pi/2, 0.5);
for i = 1:length(t)
    if (x(i) < 0)
        x(i) = 0;
    end
end

Espetro(x, Ta, 0);
%plot(t,x);