package pi.growmate.datamodel.task;

import jakarta.persistence.AttributeConverter;

public class TaskTypeConverter implements AttributeConverter<TaskType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TaskType taskType) {
        return switch (taskType) {
            case FERTILIZER -> 4;
            case CHECK_PLANT -> 3;
            case SOIL_CHANGE -> 1;
            case WATERING -> 0;
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };
    }

    @Override
    public TaskType convertToEntityAttribute(Integer taskTypeInt) {
        return switch (taskTypeInt) {
            case 0 -> TaskType.WATERING;
            case 1 -> TaskType.SOIL_CHANGE;
            case 3 -> TaskType.CHECK_PLANT;
            case 4 -> TaskType.FERTILIZER;
            default -> throw new IllegalArgumentException("Invalid task type integer: " + taskTypeInt);
        };
    }
}
