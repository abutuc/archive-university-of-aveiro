function NumBits = NumeroBits(Texto)

    [dic,f] = Alfabeto2(Texto);
    N = length(Texto);
    M = length(dic);
    
    f_sorted = sort(f,"descend");

    NumBits = sum(f_sorted*N.*(1:M));
    
end