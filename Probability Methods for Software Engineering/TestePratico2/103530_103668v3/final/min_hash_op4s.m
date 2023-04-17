function inteF_min_hash = min_hash_op4s(k,inte_shingles)
    inteF_min_hash = inf(1,k);
    for i = 1:length(inte_shingles)
        chave = char(inte_shingles{i});
        hashCodes = zeros(1,k);
        for hash = 1:k
            chave = [chave num2str(hash)];
            hashCodes(hash) =string2hash(chave);
        end
        inteF_min_hash(1,:) = min([inteF_min_hash(1,:); hashCodes]);
    end
end