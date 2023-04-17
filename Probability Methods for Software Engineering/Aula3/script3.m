% para verificar se a matriz é estocástica, a soma das colunas é 1
% para obter uma matriz estocástica, dividir a soma das colunas pela
% própria coluna

%% Clear
% clear;
% close all;
% clc;
%%

% a)
estados=20;
T_rand = rand(estados, estados);
T=T_rand./sum(T_rand);
T_sum = sum(T); % verificar se é estocástica.

% b)
estado_inicial = zeros(1,20);
estado_inicial(1) = 1;
transicoes = 10;
T_transicoes = T^transicoes;
prob=T_transicoes*estado_inicial';
prob10 = prob(20);