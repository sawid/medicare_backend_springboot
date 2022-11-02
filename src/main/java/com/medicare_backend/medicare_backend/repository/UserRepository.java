package com.medicare_backend.medicare_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicare_backend.medicare_backend.schema.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(@Param("name") String isbn);

    List<User> findByName(String name);

    List<User> findByIdentificationNumber(String identificationNumber);
    
}
