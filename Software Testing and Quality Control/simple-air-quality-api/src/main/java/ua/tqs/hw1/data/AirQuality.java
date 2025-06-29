package ua.tqs.hw1.data;
import java.util.Map;
import java.util.Objects;

public class AirQuality {
    private String location;
    private Coordinates coordinates;
    private Integer qualityIndex;
    private Map<String, Double> componentsConcentration;
    private String date;

    public AirQuality(){}

    public AirQuality(String location, Coordinates coordinates, Integer qualityIndex,
                      Map<String, Double> componentsConcentration, String date){
        this.location = location;
        this.coordinates = coordinates;
        this.qualityIndex = qualityIndex;
        this.componentsConcentration = componentsConcentration;
        this.date = date;
    }


    public String getLocation() {
        return location;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Integer getQualityIndex() {
        return qualityIndex;
    }

    public Map<String, Double> getComponentsConcentration() {
        return componentsConcentration;
    }

    public String getDate() {return date;}

    @Override
    public String toString() {
        return "AirQuality{" +
                "location='" + location + '\'' +
                ", coordinates=" + coordinates +
                ", qualityIndex=" + qualityIndex +
                ", componentsConcentration=" + componentsConcentration +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirQuality that = (AirQuality) o;
        return Objects.equals(location, that.location) && Objects.equals(coordinates, that.coordinates)
                && Objects.equals(qualityIndex, that.qualityIndex)
                && Objects.equals(componentsConcentration, that.componentsConcentration)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, coordinates, qualityIndex, componentsConcentration, date);
    }

}
