function find_name(user_data,names_min_hash,tam_shingle,limiar)
    prompt='\nWrite a string: ';
    str=input(prompt,"s");
    filtro=create_filtro(length(user_data));
    K=size(names_min_hash,2);

    for i=1:length(user_data)
        filtro = add(lower([user_data{i,2} ' ' user_data{i,3}]), filtro, K);
    end

    existe = check(filtro,str , K);

    if existe
        fprintf("A String inserida existe na lista de utilizadores\n")
    else
        fprintf("A String inserida não existe na lista de utilizadores\n")
    end
    
    % Cell array com os shingles da string introduzida
    str_shingles = {};
    for s = 1:length(str) - tam_shingle+1
        shingle = str(s:s+tam_shingle-1);
        str_shingles{s} = shingle;
    end

    %k=150;
    string_min_hash=min_hash_op3s(K,str_shingles);%minhash dos shingles da string
     % Distancia de Jaccard entre a string e cada utilizador
    dist = ones(1, size(user_data,1));  % array para guardar distancias
    for i=1:size(user_data, 1)  % cada hashcode da string
        dist(i) = sum(names_min_hash(i,:) ~= string_min_hash)/K;
    end
    
    
    if existe
        fprintf("\nLista do nomes dos utilizadores:\n");
    end

    for i = 1:7 % 7-maximo de nomes da lista(informação do enunciado)
        [val, pos] = min(dist);  %valor minimo ---> mais similaridade
        
        if (val <= limiar)  % Se o valor minimo já nao pertencer ao threshold não dá print
            fprintf('%s %s : (%f)\n', user_data{pos, 2}, user_data{pos, 3}, val);
        end
        dist(pos) = 1;  % Retirar esse nome dando uma distancia igual a 1
    end
end
