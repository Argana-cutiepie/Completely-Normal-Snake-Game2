package com.mycompany.snakegame.exception;

/**
 * MODULE 5 PROOF (Custom exceptions): a CHECKED exception for a business-rule
 * violation. Because it extends Exception (not RuntimeException) the compiler
 * forces callers to catch it or declare it with 'throws'.
 */
public class InvalidPlayerNameException extends Exception {

    public InvalidPlayerNameException(String message) {
        super(message);
    }
}
