clear
clc
close all

user_data=readcell("utilizadores.txt",'Delimiter',';');
friends=readcell("amigos.txt",'Delimiter',' ');

[Nu, max_team] = size(user_data);
[Nfriends, Datafriends] = size(friends);
users_struct_age= cell(Nu,1);
for friend_entry = 1:Nfriends
    users_struct_age{cell2mat(friends(friend_entry, 1))} = [users_struct_age{cell2mat(friends(friend_entry, 1))} cell2mat(user_data(cell2mat(friends(friend_entry, 2)), 4))];
end
save("user_struct_age.mat", 'users_struct_age');

users_interests = cell(Nu, 1);
for user = 1:Nu
    for field=5:max_team
        users_interests{cell2mat(user_data(user, 1))} = [users_interests{cell2mat(user_data(user, 1))} user_data(user, field)];
    end
end
save("user_interests.mat", 'users_interests');


users_names = cell(1, 1);
for user = 1:Nu
    for field=2:3
        users_names{1} = [users_names{1} user_data(user, field)];
    end
end
save("users_names.mat", 'users_names')
shing = shingles(users_names{1,1}, 3);
save("shingles.mat", "shing");