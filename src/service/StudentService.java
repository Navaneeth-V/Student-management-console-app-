package service;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class StudentService {
    private static final String filePath = "C:/Users/navaneeth/Downloads/learning/Student Management System/src/students.txt";
    private static final ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student newStudent) {
        Student student = new Student();
        student.setRollNo(newStudent.getRollNo());
        student.setName(newStudent.getName());
        student.setAge(newStudent.getAge());
        students.add(student);
        this.saveToFile();
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
        this.saveToFile();
    }

    public void deleteStudent(Integer rollNo) {
        Student student = getStudentByRollNo(rollNo);
        students.remove(student);
        saveToFile();
    }

    public void loadFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] studentDetails = line.split(",");
                Student student = new Student();
                student.setRollNo(Integer.valueOf(studentDetails[0]));
                student.setName(studentDetails[1]);
                student.setAge(Integer.valueOf(studentDetails[2]));
                students.add(student);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for(Student student : students) {
                String newLine = student.getRollNo() + "," + student.getName() + "," + student.getAge();
                bufferedWriter.write(newLine + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
