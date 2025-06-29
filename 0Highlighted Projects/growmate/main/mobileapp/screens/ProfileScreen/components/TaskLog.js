import {ScrollView, View} from "react-native";
import {IconButton, Text, useTheme} from "react-native-paper";
import React, {useRef, useState} from "react";

export default function TaskLog({log}){
    const theme = useTheme();
    const scrollViewRef = useRef(null);
    const [scrollOffset, setScrollOffset] = useState(0);

    const handleScroll = (event) => {
        const offsetY = event.nativeEvent.contentOffset.y;
        setScrollOffset(offsetY);
    };
    const handleScrollToBottom = () => {
        const newOffset = scrollOffset + 100;
        // Access the ScrollView component and scroll to the bottom
        scrollViewRef.current?.scrollTo({ y: newOffset, animated: true });
        setScrollOffset(newOffset);
    };
    return (
        <View style={{justifyContent: "center", alignItems: "center", marginTop: 20}}>
            <ScrollView ref={scrollViewRef} style={{maxHeight: 270}} onScroll={handleScroll}>
                {log.map((task, index) => (
                    <View key={index}  style={{flexDirection: "column", margin: 5}}>
                        <Text variant={"bodyMedium"} style={{textAlign: "center"}}>
                            {task.name}
                        </Text>
                        <Text variant={"bodySmall"} style={{textAlign: "center"}}>
                            {task.doneDate}
                        </Text>
                    </View>

                ))}
            </ScrollView>
            <IconButton icon={"chevron-down"} iconColor={theme.colors.primary} size={35} style={{margin: 0}}
                        onPress={handleScrollToBottom}/>
        </View>
    )
}