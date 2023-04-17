%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

%%

% % 2a)
% Ta_x = 0.2;
% t_x = 0:Ta_x:5-Ta_x;
% x = sin(2*pi*t_x);
% figure(1)
% plot(t_x, x);
% 
% [r_x, r_t_x] = ReconstroiSinal(x, Ta_x);
% figure(2)
% plot(r_t_x, r_x)
% 
% % 2b)
% Ta_y = 0.04;
% t_y = 0:Ta_y: 5-Ta_y;
% y = sin(10*pi*t_y) + cos(12*pi*t_y) + cos(14*pi*t_y - (pi/4));
% figure(3)
% plot(t_y, y)
% 
% [r_y, r_t_y] = ReconstroiSinal(y, Ta_y);
% figure(4)
% plot(r_t_y, r_y)
% 
% 
% % 2c)
% Ta_z = 0.1;
% t_z = -5:Ta_z:(5-Ta_z);
% z = sinc(5*t_z);
% figure(5)
% plot(t_z, z)
% 
% [r_z, r_t_z] = ReconstroiSinal(z, Ta_z);
% figure(6)
% plot(r_t_z - 5, r_z)

%%

% Ex3
A = imread('Parede.jpg');
[N_rows, N_columns, N_colours] = size(A);
figure(7)
imshow(A)

new_A1 = A(1:2:end, 1:2:end, :);
figure(8)
imshow(new_A1)

new_A2 = A(1:4:end, 1:4:end, :);
figure(9)
imshow(new_A2)

new_A3 = A(1:8:end, 1:8:end, :);
figure(10)
imshow(new_A3)

new_A4 = A(1:16:end, 1:16:end, :);
figure(11)
imshow(new_A4)
