package demo.jdbc.repositories;

import demo.jdbc.entities.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book findById(String isbn);
    void save(Book book);
    boolean update(String isbn, Book book);
    boolean delete(String isbn);
    void saveAll(List<Book> books);

}
