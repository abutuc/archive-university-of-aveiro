function y=NullPhaseSignal(x, Ta)
    N = length(X);
    %T = Ta*N;
    %fa = 1/Ta;
    espetroX = fftshift(fft(x)/N);
    %f = (-fa/2):(fa/N):((fa/2) - fa/N);
    espetroY = abs(espetroX);
    y = ifft(ifftshift(espetroY))*N;
end
