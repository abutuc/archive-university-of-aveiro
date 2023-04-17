function c=MainComponent(x,Ta)
    N = length(x);
    fa = 1/Ta;
    k = 1:N;
    f = (k-1)*fa/N;
    f = ifftshift(f);
    f = f-f(1);
    f = fftshift(f);
    l = 0;
    X = fft(x)/N;
    X = fftshift(X);
    magnitude = round(max(abs(X)), 4);
    for i=1:length(X)
        if(round(max(abs(X(i))), 4) == magnitude)
            l = abs(f(i));
            break
        end
    end
    
    t = 0:Ta:(N*Ta)-Ta;
    c = magnitude*sin(2*pi*l*t);
end