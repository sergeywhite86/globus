package org.sergey_white.globus.repository;

import org.sergey_white.globus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByMailEquals(String mail);
}
