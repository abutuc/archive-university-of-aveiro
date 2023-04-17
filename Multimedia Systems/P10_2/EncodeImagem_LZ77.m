function Str = EncodeImagem_LZ77(Im, Nw, Mw)
    Im = Im';
    Im = Im(:);
    JanD = Im(1:Nw);
    JanO = Im(Nw+1:Nw+Mw);
    pos = Nw + 1;
    Str = JanD;
    N = length(Im);
    stop = false;
    while (~stop)
        for Mc = 2:Mw
            Seq =  JanO(1:Mc);
            pos = seq_find(JanD, Seq);
            if (isempty(pos))
                    break;
            end
        end
        
    end
end