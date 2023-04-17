Ta = 0.001;
T = 4;
t = 0:Ta:T;
x = cos(10*pi*t);
y = sin(20*pi*t);
z = ones(1,length(t));
w = (1/2)*cos(2*pi*t)+(1/2)*sin(4*pi*t);

xpot = potencia(x, Ta, T);
ypot = potencia(y, Ta, T);
zpot = potencia(z, Ta, T);
wpot = potencia(w, Ta, T);