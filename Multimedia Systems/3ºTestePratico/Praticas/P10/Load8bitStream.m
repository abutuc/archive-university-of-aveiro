function [N,M,stream] = Load8bitStream(fname,N,M,stream)
    
    file = fopen(fname, "rb");

    N = fread(file, 1, "uint16");
    M = fread(file, 1, "uint16");
    stream = fread(file, "uint8=>uint8");
   
    fclose(file);

end