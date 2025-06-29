package ua.tqs.hw1.utils;

import org.junit.jupiter.api.Test;
import ua.tqs.hw1.data.AirQuality;
import ua.tqs.hw1.data.Coordinates;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
class AirQualityComparatorTest {

    @Test
    void whenGivenMultipleAirQualities_thenReturnSortedByDate(){
        Coordinates aveiroCoords = new Coordinates(40.640496, -8.6537841);
        Map<String, Double> components = new HashMap<>();
        String[] components_text = {"co", "no", "no2", "o3", "so2", "pm2_5", "pm10", "nh3"};
        Double[] components_value = {367.17, 0.0, 19.71, 47.21, 1.46, 16.65, 20.7, 3.29};
        for (int i = 0; i < components_text.length; i++){
            components.put(components_text[i], components_value[i]);
        }
        Date date1 = new Date(1680822000L * 1000L);
        Date date2 = new Date(1680984000L * 1000L);
        Date date3 = new Date(1681088400L * 1000L);

        AirQuality airQuality1 = new AirQuality("Aveiro, PT", aveiroCoords, 2, components, date1.toString());
        AirQuality airQuality2 = new AirQuality("Aveiro, PT", aveiroCoords, 2, components, date2.toString());
        AirQuality airQuality3 = new AirQuality("Aveiro, PT", aveiroCoords, 2, components, date3.toString());

        List<AirQuality> unsortedList = new ArrayList<>();
        unsortedList.add(airQuality3);
        unsortedList.add(airQuality1);
        unsortedList.add(airQuality2);

        List<AirQuality> sortedList = new ArrayList<>();
        sortedList.add(airQuality1);
        sortedList.add(airQuality2);
        sortedList.add(airQuality3);

        unsortedList.sort(new AirQualityComparator());

        assertThat(unsortedList).isEqualTo(sortedList);
    }
}