%% Clear
clear;
close all;
clc;
%%
N = 1e5; % numero de vezes que repete-se a experiência
p = 0.5;  % probabilidade de sucesso
k1 = 1;  % marca de 1 rapaz
k2 = 2; % marca de 2 rapazes
n = 5; % numero de casos possiveis

filhos = rand(n,N) < p;     % geração das famílias de 2 filhos e o
                            % mapeamento de quem é rapaz com 1 e quem é
                            % rapariga com 0

sucessos = sum(filhos) >= k1;    % soma do número de rapazes numa familia
                                % e a consequentemente mapeação num array
                                % das familias que têm pelo menos 1 rapaz.

casos_possiveis = sum(sucessos); % Casos em que pelo menos um dos filhos é 
                                 % rapaz.

sucessos2 = sum(filhos) == k2; % mapeamento das familias que tem dois
                               % e apenas dois rapazes.

casos_favoraveis = sum(sucessos2); % Casos em que só há 2 rapazes.

probSimulacao = casos_favoraveis/casos_possiveis;