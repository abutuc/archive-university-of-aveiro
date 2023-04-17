function [Simbolos, Frequencia] = Alfabeto2(Texto)
    Simbolos = Texto(1);
    for i=2:length(Texto)
        if(~contains(Simbolos, Texto(i)))
            Simbolos = [Simbolos, Texto(i)];
        end
    end
    Frequencia = zeros(size(Simbolos));
    for f=1:length(Simbolos)
        for s=1:length(Texto)
            if(~contains(Simbolos(f), Texto(s)))
                continue
            else
                Frequencia(f) = Frequencia(f)+1;
            end
        end
    end
    Frequencia = (Frequencia/length(Texto)) * 100;
end