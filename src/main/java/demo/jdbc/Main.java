package demo.jdbc;


import demo.jdbc.repositories.BookRepository;



public class Main {

    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();

        bookRepository.findAll().forEach(System.out::println);


    }

}
