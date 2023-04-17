%% Clear
clear;
close all;
clc;

% a)
    % 1 2 4 3 5
T = [0.8  0    0.3  0  0       % 1
     0.2  0.6  0.2  0  0      % 2
     0    0.1  0.4  0  0     % 4
     0    0.3  0    1  0     % 3
     0    0    0.1  0  1     % 5
     ];

% b)
N = 100;
passos = 1:N;
estado_inicial = [1 0 0 0 0];
prob_estado_2 = zeros(1, N);
for passo=passos
    temp = (T^passo);
    prob_estado_2(passo) = temp(2,1);
end

%  figure(1)
%  plot(passos, prob_estado_2, "*:");
%  title("Probabilidade do Estado 2 em relação aos passos")
%  xlabel("Passos")
%  ylabel("Probabilidade Estado")
 
% c)
prob_estado_3 = zeros(1,100);
prob_estado_5 = zeros(1,100);
for passo=passos
    temp2 = (T^passo)*estado_inicial';
    prob_estado_3(passo) = temp2(4, 1);
    prob_estado_5(passo) = temp2(5, 1);
end

figure(2)
plot(passos, prob_estado_3, "*:");
hold on
plot(passos, prob_estado_5, "*:");
legend("estado3", "estado5")
title("Probabilidade do Estado 3 e Estado 5 em relação aos passos")
xlabel("Passos")
ylabel("Probabilidade Estado")

% d) Estados Absorventes = {3, 5}
% Matriz Q, estados não absorventes para estados não absorventes
    % 1 2 4
% Q = [ 0.8 0   0.3      %1
%       0.2 0.6 0.2       %2
%       0   0.1 0.4      %4
% ];

Q = T([1 2 3], [1 2 3]);
% e)
% F = (I - Q) ^(-1)
F = (eye(size(Q)) - Q)^(-1);
%F = inv((eye(size(Q)) - Q));
% f)
m_valores = sum(F);
m1 = m_valores(1);
m2 = m_valores(2);
m4 = m_valores(3);

% g)  1 2 4
% R = [ 0 0.3 0       % 3
%       0 0   0.1     % 5
%     ];

R = T([4 5], [1 2 3]);

B = R*F;

p_abs_3 = B(1,1);
p_abs_5 = B(2,1);
p_abs_3_sim = prob_estado_3(1, end);
p_abs_5_sim = prob_estado_5(1, end);
