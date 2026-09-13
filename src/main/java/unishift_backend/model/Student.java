/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unishift_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends User {
    // Private fields (Data Hiding / Encapsulation)
    private String universityName;
    private String studentIdNumber;

    // Default constructor (required by JPA)
    public Student() {
        super();
        this.setRole("STUDENT");
    }

    // Parameterized constructor with essential credentials
    public Student(String username, String password) {
        super(username, password);
        this.setRole("STUDENT");
    }

    // Full parameterized constructor
    public Student(String username, String password, String name, String universityName, String studentIdNumber) {
        super(username, password, name, "STUDENT");
        this.universityName = universityName;
        this.studentIdNumber = studentIdNumber;
    }

    // Encapsulation: Getter and Setter methods
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }
}