function friends_min_hash=min_hash_op2(k,friends,user_data)
    user_length=length(user_data);
    frd_length=length(friends);
    friends_min_hash=inf(user_length,k);
    user_friends = cell(user_length, 1);

    for i = 1:frd_length
        user = friends{i, 1};
        friend = friends{i, 2};
        user_friends{user, 1} = [user_friends{user, 1} friend];
    end
    
    for i=1:user_length
        for j=1:length(user_friends{i, 1})
            chave=char(user_data{user_friends{i,1}(j), 4});
            hashcode=zeros(1,k);
             
            for hk=1:k
                chave=[chave num2str(hk)];
                hashcode(hk)=string2hash(chave);
            end
            
            friends_min_hash(i,j)=min(hashcode);
        end
    end
end