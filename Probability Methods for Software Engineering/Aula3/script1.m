%% Clear
clear;
close all;
clc;
%%
    % F   P
T = [0.2 0.3; 0.8 0.7];
% a)

v0 = [0 1]';

v3 = T^2*v0;
pA = v3(2);

% b)

v0 = [1 0]';
v3 = T^2*v0;
pB = v3(2);


% c)
v0 =[0 1]'; % F, P
v30 = T^29*v0; % aulas = 15*2, transições = aulas-1;
pC = v30(2);

% d)

v0 = [0.15 0.85]';
probabilidades = zeros(1, 30);
aulas = 1:1:length(probabilidades);
for aula=aulas
    transicao = aula - 1;
    map = (T^transicao*v0);
    probabilidades(aula) = map(1);
end

figure(1)
plot(aulas, probabilidades, '-*');
xlabel("Nº Aulas")
ylabel("Probabilidade de faltar")
title("ProbabilidadeFaltar(NºAulas)")

