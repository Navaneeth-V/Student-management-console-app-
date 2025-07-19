package app;

import model.Student;
import service.StudentService;

import java.util.Scanner;

public class StudentMangerApp {
    public static void main (String[] args) {
        StudentService studentService = new StudentService();
        Scanner sc = new Scanner(System.in);
        studentService.loadFile();

        while(true) {
            System.out.println("1. Add Student");
            System.out.println("2. List Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Student newStudent = readStudentDetails(sc);
                    studentService.addStudent(newStudent);
                    break;
                case 2:
                    studentService.listStudent().forEach((student) -> System.out.println(student.getRollNo() + " " + student.getName() + " " + student.getAge()));
                    break;
                case 3:
                    Student updatedStudent = readStudentDetails(sc);
                    studentService.UpdateStudent(updatedStudent.getRollNo(), updatedStudent);
                    break;
                case 4:
                    System.out.println("Enter roll no to delete");
                    Integer rollNo = sc.nextInt();
                    studentService.deleteStudent(rollNo);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public static Student readStudentDetails(Scanner sc) {
        System.out.println("Enter roll no");
        Integer rollNo = sc.nextInt();
        System.out.println("Enter name");
        String name = sc.next();
        System.out.println("Enter age");
        Integer age = sc.nextInt();

        Student student = new Student();
        student.setRollNo(rollNo);
        student.setName(name);
        student.setAge(age);
        return student;
    }
}
