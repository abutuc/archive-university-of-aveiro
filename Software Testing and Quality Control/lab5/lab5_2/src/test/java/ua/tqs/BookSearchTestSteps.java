package ua.tqs;


import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
public class BookSearchTestSteps {
    private Library library;
    private List<Book> search_result;

    @Before
    public void setUp(){
        library = new Library();
        search_result = new ArrayList<>();
    }


    @ParameterType("[0-9]{4}-[0-9]{2}-[0-9]{2}")
    public Date date(String date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterType("[0-9]{4}")
    public Date year_start(String year){
        return date(year + "-01-01");
    }

    @ParameterType("[0-9]{4}")
    public Date year_end(String year){
        return date(year + "-12-31");
    }

    @DataTableType
    public Book bookEntry(Map<String, String> entry){
        return new Book(
                entry.get("title"),
                entry.get("author"),
                date(entry.get("published")));
    }

    @Given("the following books")
    public void add_following_books(List<Book> books){
        for (Book book: books){
            library.addBook(book);
        }
    }

    @And("a/another book with the title {string}, written by {string}, published in {date}")
    public void and_a_another_book(String title, String author, Date date){
        Book book = new Book(title, author, date);
        library.addBook(book);
    }

    @When("the customer searches for books published between {year_start} and {year_end}")
    public void customer_searches_between_years_x_and_y(Date start, Date end){
        search_result = library.findBooks(start, end);
    }

    @Then("{int} books should have been found")
    public void x_books_should_have_been_found(int x){
        assertThat(search_result.size()).isEqualTo(x);
    }

    @And("Book {int} should have the title {string}")
    public void book_i_should_have_title_t(int i, String title){
        assertThat(search_result.get(i-1).getTitle()).isEqualTo(title);
    }

}
