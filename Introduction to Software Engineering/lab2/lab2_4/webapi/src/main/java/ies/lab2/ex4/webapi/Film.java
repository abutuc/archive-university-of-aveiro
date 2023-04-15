package ies.lab2.ex4.webapi;

public class Film {
    private final long id;
    private final String title;
    private final Quote quote;

    public Film(long id, String title, Quote quote){
        this.id = id;
        this.title = title;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public Quote getQuote(){
        return quote;
    }
}
