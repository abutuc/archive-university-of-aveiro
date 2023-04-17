function print_friends2(user_id,user_data,friends)
    frd_length=length(friends);
    
    for i=1:frd_length
        if friends{i,1}==user_id
            id=friends{i,2};
            A=[user_data{friends{i,2},2},' ',user_data{friends{i,2},3}];
            fprintf(""+id+"-");
            disp(A);
        end
    end
    fprintf("\n\n");
   
end