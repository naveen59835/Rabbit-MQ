package org.niit.c2s1demo.repository;

import org.niit.c2s1demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    public List<User> findByFirstName(String firstName);
}
