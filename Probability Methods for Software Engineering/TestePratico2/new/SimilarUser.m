function [jaccard, similar_user] = SimilarUser(Nu, FriendsMh, user, k)
    S = zeros(1, Nu);
    for n = 1:Nu
        if n == user
            continue
        end
        S(n) = sum(FriendsMh(n, :) == FriendsMh(user, :));
    end
    [jaccard, similar_user] = max(S);
end