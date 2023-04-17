function CodImage = CompressImage_RLE(ImMatrix)
    CodImage=[];
    [N, M] = size(ImMatrix);
    next=1;
    occur = 1;
    for line = 1:N
        if (next == 1)
            for collumn = 1:M-1
                if (ImMatrix(line, collumn) == ImMatrix(line, collumn+1))
                    occur = occur + 1;
                else
                    next = 0;
                    CodImage = CodImage + [ImMatrix(line, collumn) occur];
                    occur = 1;
                end
            end

        elseif (next == 0)
            for collumn = M:2
                if (ImMatrix(line, collumn) == ImMatrix(line, collumn-1))
                    occur = occur + 1;
                else
                    next = 1;
                    CodImage = CodImage + [ImMatrix(line, collumn) occur];
                    occur = 1;
                end
            end
        end
    end
end