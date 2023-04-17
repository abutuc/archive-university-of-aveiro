%% Clear
clc
clear
close all
%%
t = 0:0.001:5;
z = sin(2*pi*t) - 2*sin(6*pi*t);
amplmax = max(z);
amplmin = min(z);
VFS = amplmax-amplmin;