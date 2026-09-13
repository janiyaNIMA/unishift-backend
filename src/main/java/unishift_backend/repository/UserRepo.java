/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unishift_backend.repository;

/**
 *
 * @author janidu
 */

import unishift_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    // Username සහ Password එක දීලා User කෙනෙක් ඉන්නවද කියලා හොයන අලුත් ක්‍රමයක්
    Optional<User> findByUsernameAndPassword(String username, String password);

    // Registration වලදී username එක කලින් අරන්ද කියලා පරීක්ෂා කිරීමට
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}