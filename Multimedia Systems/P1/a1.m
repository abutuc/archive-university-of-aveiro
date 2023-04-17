%% Clear
clear; % apagar as variavéis no workspace
close all; % apagar os objetos (figuras) no workspace
clc;    % clear da linha de comandos

%% Ex1
Ta = 0.01;
t = 0:Ta:5; % cria um array com elementos de 0 a 5 com Ta, também se pode usar linspace(0,5,N); N -> número de pontos

% a)
x = 2*sin(4*pi*t); 

% b)
y = cos(10*pi*t); 

% c)
z = x .* y;     

% d)
tW = 0:Ta:10;
w = 3*sin(pi*tW) + 2*sin(6*pi*tW); 

% e)
t1 = 0:Ta:5;
t2 = 0:Ta:5;

N1 = length(t1);
N2 = length(t2);
%q = zeros(N1, N2); % inicializar um array N1 por N2 de zeros. Para inicializar com "uns" usa-se ones(N1,N2)

% Forma 1, caclula-se todas as colunas linha-a-linha
%for k1 = 1:N1
 %   q(k1, :) = 2*sin(2*pi*(2*t1(k1) + t2));
%end

% Forma 2, mais eficaz
[T2, T1] = meshgrid(t1,t2);
q2 = 2*sin(2*pi*(2*T1+T2));

%% Ex2
% plot x
figure(1)
plot(t, x)
figure(2)
% plot y
plot(t, y)
figure(3)
% plot z
plot(t, z)
figure(4)
% plot w
plot(tW, w)
figure(5)
% plot q
surf(T1,T2,q2)
%% Ex3
figure(6)
p = plot(t, x, 'r', t, y, '--b', t, z, 'g.', tW, w, 'y');
p(2).LineWidth = 2;
p(4).LineWidth = 2;

%% Ex4
figure(7)
surf(T1,T2,q2, 'EdgeColor', 'none');
xlabel('Time(s)');
ylabel("Time(s)");
zlabel("Amplitude");
grid on;
cmap = colormap;
cmap_grey = repmat(linspace(0,1,length(cmap))',1,3);
plot3(cmap_grey, cmap_grey, cmap_grey);
colormap(cmap_grey)
%% Ex5 
Ta = 1/25;
t = 0:Ta:5;
x1 = -5:Ta:5;
x2 = -5:Ta:5;
N = length(t);
N1 = length(x1);
r = zeros(N1,N1);
for n=1:N
  tic
  for i=1:N1
    r(i,:) = 2*sin(2*pi*sqrt(x1(i)^2+x2.^2)-2*pi*t(n));
  end
  figure(8)
  mesh(x1,x2,r);
  view(2);
  drawnow();
  pause(Ta-toc);
end
