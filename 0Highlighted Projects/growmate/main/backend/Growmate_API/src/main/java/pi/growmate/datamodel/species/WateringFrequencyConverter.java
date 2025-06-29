package pi.growmate.datamodel.species;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WateringFrequencyConverter implements AttributeConverter<WateringFrequency, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WateringFrequency wateringFrequency) {
        return switch (wateringFrequency) {
            case INFREQUENT -> 1;
            case AVERAGE -> 2;
            case FREQUENT -> 3;
            default -> throw new IllegalArgumentException("Invalid watering frequency: " + wateringFrequency);
        };
    }

    @Override
    public WateringFrequency convertToEntityAttribute(Integer wateringFrequencyInt) {
        return switch (wateringFrequencyInt) {
            case 1 -> WateringFrequency.INFREQUENT;
            case 2 -> WateringFrequency.AVERAGE;
            case 3 -> WateringFrequency.FREQUENT;
            default -> throw new IllegalArgumentException("Invalid watering frequency integer: " + wateringFrequencyInt);
        };
    }
}
