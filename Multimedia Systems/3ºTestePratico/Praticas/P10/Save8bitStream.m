function [] = Save8bitStream(fname,N,M,stream)
    
    file = fopen(fname, "wb");

    fwrite(file, N, "uint16");
    fwrite(file, M, "uint16");
    fwrite(file, stream, "uint8");
   
    fclose(file);
    
end