function data_struct = create(users,Nu,text)
    data_struct= cell(Nu,1); % Usa ce´lulas
    for n = 1:Nu % Para cada utilizador
        % Obte´m os filmes de cada um
        ind = find(text(:,1) == users(n));
        % E guarda num array. Usa ce´lulas porque utilizador tem um nu´mero
        % diferente de filmes. Se fossem iguais podia ser um array
        data_struct{n} = [data_struct{n} text(ind,2)];
    end
end