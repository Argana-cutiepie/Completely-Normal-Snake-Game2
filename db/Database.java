package com.mycompany.snakegame.db;

import com.mycompany.snakegame.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Opens connections and creates the table.
 *
 * MODULE 8 PROOF (JDBC architecture): Java app -> JDBC API (java.sql) -> driver
 * (sqlite-jdbc) -> DBMS -> tables. DriverManager.getConnection() creates the session.
 * The file snake_scores.db is created next to the project the first time it runs.
 *
 * To use MySQL instead (as in the Module 8 slides), change URL to
 * "jdbc:mysql://localhost:3306/school", add the Connector/J dependency to pom.xml,
 * and call DriverManager.getConnection(URL, user, password).
 */
public final class Database {

    private static final String URL = "jdbc:sqlite:snake_scores.db";

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS score (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                player      TEXT    NOT NULL,
                points      INTEGER NOT NULL CHECK (points >= 0),
                difficulty  TEXT    NOT NULL,
                played_at   TEXT    NOT NULL
            )
            """;

    private Database() {
    }

    public static Connection connect() throws DatabaseException {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            logTechnical(e);
            throw new DatabaseException("Could not open the score database.", e);
        }
    }

    /** MODULE 8 PROOF: try-with-resources closes Connection and Statement automatically. */
    public static void init() throws DatabaseException {
        try (Connection con = connect();
             Statement st = con.createStatement()) {
            st.executeUpdate(CREATE_TABLE);                       // DDL uses executeUpdate()
        } catch (SQLException e) {
            logTechnical(e);
            throw new DatabaseException("Could not prepare the score table.", e);
        }
    }

    /** Technical detail goes to the log; users only see the safe message. */
    static void logTechnical(SQLException e) {
        System.err.println("[DB] " + e.getMessage() + " (SQLState=" + e.getSQLState() + ")");
    }
}
