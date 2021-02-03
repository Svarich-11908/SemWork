package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractCrudRepository<User>{

    private static final String TABLE_NAME = "users";
    private static final RowMapper<User> USER_ROW_MAPPER = (row, rowNumber) -> new User(
            row.getLong("id"),
            row.getString("email"),
            row.getString("hash_password"));

    //language=sql
    private static final String INSERT = "insert into users(email,hash_password) values (?,?)";
    //language=sql
    private static final String UPDATE = "update users set email = ?, hash_password = ? where id = ?";

    public UserRepository(DataSource dataSource) {
        super(TABLE_NAME, USER_ROW_MAPPER, new JdbcTemplate(dataSource));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(INSERT, entity.getEmail(), entity.getHashPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UPDATE, entity.getEmail(), entity.getHashPassword(), entity.getId());
    }

    public Optional<User> findByEmail(String email) {
        try {
            List<User> list = super.findAllByField(User.class.getDeclaredField("email"), email);
            if (!list.isEmpty()) {
                return Optional.of(list.get(0));
            } else {
                return Optional.empty();
            }
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }
}
