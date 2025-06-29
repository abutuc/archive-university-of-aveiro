package pi.growmate.repositories.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.growmate.datamodel.task.Tasks_History;

public interface HistoricTaskRepository extends JpaRepository<Tasks_History, Long> {
}
