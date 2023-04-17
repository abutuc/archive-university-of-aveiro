clear;
close all;
clc;
%% R1
seq_10bits = 2^10; % Para n bits, 2^n.

%% R2
diff_seq_alfabeto = factorial(10); % Para n símbolos, factorial(n)

%% R3
n = 2; % número de perguntas do teste
n_maneiras_diff = 2^n; % número de maneiras de responder ao teste V/F
p = 0.5;
k = n;
prob = ProbTeorica(k, n, p);

%% R4
totoloto = nchoosek(49,5);
euromilhoes = nchoosek(50,5)*nchoosek(11,2);

%% R5
cartas_diff = factorial(20);
cartas_diff_alt = factorial(10)*factorial(10)*2;
prob_cartas_alt = cartas_diff_alt/cartas_diff;

%% R6
espacoAmos = 2:12;
casos_pos = 6*6;
casos_fav = 8;
prob_dados = casos_fav/casos_pos;

%% R7
n = 10;
p = 8/50;
k = 3;
prob_defeito = ProbTeorica(k, n, p);

%% R8
pass_a = 10^5;
pass_b = 26^5;
