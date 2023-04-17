function F = Fonseca_FiltraImagem_Media(I,Np)
    [h,w,rgb] = size(I);

    % "Raio" do quadrado, excluindo o píxel central
    sq_radius = (Np-1)/2;

    % Imagem (F)iltrada
    F = uint8(zeros(h,w,rgb));
    
    % Para cada píxel da imagem
    for x=1:h
        for y=1:w
            
            % Limites do quadrado na imagem
            x_min = x-sq_radius;
            x_max = x+sq_radius;
            y_min = y-sq_radius;
            y_max = y+sq_radius;
            % Não podem exceder os limites da imagem
            if (x_min < 1); x_min = 1; end
            if (x_max > h); x_max = h; end
            if (y_min < 1); y_min = 1; end
            if (y_max > w); y_max = w; end

            % Determinar os píxeis do quadrado
            sq = I(x_min:x_max, y_min:y_max, :);
            
            % Média dos valores RGB
            med_r = mean(sq(:,:,1),"all");
            med_g = mean(sq(:,:,2),"all");
            med_b = mean(sq(:,:,3),"all");

            % Guardar os novos valores RGB
            F(x,y,1) = floor(med_r);
            F(x,y,2) = floor(med_g);
            F(x,y,3) = floor(med_b);

        end
    end
    
    figure;
    subplot(1,2,1);
    imshow(I); title("Original");
    subplot(1,2,2);
    imshow(F); title("Filtrada");

end