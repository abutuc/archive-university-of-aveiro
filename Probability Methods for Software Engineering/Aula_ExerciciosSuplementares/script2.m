%% Clear
clear;
close all;
clc;
%%
p1 = 0.002;
p2 = 0.005;
pa = 0.01;
p = p1 + p2 + pa - (p1*p2) - (p1*pa) - (p2*pa) - (p1*p2*pa);
N = 10e5;
n = 8;

% a) Sim, é consistente.
brinquedos_com_defeito = rand(n,N) < p;
sum_brinquedos_com_defeito = sum(brinquedos_com_defeito);
caixa_sem_defeito = sum(sum_brinquedos_com_defeito == 0);
probB = caixa_sem_defeito/N;


% b) Concluio que é praticamente igual.
probB_teorico = nchoosek(n, 0)*p^0*(1-p)^n;

% c) A Prob(B) diminui praticamente linearmente à medida que o n aumenta.
% O que faz sentido já que se n aumenta, 
% a componente de (1-p) diminui (1-p) < 1.

n_vetor = 2:1:20;
n_valores = length(n_vetor);
probB_vetor = zeros(1, n_valores);
for n1=1:n_valores
    probB_vetor(n1)= nchoosek(n_vetor(n1), 0)*p^0*(1-p)^n_vetor(n1);
end
figure(1)
plot(n_vetor, probB_vetor);
xlabel("Capacidade da caixa (nºbrinquedos)")
ylabel("Prob(B)")
title("Probabilidade B vs Capacidade da Caixa")

% d)
capacidade_caixas_pelo_menos_noventa = zeros(1, n_valores);
for caixa=1:n_valores
    if (probB_vetor(caixa) >= 0.9)
        capacidade_caixas_pelo_menos_noventa(caixa) = n_vetor(caixa);
    end
end

capacidade_maxima = max(capacidade_caixas_pelo_menos_noventa);



