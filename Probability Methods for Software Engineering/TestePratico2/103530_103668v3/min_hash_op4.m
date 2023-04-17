function interesses_min_hash=min_hash_op4(k,tam_shingle,user_data)
    interesses_min_hash = inf(length(user_data),k);
    for i = 1:length(user_data)
        interesses_friends=[];
        for p=5:length(user_data(i,:))
            if(~isequal(class(user_data{i, p}), 'missing'))
                interesses_friends=[interesses_friends string(user_data{i, p})];
            end
        end
    
        shingles = {};
        count = 0;
        for q=1:length(interesses_friends)
            interest=char(interesses_friends(q));
            for j= 1 : length(interest) - tam_shingle+1  % Criacao dos shingles parao nome de cada utilizador
                count = count+1;
                shingle = interest(j:j+tam_shingle-1);
                shingles{count} = shingle;
            end
        end
        
        for j = 1:length(shingles)
            chave = char(shingles(j));
            hashcode = zeros(1,k);
            for hk = 1:k
                chave = [chave num2str(hk)];
                hashcode(hk) = string2hash(chave);
            end
            interesses_min_hash(i,:) = min([interesses_min_hash(i,:);hashcode]); 
        end
    end
    
end