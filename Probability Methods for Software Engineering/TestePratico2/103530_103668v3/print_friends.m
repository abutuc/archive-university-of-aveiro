function print_friends(user_id,user_data,friends)
    frd_length=length(friends);
    
    for i=1:frd_length
        if friends{i,1}==user_id
            str=" "+user_data{friends{i,2},2}+" "+user_data{friends{i,2},3};
            fprintf(""+user_data{friends{i,2},1}+"-"+str+"\n");
        end
    end
end