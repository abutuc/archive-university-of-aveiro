clear
clc
close all

udata=load('u.data'); % Carrega o ficheiro dos dados dos filmes
% Fica apenas com as duas primeiras colunas
u= udata(1:end,1:2); clear udata;

users = unique(u(:,1)); % Extrai os IDs dos utilizadores
Nu= length(users); % Nu´mero de utilizadores

Set = create(users,Nu, u);

Signatures = signature(Set, 2);   % devolve uma matriz com as assinaturas 
% a matriz tem a seguinte estrutura
% 
Similarity = similarity(Signatures);
unique_sim = unique(Similarity);
People_Similar_To_User_1 = sum(Similarity(2, :) > 0.0);

compare = Signatures([1 76], :);