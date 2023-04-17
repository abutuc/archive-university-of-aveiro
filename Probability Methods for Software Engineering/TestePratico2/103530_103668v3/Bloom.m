function filtro = Bloom(user_data, names_min_hash)
    filtro=create_filtro(length(user_data));
    K=size(names_min_hash,2);
    for i=1:length(user_data)
        filtro = add(lower([user_data{i,2} ' ' user_data{i,3}]), filtro, K);
%         fame = [user_data{i,2} ' ' user_data{i,3}];
%         lower(fame);
%         filtro = add(fame, filtro, K);
    end
end