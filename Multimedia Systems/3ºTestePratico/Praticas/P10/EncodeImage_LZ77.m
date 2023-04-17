function Stream = EncodeImage_LZ77(Image, Nw, Mw)    
    
    % Trocar valores da imagem pelo índice em Symb
    [Symb,~] = ImageSymbols(Image);
    for i = 1:length(Symb)
        Image(Image == Symb(i)) = i;
    end

    % Transformar imagem num vetor
    v = reshape(Image',1,[]);
    len_v = length(v);

    % Dados iniciais
    Stream = v(1:Nw);
    k = Nw+1; % Índice da primeira posição livre no vetor
    n = 1;    % Índice do primeiro valor da janela deslizante no vetor
    m = k;    % Índice do primeiro valor da janela de observação no vetor
    
    % Codificar vetor
    while m < len_v
        sliding   = v(n:Nw+n-1);
        observing = v(m:min(Mw+m-1, len_v)); % min(), pois podemos estar no final do vetor
        [pos,len] = GetPosLenPair(sliding, observing);
        if pos == 0
            % Não foi encontrada nenhuma sequência com 2 
            % ou mais elementos na janela de observação:
            Stream(k) = v(m);
            k = k + 1;
            n = n + 1;
            m = m + 1;
        else
            % Foi encontrada uma sequência:
            Stream(k:k+1) = [uint8(pos+128) len]; % o bit mais significativo é 1
            k = k + 2;
            n = n + len;
            m = m + len;
        end
    end
    
end

function [Symb, Freq] = ImageSymbols(Image)
    V = Image(:);
    [count,Symb] = groupcounts(V);
    Freq = count./length(V);
end

function [pos,len] = GetPosLenPair(sliding, observation)
    pos = 0;
    len = 0;
    Mw = length(observation);
    while Mw > 1
        k = strfind(sliding, observation(1:Mw));
        if isempty(k)
            Mw = Mw - 1;
        else
            pos = k(1);
            len = Mw;
            break;
        end
    end
end