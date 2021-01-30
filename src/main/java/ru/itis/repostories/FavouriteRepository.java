package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class FavouriteRepository extends AbstractDependencyRepository {

    private static final String TABLE_NAME = "favourites";
    private static final String FIRST_ROW_NAME = "user_id";
    private static final String SECOND_ROW_NAME = "movie_id";

    //language=sql
    private static final String INSERT = "insert into favourites(user_id, movie_id) values(?,?)";
    //language=sql
    private static final String DELETE = "delete from favourites where user_id = ? and movie_id = ?";

    public FavouriteRepository(DataSource dataSource) {
        super(TABLE_NAME, FIRST_ROW_NAME, SECOND_ROW_NAME, new JdbcTemplate(dataSource));
    }

    @Override
    public void save(Long first, Long second) {
        jdbcTemplate.update(INSERT, first, second);
    }

    @Override
    public void delete(Long first, Long second) {
        jdbcTemplate.update(DELETE, first, second);
    }
}
