package com.example.tusker.repositories;

import com.example.tusker.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.SQLException;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npjt){
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npjt;
    }

    @Override
    public void create(User u) {
        if (!Objects.isNull(u)){
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("account_name", u.name())
                    .addValue("email", u.email())
                    .addValue("password", u.pass());

            npJdbcTemplate.update("""
                    INSERT INTO accounts (email, password, account_name)
                    VALUES(:email, :password, :account_name)
                """,
                parameters);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User u) throws SQLException {

    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
