function string_min_hash=min_hash_op3s(K,shingles)
    string_min_hash = inf(1,K);
    for q = 1:length(shingles)
            chave = char(shingles(q));
            hashcode = zeros(1,K);
            for hash = 1:K
                chave=[chave num2str(hash)];
                hashcode(hash)=string2hash(chave);
            end
            string_min_hash(1,:) = min([string_min_hash(1, :); hashcode]);  % Valor minimo da hash para este shingle
    end
end