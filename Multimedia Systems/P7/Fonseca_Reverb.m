function y=Fonseca_Reverb(x,fa,Delay,Gain)
    
    N=length(x);
    y=x;

    da=round(Delay*fa);
    for i=1:N
        if mod(i,da) == 0
            y(i:end)=y(i:end)+Gain*y(1:end-i+1);
        end
    end

    y=sqrt(sum(x.^2)/sum(y.^2)).*y;
    
end