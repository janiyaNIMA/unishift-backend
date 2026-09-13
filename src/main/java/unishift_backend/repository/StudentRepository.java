package unishift_backend.repository;

import unishift_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
    Optional<Student> findByStudentIdNumber(String studentIdNumber);
}
