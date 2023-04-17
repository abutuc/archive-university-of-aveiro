function entropia = Entropia(probabilidades)
    N = length(probabilidades);
    somatorio = 0;
    for i=1:N
        somatorio = somatorio + probabilidades(i)*log2(probabilidades(i));
    end
    entropia = -somatorio;
end