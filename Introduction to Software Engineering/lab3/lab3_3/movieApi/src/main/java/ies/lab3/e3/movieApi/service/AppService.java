package ies.lab3.e3.movieApi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.lab3.e3.movieApi.exceptions.ResourceNotFoundException;
import ies.lab3.e3.movieApi.model.Movie;
import ies.lab3.e3.movieApi.model.Quote;
import ies.lab3.e3.movieApi.repository.MovieRepository;
import ies.lab3.e3.movieApi.repository.QuoteRepository;

@Service
public class AppService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private QuoteRepository quoteRepository;

    // POST Movie Request
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    // GET Movie requests
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(long id) throws ResourceNotFoundException{
        return movieRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + id));
    }

    public Movie getMovieByTitle(String title) throws ResourceNotFoundException{
        return movieRepository.findByTitle(title)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this title :: " + title));
    }

    public List<Movie> getMoviesByYear(int year) throws ResourceNotFoundException{
        return movieRepository.findByYear(year)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this title :: " + year));
    }

    public List<Movie> getMoviesByDirector(String director) throws ResourceNotFoundException{
        return movieRepository.findByDirector(director)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this director :: " + director));
    }

    public List<Movie> getMoviesByGenre(String genre) throws ResourceNotFoundException{
        return movieRepository.findByGenre(genre)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this genre :: " + genre));
    }


    // POST Quote Request
    public Quote createQuote(Quote quote){
        return quoteRepository.save(quote);
    }


    // GET Quote Requests

    public List<Quote> getAllQuotes(){
        return quoteRepository.findAll();
    }

    public Quote getQuoteById(long id) throws ResourceNotFoundException{
        return quoteRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id :: " + id));
    }

    public List<Quote> getQuoteByAuthor(String author) throws ResourceNotFoundException{
        return quoteRepository.findByAuthor(author)
          .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this author :: " + author));
    }

    public List<Quote> getQuotesByMovie(long movie_id) throws ResourceNotFoundException{
        return quoteRepository.findByMovieId(movie_id)
          .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this movie :: " + movie_id));
    }

    // PUT Quote Request

    public Quote updateQuote(long id, Quote updated_quote) throws ResourceNotFoundException{
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id :: " + id));
        quote.setAuthor(updated_quote.getAuthor());
        quote.setMovie(updated_quote.getMovie());
        quote.setQuote(updated_quote.getQuote());
        return createQuote(quote);
    }

    // DELETE Quote Request
    public Map<String, Boolean> deleteQuote(long id) throws ResourceNotFoundException {
        quoteRepository.delete(getQuoteById(id));
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    // PUT Movie Request
    public Movie updateMovie(long id, Movie updated_movie) throws ResourceNotFoundException{
        System.out.println(updated_movie);
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + id));
        movie.setTitle(updated_movie.getTitle());
        movie.setDirector(updated_movie.getDirector());
        movie.setGenre(updated_movie.getGenre());
        movie.setRating(updated_movie.getRating());
        movie.setYear(updated_movie.getYear());
        List<Quote> quotes_to_update = getQuotesByMovie(updated_movie.getId());
        for (Quote q: quotes_to_update){
            q.setMovie(movie);
            updateQuote(q.getId(), q);
        }
        return createMovie(movie);
    }


    // DELETE Movie Request
    public Map<String, Boolean> deleteMovie(long id) throws ResourceNotFoundException {
        List<Quote> quotes_to_delete = getQuotesByMovie(id);
        for (Quote q: quotes_to_delete){
            deleteQuote(q.getId());
        }

        movieRepository.delete(getMovieById(id));

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    
    // API Specific End Points
    public Quote getRandomQuote(){
        List<Quote> quotes = getAllQuotes();
        Random random = new Random();
        int random_idx = random.nextInt(quotes.size());
        return quotes.get(random_idx);
    }

    public Quote getRandomQuoteFromMovie(long id) throws ResourceNotFoundException{
        List<Quote> quotes = getQuotesByMovie(id);
        Random random = new Random();
        int random_idx = random.nextInt(quotes.size());
        return quotes.get(random_idx);
    }

}