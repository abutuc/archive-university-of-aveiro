function [Symb, Freq] = ImageSymbols(Image)
    
    V = Image(:);
    [count,Symb] = groupcounts(V);
    Freq = count./length(V);

end