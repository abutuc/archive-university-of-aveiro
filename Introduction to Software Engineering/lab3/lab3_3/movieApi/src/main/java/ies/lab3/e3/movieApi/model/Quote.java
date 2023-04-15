package ies.lab3.e3.movieApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {
    private long id;
    private Movie movie;
    private String author;
    private String quote;

    public Quote(){}

    public Quote(Movie movie, String author, String quote){
        this.movie = movie;
        this.author = author;
        this.quote = quote;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name="author", nullable = false)
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name="quote", nullable = false)
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Quote [id=" + id + ", movie=" + movie.getTitle() + ", author=" + author + ", quote=" + quote + "]";
    }

}
