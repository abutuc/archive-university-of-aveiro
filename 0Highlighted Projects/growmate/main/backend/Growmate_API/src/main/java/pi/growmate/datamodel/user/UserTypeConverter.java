package pi.growmate.datamodel.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserType userType) {
        return switch (userType) {
            case ADMIN -> 0;
            case NORMAL -> 1;
            case PREMIUM -> 2;
            default -> throw new IllegalArgumentException("Invalid user type: " + userType);
        };
    }

    @Override
    public UserType convertToEntityAttribute(Integer userTypeInt) {
        return switch (userTypeInt) {
            case 0 -> UserType.ADMIN;
            case 1 -> UserType.NORMAL;
            case 2 -> UserType.PREMIUM;
            default -> throw new IllegalArgumentException("Invalid user type integer: " + userTypeInt);
        };
    }
}
