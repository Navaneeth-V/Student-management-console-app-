package service;

import model.Student;

import java.util.ArrayList;
import java.util.Optional;

public class StudentService {
    private static final ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student newStudent) {
        Student student = new Student();
        student.setRollNo(newStudent.getRollNo());
        student.setName(newStudent.getName());
        student.setAge(newStudent.getAge());
        students.add(student);
    }

    public ArrayList<Student> listStudent() {
        return students;
    }

    public Student getStudentByRollNo(Integer rollNo) {
        Optional<Student> studentWithRollNo =  students.stream()
                .filter(student -> student.getRollNo().equals(rollNo))
                .findFirst();

        return studentWithRollNo.orElse(null);
    }

    public void UpdateStudent(Integer rollNo, Student updatedStudent) {
        Student student = getStudentByRollNo(rollNo);
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        students.set(students.indexOf(student), student);
    }

    public void deleteStudent(Integer rollNo) {
        Student student = getStudentByRollNo(rollNo);
        students.remove(student);
    }
}
