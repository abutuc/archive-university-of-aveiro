function [Simbolos,Frequencia] = Alfabeto2(Texto)

    Simbolos = unique(Texto);

    N = length(Texto);
    M = length(Simbolos);

    Frequencia = zeros(1,M);
    for i = 1:M
        Frequencia(i) = count(Texto, Simbolos(i))./N;
    end

end