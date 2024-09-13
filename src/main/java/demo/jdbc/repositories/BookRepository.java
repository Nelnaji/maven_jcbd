package demo.jdbc.repositories;

import demo.jdbc.entities.Author;
import demo.jdbc.entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public Book findById(String isbn) {

        try{
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_jdbc", "postgres", "Test1234=");
            PreparedStatement stmt = con.prepareStatement("select * from book b where isbn=?");
            stmt.setString(1,isbn);
            ResultSet rslt = stmt.executeQuery();

            Book book;

            con.close();

            return book;


        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }



    public List<Book> findAll() {

        try{
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_jdbc", "postgres", "Test1234=");
            Statement stmt = con.createStatement();
            ResultSet rslt = stmt.executeQuery("select * from book b join author a on b.author_id  = a.id");
            List<Book> books = new ArrayList<>();
            while (rslt.next() ) {
                books.add(mapBook(rslt));
            }


            con.close();

            return books;


        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }


    private Book mapBook(ResultSet rslt) throws SQLException{

        Author author = new Author(
                rslt.getInt("id"),
                rslt.getString("firstname"),
                rslt.getString("lastname"),
                rslt.getDate("birthdate").toLocalDate()
        );

        return new Book(
                rslt.getString("isbn"),
                rslt.getString("title"),
                rslt.getString("description"),
                rslt.getDate("release_date").toLocalDate(),
                author
        );

    }




    public Book fingById(int id){
return null;
    }

}
