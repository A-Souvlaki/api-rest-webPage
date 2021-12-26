package com.icesi.DAO;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    
    Optional<T> get(Integer id);
    
    Optional<T> get1(long id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t);
   
}