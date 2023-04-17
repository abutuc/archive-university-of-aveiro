clear;
close all;
clc;
N = 1000;
n1 = 2;
n2 = 4;
p = logspace(-3,log10(1/2),100);
comps = zeros(2, length(p));
for i=1:length(p)
    probI = p(i);
    falhas1 = rand(n1, N)<probI;
    falhas2 = rand(n2, N)<probI;
    sum_falhas1 = sum(falhas1);
    sum_falhas2 = sum(falhas2);
    comp1 = sum(sum_falhas1 > 1);
    comp2 = sum(sum_falhas2 > 2);
    comps(1,i)=comp1/N;
    comps(2,i)=comp2/N;

end
x = 1:length(p);
A = comps(1,:);
B = comps(2,:);

figure(1)
plot(x,A)
hold on
plot(x,B)
legend("dataA", "dataB")

% Preferia voar num avião com 2 motores.
