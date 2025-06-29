package ua.tqs.lab7_3.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findBookByAuthor(String author);
    public Book findBookByBookId(Long id);
    public Book findBookByTitle(String title);
}
