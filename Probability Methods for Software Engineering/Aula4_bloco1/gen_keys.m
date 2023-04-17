function [keys]=gen_keys(N, tam_min, tam_max, alfabeto, probabilidades)
    if (nargin == 5)
        probabilidades = cumsum(probabilidades);
    end
    keys=cell(1, N);
    for i=1:N
        l = randi([tam_min, tam_max], 1);
        switch nargin
            case 5
                v = zeros(1, l);
                count = 1;
                for s=1:l
                    p = rand(1,1);
    
                    if ((0 < p) && (p <= probabilidades(1)))
                        v(count) = 1;
                        count = count + 1;
                        continue;
                    end
                    for f=2:length(probabilidades)
                        if ((probabilidades(f-1) < p) && (p <= probabilidades(f)))
                            v(count) = f;
                            count = count + 1;
                            break;
                        end
                    end
                end
            case 4
                v = randi(length(alfabeto), 1, l);
        end
        word = join(alfabeto(v));
        keys(i)={word};
    end

end
