package ies.lab3.e3.movieApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.lab3.e3.movieApi.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{
    
    Optional<List<Quote>> findByMovieId(long movieId);
    Optional<List<Quote>> findByAuthor(String author);
}
