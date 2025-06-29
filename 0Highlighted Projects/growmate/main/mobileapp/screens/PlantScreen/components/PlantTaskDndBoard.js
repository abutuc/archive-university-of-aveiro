import { useState, useEffect } from 'react';
import { StyleSheet, View, Dimensions } from 'react-native';
import { DraxProvider, DraxView } from 'react-native-drax';
import { Text, useTheme } from 'react-native-paper';
import TaskCard from './TaskCard';
import { updateTask } from '../../../service/PlantScreenService';

export default function PlantTaskDndBoard({userID, plantID, tasks}) {
    const theme = useTheme();
    const width = Dimensions.get("window").width;

    const [plantTasks, setPlantTasks] = useState([]);

    useEffect(() => {
        setPlantTasks(tasks)
    }, [tasks])

    const styles = StyleSheet.create({
        container: {
            flex: 1,
            justifyContent: 'flex-start',
            alignItems: 'center',
            paddingVertical: 30
        },
        receiver: {
            flexGrow: 1,
            width: width,
            rowGap: 10,
            alignItems: "center",
        },
        text: {
            color: theme.colors.primary,
            paddingBottom: 5,
        }
    });

    return (
        <DraxProvider>
            <View style={styles.container}>
                <Text variant="headlineLarge" style={styles.text}>To Do</Text>
                <DraxView
                    style={styles.receiver}
                    onReceiveDragDrop={({ dragged: { payload } }) => {
                        const taskIndex = plantTasks.findIndex(task => task.id === payload);
                        if (taskIndex >= 0) {
                            const updatedTasks = [...plantTasks];
                            updatedTasks[taskIndex].done = false;
                            setPlantTasks(updatedTasks);
                            updateTask(userID, plantID, payload, false);
                        }
                    }}
                >
                    {plantTasks.filter((task) => task.done === false).map((task, index) => {
                        return (
                            <DraxView
                                key={index}
                                payload={task.id}
                            >
                                <TaskCard name={task.name} key={index} />
                            </DraxView>

                        )
                    })}
                </DraxView>
                <Text variant="headlineLarge" style={styles.text}>Done</Text>
                <DraxView
                    style={styles.receiver}
                    onReceiveDragDrop={({ dragged: { payload } }) => {
                        const taskIndex = tasks.findIndex(task => task.id === payload);
                        if (taskIndex >= 0) {
                            const updatedTasks = [...tasks];
                            updatedTasks[taskIndex].done = true;
                            setPlantTasks(updatedTasks);
                            updateTask(userID, plantID, payload, true);
                        }
                    }}
                >
                    {plantTasks.filter((task) => task.done === true).map((task, index) => {
                        return (
                            <DraxView
                                key={index}
                                style={styles.cardStack}
                                payload={task.id}
                            >
                                <TaskCard name={task.name} key={index} />
                            </DraxView>

                        )
                    })}
                </DraxView>
            </View>
        </DraxProvider>
    )
}