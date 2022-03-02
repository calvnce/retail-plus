package inventory.service;

import java.util.List;

public interface Service <T>{
    void add(T t);
    void edit(T t);
    void delete(long id);
    T getById(long id);
    List<T> getAll();
    List<T> filter(String name);
}
