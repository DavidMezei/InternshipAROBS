package com.example.musify.repo;

import com.example.musify.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    Optional<User> findUserByEmail(String username);
}