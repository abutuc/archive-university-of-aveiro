import {ScrollView, View} from "react-native";
import DayTasks from "./DayTasks";


export default  function Tasks({tasks, userId, plantID, setCounter, counter, maxHeight, setChange}){
    return (
        <View style={{ marginTop: 10, marginHorizontal: 30, justifyContent: "center", alignItems:"center"}}>
                <ScrollView style={{ maxHeight: maxHeight}}  >
                    {tasks.map((task, index) => (
                        <DayTasks
                            dateString={task.dateString}
                            key={index}
                            weekday={task.weekday}
                            day={task.day}
                            month={task.month}
                            tasks = {task.tasks}
                            userId={userId}
                            plantId={plantID ? plantID : task.plantID}
                            taskId={task.id}
                            setCounter={setCounter}
                            counter={counter}
                            setChange={setChange}
                            taskType={task.taskType}
                        />
                    ))}
                </ScrollView>
        </View>
    )
}
