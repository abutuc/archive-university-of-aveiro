function H = Entropia(Texto)
    [s, f] = Alfabeto2(Texto);
    f=f/100;
    H = -sum(f.*log2(f));
end