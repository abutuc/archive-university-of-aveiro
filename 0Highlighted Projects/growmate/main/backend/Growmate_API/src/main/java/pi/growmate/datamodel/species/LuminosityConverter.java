package pi.growmate.datamodel.species;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LuminosityConverter implements AttributeConverter<OptimalLuminosity, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OptimalLuminosity optimalLuminosity) {
        return switch (optimalLuminosity) {
            case SUNNY -> 4;
            case HIGH -> 3;
            case MEDIUM -> 2;
            case LOW -> 1;
            default -> throw new IllegalArgumentException("Invalid luminosity converter: " + optimalLuminosity);
        };
    }

    @Override
    public OptimalLuminosity convertToEntityAttribute(Integer optimalLuminosityInt) {
        return switch (optimalLuminosityInt) {
            case 1 -> OptimalLuminosity.LOW;
            case 2 -> OptimalLuminosity.MEDIUM;
            case 3 -> OptimalLuminosity.HIGH;
            case 4 -> OptimalLuminosity.SUNNY;
            default -> throw new IllegalArgumentException("Invalid luminosity converter integer: " + optimalLuminosityInt);
        };
    }
}
