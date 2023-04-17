%% Clear
clear;
close all;
clc;

%% 

N = 10^5;
imin = 6;
imax = 20;
alfabeto = ['a':'z' 'A':'Z'];

keys = gen_keys(N, imin, imax, alfabeto);


n = 1e6;

sizes=[1e5 1e6 1e7];
hCodes = zeros(length(sizes), N);
count = zeros(length(sizes), n);

for siz=1:length(sizes)
    for i = 1:N
        key = keys{i};
        hCode = mod(hashstring(key, sizes(siz))+1, n) ;
        hCodes(siz, i) = hCode;
        count(siz,hCode) = count(siz,hCode) + 1;
    end
end
figure(1);
histogram(hCodes);

figure(2);
histogram(count);