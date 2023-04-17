function [z,w] = ExchangePhase(x,y)
    N = length(x);
    ck_x = real(fft(x));
    fase_x = imag(fft(x));
    ck_y = real(fft(y));
    fase_y = imag(fft(y));
    z = ck_x + fase_y;
    w = ck_y + fase_x;
    z = ifft(z);
    w = ifft(w);
end