function [keys]=gen_keys(N, tam_min, tam_max, alfabeto)
    keys=cell(1, N);
    for i=1:N
        l = randi([tam_min, tam_max], 1);
        v = randi(length(alfabeto), 1, l);
        word = join(alfabeto(v));
        keys(i)={word};
    end
end
