% Verifica que se a chave está no filtro ou não
function flag = verificar(elem, B, k)
    flag = true;
    str = elem;
    for i=1:k
        str = [str num2str(i)];
        hcode = string2hash(str);
        hcode = mod(hcode, length(B));
        if B(hcode+1) ~= 1
            flag = false;
            break;
        end
    end
end