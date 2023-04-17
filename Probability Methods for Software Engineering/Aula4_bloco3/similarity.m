function dist = similarity(signature)
    [Nu, Nh] = size(signature);
    dist = zeros(Nu, Nu);
    for n1 = 1:Nu
        for n2 = n1+1:Nu
            dist(n1, n2) = sum(signature(n1, :) == signature(n2, :));
        end
    end
end