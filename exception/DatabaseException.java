package com.mycompany.snakegame.exception;

/**
 * MODULE 5 + 8 PROOF: wraps the low-level SQLException in a safe, readable
 * exception. The original error is kept as the "cause" for debugging, while the
 * message is safe to show to the user (raw SQL errors are never shown).
 */
public class DatabaseException extends Exception {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
