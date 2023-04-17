%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos
%% Ex3
Ta=0.001;
f0 = 1;
Np = 6;

K = 100;

ak = zeros(K,1);

k = 1:K;

bk = 2./(pi*k).*(1-cos(pi*k));

[sign, t] = SinalFourier(Ta, f0, Np, ak, bk);
plot(t,sign);



% Variar depois os k, como no último exercício do guião 1.


%% Ex5
sign = cos(2*pi*t);
[a, b] = CoefAkBk(Ta, 1, sign, K);

%% Ex6
t = 0:Ta:4;
figure(1)
plot(t, sawtooth(2*pi*t + (pi/2), 0.5));
axis([0 4 -2 2]);
[a1, b1] = CoefAkBk(Ta, 1, sawtooth(2*pi*t + (pi/2), 0.5), K);
[sign, t] = SinalFourier(Ta, 1, 4, a1, b1);
figure(2)
plot(t, sign)
axis([0 4 -2 2]);


figure(3)
saw_tooth = sawtooth(2*pi*t + (pi/2), 0.9);
positive = saw_tooth >= 0;
plot(t(positive), saw_tooth(positive));
hold on
plot(t(positive)+(pi/2), saw_tooth(positive));
axis([0 4 -2 2]);
