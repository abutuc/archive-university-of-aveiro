function Image = DecodeImage_LZ77(N,M,Nw,Mw,Symb,Stream)

    Buffer = uint8(zeros(N*M,1)); % inicializar buffer
    Buffer(1:Nw) = Stream(1:Nw); % copiar os primeiros elementos 

    si = uint32(Nw+1); % índice na stream
    bi = uint32(Nw+1); % índice no buffer

    while si < length(Stream)

        if Stream(si) > 127
            % par posição-comprimento
            pos = uint32(Stream(si)-uint8(128));
            len = uint32(Stream(si+1));
            Buffer(bi:(bi+len-1)) = Buffer((bi-Nw+pos-1):(bi-Nw+pos+len-2));
            si = si+2;
            bi = bi+len;
        else
            % símbolo individual
            Buffer(bi) = Stream(si);
            si = si+1;
            bi = bi+1;
        end

    end

    ImIndex = uint8(zeros(N,M));
    for n=1:N
        ImIndex(n,:) = Buffer((1:M) + M*(n-1))'; 
    end

    Image = uint8(zeros(N,M));
    for i=1:length(Symb)
        Image(ImIndex == i) = Symb(i);
    end

end