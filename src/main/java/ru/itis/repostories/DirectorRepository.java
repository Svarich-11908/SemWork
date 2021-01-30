package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Director;

import javax.sql.DataSource;

public class DirectorRepository extends AbstractCrudRepository<Director>{

    private static final String TABLE_NAME = "directors";
    private static final RowMapper<Director> rowMapper = (row, rowNumber) -> new Director(
            row.getLong("id"),
            row.getString("name"),
            row.getString("picture_path"));

    //language=sql
    private static final String INSERT = "insert into directors(name,picture_path) values (?,?)";
    //language=sql
    private static final String UPDATE = "update directors set name = ?, picture_path = ? where id = ?";

    public DirectorRepository(DataSource dataSource) {
        super(TABLE_NAME, rowMapper, new JdbcTemplate(dataSource));
    }

    @Override
    public void save(Director entity) {
        jdbcTemplate.update(INSERT, entity.getName(), entity.getPicturePath());
    }

    @Override
    public void update(Director entity) {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getPicturePath(), entity.getId());
    }

}
