package demo.jdbc.repositories.impls;


import demo.jdbc.entities.Author;
import demo.jdbc.entities.Book;
import demo.jdbc.repositories.BookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookRepositoryImpl implements BookRepository {

    private static final String _connectionString = "jdbc:postgresql://localhost:5432/demo_jdbc";
    private static final String _username = "postgres";
    private static final String _password = "postgres";

    public List<Book> findAll() {
        try {

            List<Book> books = new ArrayList<>();


            Connection con = openConnection();


            Statement stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery("select * from book b ");

            while (rs.next()) {

                books.add(this.mapBook(rs));

            }
            con.close();

            return books;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public Book findById(String isbn) {
        try {
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "select * " +
                            "from book b " +
                            "where isbn=?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            Book book;
            if (!rs.next()) {
                throw new NoSuchElementException("Book with ISBN " + isbn + " not found");
            }
            book = this.mapBook(rs);

            con.close();

            return book;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void save(Book book) {

        try {
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "insert into book(" +
                            "isbn," +
                            "title," +
                            "description," +
                            "release_date," +
                            "author_id)" +
                            "VALUES(?, ?, ?, ?, ?)"
            );
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getDescription());
            stmt.setDate(4,Date.valueOf(book.getPublicationDate()));
            stmt.setInt(5, book.getAuthorId());

            int nbRowsAffected = stmt.executeUpdate();

            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(_connectionString,_username,_password);
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getString("isbn"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("release_date").toLocalDate(),
                rs.getInt("author_id")
        );
    }

    private Author mapAuthor(ResultSet rs) throws SQLException {
        return new Author(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birthdate").toLocalDate()
        );
    }

    private Book mapFullBook(ResultSet rs) throws SQLException {
        Book book = mapBook(rs);
        book.setAuthor(mapAuthor(rs));
        return book;
    }


    public boolean update(String isbn, Book book){
        try {
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "update book " +
                            " set title=?,"  +
                            "description=?," +
                            "release_date=?," +
                            "author_id=?" + " where isbn=?"
            );


            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getDescription());
            stmt.setDate(3,Date.valueOf(book.getPublicationDate()));
            stmt.setInt(4, book.getAuthorId());
            stmt.setString(5, isbn);

            int nbRowsAffected = stmt.executeUpdate();

            con.close();

            return nbRowsAffected == 1;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }


    public boolean delete(String isbn) {

        try{
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "delete from book where isbn=?"
            );
            stmt.setString(1,isbn);

            int nbRowsAffected = stmt.executeUpdate();

            con.close();
            return nbRowsAffected == 1;
        } catch (SQLException e) {

            System.out.println("Error: " +e.getMessage());
            return false;
        }


    }



    public void saveAll(List<Book> books){

        try{
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "insert into book( isbn, title, description, release_date, author_id) values(?,?,?,?,?)"
            );
            for (Book book : books) {
                stmt.setString(1, book.getIsbn());
                stmt.setString(2,book.getTitle());
                stmt.setString(3, book.getDescription());
                stmt.setDate(4, Date.valueOf(book.getPublicationDate()));
                stmt.setInt(5, book.getAuthorId());
                stmt.addBatch();


            }


        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}