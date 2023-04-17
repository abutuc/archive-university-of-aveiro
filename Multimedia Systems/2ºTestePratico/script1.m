%% Clear
clear;
close all;
clc;
%
t = [0:0.02:2]';
x = sin(10*pi*t)+sin(14*pi*t)+cos(20*pi*t);
plot(t, x);