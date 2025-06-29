package ua.tqs.lab7_3;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.assertj.core.api.Assertions.assertThat;
import ua.tqs.lab7_3.data.Book;
import ua.tqs.lab7_3.data.BookRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
public class BookIntegrationTest {
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withUsername("tqs")
            .withPassword("tqs")
            .withDatabaseName("tqs");


    @Autowired
    private BookRepository bookRepository;

    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    public void testCreateBook(){
        Book book = new Book();
        book.setAuthor("Steve");
        book.setTitle("Minecraft Tutorial 101");

        bookRepository.save(book);
    }


    @Test
    @Order(2)
    public void testFindBookByTitle(){
        Book book = bookRepository.findBookByTitle("Minecraft Tutorial 101");
        assertThat(book.getTitle()).isEqualTo("Minecraft Tutorial 101");
    }

    @Test
    @Order(2)
    public void testFindBookByAuthor(){
        Book book = bookRepository.findBookByAuthor("Steve");
        assertThat(book.getAuthor()).isEqualTo("Steve");
    }

}
