package pi.growmate.repositories.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.task.TaskType;
import pi.growmate.datamodel.task.Tasks_Current;

@Repository
public interface CurrentTaskRepository extends JpaRepository<Tasks_Current, Long>{
    Tasks_Current findFirstByPlantAndTaskType(Plant plant, TaskType type);
}