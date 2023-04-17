%% Clear
clear;
close all;
clc;

%% Representation of signals
% Multiplication element-wise
Ta = 0.01;
t = 0:Ta:5;
x = 2*sin(4*pi*t); 
y = cos(10*pi*t); 
z = x .* y; % element-wise multiplication

% How make a matrix function that takes two arguments
Ta = 0.01;
t1 = 0:Ta:5;
t2 = 0:Ta:5;
[T2, T1] = meshgrid(t1, t2);
q = 2*sin(2*pi*(2*T1+T2));


% Line Styles
% '-', solid line; '--', dashed line; ':', dotted line; 
% '-.', dash-dotted line

% Markers
% 'o', circle; '+', plus sign, '*', asterisk, '_', horizontal line
% '|', vertical line; 's', square; 'd', diamond; '^', upward-pointing
% triangle; 'v', downward-pointing triangle; '>', right-pointing triangle;
% '<', left-pointing triangle; 'p', pentagram; 'h', hexagram.

% Colors
% 'r', red; 'g', green; 'b', blue; 'c', cyan; 'm', magenta;
% 'y', yellow; 'k', black; 'w', white.


% Using colormap
cmap = colormap;
cmap_grey = repmat(linspace(0,1,length(cmap))',1,3); % repmate replicates the column 3 times
mesh(q);
colormap(cmap_grey)

% Signal Dependent of 3 variables, x1, x2 and t
Ta = 1/25;
t = 0:Ta:5;
x1 = -5:Ta:5;
x2 = -5:Ta:5;
N = length(t);
N1 = length(x1);
N2 = length(x2);
r = zeros(N1,N2);
for n=1:N
  tic % starts counter
  for i=1:N1
    r(i,:) = 2*sin(2*pi*sqrt(x1(i)^2+x2.^2)-2*pi*t(n));
  end
  figure(8)
  mesh(x1,x2,r);
  view(2); % views the 3D-graph from up
  drawnow(); % updates window
  pause(Ta-toc); % pauses for Ta-toc seconds, being toc the end of the timer that returns time between tic and toc.
end


%% Information from Signals
clear;
close all;
clc;
% Amplitude, use max(signal)
% Periodo:
fs = 2*8*10;
Ta = 1/fs;
T = 6;
t = 0:Ta:T-Ta;
x = sin(6*pi*t) + sin(7*pi*t) + sin(8*pi*t);
max_x = round(max(x), 4);
t_peak1 = 0;
found_peak1 = 0;
t_peak2 = 0;
found_peak2 = 0;

for i=1:length(t)
    if (round(x(i), 4)==max_x) && (found_peak1 == 0)
        t_peak1 = t(i);
        found_peak1 = 1;
        continue
    end

    if  (round(x(i),4)==max_x) && (found_peak2 == 0)
        t_peak2 = t(i);   
        found_peak2 = 1;
        break;
    end
end

periodo = t_peak2 - t_peak1;

% Função potencia de sinal:
%function pot = potencia(x, Ta, T)
%% Determina a potência associada a um sinal com o vetor de amostras (x),
%% período de amostragem (Ta) e o período do sinal (T).
    %x2 = x.^2;
    %area = x2*Ta;
    %integral = sum(area);
    %pot = integral / T;

% Podemos gerar 3 fases de -pi a pi usando o seguinte comando: phi = rand(3)*2*pi-pi


%% Número complexo
% Tendo um número x complexo que representa um sinal, 
% em Matlab é só fazer abs(x) para obter a amplitude 
% valor real e angle(x) para obter a fase


