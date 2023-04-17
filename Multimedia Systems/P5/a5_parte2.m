%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

%% 1a)
x1 = 0:0.01:5;
x2 = 0:0.01:5;
[X1,X2] = meshgrid(x1, x2);
y=cos(2*pi*(X1-2*X2));
figure(1)
imshow(y)


%% 1b)

x1 = -5:0.01:5;
x2 = -5:0.01:5;
[X1,X2] = meshgrid(x1, x2);
z=cos(2*pi*sqrt((X1.^2)+(X2.^2)));
figure(2)
imshow(z)


%% 2)
figure(3)
Espetro2(z, 0)