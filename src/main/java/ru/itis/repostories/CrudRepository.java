package ru.itis.repostories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(Long id);

    Optional<T> findById(Long id);
    List<T> findAll();
    List<T> findAllByIds(List<Long> ids);
}
