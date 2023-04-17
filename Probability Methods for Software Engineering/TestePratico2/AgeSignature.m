function sig = AgeSignature(Set, k)
    sig = inf(length(Set), k);
    for user = 1:length(Set)
        for hash = 1:k
            hash_Codes = zeros(length(Set(user)));
            for mov = 1:length(Set(user))
                key = char(Set{user}(mov));
                key = [key num2str(hash)];
                disp(key);
                hash_Codes(mov) = DJB31MA(key, 127);
            end
            sig(user, hash) = min(hash_Codes);
            break;
        end
    end
end