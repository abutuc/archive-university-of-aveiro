%% Clear
clear;
close all;
clc;
%%
%% a)
%    Não Faltou | Faltou
T = [0.7 0.8       % Não Faltou
     0.3 0.2       % Faltou
    ];
  % Não Faltou | Faltou
v0 = [1 0];

v2 = T^2 * v0';
probA = v2(1);

%% b)

v0 = [0 1];
v2 = T^2 * v0';
probB = v2(2);

%% c)

v0 = [1 0];
semanas=15;
aulas=semanas*2;
transicoes=aulas-1;
v = T^transicoes * v0';
probC = v(1);

%% d)
aulas=1:30;
probs=zeros(size(aulas));
probs(1)=0.15;
v0 = [0.85 0.15];
for i=2:length(aulas)
    c = T^(i-1)*v0';
    probs(i)=c(2);
end

figure(1);
plot(aulas, probs, "-*");
