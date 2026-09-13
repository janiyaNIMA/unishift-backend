package unishift_backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    // Encapsulation: Private fields to restrict direct outside access
    private String username;
    private String password;
    private String name;
    private String universityName;
    private String studentIdNumber;

    // Default constructor
    public RegisterRequest() {
    }

    // Constructor with primary credentials
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Full constructor
    public RegisterRequest(String username, String password, String name, String universityName, String studentIdNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.universityName = universityName;
        this.studentIdNumber = studentIdNumber;
    }

    // Encapsulation: Public Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
