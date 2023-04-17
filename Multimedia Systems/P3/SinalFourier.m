function [x, t] = SinalFourier(Ta, f0, Np, ak,bk)
    % Determina o sinal quadrado a partir dos coeficiente ak e bk.
    T0 = 1/f0;
    T = T0 * Np;
    t = 0:Ta:T-Ta;
    omega0 = 2*pi/T0;
    akSide = 0;
    bkSide = 0;
    for k=1:length(ak)
        akSide = akSide + ak(k)*cos(k*omega0*t);
        bkSide = bkSide + bk(k)*sin(k*omega0*t);
    end
    x = akSide + bkSide;
end
