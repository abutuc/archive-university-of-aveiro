import {Button, Dialog, Text, TextInput, useTheme, Switch} from "react-native-paper";
import React, {useState} from "react";
import {View} from "react-native";
import {toggleTaskMode, updateTaskDate, updateTaskFrequency} from "../../../service/PlantScreenService";


export default function TaskDialog({visibleChange, hideChange, taskName, taskDueDate, taskMode, taskFrequency, setTaskDueDate, setTaskMode, setTaskFrequency, userID, taskID,
                                   initialTaskDueDate, initialTaskFrequency, initialTaskMode, setCounter, counter, plantID, taskType}) {
    const theme = useTheme();
    const [editTask, setEditTask] = useState(false);
    const [validDate, setValidDate] = useState(true);
    const [validFrequency, setValidFrequency] = useState(true);
    const dateRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|1\d|2\d|3[01])$/;
    const frequencyRegex = /^\d+$/;



    const dateTextChanged = (text) => {
        if (dateRegex.test(text)){
            setValidDate(true);
        }
        else {
            setValidDate(false);
        }
        setTaskDueDate(text);
    }

    const frequencyTextChanged = (text) => {
        if (frequencyRegex.test(text)){
            setValidFrequency(true);
        }
        else {
            setValidFrequency(false);
        }
        setTaskFrequency(text);
    }

    const saveSettings = () => {

        if (initialTaskMode !== taskMode){
            if (initialTaskFrequency.toString() !== taskFrequency.toString()){
                toggleTaskMode(userID, plantID, taskType, taskFrequency);
            }
            else {
                toggleTaskMode(userID, plantID, taskType);
            }

        }
        else {
            if (initialTaskFrequency.toString() !== taskFrequency.toString()){
                updateTaskFrequency(userID, plantID, taskType, taskFrequency);
            }
        }

        if (initialTaskDueDate !== taskDueDate){
            updateTaskDate(userID, taskID, taskDueDate);
        }

        setEditTask(false);
        setCounter(counter+1);
    }

    return (
        <Dialog visible={visibleChange} onDismiss={hideChange} style={{ backgroundColor: theme.colors.background }}>
            <Dialog.Content>
                <Text variant={"bodyLarge"} style={{textAlign: "center", marginBottom: 15, color: theme.colors.primary}}>{taskName}</Text>
                    {editTask ?
                        <View>
                            <View style={{flexDirection: "row", marginBottom: 10, marginHorizontal: 30, alignItems: "center", justifyContent: "space-between"}}>
                                <Text variant={"bodyMedium"}>Due date: </Text>
                                <TextInput
                                value={taskDueDate}
                                onChangeText={text => dateTextChanged(text)}
                                contentStyle={{backgroundColor: theme.colors.background}}
                                activeUnderlineColor={validDate ? theme.colors.primary : theme.colors.error}
                                underlineColor={validDate ? theme.colors.primary : theme.colors.error}/>
                            </View>
                            <View style={{flexDirection: "row", marginBottom: 10, marginHorizontal: 30, alignItems: "center", justifyContent: "space-between"}}>
                                <Text variant={"bodyMedium"}>Automatic Mode: </Text>
                                <Switch value={taskMode} onValueChange={() => {setTaskMode(!taskMode)}} style={{ transform: [{ scaleX: 1.2 }, { scaleY: 1.3 }] }}
                                />
                            </View>
                            {taskMode === false &&
                                <View style={{flexDirection: "row", marginBottom: 10, marginHorizontal: 30, alignItems: "center", justifyContent: "space-between"}}>
                                    <Text variant={"bodyMedium"}>Task frequency: </Text>
                                    <TextInput
                                        value={taskFrequency.toString()}
                                        onChangeText={text => frequencyTextChanged(text)}
                                        contentStyle={{backgroundColor: theme.colors.background}}
                                        activeUnderlineColor={validFrequency ? theme.colors.primary : theme.colors.error}
                                        underlineColor={validFrequency ? theme.colors.primary : theme.colors.error}/>
                                </View>
                            }
                        </View>
                        :
                        <View>
                            <View style={{flexDirection: "row", marginBottom: 10, marginHorizontal: 30, alignItems: "center", justifyContent: "space-between"}}>
                                <Text variant={"bodyMedium"}>Due date: </Text>
                                <Text variant={"bodyMedium"}>{taskDueDate}</Text>
                            </View>
                            <View style={{flexDirection: "row", marginBottom: 10, marginHorizontal: 30, alignItems: "center", justifyContent: "space-between"}}>
                                <Text variant={"bodyMedium"}>Automatic Mode: </Text>
                                <Switch disabled={true} value={taskMode} onValueChange={() => {setTaskMode(!taskMode)}} style={{ transform: [{ scaleX: 1.2 }, { scaleY: 1.3 }] }}
                                />
                            </View>
                            {taskMode === false &&
                                <View style={{flexDirection: "row", marginBottom: 10, marginHorizontal: 30, alignItems: "center", justifyContent: "space-between"}}>
                                    <Text variant={"bodyMedium"}>Task frequency: </Text>
                                    <Text variant={"bodyMedium"}>{taskFrequency}</Text>
                                </View>
                            }
                        </View>
                    }

            </Dialog.Content>
            <Dialog.Actions>
                {editTask ?
                    <Button disabled={!(validDate && validFrequency)} onPress={saveSettings} style={{width: 50}} buttonColor={theme.colors.primary} textColor={theme.colors.background}>Save</Button>
                    :
                    <Button onPress={() => {setEditTask(true)}} style={{width: 50}} buttonColor={theme.colors.secondary} textColor={theme.colors.background}>Edit</Button>

                }
            </Dialog.Actions>
        </Dialog>
    )
}