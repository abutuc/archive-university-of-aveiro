clear;
close all;
clc;

user_data=readcell("utilizadores.txt",'Delimiter',';');                                             % dic with user data included in utilizadores.txt
friends=readcell("amigos.txt",'Delimiter',' ');                                             %dic with friend information from each person
k=100; % number of hash functions

%friends_min_hash=min_hash_op2(k,friends,user_data);
k=150;%number of hash functions
tam_shingle=3; %shingle
%names_min_hash=min_hash_op3(k,tam_shingle,user_data);
interesses_min_hash=min_hash_op4(k,tam_shingle,user_data);
save("user_data.mat", "user_data");
save("friends.mat", "friends");
%save("friends_min_hash.mat", "friends_min_hash");
%save("names_min_hash.mat", "names_min_hash");
save("interesses_min_hash.mat", "interesses_min_hash");
