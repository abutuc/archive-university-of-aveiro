function Image = DecodeImage_RLE(N,M,Stream)

    Buffer = uint8(zeros(N*M,1));
    
    si = uint32(1);
    bi = uint32(1);
    
    while si < length(Stream)

        len = uint32(Stream(si+1));
        Buffer(bi:(bi+len-1)) = uint8(ones(len,1))*Stream(si);
        si = si+2;
        bi = bi+len;

    end

    Image = uint8(zeros(N,M));
    for n=1:N
        Image(n,:) = Buffer((1:M)+M*(n-1))';
    end

end