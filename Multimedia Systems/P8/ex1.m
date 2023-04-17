clear
clc 
close all

load("Mensagem.mat");
%Alfabeto1(Mensagem)
%Mensagem = 'AAA';
[Simbolos, Frequencia] = Alfabeto2(Mensagem);
NumBits = NumeroBits(Mensagem);
H = Entropia(Mensagem);