package org.example.manager;

import org.example.dao.StudentDao;
import org.example.entity.Student;
import org.example.validate.Validated;

import java.util.List;

public class StudentManager {
    private final StudentDao dao = new StudentDao();

    public void showMenu() {
        while (true) {
            System.out.println("\n\n1. List Students");
            System.out.println("2. Add Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            int choose = Validated.inputInteger("Your choice: ", "Invalid choice. Please enter a number between 1 and 5.", 1, 5);

            if (choose == 5) {
                System.out.println("Exiting program...");
                break;
            }

            switch (choose) {
                case 1 -> listStudents();
                case 2 -> addStudent();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                default -> System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private void listStudents() {
        List<Student> students = dao.findAll();
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void addStudent() {
        String name = Validated.inputString("Enter student name: ", "Invalid name. Please try again.", 1, 50);
        int age = Validated.inputInteger("Enter student age: ", "Invalid age. Age must be a number.", 1, 150);

        Student newStudent = new Student(name,age);
        if (dao.add(newStudent)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    private void updateStudent() {
        int updateId = Validated.inputInteger("Enter student ID to update: ", "Invalid ID. ID must be a number.", 1, Integer.MAX_VALUE);

        Student existingStudent = dao.findById(updateId);
        if (existingStudent != null) {
            String newName = Validated.inputString("Enter new name: ", "Invalid name. Please try again.", 1, 50);
            int newAge = Validated.inputInteger("Enter new age: ", "Invalid age. Age must be a number.", 1, 100);

            existingStudent.setStudentName(newName);
            existingStudent.setStudentAge(newAge);
            if (dao.update(existingStudent)) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Failed to update student.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        int deleteId = Validated.inputInteger("Enter student ID to delete: ", "Invalid ID. ID must be a number.", 1, Integer.MAX_VALUE);

        if (dao.delete(deleteId)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Failed to delete student.");
        }
    }
}
