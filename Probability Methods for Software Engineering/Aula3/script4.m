format long
%% Clear
clear;
close all;
clc;

%%

p = 0.4;
q = 0.6;
    %A B C D
T = [p^2 0 0 q^2    % A
     (1-p)^2 0 0 q*(1-q)             % B
     p*(1-p) 0 0 q*(1-q)               % C
     p*(1-p) 1 1 (1-q)^2];           % D


estado_inicial = [1 0 0 0];
P_5_transicoes = T^5 * estado_inicial';
P_10_transicoes = T^10 * estado_inicial';
P_100_transicoes = T^100 * estado_inicial';
P_200_transicoes = T^200 * estado_inicial';


M_prob = [P_5_transicoes P_10_transicoes P_100_transicoes P_200_transicoes];


% 4 c)

M = [T-eye(size(T)); 
    ones(1,length(T))];
x = zeros(1,length(T)+1);
x(length(T)+1)=1;
x=x';
u = M\x;