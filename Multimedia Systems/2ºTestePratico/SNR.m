%% Clear
clc
clear
close all
%%

Ta = 0.001;
t = 0:Ta:5-Ta;
x = sawtooth(2*pi*t, 0.5);
r = 0.1*randn(1, length(t));

Px = potencia(x, Ta, 1);
Pe = potencia(r, Ta, 1);

SNR_normal = Px/Pe;
SNRdb = 10*log10(SNR_normal);