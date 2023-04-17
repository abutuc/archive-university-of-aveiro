function Nbits = NumeroBits(Mesg)
    N = length(Mesg);
    symbols = unique(Mesg);
    Nsymbols = length(symbols);
    freq = zeros(Nsymbols);
    for i=1:N
        for f=1:Nsymbols
            if(Mesg(i) == symbols(f))
                freq(f) = freq(f) + 1;
            end
        end
    end
    freq = sort(freq, "descend");
    Nbits = 0;
    for s=1:length(freq)
        Nbits = Nbits + s-1 + 1;
    end
end