package pi.growmate.datamodel.species;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HumidityConverter implements AttributeConverter<OptimalHumidity, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OptimalHumidity optimalHumidity) {
        return switch (optimalHumidity) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> 3;
            default -> throw new IllegalArgumentException("Invalid humidity converter: " + optimalHumidity);
        };
    }

    @Override
    public OptimalHumidity convertToEntityAttribute(Integer optimalHumidityInt) {
        return switch (optimalHumidityInt) {
            case 1 -> OptimalHumidity.LOW;
            case 2 -> OptimalHumidity.MEDIUM;
            case 3 -> OptimalHumidity.HIGH;
            default -> throw new IllegalArgumentException("Invalid humidity converter integer: " + optimalHumidityInt);
        };
    }
}
