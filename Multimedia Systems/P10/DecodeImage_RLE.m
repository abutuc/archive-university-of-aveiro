function Image = DecodeImage_RLE(N, M, Stream)
    K = length(Stream);
    Image = [];
    for k = 1:2:K-1
        simb = Stream(k);
        count = Stream(k+1);
        Im_parc = repformat(simb, count, 1);
        Image = [Image; Im_parc];
    end
    Image = uint8(reshape(Image, M, N))';
end