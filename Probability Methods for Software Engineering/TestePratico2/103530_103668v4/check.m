function existe=check(filtro,str,k)
    existe=true;
    chave=str;
    for i=1:k
        chave=[chave num2str(i)];
        hc=string2hash(chave);
        hc=mod(hc,length(filtro));

        if filtro(hc+1)~=1
            existe=false;
            break
        end
    end


end