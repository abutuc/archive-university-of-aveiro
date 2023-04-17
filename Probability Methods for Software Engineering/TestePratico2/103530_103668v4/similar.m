function similar(user_id,user_data,friends_min_hash)
    user_length=length(user_data); %number of users
    dist=ones(1,user_length); %array distancias

     for i = 1:user_length
        if i ~= user_id
            users_hash = [];
            other_users_hash = [];
            for b = 1:length(friends_min_hash(user_id, :))
                users_hash = [users_hash friends_min_hash(user_id, b)];
            end

            for b = 1:length(friends_min_hash(i, :))
                other_users_hash = [other_users_hash friends_min_hash(i, b)];
            end
            dist(i) = sum(users_hash ~= other_users_hash)/100; %distancia de Jaccard para todos os pares possiveis do user atual
            %dividir por 100 porque 100 é o numero de hash functions usadas
            %para calcular o friends_min_hash
        end
     end

     [~, user_similar] = min(dist);
     interesses_user=[];
     interesses_similar=[];
     sugestoes = [];

     for i=5:length(user_data(1,:))
        if(~isequal(class(user_data{user_similar, i}), 'missing') && ~isequal(class(user_data{user_id, i}), 'missing'))
            interesses_user=[interesses_user string(user_data{user_id, i})];
            interesses_similar=[interesses_similar string(user_data{user_similar, i})];
        end
    end

    for n = 1:length(interesses_similar)
        if (~ismember(interesses_similar(n), interesses_user))
            sugestoes = [sugestoes interesses_similar(n)];
        end
    end

    %fazer print dos interesses do utilizador com o ID inserido inicialmente
    fprintf("\nUtilizador->ID: %d - Lista de interesses:\n ", user_id);
    for i=1:length(interesses_user)
        fprintf("-%s;\n ",interesses_user(i));
    end
    
    fprintf("\n");
    %fazer print dos interesses do utilizador mais similar ao utilizador
    %com id inserido inicialmente
    fprintf("\nUtilizador Similar->ID: %d - Lista de Interesses: \n", user_similar);
    for i=1:length(interesses_similar)
        fprintf("-%s;\n",interesses_similar(i));
    end
    fprintf("\n");
    %fazer print de sugestões, estas sendo as categorias não presentes no
    %utilizador atual mas presentes no utilizador similar
    if isempty(sugestoes)  % Se nao houver sugestões
        fprintf('\nLista de sugestões vazia.\n');
    else
        fprintf("Lista de Sugestões: \n");
        for i=1:length(sugestoes)
            fprintf("-%s;\n",sugestoes(i));
        end
        fprintf("\n");
        fprintf("\n");
    end

end