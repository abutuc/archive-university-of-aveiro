close all
clc
clear

t = [0:0.01:5]';
y = zeros(size(t));
for k=1:10
    ampli = rand(1,1)*0.5 + 0.5;
    freqi = rand(1,1)*9 + 1;
    y = y + (rand(1,1)*0.5 + 0.5)*sin(2*pi*(rand(1,1)*9 + 1)*t);
end

plot(t,y);
xlabel('Tempo(seg)');