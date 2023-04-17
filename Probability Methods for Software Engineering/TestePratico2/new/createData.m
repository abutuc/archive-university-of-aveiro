clear
clc
close all

user_data=readcell("utilizadores.txt",'Delimiter',';'); % reads user personal data
friends_data=readcell("amigos.txt",'Delimiter',' '); % reads friend connections

Nu = length(user_data);         % number of users
Nc = length(friends_data);      % number of friend connections

% Estrutura para a opção 1, Cell Array dos amigos de cada user.
friends = cell(Nu, 1);
for i = 1:Nc
    user = friends_data{i, 1};
    friend = friends_data{i, 2};
    friends{user, 1} = [friends{user, 1} friend];
end
save("Friends.mat", "friends");
% Estrutura para a opção 2, MinHash das idades dos amigos do utilizador
k = 100;
FriendsMh = inf(Nu, k);
a = randi([1 3865608599], k);
b = randi([1 4320055217], k);
for i = 1:Nu
    friends_list = friends{i};
    for j=1:length(friends_list)
        key = user_data{friends_list(j), 4};
        hash = zeros(1, k);
        for hf = 1:k
            %key = [key num2str(hf)];
            %hash(hf) = DJB31MA(key, 127);
            hash(hf) = a(hf) * key + b(hf);
        end
        FriendsMh(i, :) = min([FriendsMh(i, :); hash]);
    end
end
save("FriendsMh.mat", "FriendsMh");

