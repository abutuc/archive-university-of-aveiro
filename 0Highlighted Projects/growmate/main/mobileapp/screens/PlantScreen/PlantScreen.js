import React, { useState, useEffect } from "react";
import { View, Dimensions, ScrollView } from "react-native";
import { Tabs, TabScreen } from 'react-native-paper-tabs';
import { useTheme, ActivityIndicator } from "react-native-paper";
import { useNavigation} from '@react-navigation/native';

import BottomMenu from "../../components/BottomMenu";
import PlantHeader from "./components/PlantHeader";
import PlantAvatar from "./components/PlantAvatar";
import DeletePlant from "./components/DeletePlant";
import CheckSpeciesButton from "./components/CheckSpeciesButton";
import PlantInformation from "./components/PlantInformation";
import SensorsCarousel from "./components/SensorsCarousel";
import PlantStatus from "./components/PlantStatus";
import SensorGraphStack from "./components/SensorGraphStack";
import TaskCalendar from "../TasksScreen/components/TaskCalendar";
//API functions
import {
    getPlantInfo,
    getAllDivisions,
    getPlantTasksTodo,
    getSensorsForPlant,
    deletePlant,
    getTaskSettings,
    getLastThreeDaysMeasurements,
    updatePlantInfo,
} from "../../service/PlantScreenService";
import { deleteImage } from "../../service/FirebaseService";
import Tasks from "../TasksScreen/components/Tasks";
import GoBackButton from "../TasksScreen/components/GoBackButton";
import TaskDialog from "./components/TaskDialog";
import { userID, userType } from "../../user";

