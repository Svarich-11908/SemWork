package ru.itis.repostories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public abstract class AbstractDependencyRepository implements DependencyRepository<Long,Long>{
    protected String tableName;
    protected String firstRowName;
    protected String secondRowName;
    protected JdbcTemplate jdbcTemplate;
    protected RowMapper<Long> rowMapper;

    //language=sql
    private static String count = "select count(*) from & where % = ?";
    //language=sql
    private static String getList = "select $ from & where % = ?";

    public AbstractDependencyRepository(String tableName, String firstRowName, String secondRowName, JdbcTemplate jdbcTemplate) {
        this.tableName = tableName;
        this.firstRowName = firstRowName;
        this.secondRowName = secondRowName;
        this.jdbcTemplate = jdbcTemplate;

        rowMapper = (row, rowNumber) -> new Long(row.getLong(1));
    }

    @Override
    public Long countFirstByKey(Long key) {
        return jdbcTemplate.queryForObject(count
                .replace("&", tableName)
                .replace("%", firstRowName),
                rowMapper,
                key);
    }

    @Override
    public Long countSecondByKey(Long key) {
        return jdbcTemplate.queryForObject(count
                        .replace("&", tableName)
                        .replace("%", secondRowName),
                rowMapper,
                key);
    }

    @Override
    public List<Long> findFirstByKey(Long key) {
        return jdbcTemplate.query(getList
                .replace("&", tableName)
                .replace("$", firstRowName)
                .replace("%", secondRowName),
                rowMapper,
                key);
    }

    @Override
    public List<Long> findSecondByKey(Long key) {
        return jdbcTemplate.query(getList
                        .replace("&", tableName)
                        .replace("$", secondRowName)
                        .replace("%", firstRowName),
                rowMapper,
                key);
    }
}
