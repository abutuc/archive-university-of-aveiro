function pot = Potencia(x, Ta, T)
% Determina a potência associada a um sinal com o vetor de amostras (x),
% período de amostragem (Ta) e o período do sinal (T).
    x2 = x.^2;
    area = x2*Ta;
    integral = sum(area);
    pot = integral / T;