package com.example.geofenceapp.repository;

import com.example.geofenceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUserId(Long id);
    boolean existsUserByUsername(String username);
    boolean existsUserByUserId(Long id);
    void deleteByUserId(Long id);
}