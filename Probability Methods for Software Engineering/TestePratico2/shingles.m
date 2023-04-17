function S = shingles(names, shingle_size)
  for i = 1:length(names)
      name = names(i);
    for s = 1:length(name{1,1})-shingle_size + 1
        t = '';
        for j = s:s+shingle_size-2
            disp(name{1,1}(j));
           t = strcat(t,name{1,1}(j),' '); 
        end
        t = strcat(t, name{1,1}(s + shingle_size - 1));
    end
        S{i} = t;
        break;
  end
end