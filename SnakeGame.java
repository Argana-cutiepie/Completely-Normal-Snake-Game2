package com.mycompany.snakegame;

import com.mycompany.snakegame.db.Database;
import com.mycompany.snakegame.db.ScoreDao;
import com.mycompany.snakegame.exception.DatabaseException;
import com.mycompany.snakegame.service.ScoreService;
import com.mycompany.snakegame.ui.MainFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SnakeGame {

    public static void main(String[] args) {
        System.out.printf("Starting Snake Game on Java %s (%s)%n",
                Runtime.version(), System.getProperty("java.vm.name"));

        final String databaseWarning = prepareDatabase();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(new ScoreService(new ScoreDao()));
            frame.setVisible(true);
            if (databaseWarning != null) {
                JOptionPane.showMessageDialog(frame, databaseWarning, "Scores unavailable",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private static String prepareDatabase() {
        try {
            Database.init();
            return null;
        } catch (DatabaseException e) {
            return e.getMessage() + "\nYou can still play, but scores cannot be saved.";
        }
    }
}
