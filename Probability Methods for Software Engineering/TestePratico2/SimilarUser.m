function [valor_jaccard, sim_user] = SimilarUser(msig, current_user, Nu)
    k = 100;
    J = zeros(1, Nu);

    for n1 = 1:Nu
        if (n1 == current_user)
            continue
        end
        J(n1) = sum(msig(n1, :) == msig(current_user, :))/k;
    end
    [valor_jaccard, sim_user] = max(J);
end