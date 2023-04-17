clear;
close all;
clc;

load("user_data.mat");
load("friends.mat");
load("friends_min_hash.mat");
load("names_min_hash.mat");
load("interesses_min_hash.mat");

invalid=1;
while (invalid)
    prompt_id="Insert Valid User ID: ";
    user_id=input(prompt_id);   

    if (user_id >=1 && user_id <=1000)
        invalid=0;
        break;
    end
    fprintf("Invalid User Id\n\n");
end

fprintf("Your id is %d\n\n",user_id);


op=1;
while (op>1 || op<5) 
    fprintf("\n1 - Your Friends\n");
    fprintf("2 - Interests from most similar users\n");
    fprintf("3 - Search name\n");
    fprintf("4 - Find most similar users based in the list of interests\n");
    fprintf("5 - Exit\n\n");

    prompt = 'choose an option:  ';
    op = input(prompt);
    if (op>5 || op<1)
        fprintf("\nInvalid option\nTry again\n\n");
    end

    if op==1
        print_friends2(user_id,user_data,friends);
    elseif op==2
        k=100; % number of hash functions
        similar(user_id,user_data,friends_min_hash, k);
    elseif op==3
        k=150;%number of hash functions
        tam_shingle=3; %shingle
        limiar=0.8; %threshold=0.8 dado do enunciado
        filtro = find_name(user_data,names_min_hash,tam_shingle,limiar);
    elseif op==4
        k=150;
        tam_shingle=3;
        similar_interesses(user_id,user_data,friends,interesses_min_hash, tam_shingle);
    elseif op==5
        fprintf("\nProgram Ended, Goodbye\n");
        return;
    end
end