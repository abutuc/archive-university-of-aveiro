function y = delay_im(X, N1, N2)
    y = zeros(size(X));
    if (N1 >= 0)
        y(N1+1: end, : , :)=X(1:end-N1, :, :);
    else
        y(1: end+N1, : , :)=X(1-N1:end, :, :);
    end

    if (N2 >= 0)
         y(:, N2+1:end, :)= y(:, 1:end-N2,:);
         y(:, 1:N2, :) = 0;
    else
        y(:,1:end+N2, :) = y(:, 1-N2:end,:);
        y(:, end+N2+1:end, :) = 0;
    end
end