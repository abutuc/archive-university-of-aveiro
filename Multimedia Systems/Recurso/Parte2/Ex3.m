close all
clc
clear


t = 0:0.001:2;
z = 2*cos(2*pi*t) + 2*sin(3*pi*t);

maxz = max(z);
minz = min(z);

gama = maxz - minz;

delta = gama / 2^10;

SNR = (3/2)* (gama^2 / delta^2);
SNRlog10 = 10*log10(SNR);