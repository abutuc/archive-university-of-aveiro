function sig = signature(Set, k)
    sig = zeros(length(Set), k);
    prime = 4294967311;    %High prime number, used in our Hash Function
    aVals = rand(1,k)*prime + 1;
    bVals = rand(1,k)*prime + 1;
    for user = 1:length(Set)
        for hash = 1:k
            hash_Codes = zeros(length(Set(user)));
            for mov = 1:length(Set(user))
                hash_Codes(mov) = round(mod(bVals(hash) * Set{user}(mov) + aVals(k-hash+1),prime));
            end
            sig(user, hash) = min(hash_Codes);
        end

    end
end