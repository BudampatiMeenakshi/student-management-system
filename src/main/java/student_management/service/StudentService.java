package student_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student_management.entity.Student;
import student_management.repository.StudentRepository;
import student_management.exception.StudentNotFoundException;
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {

        int total =
                student.getSubject1() +
                student.getSubject2() +
                student.getSubject3();

        student.setTotal(total);

        double average = total / 3.0;

        if(average >= 90)
            student.setGrade("A+");
        else if(average >= 80)
            student.setGrade("A");
        else if(average >= 70)
            student.setGrade("B");
        else if(average >= 60)
            student.setGrade("C");
        else
            student.setGrade("F");

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id));
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    public Student updateStudent(Long id, Student updatedStudent) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id));

        student.setName(updatedStudent.getName());
        student.setRollNo(updatedStudent.getRollNo());
        student.setBranch(updatedStudent.getBranch());

        student.setSubject1(updatedStudent.getSubject1());
        student.setSubject2(updatedStudent.getSubject2());
        student.setSubject3(updatedStudent.getSubject3());

        int total =
                updatedStudent.getSubject1() +
                updatedStudent.getSubject2() +
                updatedStudent.getSubject3();

        student.setTotal(total);

        double average = total / 3.0;

        if(average >= 90)
            student.setGrade("A+");
        else if(average >= 80)
            student.setGrade("A");
        else if(average >= 70)
            student.setGrade("B");
        else if(average >= 60)
            student.setGrade("C");
        else
            student.setGrade("F");

        return studentRepository.save(student);
    }
    public Student getByRollNo(String rollNo) {
        return studentRepository.findByRollNo(rollNo);
    }
    public long getCount() {
        return studentRepository.count();
    }
    public Student getTopper() {
        return studentRepository.findAll()
                .stream()
                .max((s1, s2) -> Integer.compare(s1.getTotal(), s2.getTotal()))
                .orElse(null);
    }
}