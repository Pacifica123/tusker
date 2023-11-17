package com.example.tusker.repositories;

import com.example.tusker.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;
    private final RowMapper<User> rowMapper = new DataClassRowMapper<>(User.class);

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
                    .addValue("password", u.password());

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
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM accounts WHERE email = ?",
                    rowMapper,
                    email
            );
        }
        catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException(e.getExpectedSize());
        } catch (IncorrectResultSizeDataAccessException e) {
            // Найдено более одной записи
            throw new IncorrectResultSizeDataAccessException(1, e.getActualSize());
        }
    }
}
