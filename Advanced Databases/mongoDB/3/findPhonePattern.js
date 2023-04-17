findPhonePattern = function (pattern){
    const cursor = db.phones.find();
    if (pattern.toLowerCase() == "capicua"){
        var capicuas = [];
        cursor.forEach(doc => {
            const number = doc.display.split("-")[1];
            const part1 = number.slice(0, 4);
            const part2 = number.slice(5).split("").reverse().join("");
            if (part1 == part2){
                capicuas.push(doc.display);
            }
        })

        print("Found Capicuas: ");
        capicuas.forEach(n => print(n));
    }

    else if (pattern.toLowerCase() == "non repeated digits"){
        var non_repeated_digit = [];
        cursor.forEach(doc => {
            const number = doc.display.split("-")[1];
            const digits = number.split("");
            const unique_digits = [... new Set(digits)];
            if (digits.length == unique_digits.length){
                non_repeated_digit.push(number);
            }
        })

        print("Found Non Repeating Digits Numbers:");
        non_repeated_digit.forEach(n => print(n));
    }


}