package com.example.tusker.repositories;

import com.example.tusker.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    void create(User u);
    void delete(Long id);
    void update(User u) throws SQLException;
    User findByEmail(String email) throws SQLException;
}
