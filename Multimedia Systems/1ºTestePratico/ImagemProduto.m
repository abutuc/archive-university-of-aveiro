function img = ImagemProduto(Q1, Q2)
    img = Q1 .* Q2;
    figure(3)
    mesh(img)
    view(2)
end