function Qn=FiltraImagem(Q, fmax)
    [M, N] = size(Q);
    freq = 1/(M*N);

    CkF = fftshift(abs((fft2(Q)/(N*M))));
    for i=1:length(freq)
        if (freq(i) > fmax)
            CkF(i)=0;
        end
    end
    Qn = iffshift(ifft2(CkF));

end