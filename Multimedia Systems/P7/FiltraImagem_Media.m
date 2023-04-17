function imagem_med = FiltraImagem_Media(imagem, Np)
    imagem_new = double(imagem);
    imagem_med = zeros(size(imagem));
    for k1 = -(Np-1)/2:(Np-1)/2
        for k2 = -(Np-1)/2:(Np-1)/2
            imagem_med = imagem_med + delay_im(imagem_new, k1, k2);
        end
    end
    imagem_med = imagem_med/Np^2;
    subplot(1, 2, 1);
    imshow(imagem);
    subplot(1, 2, 2);
    imshow(uint8(imagem_med));
end
