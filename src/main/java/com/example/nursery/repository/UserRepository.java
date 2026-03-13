package com.example.nursery.repository;

import com.example.nursery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    List<User> findByLocation_Province_Code(String code);

    List<User> findByLocation_Province_Name(String name);
}
