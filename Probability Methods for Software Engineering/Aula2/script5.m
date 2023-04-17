clear;
close all;
clc;

N = 1e6;
n = 2;
m = 6;

lancamentos = randi([1 m],n,N);

%% a)
%% Acontecimento A

cfA = sum(sum(lancamentos) == 9);
probA = cfA/N;

%% Acontecimento B

segundo_lancamento = lancamentos(2, :);
cfB = sum(mod(segundo_lancamento, 2)==0);
probB = cfB/N;

%% Acontecimento C

pelo_menos_um_5 = lancamentos == 5;
cfC = sum(sum(pelo_menos_um_5) >= 1);
probC = cfC/N;

%% Acontecimento D

nenhum_igual_a_um = lancamentos ~= 1;
cfD = sum(sum(nenhum_igual_a_um) == 2);
probD = cfD/N;


