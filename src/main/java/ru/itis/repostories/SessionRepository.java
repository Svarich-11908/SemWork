package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Session;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class SessionRepository extends AbstractCrudRepository<Session>{

    private static final String TABLE_NAME = "sessions";
    private static final RowMapper<Session> rowMapper = (row, rowNumber) -> new Session(
            row.getLong("id"),
            row.getString("session_id"),
            row.getLong("user_id"));

    //language=sql
    private static final String INSERT = "insert into sessions(session_id,user_id) values (?,?)";
    //language=sql
    private static final String UPDATE = "update sessions set session_id = ?, user_id = ? where id = ?";

    public SessionRepository(DataSource dataSource) {
        super(TABLE_NAME, rowMapper, new JdbcTemplate(dataSource));
    }

    @Override
    public void save(Session entity) {
        jdbcTemplate.update(INSERT, entity.getSessionId(), entity.getUserId());
    }

    @Override
    public void update(Session entity) {
        jdbcTemplate.update(UPDATE, entity.getSessionId(), entity.getUserId(), entity.getId());
    }

    public Optional<Session> findBySessionId(String sid) {
        try {
            List<Session> list = super.findAllByField(Session.class.getDeclaredField("sessionId"), sid, "session_id");
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
