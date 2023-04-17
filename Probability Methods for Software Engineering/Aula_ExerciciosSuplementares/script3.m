%% Clear
clear;
close all;
clc;
%%
p1 = 0.002;
p2 = 0.005;
pa = 0.01;
p = p1 + p2 + pa - (p1*p2) - (p1*pa) - (p2*pa) - (p1*p2*pa);
N = 10e5;
n = 8;

% a) É consistente com o resultado obtido na questão 2(a).
brinquedos_com_defeito = rand(n,N) < p;
sum_brinquedos_com_defeito = sum(brinquedos_com_defeito);
px = zeros(1,n);
for i=1:1:(n+1)
    px(i) = sum(sum_brinquedos_com_defeito==i-1)/N;
end

x = 0:1:n;
figure(1)
stem(x, px);
xlabel("Número de Brinquedos Defeituosos");
ylabel("P(Número de Brinquedos Defeituosos)");
title("Função Massa de Probabilidade de X");
axis([0 n 0 1.5]);

% b) Concluio que é extreamente pequena.
PX_maior_igual_dois = 1 - px(1) - px(2);

% c)
EX = px*x';
variancia = sum((px-EX).^2) / n;
desvio_padrao = sqrt(variancia);
%% d)
% A probabiliade para acima de 0 aumenta ligeiramente, o que faz com o que
% o valor esperado também aumente, a variância e desvio padrão são quase
% iguais.
n2=16;

brinquedos_com_defeito2 = rand(n2,N) < p;
sum_brinquedos_com_defeito2 = sum(brinquedos_com_defeito2);
px2 = zeros(1,n2);
for i=1:1:(n2+1)
    px2(i) = sum(sum_brinquedos_com_defeito2==i-1)/N;
end

x2 = 0:1:n2;
figure(2)
stem(x2, px2);
xlabel("Número de Brinquedos Defeituosos");
ylabel("P(Número de Brinquedos Defeituosos)");
title("Função Massa de Probabilidade de X");
axis([0 16 0 1.5]);

% b) Concluio que é extreamente pequena.
PX_maior_igual_dois2 = 1 - px2(1) - px2(2);

% c)
EX2 = px2*x2';
variancia2 = sum((px2-EX2).^2) / n2;
desvio_padrao2 = sqrt(variancia2);