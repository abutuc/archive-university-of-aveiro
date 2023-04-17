package cbd.lab2.e5;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App 
{
    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try(MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("pokemon");
            
            // Populate database
            populatePokemonCollection(5000, collection);
        }
    }

    public static void populatePokemonCollection(int n, MongoCollection<Document> collection){
        Random random = new Random();

        // List load and their sizes;
        List<String> names = loadFile("/home/andrebutuc/Desktop/CBD/G_2/P3_103530_Lab2/5/pokemon/names.txt");
        Integer names_size = names.size();
        List<String> nationalities = loadFile("/home/andrebutuc/Desktop/CBD/G_2/P3_103530_Lab2/5/pokemon/nationalities.txt");
        Integer nationalities_size = nationalities.size();
        List<String> types = loadFile("/home/andrebutuc/Desktop/CBD/G_2/P3_103530_Lab2/5/pokemon/types.txt");
        Integer types_size = types.size();
        List<String> adjectives = loadFile("/home/andrebutuc/Desktop/CBD/G_2/P3_103530_Lab2/5/pokemon/adjectives.txt");
        Integer adjectives_size = adjectives.size();
        List<String> pokemons = loadFile("/home/andrebutuc/Desktop/CBD/G_2/P3_103530_Lab2/5/pokemon/pokemons.txt");
        Integer pokemons_size = pokemons.size();

        // Boundaries
        Integer max_pokemon = 6;
        Integer min_pokemon = 1;
        Integer max_adjectives = 3;
        Integer min_adjectives = 1;
        Integer max_level = 50;
        Integer max_types = 2;
        Integer min_types = 1;
        Integer max_height = 100;
        Integer max_weight = 1000;
        Integer min_age = 10;
        Integer max_age = 100;
        Integer max_wins = 100;
        Integer max_losses = 100;
        Integer max_evolution = 100;

        // Properties variables
        Integer idx_name;
        Integer age;
        Integer idx_nationality;
        Integer trainer_level;
        Integer n_strengths;
        ArrayList<Document> strengths = new ArrayList<>();
        Integer n_weaknesses;
        ArrayList<Document> weaknesses = new ArrayList<>();
        Integer idx_enemy;
        Integer n_pokemons;
        ArrayList<Document> chosen_pokemons = new ArrayList<>();

        // Pokemon Properties
        Integer pokemon_id;
        Integer n_types;
        ArrayList<Document> pokemon_types = new ArrayList<>();
        Double height;
        Double weight;
        Integer wins;
        Integer losses;
        Integer evolution;
        Date acquired_date;



        for (int i = 0; i<n; i++){
            idx_name = random.nextInt(names_size);
            age = random.nextInt(max_age)+min_age;
            idx_nationality = random.nextInt(nationalities_size);
            trainer_level = random.nextInt(max_level);
            n_strengths = random.nextInt(max_adjectives)+min_adjectives;
            for (int s = 0; s < n_strengths; s++){
                strengths.add(new Document("strength", adjectives.get(random.nextInt(adjectives_size))));
            }

            n_weaknesses = random.nextInt(max_adjectives)+min_adjectives;
            for (int w = 0; w < n_weaknesses; w++){
                weaknesses.add(new Document("weakness", adjectives.get(random.nextInt(adjectives_size))));
            }

            idx_enemy = random.nextInt(names_size);

            n_pokemons = random.nextInt(max_pokemon)+min_pokemon;

            for (int p = 0; p < n_pokemons; p++){
                pokemon_id = random.nextInt(pokemons_size);
        
                n_types = random.nextInt(max_types) + min_types;
                for (int t = 0; t<n_types; t++){
                    pokemon_types.add(new Document("type", types.get(random.nextInt(types_size))));
                }

                height = random.nextDouble()*max_height;
                weight = random.nextDouble()*max_weight;
                wins = random.nextInt(max_wins);
                losses = random.nextInt(max_losses);
                evolution = random.nextInt(max_evolution);
                acquired_date = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
                chosen_pokemons.add(
                    new Document("_id", new ObjectId())
                    .append("pokemon_id", pokemon_id)
                    .append("pokemon_name", pokemons.get(pokemon_id))
                    .append("pokemon_type", new ArrayList<>(pokemon_types))
                    .append("height", round(height, 2))
                    .append("weight", round(weight, 2))
                    .append("wins", wins)
                    .append("losses", losses)
                    .append("evolution", evolution)
                    .append("acquired_date", acquired_date)
                );
                pokemon_types.clear();
            }
            // Document Insertion 
            collection.insertOne(
                new Document("_id", new ObjectId())
                .append("name", names.get(idx_name))
                .append("age", age)
                .append("nationality", nationalities.get(idx_nationality))
                .append("level", trainer_level)
                .append("strengths", strengths)
                .append("weaknesses", weaknesses)
                .append("enemy", names.get(idx_enemy))
                .append("pokedex", chosen_pokemons)

            );
            strengths.clear();
            weaknesses.clear();
            chosen_pokemons.clear();
        }
        
    }



    public static List<String> loadFile(String filename){
        try {
            Scanner sc = new Scanner(new File(filename));
            ArrayList<String> content = new ArrayList<>();
            while(sc.hasNextLine()){
                content.add(sc.nextLine());
            }
            sc.close();
            return content;
        }
        catch (FileNotFoundException e){
            System.err.println("File not found.");
            return null;
        }
    }
    
    // method taken from StackOverFlow https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
