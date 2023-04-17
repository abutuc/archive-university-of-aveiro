function Stream = EncodeImage_RLE(Image)
    
    Image = Image';
    pixels = Image(:);

    si = 1;
    Stream = uint8([pixels(si); 1]);

    % Para cada pixel
    for i=2:length(pixels)
        if pixels(i) == Stream(si) && Stream(si+1) ~= uint8(255)
            Stream(si+1)=Stream(si+1)+uint8(1);
        else
            si=si+2;
            Stream(si)=pixels(i);
            Stream(si+1)=1;
        end

    end

end