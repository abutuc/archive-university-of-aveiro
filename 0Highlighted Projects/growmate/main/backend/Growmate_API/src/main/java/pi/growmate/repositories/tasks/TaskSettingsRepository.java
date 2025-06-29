package pi.growmate.repositories.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.task.TaskType;
import pi.growmate.datamodel.task.Task_Settings;

import java.util.List;

public interface TaskSettingsRepository extends JpaRepository<Task_Settings, Long> {
    Task_Settings findFirstByPlantAndTaskType(Plant plant, TaskType type);
    List<Task_Settings> findAllByPlant(Plant plant);
}
