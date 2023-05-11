package com.company;

import java.sql.*;

public class NoteManager {
    private Connection connection;

    public NoteManager() {
        // Connect to the SQLite database
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:notes.db");
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        // Create the 'notes' table if it doesn't exist
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNote(String title, String content) {
        // Create a new note
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes (title, content) VALUES (?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editNote(int noteId, String newTitle, String newContent) {
        // Edit an existing note
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE notes SET title = ?, content = ? WHERE id = ?");
            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newContent);
            preparedStatement.setInt(3, noteId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(int noteId) {
        // Delete a note
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM notes WHERE id = ?");
            preparedStatement.setInt(1, noteId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        // Close the database connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
