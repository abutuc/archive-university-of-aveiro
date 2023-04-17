%% Clear
clear;
close all;
clc;
%%

t = 0:1/8:10;
y = sin(2*pi*t-pi/3)+cos(10*pi*t+pi/5);
Espetro(y, 1/8, 0);