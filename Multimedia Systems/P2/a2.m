%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

%% Ex1 + Ex2 + Ex3
%figure(1);
fs = 2*8*10;
Ta = 1/fs;
T = 6;
t = 0:Ta:T-Ta;
x = 2*sin(4*pi*t);
maxx = max(x); % para obter a amplitude
%  plot(t, x);
%  xlabel("Time(s)")
%  ylabel("x")
[~, idx] = findpeaks(x);
potX = potencia(x, Ta, T);
%figure(2);
y = sin(10*pi*t + pi/2);
maxy = max(y); % para obter a amplitude
% plot(t, y);
% xlabel("Time(s)")
% ylabel("y")
potY = potencia(y, Ta, T);
%figure(3);
z = sin(6*pi*t) + sin(8*pi*t);
maxz = max(z); % para obter a amplitude
% plot(t, z);
% xlabel("Time(s)")
% ylabel("z")

%figure(4);
w = sin(6*pi*t) + sin(8*pi*t + 0.1);
maxw = max(w); % para obter a amplitude
% plot(t, w);
% xlabel("Time(s)") 
% ylabel("w")
potW = potencia(w, Ta, T);


%figure(5);
q = sin(6*pi*t) + sin(7*pi*t) + sin(8*pi*t);
maxq = max(q); % para obter a amplitude
% plot(t, q);
% xlabel("Time(s)")
% ylabel("q")
potQ = potencia(q, Ta, T);


%% Ex4
Ta_3 = 1/120e3;
T_3 = 1/.1e3;
t_3 = (0:Ta_3:T_3-Ta_3);
phi = rand(3)*2*pi-pi

x1 = sin(2*pi*1.1e3*t_3 + phi(1));
x2 = sin(2*pi*1.2e3*t_3 + phi(2));
x3 = sin(2*pi*1.2e3*t_3 + phi(3));
x_sum = x1+x2+x3;
potX1 = potencia(x1, Ta_3, T_3)
potX2 = potencia(x2, Ta_3, T_3)
potX3 = potencia(x3, Ta_3, T_3)
figure(1)
hold on
plot(x1)
plot(x2)
plot(x3)
hold off

figure(2)
plot(x_sum)
pot_sum = potencia(x_sum, Ta_3, T_3)



