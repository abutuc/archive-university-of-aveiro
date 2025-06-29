package pi.growmate.datamodel.species;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeasonConverter implements AttributeConverter<Season, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Season season) {
        return switch (season) {
            case FALL -> 4;
            case SUMMER -> 3;
            case SPRING -> 2;
            case WINTER -> 1;
            default -> throw new IllegalArgumentException("Invalid season converter: " + season);
        };
    }

    @Override
    public Season convertToEntityAttribute(Integer seasonInt) {
        return switch (seasonInt) {
            case 1 -> Season.WINTER;
            case 2 -> Season.SPRING;
            case 3 -> Season.SUMMER;
            case 4 -> Season.FALL;
            default -> throw new IllegalArgumentException("Invalid season integer: " + seasonInt);
        };
    }
}
