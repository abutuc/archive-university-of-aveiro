package cbd.lab2.ex4;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class App 
{
    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");
            // Alinea A
            alineaA(collection);
            
            // Alinea B
            alineaB(collection);

            // ALinea C
            alineaC(collection);

            // Alinea D
            alineaD(collection);
        
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void alineaA(MongoCollection<Document> collection){
        try {
            Document doc = collection.find().first(); // pesquisa da coleção, equivalente a db.restaurants.find().limit(1)
            System.out.println(doc.toJson());
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("\nAttempt to create and insert restaurant:");
            InsertOneResult result=collection.insertOne(createRestaurantDocument(createAddress("Fake Building", createRandomCoords(), "Fake Stress", 404), "Fake Localidade", "Fake Gastronomia", createRandomGrades(3), "Fake Name", 404404));
            ObjectId insertedId = result.getInsertedId().asObjectId().getValue();
            System.out.println("Inserted document with the following id: " + insertedId);

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("\nAttempt to update restaurant:");
            Bson update = Updates.set("gastronomia", "Healthy");
            UpdateResult result = collection.updateOne(eq("nome", "Fake Name"), update);
            System.out.println("Matched " + result.getMatchedCount() + " documents. Updated documents: " + result.getModifiedCount());

        } catch (Exception e) {
            System.out.println(e);
        }
        /* */
        try {
            System.out.println("\nAttempt to delete restaurant:");
            collection.deleteOne(eq("nome", "Fake Name"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void alineaB(MongoCollection<Document> collection){
       
        String resultCreateIndexLocalidade = createAscendingIndex(collection, "localidade");
        System.out.println(String.format("Index created: %s", resultCreateIndexLocalidade));

        String resultCreateIndexGastronomia = createAscendingIndex(collection, "gastronomia");
        System.out.println(String.format("Index created: %s", resultCreateIndexGastronomia));

        String resultCreateIndexNome = createTextIndex(collection, "nome");
        System.out.println(String.format("Index created: %s", resultCreateIndexNome));
       

        Bson filter = eq("gastronomia", "American");
        Bson sort = Sorts.ascending("localidade");
        Bson projection = fields(include("nome", "localidade"), excludeId());
        FindIterable<Document> cursor = collection.find(filter).sort(sort).projection(projection);
        cursor.forEach(doc -> System.out.println(doc.toJson()));

        filter = eq("localidade", "Brooklyn");
        sort = Sorts.ascending("gastronomia");
        projection = fields(include("nome", "gastronomia"), excludeId());
        cursor = collection.find(filter).sort(sort).projection(projection);
        cursor.forEach(doc -> System.out.println(doc.toJson()));

        filter = text("Deli");
        projection = fields(include("nome"), excludeId());
        cursor = collection.find(filter).projection(projection);
    
        cursor.forEach(doc -> System.out.println(doc.toJson()));


    }

    public static void alineaC(MongoCollection<Document> collection) throws ParseException{
        
        // Query 2.9
        Bson filter = and(lt("address.coord.0", -65), ne("gastronomia", "American"), gt("grades.score", 70));
        Bson projection = fields(include("nome"), excludeId());
        
        System.out.println("Query 2.9:");
        collection.find(filter).projection(projection).forEach(doc -> System.out.println(doc.toJson()));

        // Query 2.16
        filter = and(gt("address.coord.1", 42), lte("address.coord.1", 52));
        projection = fields(include("nome", "address"), excludeId());
        
        System.out.println("Query 2.16:");
        collection.find(filter).projection(projection).forEach(doc -> System.out.println(doc.toJson()));

        // Query 2.19
        
        System.out.println("Query 2.19:");

        collection.aggregate(Arrays.asList(Aggregates.group("$localidade", Accumulators.sum("count", 1)), Aggregates.sort(Sorts.descending("count")))).forEach(doc -> System.out.println(doc.toJson()));


        // Query 2.21

        System.out.println("Query 2.21:");
        filter = and(gt("scoreSum", 50), eq("gastronomia", "Portuguese"), lt("address.coord.0", -60));
        collection.aggregate(Arrays.asList(Aggregates.project(
            Projections.fields(
                excludeId(),
                include("nome", "gastronomia", "address.coord"),
                computed("scoreSum", new Document("$sum", Arrays.asList("$grades.score"))))
            ), Aggregates.match(filter)
        )).forEach(doc -> System.out.println(doc.toJson()));

        // Query 2.23
        System.out.println("Query 2.23:");
        filter = eq("address.rua", "Fifth Avenue");
        int sumGastronomia = 0;
        DistinctIterable<String> docs = collection.distinct("gastronomia", filter, String.class);
        MongoCursor<String> results = docs.iterator();
        while(results.hasNext()){
            sumGastronomia++;
            results.next();
        }
        System.out.println("Número de gastronomias: " + sumGastronomia);

    }
        

    public static void alineaD(MongoCollection<Document> collection){
        try {

            FileWriter writer = new FileWriter("CBD_L204_103530.txt");
            writer.write("Número de localidades distintas: " + countLocalidades(collection));
    
            writer.write("\n\nNúmero de restaurantes por localidade: \n");
            Map<String, Integer> m = countRestByLocalidade(collection);
            for (String localidade: m.keySet()){
                writer.write("-> " + localidade + " - " + m.get(localidade) + "\n");
            }
    
            writer.write("\n\nNome de restaurantes contendo 'Park' no nome:\n");
            List<String> restaurants = getRestWithNameCloserTo("Park", collection);
            for (String restaurant: restaurants){
                writer.write("-> " + restaurant + "\n");
            } 
            writer.close();  
        } catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }


    public static String createAscendingIndex(MongoCollection<Document> collection, String field){
        return collection.createIndex(Indexes.ascending(field));
    }

    public static String createTextIndex(MongoCollection<Document> collection, String field){
        return collection.createIndex(Indexes.text(field));
    }


    public static Document createRestaurantDocument(Document address, String localidade, String gastronomia, List<Document> grades, String nome, Integer restaurant_id){
        return new Document("_id", new ObjectId())
            .append("address", address)
            .append("localidade", localidade)
            .append("gastronomia", gastronomia)
            .append("grades", grades)
            .append("nome", nome)
            .append("restaurant_id", restaurant_id);
    }
    
    public static Document createAddress(String building, List<Double> coords, String rua, Integer zipcode){
        return new Document("building", building)
                            .append("coord", coords)
                            .append("rua", rua)
                            .append("zipcode", zipcode);

    }

    public static List<Double> createCoords(Double latitude, Double longitude){
        ArrayList<Double> coords = new ArrayList<>();
        coords.add(latitude);
        coords.add(longitude);
        return coords;
    }

    public static List<Double> createRandomCoords(){
        ArrayList<Double> coords = new ArrayList<>();
        Random random = new Random();
        coords.add(random.nextDouble()*180 - 90);
        coords.add(random.nextDouble()*360 - 180);
        return coords;
    }

    public static List<Document> createRandomGrades(Integer numberOfGrades){
        List<Document> grades = new ArrayList<>();
        for (int i = 0; i<numberOfGrades; i++){
            grades.add(createRandomGrade(i));
        }
        return grades;
    }

    public static Document createRandomGrade(Integer seed){
        Random random = new Random(seed);
        String[] grades = {"A", "B", "C", "D", "E"};
        String chosen_grade = grades[random.nextInt(grades.length)];
        Integer chosen_score = random.nextInt(21);
        return new Document("date", new Date())
                            .append("grade", chosen_grade)
                            .append("score", chosen_score);
    }

    public static int countLocalidades(MongoCollection<Document> collection){
        int sumLocalidade = 0;
        DistinctIterable<String> docs = collection.distinct("localidade", String.class);
        MongoCursor<String> results = docs.iterator();
        while(results.hasNext()){
            sumLocalidade++;
            results.next();
        }
        return sumLocalidade;
    }

    public static Map<String, Integer> countRestByLocalidade(MongoCollection<Document> collection){
        Map<String, Integer> m = new HashMap<>();
        collection.aggregate(Arrays.asList(Aggregates.group("$localidade", Accumulators.sum("count", 1)), Aggregates.sort(Sorts.descending("count")))).forEach(doc -> m.put(doc.get("_id").toString(), Integer.parseInt(doc.get("count").toString())));
        return m;
    }

    public static List<String> getRestWithNameCloserTo(String name, MongoCollection<Document> collection){
        ArrayList<String> restaurants = new ArrayList<>();
        Bson filter = text(name);
        Bson projection = fields(include("nome"), excludeId());
        FindIterable<Document> cursor = collection.find(filter).projection(projection);
        cursor.forEach(doc -> restaurants.add(doc.get("nome").toString()));
        return restaurants;

    }
}
