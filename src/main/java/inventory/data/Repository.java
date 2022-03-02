package inventory.data;

import java.util.List;

public interface Repository <T>{
    void add(T t);
    void edit(T t);
    void delete(long id);
    T getById(long id);
    List<T> getAll();
    List<T> filter(String str);
}
