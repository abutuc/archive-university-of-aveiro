package ies.lab3.e3.movieApi.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.lab3.e3.movieApi.exceptions.ResourceNotFoundException;
import ies.lab3.e3.movieApi.model.Quote;
import ies.lab3.e3.movieApi.service.AppService;

@RestController
@RequestMapping("/apiquo/")
public class QuoteController {

    @Autowired
    private AppService service;

    @GetMapping("/quotes")
    public List<Quote> getAllQuotes(@RequestParam(name="author", required = false) String author, @RequestParam(name="movie", required = false) Long movieId) throws ResourceNotFoundException{
        if (author != null){
            return service.getQuoteByAuthor(author);
        }
        else if (movieId != null){
            return service.getQuotesByMovie(movieId);  
        }

        return service.getAllQuotes();
    }

    @GetMapping("/quotes/{id}")
    public Quote getQuoteById(@PathVariable(value = "id") Long quoteId) throws ResourceNotFoundException{
        return service.getQuoteById(quoteId);
    }

    @GetMapping("/quotes/movie/{movie}")
    public Quote getRandomQuoteFromMovie(@PathVariable(value = "movie") Long movieId) throws ResourceNotFoundException{
        return service.getRandomQuoteFromMovie(movieId);
    }

    @GetMapping("/quote")
    public Quote getRandomQuote(){
        return service.getRandomQuote();
    }

    @PostMapping("/quotes")
    public Quote createQuote(@Valid @RequestBody Quote quote){
        return service.createQuote(quote);
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable(value = "id") Long quoteId, @Valid @RequestBody Quote quoteDetails) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.updateQuote(quoteId, quoteDetails));
    }

    @DeleteMapping("/quotes/{id}")
    public Map<String, Boolean> deleteQuote(@PathVariable(value = "id") Long quoteId ) throws ResourceNotFoundException{
        return service.deleteQuote(quoteId);
    }

}
