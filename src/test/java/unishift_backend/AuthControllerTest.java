package unishift_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import unishift_backend.controller.AuthController;
import unishift_backend.dto.LoginRequest;
import unishift_backend.dto.RegisterRequest;
import unishift_backend.model.Student;
import unishift_backend.model.User;
import unishift_backend.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testRegisterUserSuccessfully() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("student_sam");
        request.setPassword("pass123");
        request.setName("Sam Perera");
        request.setUniversityName("University of Colombo");
        request.setStudentIdNumber("STU-1001");

        ResponseEntity<String> response = authController.register(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully!", response.getBody());

        // Verify that the student is saved in the database
        Optional<User> savedUser = userRepository.findByUsername("student_sam");
        assertTrue(savedUser.isPresent());
        assertInstanceOf(Student.class, savedUser.get());

        Student student = (Student) savedUser.get();
        // Test encapsulation methods (getters)
        assertEquals("student_sam", student.getUsername());
        assertEquals("pass123", student.getPassword());
        assertEquals("Sam Perera", student.getName());
        assertEquals("STUDENT", student.getRole());
        assertEquals("University of Colombo", student.getUniversityName());
        assertEquals("STU-1001", student.getStudentIdNumber());
    }

    @Test
    void testRegisterUserWithOnlyUsernameAndPassword() {
        RegisterRequest request = new RegisterRequest("minimal_user", "password123");

        ResponseEntity<String> response = authController.register(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully!", response.getBody());

        Optional<User> savedUser = userRepository.findByUsername("minimal_user");
        assertTrue(savedUser.isPresent());
        assertEquals("minimal_user", savedUser.get().getUsername());
        assertEquals("password123", savedUser.get().getPassword());
        assertEquals("STUDENT", savedUser.get().getRole());
    }

    @Test
    void testRegisterDuplicateUsernameFails() {
        RegisterRequest firstUser = new RegisterRequest("duplicate_test_user", "pass1");
        authController.register(firstUser);

        RegisterRequest duplicateRequest = new RegisterRequest("duplicate_test_user", "pass2");
        ResponseEntity<String> response = authController.register(duplicateRequest);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username is already taken!", response.getBody());
    }

    @Test
    void testRegisterMissingCredentialsFails() {
        RegisterRequest request = new RegisterRequest("", "");

        ResponseEntity<String> response = authController.register(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username and password are required!", response.getBody());
    }

    @Test
    void testLoginAfterRegistration() {
        RegisterRequest regRequest = new RegisterRequest();
        regRequest.setUsername("kamal");
        regRequest.setPassword("kamal123");
        regRequest.setName("Kamal Gunaratne");

        ResponseEntity<String> regResponse = authController.register(regRequest);
        assertEquals(HttpStatus.CREATED, regResponse.getStatusCode());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("kamal");
        loginRequest.setPassword("kamal123");

        String loginResponse = authController.login(loginRequest);
        assertEquals("Welcome back Kamal Gunaratne!", loginResponse);
    }

    @Test
    void testStudentModelEncapsulation() {
        Student student = new Student();
        student.setUsername("encap_test");
        student.setPassword("secret");
        student.setName("Encap Student");
        student.setUniversityName("UCSC");
        student.setStudentIdNumber("S12345");

        assertEquals("encap_test", student.getUsername());
        assertEquals("secret", student.getPassword());
        assertEquals("Encap Student", student.getName());
        assertEquals("UCSC", student.getUniversityName());
        assertEquals("S12345", student.getStudentIdNumber());
        assertEquals("STUDENT", student.getRole());
    }
}
