function [] = SaveLZ77Image(Filename,N,M,Nw,Mw,Symb,Stream)

fid = fopen(Filename, 'wb');
fwrite(fid,N,'uint16');
fwrite(fid,M,'uint16');
fwrite(fid,Nw,'uint8');
fwrite(fid,Mw,'uint8');
fwrite(fid,length(Symb),'uint8');
fwrite(fid,Symb,'uint8');
fwrite(fid,Stream,'uint8');
fclose(fid);

end