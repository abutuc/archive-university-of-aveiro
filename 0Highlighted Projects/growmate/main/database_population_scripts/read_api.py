import requests
import json

with open('text.txt', 'r') as file:

    sn = "gynura aurantiaca"
      
    api1 = "http://trefle.io/api/v1/species/search"
    param1 = {"token": "N_mOO5XHBVFf0zv-DCUy76uKGl1ZQmml2VSDwueftWY", "q": sn}

    response = requests.get(api1, params=param1)

    if response.status_code == 200:
        data = response.text
        parse_json = json.loads(data)
        if (parse_json['data']) != []:
            api2 = "http://trefle.io/api/v1/species/" + str(parse_json['data'][0]['id'])
            param2 = {"token": "N_mOO5XHBVFf0zv-DCUy76uKGl1ZQmml2VSDwueftWY"}

            response2 = requests.get(api2, params=param2)

            if response2.status_code == 200:
                data2 = response2.text
                parse_json2 = json.loads(data2)
                print(parse_json2)
                print(str(parse_json2['data']['image_url']))
                #cycle:
                #print(parse_json2['data'])

