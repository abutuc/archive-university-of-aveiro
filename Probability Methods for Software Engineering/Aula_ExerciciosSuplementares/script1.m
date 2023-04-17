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
% a)
brinquedos_com_defeito = rand(n,N) < p;
sum_brinquedos_com_defeito = sum(brinquedos_com_defeito);
caixa_com_pelo_menos_1 = sum(sum_brinquedos_com_defeito >= 1);
probA = caixa_com_pelo_menos_1/N;


% b)
brinquedos_com_defeito_pA = rand(n,N) < pa;
sum_brinquedos_com_defeito_pA = sum(brinquedos_com_defeito_pA);
numero_medio_brinquedos_defeituosos = sum(sum_brinquedos_com_defeito_pA) / (N*8);

