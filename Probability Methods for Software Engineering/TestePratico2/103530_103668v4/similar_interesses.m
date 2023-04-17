function similar_interesses(user_id,user_data,friends,interesses_min_hash,tam_shingle)
    fprintf("Lista de amigos do Utilizador %d\n",user_id);
    print_friends2(user_id,user_data,friends);
    amigo=input("Insira o id de um amigo: ","s");

    K=size(interesses_min_hash,2);

    interesses=[];
    for i=5:length(user_data(1,:))
        if(~isequal(class(user_data{amigo, i}), 'missing'))
            interesses=[interesses string(user_data{amigo, i})];
        end
    end
    
    inte_shingles = {};
    count=0;
    for k=1:length(interests)
        interest=char(interests(k));
        for j= 1 : length(interest) - shingle_size+1  % Criacao dos shingles parao nome de cada utilizador
            count=count+1;
            shingle = interest(j:j+shingle_size-1);
            inte_shingles{count} = shingle;
        end
    end

    inteF_min_hash=min_hash_op4s(K,inte_shingles,tam_shingle);
    % Distancia de Jaccard entre a string e cada utilizador
    dist = ones(1, size(user_data,1)); 
    for i=1:size(user_data, 1)  % cada hashcode da string

        dist(i) = sum(interesses_min_hash(i,:) ~= inteF_min_hash)/K;
    end

   for i = 1:3
        [~, pos] = min(dist);  %valor minimo--->mais similaridade
        fprintf('ID:%4d - %s %s\n', user_data{pos, 1}, user_data{pos, 2}, user_data{pos, 3});
        dist(pos) = 1; 
   end

end