package exercice.repositories.student.impls;

import exercice.entities.Section;
import exercice.entities.Student;
import exercice.repositories.student.StudentRepository;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentRepositoryImpl implements StudentRepository {

    private final static String _username = "jdbc:postgresql://localhost:5432/studentdb";
    private final static String _password = "postgres";
    private final static String _connectionUrl = "postgres";

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(_connectionUrl, _username, _password);
    }

    public Student mapStudent(ResultSet result) throws SQLException {
        return new Student(
                result.getInt("id"),
                result.getString("firstname"),
                result.getString("lastname"),
                result.getString("matricule"),
                result.getInt("sectionn_id")
        );
    }

    public Section mapSection(ResultSet result) throws SQLException {
        return new Section(
                result.getInt("id"),
                result.getString("section_name"),
                result.getString("section_code"),
                result.getString("description"),
                result.getDate("start_date").toLocalDate(),
                result.getDate("end_date").toLocalDate()

        );
    }


    @Override
    public Student findStudentById(int studentId) {
        try {


            Connection con = openConnection();

            PreparedStatement stmt = con.prepareStatement("Select * from table=? where studentId=?");

            stmt.setString(1, "student");
            stmt.setInt(2, studentId);
            ResultSet myRs = stmt.executeQuery();



            if (!myRs.next()) {
                throw new NoSuchElementException("Student with Id " + studentId + " not found");
            }

            Student student = this.mapStudent(myRs);
            con.close();
            return student;


        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
            return null;
        }
    }


    @Override
    public List<Student> findAll() {

        try {

            List<Student> students = new ArrayList<>();
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from table=?");
            stmt.setString(1, "student");
            ResultSet myRs = stmt.executeQuery();
            while (myRs.next()) {
                students.add(this.mapStudent(myRs));
            }
            con.close();  // never forget to close the connection
            return students;

        } catch (SQLException e) {

            System.out.println("Error :" + e.getMessage());
            return null;

        }
    }

    @Override
    public void save(Student student) {


        try{
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement("insert into table=? (firstName, lastName, matricule, section_id) values(?, ?, ?, ?)" );
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getMatricule());
            stmt.setInt(4, student.getSectionId());


            con.close();


        }catch(SQLException e){
                     System.out.println("Error: " + e.getMessage());
        }



    }

    @Override
    public boolean update(int studentId, Student student) {
        return false;
    }

    @Override
    public boolean delete(int studentId) {
        return false;
    }

    @Override
    public void saveAll(List<Student> students) {

    }
}
