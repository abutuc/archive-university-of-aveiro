clear
clc
close all

N = 10e4;
n1 = 100;
n = 5;
p = 0.002;
Y = zeros(1, N);
for i = N
    sim = rand(n, n1);
    def = sim < p;
    occur = sum(def);
    Y(i) = sum(occur);
end

defeit = 0:100;

fmp = zeros(1, 100);
for d=defeit
    fmp(d+1) = sum(occur==d);
end
fmp = fmp/length(occur);

EX = sum(defeit.*fmp);
EX2 = sum(defeit.^2 .* fmp);

Var = EX2 - EX^2;
Desvio = sqrt(Var);



