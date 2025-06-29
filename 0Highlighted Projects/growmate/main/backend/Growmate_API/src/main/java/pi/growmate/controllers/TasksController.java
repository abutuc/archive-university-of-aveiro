package pi.growmate.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pi.growmate.datamodel.task.Task_Settings;
import pi.growmate.datamodel.task.Tasks_Current;
import pi.growmate.datamodel.task.Tasks_History;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.services.TasksService;
import pi.growmate.utils.SuccessfulRequest;

@RestController
@RequestMapping("growmate/user/tasks")
@CrossOrigin
public class TasksController {

    @Autowired
    TasksService tasksService;

    // CURRENT TASKS

    // Gets all the tasks to be completed by a User today
    @GetMapping("{idUser}/today")
    public ResponseEntity<List<Tasks_Current>> getTasksForToday(@PathVariable(value = "idUser") Long idUser) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(tasksService.getTasksForToday(idUser));
    }

    // Gets all the tasks to be done today for a Plant
    @GetMapping("{idUser}/plant/{idPlant}/today")
    public ResponseEntity<List<Tasks_Current>> getTasksForTodayPlant(@PathVariable(value = "idUser") Long idUser,
                                                                     @PathVariable(value = "idPlant") Long idPlant) throws ResourceNotFoundException{

        return ResponseEntity.ok().body(tasksService.getTasksForTodayPlant(idUser, idPlant));
    }

    // Gets all the tasks to be completed by a User in the future
    @GetMapping("{idUser}/todo")
    public ResponseEntity<Map<String, List<Tasks_Current>>> getTasksToDo(@PathVariable(value = "idUser") Long idUser) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(tasksService.getTasksToDo(idUser));
    }

    // Gets all the tasks to be completed by a User in the future, for a specific Plant
    @GetMapping("{idUser}/plant/{idPlant}/todo")
    public ResponseEntity<List<Tasks_Current>> getTasksToDoPlant(@PathVariable(value = "idUser") Long idUser,
                                                                 @PathVariable(value = "idPlant") Long idPlant) throws ResourceNotFoundException{

        return ResponseEntity.ok().body(tasksService.getTasksToDoPlant(idUser, idPlant));
    }

    // Update a task's date
    @PutMapping("{idUser}/task/{idTask}")
    public ResponseEntity<SuccessfulRequest> updateTaskDate(@PathVariable(value = "idUser") Long idUser,
                                                            @PathVariable(value = "idTask") Long idTask,
                                                            @RequestParam(value = "taskDate") Date newDate) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(tasksService.updateTaskDate(idUser, idTask, newDate));
    }

    // Changes the status of a Task to be completed. A new task of the same type for that plant will be created, while the current task is saved in the Tasks History table.
    @PutMapping("{idUser}/plant/{idPlant}/updateTask/{idTask}")
    public ResponseEntity<SuccessfulRequest> updateTasksToBeDone(@PathVariable(value = "idUser") Long idUser,
                                                                 @PathVariable(value = "idPlant") Long idPlant,
                                                                 @PathVariable(value = "idTask") Long idTask) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(tasksService.updateTaskStatus(idUser, idPlant, idTask));

    }

    // COMPLETED TASKS

    // Gets all the tasks done by a User. If a Day is passed as a Request Parameter, it will only return the tasks for that specific day
    @GetMapping("{idUser}/done")
    public ResponseEntity<List<Tasks_History>> getTasksDone(@PathVariable(value = "idUser") Long idUser) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(tasksService.getTasksDone(idUser));
    }

    // Gets all the completed tasks for a Plant.  If a Day is passed as a Request Parameter, it will only return the tasks for that specific day
    @GetMapping("{idUser}/plant/{idPlant}/done")
    public ResponseEntity<List<Tasks_History>> getTasksDonePlant(@PathVariable(value = "idUser") Long idUser,
                                                                 @PathVariable(value = "idPlant") Long idPlant) throws ResourceNotFoundException{
        
        return ResponseEntity.ok().body(tasksService.getTasksDonePlant(idUser, idPlant));
    }

    // TASK SETTINGS

    // Used to Toggle a task between manual and automatic.
    @PutMapping("{idUser}/plant/{idPlant}/toggleMode")
    public ResponseEntity<SuccessfulRequest> toggleTaskMode(@PathVariable(value = "idUser") Long idUser,
                                                            @PathVariable(value = "idPlant") Long idPlant,
                                                            @RequestParam(value = "taskType") String taskType,
                                                            @RequestParam(value = "frequency", required = false) Integer frequency) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(tasksService.toggleTaskMode(idUser, idPlant, taskType, frequency));
    }

    // Get all the task settings associated with a User. Returns in the form of a dict where the key is the Plant ID, and the value is the list of settings.
    // If the request param plantID is passed, it will only return the definitions related to that plant
    // If the request param plantID and the requestParam task type is passed, will only return the specific task related with that type and plant
    @GetMapping("{idUser}/settings")
    public ResponseEntity<Map<String, List<Task_Settings>>> getTaskSettings(@PathVariable(value = "idUser") Long idUser,
                                                                            @RequestParam(value = "idPlant", required = false) Long idPlant,
                                                                            @RequestParam(value = "taskType", required = false) String taskType) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(tasksService.getTaskSettings(idUser, idPlant, taskType));
    }

    // Used to update the frequency of a task by a User
    @PutMapping("{idUser}/plant/{idPlant}/frequency")
    public ResponseEntity<SuccessfulRequest> updateTaskFrequency(@PathVariable(value = "idUser") Long idUser,
                                                                 @PathVariable(value = "idPlant") Long idPlant,
                                                                 @RequestParam(value = "taskType") String taskType,
                                                                 @RequestParam(value = "frequency") Integer frequency) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(tasksService.updateFrequency(idUser, idPlant, taskType, frequency));
    }
}
