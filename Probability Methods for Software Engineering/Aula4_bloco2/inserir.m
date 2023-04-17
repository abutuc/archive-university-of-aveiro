
% Insere chave no filtro
function B_return = inserir(elem, B, k)
    str = elem;
    for i=1:k
        str = [str num2str(i)];
        hcode = string2hash(str);
        hcode = mod(hcode, length(B));
        B(hcode + 1) = 1;
    end
    B_return = B;
end

