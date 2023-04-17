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


filhos1 = filhos(1,:); % obtem somente os dados relativos ao primeiro filho
                       % ou seja, obtém somente a primeira linha da tabela.

somas_filhos1 = sum(filhos1); % soma os numero de rapazes da primeira linha 
                              % de filhos

m = 2; % marca de 2 rapazes
sucessosHH = sum(filhos) == m; % soma os casos HH
HHprob = sum(sucessosHH)/somas_filhos1; % probabilidade=casosHH/casosH1;

% Os acontecimenos são independentes, já que a probabilidade ser rapaz
% no segundo filho continua a ser aproximadamente 0.5