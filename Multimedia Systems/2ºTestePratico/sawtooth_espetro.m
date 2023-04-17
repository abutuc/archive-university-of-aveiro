%% Clear
clc
clear
close all

%% 

t = 0:0.01:5;
x = sawtooth(2*pi*t + (pi/2), 0.5);
for i=1:length(x)
    if x(i) < 0
        x(i)=0;
    end
end
plot(t, x);

x = sin(2*pi - pi/3) + cos(6*pi*t)
Espetro(x, 0.01, 0);