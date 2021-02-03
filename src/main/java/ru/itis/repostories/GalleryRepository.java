package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

public class GalleryRepository extends AbstractCrudRepository<String>{

    private static final String TABLE_NAME = "photos";
    private static final RowMapper<String> STRING_ROW_MAPPER = (row, rowNumber) -> row.getString("path");

    //language=sql
    private static final String INSERT = "insert into photos(path) values(?)";

    public GalleryRepository(DataSource dataSource) {
        super(TABLE_NAME, STRING_ROW_MAPPER, new JdbcTemplate(dataSource));
    }

    @Override
    public void save(String entity) {
        jdbcTemplate.update(INSERT, entity);
    }


    @Override
    public void update(String entity) {
        //no update feature for photos is provided
    }
}
