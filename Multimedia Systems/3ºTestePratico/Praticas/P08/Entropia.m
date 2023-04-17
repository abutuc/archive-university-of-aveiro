function H = Entropia(Texto)
    [dic,f] = Alfabeto2(Texto);
    H = sum(f.*log2(1./f));
end