function [X,f, windowing] = Espetro(x, Ta, w, fig)
    %% Constants
    N = length(x);
    fa = 1/Ta;
    
    %% Windowing
    if (w)
       windowing = blackman(N)';
       x = x.*windowing';
    end
    %% Frequency
    k = 1:N;
    f = (k-1)*fa/N;
    f = ifftshift(f);
    f = f-f(1);
    f = fftshift(f);

    %% Spectrum
    X = fft(x)/N;
    X = fftshift(X);
    %% Plot
    figure(fig);
    plot(f,abs(X));
    xlabel('Frequency (Hz)');
    ylabel('|X(f)|');
    title('Espetro de x');
end