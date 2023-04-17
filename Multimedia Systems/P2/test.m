%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

%%

% Ta = 0.01;
% t = 0:Ta:10;
% x = 2*sin(2*pi*t) + cos(5*pi*t);
% pot = potencia(x, Ta, 10)

% fs = 2*8*10;
% Ta = 1/fs;
% T = 6;
% t = 0:Ta:T-Ta;
% x = cos(8*pi*t - (pi/2)) + cos(14*pi*t + (pi/4)) + cos(18*pi*t + (pi/3));
% max_x = round(max(x), 4);
% t_peak1 = 0;
% found_peak1 = 0;
% t_peak2 = 0;
% found_peak2 = 0;
% 
% for i=1:length(t)
%     if (round(x(i), 4)==max_x) && (found_peak1 == 0)
%         t_peak1 = t(i);
%         found_peak1 = 1;
%         continue
%     end
% 
%     if  (round(x(i),4)==max_x) && (found_peak2 == 0)
%         t_peak2 = t(i);   
%         found_peak2 = 1;
%         break;
%     end
% end
% 
% periodo = t_peak2 - t_peak1;


Ta = 0.01; 
t = 0:Ta:10;
x = sin(2*pi*t);
plot(t, x)
