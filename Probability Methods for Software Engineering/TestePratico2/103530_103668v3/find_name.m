function [filtro] = find_name(user_data,names_min_hash,tam_shingle,limiar)
    prompt='\nWrite a string: ';
    str=lower(input(prompt,"s"));
    filtro=create_filtro(length(user_data));
    K=size(names_min_hash,2);

    for i=1:length(user_data)
        filtro = add(lower([user_data{i,2} ' ' user_data{i,3}]), filtro, K);
    end

    existe = check(filtro,str,K);

    if existe
        fprintf("\n A String introduzida pode corresponder exatamente a um dos nomes dos utilizadores.\n");
    end
    
    % Cell array com os shingles da string introduzida
    str_shingles = {};
    for s = 1:length(str) - tam_shingle+1
        shi_cell = s:s+tam_shingle-1;
        shingle = str(shi_cell);
        str_shingles{s} = shingle;
    end

    string_min_hash=min_hash_op3s(K,str_shingles);%minhash dos shingles da string
     % Distancia de Jaccard entre a string e cada utilizador
    jac = ones(1, size(user_data,1));  % array para guardar distancias
    for i=1:size(user_data, 1)  % cada hashcode da string
        mapping = names_min_hash(i,:) == string_min_hash;
        jac(i) = 1- (sum(mapping)/K);
    end
    
    
    fprintf("\nUtilizadores encontrados:\n");

    d = 1;
    count = 0;
    while(d)
        [dis, use] = min(jac);  % retira o valor mínimo do array das distância de jaccard
        if (dis <= limiar)  % se o valor mínimo não exceder o limiar, então imprime o utilizador
            fprintf('Nome Completo: %s %s (Distância de Jaccard: %f)\n', user_data{use, 2}, user_data{use, 3}, dis);
        end
        jac(use) = 1;  % "remove" o mínimo retirado, passando o utilizador mais semelhante a ser dos mais distantes
        count = count + 1;
        if (count > 7)  % condição de paragem
            d = 0;
        end
    end
end
