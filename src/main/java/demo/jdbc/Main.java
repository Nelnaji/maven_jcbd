package demo.jdbc;


import demo.jdbc.entities.Book;
import demo.jdbc.repositories.impls.BookRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookRepositoryImpl bookRepository = new BookRepositoryImpl();

        bookRepository.findAll().forEach(System.out::println);

        Book firstBook = new Book("1654+65", "Comte de monte cristo", "lorem ipsum", LocalDate.now(), 451);
        Book secondBook = new Book("1654+65", "Comte de monte cristo", "lorem ipsum", LocalDate.now(), 451);
        Book thirdBook = new Book("1654+65", "Comte de monte cristo", "lorem ipsum", LocalDate.now(), 451);

        List<Book> myBookList = new ArrayList<>();
        myBookList.add(firstBook);
        myBookList.add(secondBook);
        myBookList.add(thirdBook);

        bookRepository.saveAll(myBookList);
    }
}
