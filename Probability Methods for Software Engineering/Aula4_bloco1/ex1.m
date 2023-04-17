%% Clear
clear;
close all;
clc;

%% 

N = 10^5;
imin = 6;
imax = 20;
alfabeto = ['a':'z' 'A':'Z'];
min_alfabeto = ['a' : 'z'];
prob = load("prob_pt.txt");
keys = gen_keys(N, imin, imax, min_alfabeto, prob);
u_keys = unique(keys);
if (length(keys) == length(u_keys))
    disp("It works!")                   % só para verificar se gerou chaves todas diferentes
end

% há um bug quando colocarmos prob como parametro, o unique devolve menos
% chaves.
