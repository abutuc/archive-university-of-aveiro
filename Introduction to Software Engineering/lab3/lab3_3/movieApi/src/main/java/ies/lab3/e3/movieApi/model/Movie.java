package ies.lab3.e3.movieApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
    private long id;
    private String title;
    private String director;
    private int year;
    private String genre;
    private int rating;

    public Movie(){}

    public Movie(String title, String director, int year, String genre, int rating){
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    @Column(name="title", nullable= false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="director", nullable= false)
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    @Column(name="genre", nullable= false)
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name="rating", nullable= false)
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Column(name="year", nullable= false)
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", genre=" + genre + ", rating=" + rating + "]";
    }
}
