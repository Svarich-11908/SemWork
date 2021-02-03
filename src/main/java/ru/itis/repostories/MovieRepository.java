package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Movie;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

public class MovieRepository extends AbstractCrudRepository<Movie>{

    private static final String TABLE_NAME = "movies";
    private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (row, rowNumber) -> new Movie(
            row.getLong("id"),
            row.getString("title"),
            row.getString("description"),
            row.getLong("director_id"),
            row.getInt("length"),
            row.getInt("year"),
            row.getString("picture_path"));

    //language=sql
    private static final String INSERT = "insert into movies(title,description,director_id,length,year,picture_path) values (?,?,?,?,?,?)";
    //language=sql
    private static final String UPDATE = "update movies set title = ?, description = ?, director_id = ?, length = ?, year = ?, picture_path = ? where id = ?";

    public MovieRepository(DataSource dataSource) {
        super(TABLE_NAME, MOVIE_ROW_MAPPER, new JdbcTemplate(dataSource));
    }

    @Override
    public void save(Movie entity) {
        jdbcTemplate.update(INSERT, entity.getTitle(), entity.getDescription(), entity.getDirectorId(),
                entity.getLength(), entity.getYear(), entity.getPicturePath());
    }

    @Override
    public void update(Movie entity) {
        jdbcTemplate.update(UPDATE, entity.getTitle(), entity.getDescription(), entity.getDirectorId(),
                entity.getLength(), entity.getYear(), entity.getPicturePath(), entity.getId());
    }

    public List<Movie> findByDirectorId(Long id) {
        try {
            return super.findAllByField(Movie.class.getDeclaredField("directorId"), id, "director_id");
        } catch (NoSuchFieldException e) {
            return new LinkedList<>();
        }
    }

}
