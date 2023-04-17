%% Clear
clear;
close all;
clc;

%%
% a)
    % Sol Nuvens Chuva
T = [0.7 0.2 0.3        % Sol
     0.2 0.3 0.3        % Nuvens
     0.1 0.5 0.4];   

% b) P = Estar Sol no Dia 2 E Estar Sol no Dia 3
estado_inicial = [1 0 0];
prob = 1*T(1,1)*T(1,1);

% c) P = Não Chover no Dia 2 E Não Chover no Dia 3

v_i = [1 0 0];
v2 = T*v_i';
v_i2 = [1/2 1/2 0];
v3 = T*v_i2';
p = 1*(1-v2(3))*(1-v3(3));

% d) 
V = [1 0 0]';
dias = [1 0 0];
for i=2:31
    V = T*V;
    dias = dias + V';
end
sum(dias);

% e)

V2 = [0 0 1]';
dias2 = [1 0 0];
for i=2:31
    V2 = T*V2;
    dias2 = dias2 + V2';
end
sum(dias2);


prob_reumaticas = [0.1 0.3 0.5];

dias_reumaticas1 = dias * prob_reumaticas';
dias_reumaticas2 = dias2 * prob_reumaticas';