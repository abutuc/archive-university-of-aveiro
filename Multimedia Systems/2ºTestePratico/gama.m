%% Clear
clc
clear
close all

%% 
t = 0:0.01:2;
y = sin(6*pi*t + pi/5) + cos(18*pi*t-pi/3);
plot(t , y);
amp = max(y);
ampli = min(y);
gam = amp - ampli;
potencia_ruido = 0.026;
erro_quantizacao = sqrt(potencia_ruido*12);
dois_n = gam/erro_quantizacao;
n = round(sqrt(dois_n));