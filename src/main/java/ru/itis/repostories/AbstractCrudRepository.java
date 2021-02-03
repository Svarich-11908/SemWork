package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudRepository<T> implements CrudRepository<T> {

    protected String tableName;
    protected RowMapper<T> rowMapper;
    protected JdbcTemplate jdbcTemplate;

    //language=SQL
    private static String selectById = "select * from & where id = ?";
    //language=SQL
    private static String selectAll = "select * from &";
    //language=SQL
    private static String selectAllByField = "select * from & where # = ?";
    //language=SQL
    private static String deleteById = "delete from & where id = ?";

    protected AbstractCrudRepository(String tableName, RowMapper<T> rowMapper, JdbcTemplate jdbcTemplate) {
        this.tableName = tableName;
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<T> findById(Long id) {
        List<T> rs = jdbcTemplate.query(selectById.replace("&", tableName), rowMapper, id);
        if (rs.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(rs.get(0));
        }
    }

    @Override
    public List<T> findAll() {
        return jdbcTemplate.query(selectAll.replace("&", tableName), rowMapper);
    }

    @Override
    public List<T> findAllByIds(List<Long> ids) {
        List<T> result = new LinkedList<>();
        for (Long id : ids) {
            List<T> rs = jdbcTemplate.query(selectById.replace("&", tableName), rowMapper, id);
            if (!rs.isEmpty()) {
                result.add(rs.get(0));
            }
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(deleteById.replace("&", tableName), id);
    }

    public List<T> findAllByField(Field field, Object value) {
        return findAllByField(field, value, field.getName());
    }

    public List<T> findAllByField(Field field, Object value, String fieldName) {
        return jdbcTemplate.query(selectAllByField.replace("&", tableName).replace("#", fieldName), rowMapper, field.getType().cast(value));
}

}
