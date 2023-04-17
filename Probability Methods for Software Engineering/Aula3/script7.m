%% Clear
clear;
close all;
clc;

% a) 
%     A B   C D E   F
H = [ 0 0   0 0 1/3 0        % A
      1 0   0 0 1/3 0        % B
      0 1/2 0 1 0   0        % C
      0 0   1 0 0   0        % D
      0 1/2 0 0 0   0        % E
      0 0   0 0 1/3 0        % F
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

% b) spider trap: C e D  | dead-end: F 

% c)

%     A B   C D E   F
H1 = [0 0   0 0 1/3 1/6        % A
      1 0   0 0 1/3 1/6        % B
      0 1/2 0 1 0   1/6        % C
      0 0   1 0 0   1/6        % D
      0 1/2 0 0 0   1/6        % E
      0 0   0 0 1/3 1/6        % F
];

pr1 = (ones(1,6)/6)';
for i=1:10
   pr1 = H1 * pr1; 
end
pr1A = pr1(1);
pr1B = pr1(2);
pr1C = pr1(3);
pr1D = pr1(4);
pr1E = pr1(5);
pr1F = pr1(6);

% d) 

H2 = ones(size(H))/6;
beta = 0.8;
A = H1*beta + (1-beta)*H2;
pr2 = (ones(1,6)/6)';
for i=1:10
   pr2 = A * pr2; 
end
pr2A = pr2(1);
pr2B = pr2(2);
pr2C = pr2(3);
pr2D = pr2(4);
pr2E = pr2(5);
pr2F = pr2(6);
