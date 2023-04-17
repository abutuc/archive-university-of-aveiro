function Stream = EncodeImage_RLE(Image)
    Image = Image';
    Image = Image(:);
    N = length(Image);
    count = 1;
    simb = Image(1);
    Stream = [];
    for pos = 2: N
        if (Image(pos) == simb)
            count = count + 1;
        else
            Stream = [Stream; [simb, count]];
            simb = Image(pos);
            count = 1;
        end
    end
    Stream = [Stream; [simb, count]];
    Stream = Stream';
    Stream = Stream(:);
end