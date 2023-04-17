clear;
close all;
clc;

%% Ex1 a)
N = 1e5; % numero de vezes que repete-se a experiência
p = 0.5;  % probabilidade de sucesso
k = 1;  % numero de sucesso
n = 2; % numero de casos possiveis

filhos = rand(n,N) < p;
sucessos = sum(filhos) >= k;
probSimulacao = sum(sucessos)/N;

%% Ex1 b)
prob_anal = 3/4; % Fav/Possíveis
igualdade = prob_anal == probSimulacao; % Falso, porque probSimulacao é uma estimativa

%% Ex1 c)

Probteorico = 1/3; % Caso fav/Caso pos, {MM, FM, FF} Queremos MM
casos_pos = probSimulacao;

k = 2;
filhos = rand(n,N) < p;
sucessos = sum(filhos) == k;
casos_fav = sum(sucessos)/N;

prob = casos_fav/casos_pos;

%% Ex1 d) 
% H_ Prob(H2|H1) = Prob(H1H2) / Prob(H1)
somas_filhos1 = sum(filhos(1,:));
k = 2;
sucessosHH = sum(filhos) == k;
HHprob = sum(sucessosHH)/somas_filhos1;


%% Ex1 e)
N = 1e5; % numero de vezes que repete-se a experiência
p = 0.5;  % probabilidade de sucesso
k1 = 1;  % numero de sucesso
k2 = 2;
n = 5; % numero de casos possiveis

filhos = rand(n,N) < p;
sucessos = sum(filhos) >= k1;
casos_possiveis = sum(sucessos);

sucessos2 = sum(filhos) == k2;
casos_favoraveis = sum(sucessos2);

probE = casos_favoraveis/casos_possiveis;

%% Ex1 f)

sucesso3 = sum(filhos) >= k2;
casos_favoraveis = sum(sucesso3);
probF = casos_favoraveis/casos_possiveis;

