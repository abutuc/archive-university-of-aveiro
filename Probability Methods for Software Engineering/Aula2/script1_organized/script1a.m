%% Clear
clear;
close all;
clc;
%%
N = 1e5; % numero de vezes que se repete-se a experiência
p = 0.5;  % probabilidade de o filho ser rapaz.
k = 1;  % marca de 1 rapaz
n = 2; % numero de casos possiveis, rapaz ou rapariga.

filhos = rand(n,N) < p;     % geração das famílias de 2 filhos e o
                            % mapeamento de quem é rapaz com 1 e quem é
                            % rapariga com 0

sucessos = sum(filhos) >= k;    % soma do número de rapazes numa familia
                                % e a consequentemente mapeação num array
                                % das familias que têm pelo menos 1 rapaz.

probSimulacao = sum(sucessos)/N; % cálculo da frequência relativa do 
                                 % acontecimento "ter pelo menos um filho
                                 % rapaz"
                      