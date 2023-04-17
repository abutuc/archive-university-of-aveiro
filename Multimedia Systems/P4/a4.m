%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

%% Ex2 
%% Ex2a)
Ta = 0.01;
t = 0:Ta:10-Ta;
x = sin(2*pi*t)+sin(3*pi*t)+sin(4*pi*t);
figure(2)
plot(t, x);
[X,f] = Espetro(x, Ta, 0);

%% Ex2b)
Ta = 1/4;
t = 0:Ta:5-Ta;
y = sin(2*pi*t - pi/3) + cos(6*pi*t + pi/5);
[Y, f] = Espetro(y, Ta, 0);

%% Ex2c)
Ta = 0.01;
t = 0:Ta:5-Ta;
z = (square(2*pi*t)+1)/2;
figure(2);
plot(t, z);
[Z, f] = Espetro(z, Ta, 0);

%% Ex2d)
Ta = 0.01;
t = 0:Ta:5-Ta;
q = sawtooth(4*pi*t, 1/2);
figure(2);
plot(t, q);
[Q, f] = Espetro(q, Ta, 0);

%% Ex 3 & Ex 4

N = 500;
Ta = 1e-3;
t = 0:Ta:(N*Ta)-Ta;
freq = (20-1)*rand(1,20) + 1;
fases = (2*pi)*rand(1,20);
x = 0;
for i=1:20
    x = x + sin(2*pi*freq(i)*t + fases(i));
end
figure(2)
plot(t, x);

[X, f, window] = Espetro(x, Ta, 1);