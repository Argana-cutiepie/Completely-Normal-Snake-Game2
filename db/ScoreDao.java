package com.mycompany.snakegame.db;

import com.mycompany.snakegame.exception.DatabaseException;
import com.mycompany.snakegame.model.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDao implements Dao<Score, Integer> {

    private static final String INSERT =
            "INSERT INTO score (player, points, difficulty, played_at) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL =
            "SELECT id, player, points, difficulty, played_at FROM score ORDER BY points DESC, id ASC";
    private static final String SELECT_BY_PLAYER =
            "SELECT id, player, points, difficulty, played_at FROM score "
                    + "WHERE player LIKE ? ORDER BY points DESC, id ASC";
    private static final String UPDATE_PLAYER = "UPDATE score SET player = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM score WHERE id = ?";

    @Override
    public void insert(Score s) throws DatabaseException {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, s.player());
            ps.setInt(2, s.points());
            ps.setString(3, s.difficulty());
            ps.setString(4, s.playedAt());
            int rows = ps.executeUpdate();
            System.out.println("[ScoreDao] " + rows + " row inserted");
        } catch (SQLException e) {
            Database.logTechnical(e);
            throw new DatabaseException("Could not save the score.", e);
        }
    }

    @Override
    public List<Score> findAll() throws DatabaseException {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            return readRows(rs);
        } catch (SQLException e) {
            Database.logTechnical(e);
            throw new DatabaseException("Could not load the scores.", e);
        }
    }

    public List<Score> findByPlayer(String text) throws DatabaseException {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_PLAYER)) {
            ps.setString(1, "%" + text + "%");       
            try (ResultSet rs = ps.executeQuery()) {
                return readRows(rs);
            }
        } catch (SQLException e) {
            Database.logTechnical(e);
            throw new DatabaseException("Could not search the scores.", e);
        }
    }

    public boolean updatePlayer(int id, String newName) throws DatabaseException {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(UPDATE_PLAYER)) {
            ps.setString(1, newName);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;           
        } catch (SQLException e) {
            Database.logTechnical(e);
            throw new DatabaseException("Could not rename the player.", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DatabaseException {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);                       
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Database.logTechnical(e);
            throw new DatabaseException("Could not delete the score.", e);
        }
    }

    private static List<Score> readRows(ResultSet rs) throws SQLException {
        List<Score> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Score(
                    rs.getInt("id"),
                    rs.getString("player"),
                    rs.getInt("points"),
                    rs.getString("difficulty"),
                    rs.getString("played_at")));
        }
        return list;
    }
}
