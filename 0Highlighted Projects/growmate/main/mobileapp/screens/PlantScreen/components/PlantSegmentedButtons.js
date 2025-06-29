import { useState } from "react";
import { View } from "react-native";
import { SegmentedButtons, useTheme } from "react-native-paper";

export default function PlantSegmentedButtons() {
    const theme = useTheme();
    const [value, setValue] = useState('');

    return (
        <View style={{ alignItems: 'center', paddingHorizontal: 50, paddingBottom: 30 }}>
            <SegmentedButtons 
                value={value}
                onValueChange={setValue}
                buttons={[
                    {
                        value: 'info',
                        label: 'Info',
                        checkedColor: theme.colors.primary
                    },
                    {
                        value: 'stats',
                        label: 'Statistics',
                        checkedColor: theme.colors.primary
                    },
                    {
                        value: 'tasks',
                        label: 'Tasks',
                        checkedColor: theme.colors.primary
                    },
                ]}
            />
        </View>
    )
}