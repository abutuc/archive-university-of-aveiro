clear
clc
close all



frase1 = cellstr(split("Jogue com o homem, não com as probabilidades"))';
frase2 = cellstr(split("Jogue com as mulheres e também com as probabilidades"));
frase3 = cellstr(split("Se você desconhece até as probabilidades, isso é incerteza"));
frase4 = cellstr(split("As probabilidades, a ciência, homens e mulheres estão contra mim"));

shingles = ["temp"];
count = 1;
k = 3;


for word=frase1
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            shingles(count) = word{1}(i:i+k);
            count = count + 1;
        end
    end
end

for word=frase2
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            shingles(count) = word{1}(i:i+k);
            count = count + 1;
        end
    end
end

for word=frase3
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            shingles(count) = word{1}(i:i+k);
            count = count + 1;
        end
    end
end

for word=frase4
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            shingles(count) = word{1}(i:i+k);
            count = count + 1;
        end
    end
end

shingles = unique(shingles);
matriz = zeros(length(shingles), 4);

for word = frase1
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            for f = 1:length(shingles)
                if (word{1}(i:i+k) == shingles(f))
                    matriz(f, 1) = 1;
                end
            end
        end
    end
end

for word = frase2
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            for f = 1:length(shingles)
                if (word{1}(i:i+k) == shingles(f))
                    matriz(f, 2) = 1;
                end
            end
        end
    end
end

for word = frase3
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            for f = 1:length(shingles)
                if (word{1}(i:i+k) == shingles(f))
                    matriz(f, 3) = 1;
                end
            end
        end
    end
end

for word = frase4
    for i=1:length(word{1})-1
        if(i+k < length(word{1}))
            for f = 1:length(shingles)
                if (word{1}(i:i+k) == shingles(f))
                    matriz(f, 4) = 1;
                end
            end
        end
    end
end


