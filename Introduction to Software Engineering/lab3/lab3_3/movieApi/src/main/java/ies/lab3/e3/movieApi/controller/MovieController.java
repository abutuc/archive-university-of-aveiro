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
import ies.lab3.e3.movieApi.model.Movie;
import ies.lab3.e3.movieApi.service.AppService;

@RestController
@RequestMapping("/apimov/")
public class MovieController {
    @Autowired
    private AppService service;
    
    // intended feature: only one parameter is evaluated and the other ones are ignored
    @GetMapping("/movies")
    public List<Movie> getAllMovies(@RequestParam(name="year", required = false) Integer year, @RequestParam(name="director", required = false) String director, @RequestParam(name="genre", required = false) String genre) throws ResourceNotFoundException{
        if (year != null){
            return service.getMoviesByYear(year);
        }
        else if (director != null){
            return service.getMoviesByDirector(director);
        }
        else if (genre != null){
            return service.getMoviesByGenre(genre);
        }
        return service.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.getMovieById(movieId));
    }

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable(value = "title") String title) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.getMovieByTitle(title));
    }

    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie){
        return service.createMovie(movie);
    } 

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId, @Valid @RequestBody Movie movieDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.updateMovie(movieId, movieDetails));
    }

    @DeleteMapping("/movies/{id}")
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieId ) throws ResourceNotFoundException{
        return service.deleteMovie(movieId);
    }
}
