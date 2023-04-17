function x=FreqToTime(Ck, f, Ta)
    f_positivo = zeros(1, length(f));
    count = 1;
    for i=1:length(f)
        if (f(i) >= 0)
            f_positivo(count) = f(i);
            count = count + 1;
        end
    end
    f_positivo = sort(f_positivo);
    periodos = 1/f_positivo;
    
    x = zeros(1, length(f_positivo));
    for s=f_positivo
    x = x + ifft(Ck(s));
    end
end