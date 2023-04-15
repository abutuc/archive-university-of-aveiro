package ies.lab2.ex4.webapi;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    ArrayList<Film> quotes= new ArrayList<Film>();
    
	@GetMapping("/api/quote")
	public Quote quote() {
		return getRandomQuote();
	}

    @GetMapping("/api/films")
    public ArrayList<Film> films(){
        return loadFilms();
    }

    @GetMapping("/api/quotes")
    public Quote quotes(@RequestParam(name="show", required = true) int ID){
        return getQuoteFromID(ID); 
    }



    public static Quote getQuoteFromID(int ID){
        ArrayList<Film> films = loadFilms();
        if (ID > films.size()-1){
            return new Quote(null);
        }
        return films.get(ID).getQuote();
    }

    public static Quote getRandomQuote(){
        ArrayList<Film> films = loadFilms();
        Random random = new Random();
        Integer r = random.nextInt(10);
        return films.get(r).getQuote();
    }

    public static ArrayList<Film> loadFilms(){
        ArrayList<Film> films= new ArrayList<Film>();
        films.add(new Film(0, "Gone with the Wind" , new Quote("Frankly, my dear, I don't give a damn.")));
        films.add(new Film(1, "The Godfather", new Quote("I'm gonna make him an offer he can't refuse.")));
        films.add(new Film(2, "The Wizard of Oz", new Quote("Toto, I've a feeling we're not in Kansas anymore.")));
        films.add(new Film(3, "Casablanca", new Quote("Here's looking at you, kid.")));
        films.add(new Film(4, "Sudden Impact", new Quote("Go ahead, make my day")));
        films.add(new Film(6, "Star Wars", new Quote("May the Force be with you.")));
        films.add(new Film(7, "Taxi Driver", new Quote("You talking to me?")));
        films.add(new Film(8, "Cool Hand Luke", new Quote("What we've got here is failure to communicate.")));
        films.add(new Film(9, "Apocalypse Now", new Quote("I love the smell of napalm in the morning.")));
        return films;
    }
}