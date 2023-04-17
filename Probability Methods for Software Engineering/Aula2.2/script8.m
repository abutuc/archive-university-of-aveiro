clear;
close all;
clc;

n = 100;
lambda = 0.02 * n;
k = 0:1;
pk = 0;
for i=k
    pk = pk + (lambda^i / factorial(i)) * exp(-lambda);
end