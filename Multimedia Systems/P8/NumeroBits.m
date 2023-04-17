function NumBits = NumeroBits(Texto)
    [s, f] = Alfabeto2(Texto);
    [f, ind] = sort(f, "descend");
    s = s(ind);
    nbs = 1:1:length(s);
    for k=1:length(s)
        ns = sum(Texto == s(k));
        nb(k) = ns * nbs(k);
    end
    NumBits = sum(nb);
end