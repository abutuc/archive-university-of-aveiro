function names_min_hash=min_hash_op3(k,tam_shingle,user_data)
    names_min_hash = inf(length(user_data),k);
    for us = 1:length(user_data)
        nome_user = lower([user_data{us,2} ' ' user_data{us,3}]);
        sngs = {};
        for j= 1 : length(nome_user) - tam_shingle+1 
            sh = nome_user(j:j+tam_shingle-1);
            sngs{j} = sh;
        end
        
        for q = 1:length(sngs)
            chave = char(sngs(q));
            hashcode = zeros(1,k);
            for hk = 1:k
                chave=[chave num2str(hk)];
                hashcode(hk)=string2hash(chave);
            end
            names_min_hash(us,:) = min([names_min_hash(us, :); hashcode]);                            % Valor minimo da hash para este shingle
        end
    end
end