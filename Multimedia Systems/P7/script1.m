%% Clear
clear;
close all;
clc;
%%
load Guitar03.mat;
[X, f] = Espetro(x, 1/fa, 0);