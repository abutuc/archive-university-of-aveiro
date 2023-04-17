function Xd = my_delay(X, d)
    Xd = zeros(size(X));
    d =round(d);
    Xd(d+1:end)= X(1:end-d);
end