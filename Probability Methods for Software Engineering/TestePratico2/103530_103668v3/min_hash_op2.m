function friends_min_hash=min_hash_op2(k,friends,user_data)
    user_length=length(user_data); % número de utilizadores
    frd_length=length(friends);    % número de amizades
    friends_min_hash=inf(user_length,k);    % inicialização da MinHash
    user_friends = cell(user_length, 1);    % inicialização da estrutura de dados que contém a listagem dos amigos
    
    % construção da estrutura de dados que contém a lista de amigos
    for i = 1:frd_length
        user = friends{i, 1};
        friend = friends{i, 2};
        user_friends{user, 1} = [user_friends{user, 1} friend];
    end
    
    % construção da MinHash
    for i=1:user_length
        for j=1:length(user_friends{i, 1})
            chave=char(user_data{user_friends{i,1}(j), 4});
            hashcode=zeros(1,k);
      
            % geração dos k hashcodes
            for hash=1:k
                chave=[chave num2str(hash)];
                hashcode(hash)=string2hash(chave);
            end
            
            friends_min_hash(i,:)=min([friends_min_hash(i,:); hashcode]);
        end
    end
end