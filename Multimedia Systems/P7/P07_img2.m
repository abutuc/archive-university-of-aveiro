function img2 = P07_img2(img)
  s = size(img);
  img2 = img; % img2 = uint8(zeros(s));
  h = waitbar(0,'Please wait...');
  for y=2:s(1)-1
    waitbar(y/s(1),h);
    for x=2:s(2)-1
      for z=1:s(3)
        tmp=mean([img(y-1,x,z),img(y+1,x,z),img(y,x-1,z),img(y,x+1,z)]);
        img2(y,x,z)=64*(max(img(y,x,z)-tmp,tmp-img(y,x,z)));
      end
    end
  end
  close(h);
  if nargout == 0
    figure
    subplot(1,2,1);
    imshow(img);
    subplot(1,2,2);
    imshow(img2);
  end
end