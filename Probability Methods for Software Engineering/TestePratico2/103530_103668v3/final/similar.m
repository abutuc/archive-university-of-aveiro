function [dist_min_jaccard, user_similar] = similar(user_id,user_data,friends_min_hash, k)
    user_length=length(user_data); % number of users
    dist=ones(1,user_length); % inicializa array para guardar as distancias de jaccard

     for i = 1:user_length
        if i == user_id
            continue
        end
        mapping = friends_min_hash(i,:) == friends_min_hash(user_id,:); % verificar quais são os hashcodes que são iguais entre linhas da hashTable.
        dist(i) = 1-(sum(mapping)/k); % calculo da distancia de Jaccard entre os utilizadores 
                                                                                   % e o utilizador que iniciou sessão com o critério de similaridade de
                                                                                   % amigos com idades em comum
     end

     [dist_min_jaccard, user_similar] = min(dist); % filtração da distância de jaccard mais pequena, guardando a distância de jaccard e o indice do utilizador semelhante
   
     % somente os campos de interesse em que ambos os utilizadores têm
     % conteúdo serão avaliados e guardados
     mult_count = 1;
     for i=5:length(user_data(1,:))
        if(~ (isequal(class(user_data{user_similar, i}), 'missing') || isequal(class(user_data{user_id, i}), 'missing')))
            suser = user_data{user_id, i};
            iuser(mult_count) = string(suser);
            ssim = user_data{user_similar, i};
            isimilar(mult_count) = string(ssim);
            mult_count = mult_count + 1;
        end
    end
    
    % verifica a presença de interesses por parte semelhante nos interesses
    % do utilizador que iniciou a sessão
    s_count = 1;
    for n = 1:length(isimilar)
        if (~ismember(isimilar(n), iuser))
            sug(s_count) = isimilar(n);
            s_count = s_count + 1;
        end
    end

    %fazer print dos interesses do utilizador mais similar ao utilizador
    %que iniciou a sessão
    fprintf("\nUtilizador Similar\n");
    fprintf("ID: %d \n", user_similar);
    fprintf("Lista de Interesses: \n");
    for i=1:length(isimilar)
        fprintf("-%s;\n",isimilar(i));
    end
    fprintf("\n");
    %fazer print de sugestões, estas sendo as categorias não presentes no
    %utilizador atual mas presentes no utilizador similar
    if isempty(sug)
        fprintf('\nLista de sugestões vazia.\n');
    else
        fprintf("Lista de Sugestões: \n");
        for i=1:length(sug)
            fprintf("-%s;\n",sug(i));
        end
        fprintf("\n");
        fprintf("\n");
    end

end