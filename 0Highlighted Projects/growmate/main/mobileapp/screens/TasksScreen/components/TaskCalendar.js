import {View} from "react-native";
import {Calendar} from "react-native-calendars"
import {useTheme} from "react-native-paper";

export default function TaskCalendar({taskDates, onDaySelect, updateYear}){
    const theme = useTheme();
    return(
        <View>
            <Calendar
                theme={{
                    todayBackgroundColor: theme.colors.primary,
                    todayTextColor: theme.colors.background,
                    arrowColor: theme.colors.tertiary,
                    textDayFontSize: 14
                }}
                style={{marginHorizontal: 30}}
                // Handler which gets executed on day press. Default = undefined
                onDayPress={day => onDaySelect(day)}
                // Handler which gets executed on day long press. Default = undefined
                // Month format in calendar title. Formatting values: http://arshaw.com/xdate/#Formatting
                monthFormat={'MMMM yyyy'}
                // Handler which gets executed when visible month changes in calendar. Default = undefined
                onMonthChange={(date) => {updateYear(date.year);}}
                arrowColor={'white'}
                // If firstDay=1 week starts from Monday. Note that dayNames and dayNamesShort should still start from Sunday
                firstDay={1}
                // Handler which gets executed when press arrow icon left. It receive a callback can go back month
                onPressArrowLeft={undefined}
                // Handler which gets executed when press arrow icon right. It receive a callback can go next month
                onPressArrowRight={undefined}
                // Disable all touch events for disabled days. can be override with disableTouchEvent in markedDates
                disableAllTouchEventsForDisabledDays={true}
                // Replace default month and year title with custom one. the function receive a date as parameter
                // Enable the option to swipe between months. Default = false
                enableSwipeMonths={true}
                markedDates={taskDates}
            />
        </View>
    )
}