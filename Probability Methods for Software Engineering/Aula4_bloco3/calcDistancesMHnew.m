clear
close all
clc

v = initHashFunctions(N, Nh); 
for nh = 1:Nh
    MH(nh, nu) = mod(v.a(nh) * fs(1) + v.b(nh), v.p);

    for nf = 2:length(fs)
        htmp = mod(v.a(nh)*(fs(nf)) + v.b(nh), v.p);

        if htmp < MH(nh, num2str)
            MH(nh, nu) = htmp;
        end
    end
end