# Homework midterm assignment 1


### Running the tests

In order to properly run all the tests at once, it is important to have the SpringBoot project
running and the website at http://localhost:8080/ is running. If not, the functional
tests from the folder webapp will fail.

### Third Party API

I chose [OpenWeather](https://openweathermap.org/) that has a free plan with several APIs available, such as,
the Air Pollution API and Geocoding API.

In the assignment I will be using the following API endpoints calls:

```
GET http://api.openweathermap.org/geo/1.0/direct?q={city}&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7
```
That returns the following JSON response (for Aveiro):

```
[
    {
        "name": "Aveiro",
        "local_names": {
            "el": "Αβέιρο",
            "hu": "Aveiro",
            "ar": "آويرو",
            "lt": "Aveiras",
            "ru": "Авейру",
            "pt": "Aveiro"
        },
        "lat": 40.640496,
        "lon": -8.6537841,
        "country": "PT"
    }
]
```

Which I will use to convert the inputted city by the user to its latitude and longitude coordinates. 
Therefore, from the response, I will retrieve the attributes "name", "lat", "lon" and "country".


This conversion was necessary because of the following API call:

```
GET http://api.openweathermap.org/data/2.5/air_pollution?lat={lat}&lon={lon}&appid=8b5f3b52ca7814b00fa0587ca39d6ab7
```

Using the latitude and longitude values obtained in the previous API call, we receive:

```
{
    "coord": {
        "lon": -8.6538,
        "lat": 40.6405
    },
    "list": [
        {
            "main": {
                "aqi": 3
            },
            "components": {
                "co": 260.35,
                "no": 0,
                "no2": 2.1,
                "o3": 101.57,
                "so2": 1.31,
                "pm2_5": 13.18,
                "pm10": 22.69,
                "nh3": 1.35
            },
            "dt": 1679951110
        }
    ]
}
```

From where I will retrieve the "aqi" attribute, which corresponds to an Air Quality Index of possible values:
- 1 = Good
- 2 = Fair
- 3 = Moderate
- 4 = Poor
- 5 = Very Poor

And also "components" a list of the concentration of various components present in the air.




