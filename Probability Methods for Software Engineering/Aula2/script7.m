clear;
close all;
clc;

N = 10e4;

pA = 0.01;
nA = 20;

pB = 0.05;
nB = 30;

pC = 0.001;
nC = 50;

A = rand(nA,N) < pA;
B = rand(nB, N) < pB;
C = rand(nC, N) < pC;

result = [A; B; C];

casosFavCarlos = sum(C == 1);
casosPossiveis = sum(result == 1);

probCarlos = casosFavCarlos / casosPossiveis;

%% b)

casosFavAndre = sum(A==1);
probAndre = casosFavAndre/casosPossiveis;

casosFavBruno = sum(B==1);
probBruno = casosFavBruno/casosPossiveis;
