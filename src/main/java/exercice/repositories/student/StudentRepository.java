package exercice.repositories.student;

import exercice.entities.Student;

import java.util.List;

public interface StudentRepository {
    Student findStudentById(int studentId);
    List<Student> findAll();
    void save(Student student);
    boolean update(int id, Student student);
    boolean delete(int studentId);
    void saveAll(List<Student> students);

}
