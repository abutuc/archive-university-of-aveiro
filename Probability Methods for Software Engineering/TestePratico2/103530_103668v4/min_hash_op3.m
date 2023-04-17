function names_min_hash=min_hash_op3(k,tam_shingle,user_data)
    names_min_hash = inf(length(user_data),k);
    for i = 1:length(user_data)
        nome_user = lower([user_data{i,2} ' ' user_data{i,3}]);
        shingles = {};
        for j= 1 : length(nome_user) - tam_shingle+1  % Criacao dos shingles 
            shingle = nome_user(j:j+tam_shingle-1);
            shingles{j} = shingle;
        end
        
        for q = 1:length(shingles)
            chave = char(shingles(q));
            hashcode = zeros(1,k);
            for hk = 1:k
                chave=[chave num2str(hk)];
                hashcode(hk)=string2hash(chave);
            end
            names_min_hash(i,:) = min([names_min_hash(i, :); hashcode]);                            % Valor minimo da hash para este shingle
        end
    end


end