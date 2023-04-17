function [Symb, Freq] = ImagemSymbols(Image)
    Image = Image(:);
    Symb= unique(Image);
    K = length(Symb);
    for k =1:K
        Freq(k) = sum(Image == Symb(k));
    end
    Freq = Freq/sum(Freq);
end