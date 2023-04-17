%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

% Ta = 0.01;
% t = 0:Ta:5;
% 
% sign = sawtooth(2*pi*t, 0.9)/2 + (1/2);
% figure(2)
% plot(t, sign);
% 
% [espetro, frequencies] = Espetro(sign, Ta, 0);

% ffftshift swaps the left and right halves of X, essentially makes the 
% simetry of the graph.


Ta = 1/8;
t = 0:Ta:5-Ta;
x = sin(2*pi*t-pi/3)+cos(10*pi*t+pi/5);
Espetro(x, Ta, 0);
% sign = MainComponent(x, Ta);
% figure(3)
% plot(t, sign)