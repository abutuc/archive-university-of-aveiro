function Simbolos = Alfabeto1(Texto)
    Simbolos = Texto(1);
    for i=2:length(Texto)
        if(~contains(Simbolos, Texto(i)))
            Simbolos = [Simbolos, Texto(i)];
        end
    end
end