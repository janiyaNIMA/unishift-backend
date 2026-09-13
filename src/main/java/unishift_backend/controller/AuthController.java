package unishift_backend.controller;

import unishift_backend.model.Student;
import unishift_backend.model.User;
import unishift_backend.repository.UserRepository;
import unishift_backend.dto.LoginRequest;
import unishift_backend.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        // Validate required fields
        if (request.getUsername() == null || request.getUsername().trim().isEmpty() ||
            request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required!");
        }

        // Check if username is already taken
        if (userRepository.existsByUsername(request.getUsername().trim())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken!");
        }

        // Create new Student instance using encapsulation (setters/constructors)
        Student student = new Student();
        student.setUsername(request.getUsername().trim());
        student.setPassword(request.getPassword());
        student.setRole("STUDENT");

        // Set name (defaulting to username if name is not provided)
        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            student.setName(request.getName().trim());
        } else {
            student.setName(request.getUsername().trim());
        }

        // Set optional student-specific details
        if (request.getUniversityName() != null && !request.getUniversityName().trim().isEmpty()) {
            student.setUniversityName(request.getUniversityName().trim());
        }

        if (request.getStudentIdNumber() != null && !request.getStudentIdNumber().trim().isEmpty()) {
            student.setStudentIdNumber(request.getStudentIdNumber().trim());
        }

        // Save student information on database
        userRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        
        if (user.isPresent()) {
            String displayName = user.get().getName() != null ? user.get().getName() : user.get().getUsername();
            return "Welcome back " + displayName + "!";
        } else {
            return "Invalid username or password!";
        }
    }
}