package ies.lab3.e3.movieApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.lab3.e3.movieApi.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    
    Optional<Movie> findByTitle(String title);
    Optional<List<Movie>> findByYear(int year);
    Optional<List<Movie>> findByDirector(String director);
    Optional<List<Movie>> findByGenre(String genre);

}
