%% Clear
clear;
close all;
clc;
%%

% a)
T = [1/3 1/4 0; 1/3 11/20 1/2; 1/3 1/5 1/2];


% b)  A  B  C
v0 = ([60 15 15]/90)';


% c)
transicao = 30;
v30 = (T^30 * v0)*90;
nA = v30(1);
nB = v30(2);
nC = v30(3);

% d)
v02 = ([30 30 30]/90)';
transicao2 = 30;
v302 = (T^30 * v0)*90;
nA2 = v30(1);
nB2 = v30(2);
nC2 = v30(3);