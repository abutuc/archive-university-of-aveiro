with open('text.txt', 'r') as file:

    data = []
    id = 0

    for line in file:

        data = line.strip().split('\t')
        #print(data)
        sn = data[0].strip()
        cn = data[1].strip()
        light = data[2].split("-")[0].strip()
        temperature = data[3].split("-")[0].strip() #considerar o primeiro
        humidity = data[4].split("-")[0].strip() #considerar o primeiro
        wf = data[5].split("-")[0].strip() #considerar o primeiro
        family_id = data[6].split("-")[0].strip() #considerar o primeiro~

        #transmutations
        humidity = abs(int(humidity) - 4)
        wf = abs(int(wf) - 4)
        light = abs(int(light) - 5)

        print("(" +str(id) + ", '" +  cn + "', 'cycle', 'flowering', 'leafc', " + str(humidity) + ", " + str(light) + ", "+ str(temperature) + ", '" + str(sn) + "', 'season', 'photo', 'usual_size', " + str(wf) + ", " + str(family_id) + ")")
        id += 1
        
