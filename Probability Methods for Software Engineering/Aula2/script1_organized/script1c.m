%% Clear
clear;
close all;
clc;
%% Téorico

probTeorico = 1/3; % Casos Favoráveis: {HH}; Casos Possíveis : {HM, MH, MM}
                   % Probabilidade teórica: Favoráveis/Possíveis = 1/3
%% Simulação

N = 1e5; % numero de vezes que se repete-se a experiência
p = 0.5;  % probabilidade de o filho ser rapaz.
k = 1;  % marca de 1 rapaz
n = 2; % numero de casos possiveis, rapaz ou rapariga.

filhos = rand(n,N) < p;     % geração das famílias de 2 filhos e o
                            % mapeamento de quem é rapaz com 1 e quem é
                            % rapariga com 0

sucessos_pelo_menos_1filho = sum(filhos) >= k; % soma do número de rapazes 
                                % numa familia
                                % e a consequentemente mapeação num array
                                % das familias que têm pelo menos 1 rapaz.

casos_possiveis = sum(sucessos_pelo_menos_1filho)/N; % cálculo da 
                                 % frequência relativa do acontecimento
                                 % "ter pelo menos um filho rapaz"

m = 2; % marca de 2 rapazes
sucessos_2filhos = sum(filhos) == m; % mapeamento das familias com 2 
                                     % rapazes com o valor 1

casos_favoraveis = sum(sucessos_2filhos)/N; % Soma do número de familias
                                    % com 2 rapazes e cálculo da frequência
                                    % relativa

probSimulacao = casos_favoraveis/casos_possiveis;