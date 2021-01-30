package ru.itis.services;

import java.util.List;

public interface ListService<T> {
    List<T> getList();
    List<T> getMatching(String sub);
}
