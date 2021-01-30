package ru.itis.repostories;

import java.util.List;

public interface DependencyRepository<T,K> {
    Long countFirstByKey(K key);
    Long countSecondByKey(T key);
    List<T> findFirstByKey(K key);
    List<K> findSecondByKey(T key);

    void save(T first, K second);
    void delete(T first, K second);
}
