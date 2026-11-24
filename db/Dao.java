package com.mycompany.snakegame.db;

import com.mycompany.snakegame.exception.DatabaseException;
import java.util.List;

public interface Dao<T, K> {

    void insert(T item) throws DatabaseException;      // C - create

    List<T> findAll() throws DatabaseException;        // R - read

    boolean deleteById(K id) throws DatabaseException; // D - delete
}
