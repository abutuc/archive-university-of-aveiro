clear;
close all;
clc;

load("Friends.mat");
load("FriendsMh.mat");
user_data=readcell("utilizadores.txt",'Delimiter',';');
Nu = length(user_data);
friends=readcell("amigos.txt",'Delimiter',' ');
sdf=length(friends);
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


op=0;
while (op<1 ||op>5) 
    fprintf("1 - Your Friends\n");
    fprintf("2 - Interests from most similar users\n");
    fprintf("3 - Search name\n");
    fprintf("4 - Find most similar users based in the list of interests\n");
    fprintf("5 - Exit\n\n");

    prompt = 'choose an option:  ';
    op = input(prompt);
    if op>5 || op<1
        fprintf("\nInvalid option\nTry again\n\n");
    end

    if op==1
        print_friends(user_id,user_data,friends);
    elseif op==2
        [jaccard, similar_user] = SimilarUser(Nu, FriendsMh, user_id, 100);
        disp(similar_user);
    elseif op==3
        load('shingles.mat');
        
    elseif op==4
        fprintf("ssfdsdf");
    elseif op==5
        fprintf("Thanks for using, Goodbye\n")
        return;
    end
end