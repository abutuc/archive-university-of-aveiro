import {Dimensions, View} from "react-native";
import BottomMenu from "../../components/BottomMenu";
import React, {useEffect, useState} from "react";
import {ActivityIndicator, useTheme} from "react-native-paper";
import TasksHeader from "./components/TasksHeader";
import Tasks from "./components/Tasks";
import TaskCalendar from "./components/TaskCalendar";
import GoBackButton from "./components/GoBackButton";
import {getTodoTasks} from "../../service/TasksScreenService";
import {getTaskSettings} from "../../service/PlantScreenService";
import TaskDialog from "../PlantScreen/components/TaskDialog";
import {userID} from "../../user";

export default function TasksScreen() {
    const [todoTasks, setTodoTasks] = useState([]);
    const [todoTaskDates, setTodoTaskDates] = useState({});
    const [todoSelectedTasks, setTodoSelectedTasks] = useState([]);
    const [selected, setSelected] = useState(false);
    const [selectedDay, setSelectedDay] = useState(0);
    const [counter, setCounter] = useState(0);
    const [loadingTasks, setLoadingTasks] = useState(true);
    const [currentYear, setCurrentYear] = useState(new Date().getFullYear());

    useEffect( () => {
        getTodoTasks(userID).then((tasks) => {
            const rawTasks = tasks;
            const taskDates = {};
            const todoTasks = [];

            for (let pID in rawTasks){
                for (let r = 0; r < rawTasks[pID].length; r++){
                    let date = new Date(rawTasks[pID][r].taskDate);
                    let dateString = date.toDateString().split(" ");
                    let weekday = dateString[0];
                    let day = dateString[2];
                    let month = dateString[1];
                    let year = date.getFullYear();
                    let name = rawTasks[pID][r].name;
                    let id = rawTasks[pID][r].id
                    if (year === currentYear) {
                        todoTasks.push(
                            {
                                date: date,
                                dateString: rawTasks[pID][r].taskDate,
                                weekday: weekday,
                                day: day,
                                month: month,
                                year: year,
                                tasks: [
                                    name
                                ],
                                id: id,
                                taskType: rawTasks[pID][r].taskType,
                                plantID: pID
                            }
                        )
                        taskDates[rawTasks[pID][r].taskDate] = {marked: true, dotColor: theme.colors.primary};
                    }
                }
            }

            todoTasks.sort((a, b) => a.date - b.date);

            if (selected) {
                setTodoTaskDates(taskDates);
                setTodoTasks(todoTasks);
                onDaySelect(selectedDay, todoTasks);
            }
            else {
                setTodoTaskDates(taskDates);
                setTodoTasks(todoTasks);
                setTodoSelectedTasks(todoTasks);
            }
            setLoadingTasks(false);
        });
    }, [counter, currentYear]);

    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()

    const onDaySelect = (date, manualTodoTasks) => {
        if (manualTodoTasks){
            setSelectedDay(date);
            let selectedTasks = [];
            for (let f = 0; f < manualTodoTasks.length; f++) {
                let task = manualTodoTasks.at(f);
                if (date.dateString === task.dateString){
                    selectedTasks.push(task);
                }
            }
            setTodoSelectedTasks(selectedTasks);
            setSelected(true);
        }
        else {
            setSelectedDay(date);
            let selectedTasks = [];
            for (let f = 0; f < todoTasks.length; f++) {
                let task = todoTasks.at(f);
                if (date.dateString === task.dateString){
                    selectedTasks.push(task);
                }
            }
            setTodoSelectedTasks(selectedTasks);
            setSelected(true);
        }
    }

    const goBack = () => {
        setTodoSelectedTasks(todoTasks);
        setSelected(false);
    }

    const [visibleChange, setVisibleChange] = useState(false);
    const [taskName, setTaskName] = useState("");
    const [taskDueDate, setTaskDueDate] = useState("");
    const [taskMode, setTaskMode] = useState(true);
    const [taskFrequency, setTaskFrequency] = useState(0);
    const [taskID, setTaskID] = useState(0);
    const [taskType, setTaskType] = useState(0);
    const [plantID, setPlantID] = useState(0);
    const [initialTaskDueDate, setInitialTaskDueDate] = useState("");
    const [initialTaskMode, setInitialTaskMode] = useState(true);
    const [initialTaskFrequency, setInitialTaskFrequency] = useState(0);

    const setChange = (taskName, taskDueDate, taskType, plantID, taskID) => {
        setTaskName(taskName);
        setTaskDueDate(taskDueDate);
        setInitialTaskDueDate(taskDueDate);
        setTaskID(taskID);
        setTaskType(taskType);
        setPlantID(plantID);
        getTaskSettings(userID, plantID, taskType).then(
            (task) => {
                setTaskMode(task[plantID][0].automatic);
                setInitialTaskMode(task[plantID][0].automatic);
                setTaskFrequency(task[plantID][0].taskFrequency);
                setInitialTaskFrequency(task[plantID][0].taskFrequency)
                setVisibleChange(true);
            }
        )

    }
    const hideChange = () => setVisibleChange(false);

    if (loadingTasks) {
        return (
            <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                <ActivityIndicator size="large" color={theme.colors.primary}/>
            </View>
        )
    }
    else {
        return (
            <View style={{height: screenHeight, backgroundColor: theme.colors.background}}>
                <TasksHeader/>
                <TaskCalendar taskDates={todoTaskDates} onDaySelect={onDaySelect} updateYear={setCurrentYear}/>
                <Tasks tasks={todoSelectedTasks} selected={selected} maxHeight={220}
                       setCounter={setCounter} counter={counter} setChange={setChange}
                       userId={userID}
                />
                {selected &&
                    <GoBackButton onPress={goBack}/>
                }
                <TaskDialog hideChange={hideChange} visibleChange={visibleChange} taskName={taskName}
                            taskDueDate={taskDueDate} taskMode={taskMode} taskFrequency={taskFrequency}
                            setTaskMode={setTaskMode} setTaskDueDate={setTaskDueDate}
                            setTaskFrequency={setTaskFrequency}
                            taskID={taskID} userID={userID} initialTaskDueDate={initialTaskDueDate}
                            initialTaskFrequency={initialTaskFrequency}
                            initialTaskMode={initialTaskMode} setCounter={setCounter} counter={counter}
                            plantID={plantID} taskType={taskType}/>
                <BottomMenu screenHeight={screenHeight} active={"calendar"}/>
            </View>
        )
    }
}