package pi.growmate.datamodel.species;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TemperatureConverter implements AttributeConverter<OptimalTemperature, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OptimalTemperature optimalTemperature) {
        return switch (optimalTemperature) {
            case COOL -> 1;
            case AVERAGE -> 2;
            case WARM -> 3;
            default -> throw new IllegalArgumentException("Invalid temperature converter: " + optimalTemperature);
        };
    }

    @Override
    public OptimalTemperature convertToEntityAttribute(Integer optimalTemperatureInt) {
        return switch (optimalTemperatureInt) {
            case 1 -> OptimalTemperature.COOL;
            case 2 -> OptimalTemperature.AVERAGE;
            case 3 -> OptimalTemperature.WARM;
            default -> throw new IllegalArgumentException("Invalid temperature converter integer: " + optimalTemperatureInt);
        };
    }
}
