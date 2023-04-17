phonePerPrefix = function (){
    const cursor = db.phones.find({});
    var dict = {};
    cursor.forEach(doc => {
        const prefix = doc.components.prefix
        if (prefix in dict){
            dict[prefix]++;
        }
        else {
            dict[prefix] = 1;
        }});

    print(JSON.stringify(dict));
}