%% Clear
clear;
close all;
clc;
%%
load Guitar03.mat

Espetro(x, 1/fa, false, 1);
yc = Chorus(x, fa, 0.3, 10);
Espetro(yc, 1/fa, false, 2);