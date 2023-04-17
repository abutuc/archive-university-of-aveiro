function filtro_return=add(filtro,elemento,k)
    str=elemento;
    for i=1:k
        str=[str num2str(i)];
        hc=string2hash(str);
        hc=mod(hc,length(filtro));
        filtro(hc + 1)=1;
    end
    filtro_return=filtro;
end