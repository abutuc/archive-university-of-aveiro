clear
clc
close all

N = 100;
n = 5;
p = 0.002;

defeit = 0:5;

sim = rand(n, N);
def = sim < p;
occur = sum(def);

fmp = zeros(1, 5);
for d=defeit
    fmp(d+1) = sum(occur==d);
end
fmp = fmp/length(occur);


EX = sum(defeit.*fmp);
EX2 = sum(defeit.^2 .* fmp);

Var = EX2 - EX^2;
Desvio = sqrt(Var);



