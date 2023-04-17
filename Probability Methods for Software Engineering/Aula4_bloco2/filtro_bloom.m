clear
close all
clc

n = 8000;
n_palavras = 10000;
B = inicializar(n);

k = 3;
alfabeto = ['a':'z' 'A':'Z'];
elem = gen_keys(n_palavras, 10, 40, alfabeto);
elem2 = {};
while (length(elem) ~= length(elem2))
    elem2 = gen_keys(n_palavras, 10, 40, alfabeto);
    elem2 = setdiff(elem2, elem);
end

for i=1:length(elem)
    B = inserir(elem{i}, B, k);
end

figure(2);
stem(B);
sum(B);

%strs={'Braga', 'Londres', 'Aveiro'};
r = 0;
falsos_negativos = 0;
for i = 1:length(elem)
    r = verificar(elem{i}, B, k);
    if r
        continue
    else
        falsos_negativos = falsos_negativos + 1;
    end
end


falsos_positivos = 0;
for i = 1:length(elem2)
    r = verificar(elem2{i}, B, k);
    if r
       falsos_positivos = falsos_positivos + 1;
    end
end




