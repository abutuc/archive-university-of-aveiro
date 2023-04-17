function Espetro2(Q,passo, fig)
    fa = 1/passo;
    N1 = size(Q,1); 
    N2= size(Q,2);
    f1 = (0:N2-1)*fa - fa/2;
    f2 = (0:N2-1)*fa - fa/2;
    X = fft2(Q);
    X = fftshift(X)/(N1*N2);
    [F1,F2] = meshgrid(f1,f2);
    figure(fig)
    view(2)
    contourf(F1,F2,abs(X))
end

