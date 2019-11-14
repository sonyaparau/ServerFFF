package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    Integer deleteByUsername(String username);
    User findByEmail(String email);
}
