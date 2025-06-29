package ua.tqs.hw1.utils;
import ua.tqs.hw1.data.AirQuality;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class AirQualityComparator implements Comparator<AirQuality> {

    @Override
    public int compare(AirQuality a1, AirQuality a2){
        Map<String, Integer> months = new HashMap<>();
        String[] months_text = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i<months_text.length; i++){
            months.put(months_text[i], i+1);
        }
        String[] a1Splitted = a1.getDate().split(" ");
        String[] a2Splitted = a2.getDate().split(" ");

        if (months.get(a1Splitted[1]) > months.get(a2Splitted[1])) {
            return 1;
        }
        else if (months.get(a1Splitted[1]) < months.get(a2Splitted[1])){
            return -1;
        }

        int day1 = Integer.parseInt( a1Splitted[2]);
        int day2 = Integer.parseInt( a2Splitted[2]);

        if (day1 > day2){
            return 1;
        }
        else if (day1 < day2){
            return -1;
        }

        return 0;
    }
}
