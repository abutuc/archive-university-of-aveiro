%% Clear
clear;
close all;
clc;
%    A B C D E F
H = [0 0 0 0 1/4 0  % A
    1/4 0 1 0 1/4 0 % B
    0 1/2 0 1 0 0   % C
    1/4 1/2 0 0 1/4 0   % D
    1/4 0 0 0 0 0   % E
    1/4 0 0 0 1/4 0 % F
    ];

pr = (ones(1,6)/6)';
for i=1:10
   pr = H * pr; 
end
prA = pr(1);
prB = pr(2);
prC = pr(3);
prD = pr(4);
prE = pr(5);
prF = pr(6);

beta=0.9;

oness=ones(6)/6;
A = beta*H + (1-beta)*oness;
for i=1:3
   pr = A * pr; 
end
prA = pr(1);
prB = pr(2);
prC = pr(3);
prD = pr(4);
prE = pr(5);
prF = pr(6);
