function y=Chorus(x, fa, max_delay, Nc)
    Ta = 1/fa;
    D = fa*max_delay;
    d = rand(Nc, 1)*D;
    N = length(x);
    xc = zeros(N, Nc);
    for k1 = 1:Nc
        xc(:,k1) = my_delay(x, d(k1));
    end
    y = sum(xc, 2) + x;
    px = Potencia(x, Ta, Ta*N);
    py = Potencia(y, Ta, Ta*N);
    y = y * sqrt(px/py);