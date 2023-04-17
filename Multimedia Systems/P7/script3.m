%% Clear
clear;
close all;
clc;
load Guitar03.mat

[X, f] = Espetro(x, 1/fa, 0, 1);

for i=1:length(f)
    if (f(i) > 400) || (f(i) < 100)
        X(i)=0;
    end
end

w = Reconstruct(X, f);
plot(f, abs(w));
sound(abs(w), fa);