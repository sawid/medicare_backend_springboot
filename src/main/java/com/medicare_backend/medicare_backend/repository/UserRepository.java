package com.medicare_backend.medicare_backend.repository;

import com.medicare_backend.medicare_backend.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(@Param("name") String isbn);

    List<User> findByName(String name);
}
