package com.mycompany.snakegame.db;

import com.mycompany.snakegame.exception.DatabaseException;
import java.util.List;

/**
 * MODULE 5 PROOF (Generics): a generic interface. T is the entity type and K is the key
 * type, so the same contract can describe a ScoreDao, a StudentDao, ...
 * MODULE 4 PROOF (Interfaces): ScoreDao implements this contract.
 */
public interface Dao<T, K> {

    void insert(T item) throws DatabaseException;      // C - create

    List<T> findAll() throws DatabaseException;        // R - read

    boolean deleteById(K id) throws DatabaseException; // D - delete
}
