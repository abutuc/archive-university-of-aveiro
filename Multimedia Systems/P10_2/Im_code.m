function ImS = Im_code(Im, Symb)
    K = length(Symb);
    ImS = zeros(size(Im));
    for k = 1:K
            ImS(Im == Symb(k)) = k;
    end
end