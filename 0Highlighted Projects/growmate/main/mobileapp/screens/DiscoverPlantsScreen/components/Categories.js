import {View} from "react-native";
import CategoryCard from "./CategoryCard";

export default function Categories({categories, anonymous}){
    const cardsPerRow = 3;
    const numRows = Math.ceil(categories.length / cardsPerRow);
    const rows = [];
    for (let i = 0; i < numRows; i++) {
        const start = i * cardsPerRow;
        const end = start + cardsPerRow;
        rows.push(categories.slice(start, end));
    }


    return (
        <View>
            {rows.map((row, index) => (
                <View style={{flexDirection: "row", marginTop: 30, justifyContent: "center", alignItems: "center"}} key={index}>
                {row.map((category, index) => (
                    <CategoryCard name={category.name} image={category.photo} id={category.id} key={index} anonymous={anonymous}/>
                    ))}
                </View>
            ))}
        </View>
    )
}