export default function PlantScreen({ route }) {
    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme();
    const navigation = useNavigation();

    // Info for the API call
    const { plantID } = route.params;

    // Get Plant info
    const [plantInfo, setPlantInfo] = useState(null);
    const [loadingPlantInfo, setLoadingPlantInfo] = useState(true);
    useEffect(() => {
        getPlantInfo(userID, plantID)
            .then((info) => {
                setPlantInfo(info);
                setLoadingPlantInfo(false);
            });
    }, [])

    // Get All sensors associated with a plant
    const [sensors, setSensors] = useState(null)
    const [loadingSensors, setLoadingSensors] = useState(true);
    useEffect(() => {
        if (plantInfo) {
            getSensorsForPlant(userID, plantID, plantInfo.division)
                .then((info) => {
                    setSensors(info);
                    setLoadingSensors(false);
                });
        }
    }, [plantInfo])

    // Get All Divisions
    const [divisions, setDivisions] = useState(null);
    const [loadingDivisions, setLoadingDivisions] = useState(true);
    useEffect(() => {
        getAllDivisions(userID)
            .then((info) => {
                setDivisions(info);
                setLoadingDivisions(false);
            });
    }, [])

    // Get Last 3 days of measurements
    const [lastThreeDaysMeasurements, setLastThreeDaysMeasurements] = useState(null);
    const [loadingMeasurements, setLoadingMeasurements] = useState(true);
    useEffect(() => {
        if (sensors) {
            getLastThreeDaysMeasurements(userID, plantID)
                .then((info) => {
                    setLastThreeDaysMeasurements(info);
                    setLoadingMeasurements(false);
                });
        }
    }, [sensors])

    //Get Plant tasks
    const [currentYear, setCurrentYear] = useState(new Date().getFullYear());
    const [todoTasks, setTodoTasks] = useState([]);
    const [todoTaskDates, setTodoTaskDates] = useState({});
    const [todoSelectedTasks, setTodoSelectedTasks] = useState([]);
    const [selected, setSelected] = useState(false);
    const [counter, setCounter] = useState(0);
    const [loadingTasks, setLoadingTasks] = useState(true);
    useEffect(() => {
        getPlantTasksTodo(userID, plantID).then((tasks) => {
            const rawTasks = tasks;
            const taskDates = {};
            const todoTasks = [];
            for (let r = 0; r < rawTasks.length; r++) {
                let date = new Date(rawTasks[r].taskDate);
                let dateString = date.toDateString().split(" ");
                let weekday = dateString[0];
                let day = dateString[2];
                let month = dateString[1];
                let year = date.getFullYear();
                let name = rawTasks[r].name;
                let id = rawTasks[r].id
                if (year === currentYear) {
                    todoTasks.push(
                        {
                            date: date,
                            dateString: rawTasks[r].taskDate,
                            weekday: weekday,
                            day: day,
                            month: month,
                            year: year,
                            tasks: [
                                name
                            ],
                            id: id,
                            taskType: rawTasks[r].taskType
                        }
                    )
                    taskDates[rawTasks[r].taskDate] = {marked: true, dotColor: theme.colors.primary};
                }
            }

            todoTasks.sort((a, b) => a.date - b.date);

            if (selected) {
                setTodoTaskDates(taskDates);
                setTodoTasks(todoTasks);
                onDaySelect(selectedDay, todoTasks);
                setLoadingTasks(false);
            }
            else {
                setTodoTaskDates(taskDates);
                setTodoTasks(todoTasks);
                setTodoSelectedTasks(todoTasks);
                setLoadingTasks(false);
            }

        });
    }, [counter]);

    const [selectedDay, setSelectedDay] = useState(0);

    const onDaySelect = (date, manualTodoTasks) => {
        if (manualTodoTasks) {
            setSelectedDay(date);
            let selectedTasks = [];
            for (let f = 0; f < manualTodoTasks.length; f++) {
                let task = manualTodoTasks.at(f);
                if (date.dateString === task.dateString) {
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
                if (date.dateString === task.dateString) {
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

    // API call to delete plant;
    const handleDeletePlant = async () => {
        await deleteImage(userID, plantInfo.name);
        await deletePlant(userID, plantID, true);
        navigation.navigate('Home', {reload: true, variance: plantID.toString()});
    }

    const [visibleChange, setVisibleChange] = useState(false);
    const [taskName, setTaskName] = useState("");
    const [taskDueDate, setTaskDueDate] = useState("");
    const [taskMode, setTaskMode] = useState(true);
    const [taskFrequency, setTaskFrequency] = useState(0);
    const [taskID, setTaskID] = useState(0);
    const [taskType, setTaskType] = useState(0);
    const [initialTaskDueDate, setInitialTaskDueDate] = useState("");
    const [initialTaskMode, setInitialTaskMode] = useState(true);
    const [initialTaskFrequency, setInitialTaskFrequency] = useState(0);

    const setChange = (taskName, taskDueDate, taskType, plantID, taskID) => {
        setTaskName(taskName);
        setTaskDueDate(taskDueDate);
        setInitialTaskDueDate(taskDueDate);
        setTaskID(taskID);
        setTaskType(taskType);
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

    // Handle Save information
    const handleSave = (plantName, plantationDate) => {
        // Set plantation date to java.sql.Date format

        updatePlantInfo(userID, plantID, plantName, plantationDate).then(
            () => {
                setPlantInfo({
                    ...plantInfo,
                    name: plantName,
                    plantationDate: plantationDate
                });
                hideChange();
            }
        )
    }

    // Render loading indicator if any data is loading
    if (loadingPlantInfo || loadingSensors || loadingDivisions || loadingMeasurements || loadingTasks) {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <ActivityIndicator size="large" />
            </View>
        );
    }

    if (plantInfo && divisions && sensors && todoTasks && lastThreeDaysMeasurements && todoSelectedTasks) {
        return (
            <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
                <PlantHeader name={plantInfo.name} />
                {userType === "PREMIUM" ?
                    <Tabs
                        style={{ backgroundColor: theme.colors.background }}
                    >
                        <TabScreen label="Info" icon="information">
                            <ScrollView>
                                <View style={{ paddingBottom: 100, paddingTop: 30 }}>
                                    <CheckSpeciesButton plantId={plantID} />
                                    <PlantAvatar
                                        image={plantInfo.plantPhoto}
                                        species={plantInfo.species["commonName"]}
                                    />
                                    <DeletePlant
                                        name={plantInfo.name}
                                        deletePlant={handleDeletePlant}
                                    />
                                    <SensorsCarousel sensors={sensors} />
                                    <PlantStatus name={plantInfo.name} status={plantInfo.plantCondition} />
                                    <PlantInformation
                                        plant={plantInfo}
                                        division={plantInfo.division}
                                        handleSave={(plantName, plantationDate) => handleSave(plantName, plantationDate)}
                                    />
                                </View>
                            </ScrollView>
                        </TabScreen>
                        <TabScreen label="Statistics " icon="chart-line">
                            <SensorGraphStack measurements={lastThreeDaysMeasurements} />
                        </TabScreen>
                        <TabScreen label="Tasks " icon="pencil">
                            <View>
                                <TaskCalendar taskDates={todoTaskDates} onDaySelect={onDaySelect} updateYear={setCurrentYear}/>
                                <Tasks tasks={todoSelectedTasks} selected={selected} userId={userID} plantID={plantID} setCounter={setCounter} counter={counter} maxHeight={160} setChange={setChange} />
                                {selected &&
                                    <GoBackButton onPress={goBack} />
                                }

                            </View>
                        </TabScreen>
                    </Tabs>
                    :
                    <Tabs
                        style={{ backgroundColor: theme.colors.background }}
                    >
                        <TabScreen label="Info" icon="information">
                            <ScrollView>
                                <View style={{ paddingBottom: 100, paddingTop: 30 }}>
                                    <CheckSpeciesButton plantId={plantID} />
                                    <PlantAvatar
                                        image={plantInfo.plantPhoto}
                                        species={plantInfo.species["commonName"]}
                                    />
                                    <DeletePlant
                                        name={plantInfo.name}
                                        deletePlant={handleDeletePlant}
                                    />
                                    <PlantStatus name={plantInfo.name} status={plantInfo.plantCondition} />
                                    <PlantInformation
                                        plant={plantInfo}
                                        division={plantInfo.division}
                                        divisions={divisions}
                                        handleSave={(plantName, plantationDate) => handleSave(plantName, plantationDate)}
                                    />
                                </View>
                            </ScrollView>
                        </TabScreen>
                        <TabScreen label="Tasks " icon="pencil">
                            <View>
                                <TaskCalendar taskDates={todoTaskDates} onDaySelect={onDaySelect} updateYear={setCurrentYear} />
                                <Tasks tasks={todoSelectedTasks} selected={selected} userId={userID} plantID={plantID} setCounter={setCounter} counter={counter} maxHeight={160} setChange={setChange} />
                                {selected &&
                                    <GoBackButton onPress={goBack} />
                                }

                            </View>
                        </TabScreen>
                    </Tabs>
                }
                <TaskDialog hideChange={hideChange} visibleChange={visibleChange} taskName={taskName}
                    taskDueDate={taskDueDate} taskMode={taskMode} taskFrequency={taskFrequency}
                    setTaskMode={setTaskMode} setTaskDueDate={setTaskDueDate} setTaskFrequency={setTaskFrequency}
                    taskID={taskID} userID={userID} initialTaskDueDate={initialTaskDueDate} initialTaskFrequency={initialTaskFrequency}
                    initialTaskMode={initialTaskMode} setCounter={setCounter} counter={counter} plantID={plantID} taskType={taskType} />

                <BottomMenu screenHeight={screenHeight} active={"leaf"} />
            </View>
        )
    }
}