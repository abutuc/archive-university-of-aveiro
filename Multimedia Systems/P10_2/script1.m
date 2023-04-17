clear
clc
close all

[Imagem, ColorMap] = imread('Parede_8bit.bmp');
[Symb, Freq] = ImageSymbols(Imagem);

stream = Im_code(Imagem, Symb);

