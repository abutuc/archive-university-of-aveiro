clear;
close all;
clc;


Ex = 15;
lambda = Ex;
% a)
k = 0;
pk1 = (lambda^k / factorial(k)) * exp(-lambda);


% b)
k = 0:10;
pk2 = 0;
for i=k
    pk2 = pk2 + (lambda^i / factorial(i)) * exp(-lambda);
end

pk3 = 1-pk2;