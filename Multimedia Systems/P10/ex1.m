close all
clc
clear

[Imagem, ColorMap] = imread('Parede_8bit.bmp');
imshow(Imagem, ColorMap);
stream = EncodeImage_RLE(Imagem);
[N, M] = size(Imagem);
re_Image = DecodeImage_RLE(stream, N, M);