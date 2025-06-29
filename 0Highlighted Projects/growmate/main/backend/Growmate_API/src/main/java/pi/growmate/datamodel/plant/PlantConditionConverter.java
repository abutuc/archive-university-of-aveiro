package pi.growmate.datamodel.plant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PlantConditionConverter implements AttributeConverter<PlantCondition, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PlantCondition plantCondition) {
        return switch (plantCondition) {
            case GREAT -> 2;
            case NORMAL -> 1;
            case BAD -> 0;
            default -> throw new IllegalArgumentException("Invalid plant condition: " + plantCondition);
        };
    }

    @Override
    public PlantCondition convertToEntityAttribute(Integer plantConditionInt) {
        return switch (plantConditionInt) {
            case 0 -> PlantCondition.BAD;
            case 1 -> PlantCondition.NORMAL;
            case 2 -> PlantCondition.GREAT;
            default -> throw new IllegalArgumentException("Invalid plant condition integer: " + plantConditionInt);
        };
    }
}
