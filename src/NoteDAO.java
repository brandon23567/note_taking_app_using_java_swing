import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private Connection connection;

    public NoteDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        createTableIfNotExists();
    }

    // Create the notes table if it doesn't exist
    private void createTableIfNotExists() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS notes (
                id SERIAL PRIMARY KEY,
                title VARCHAR(255) NOT NULL,
                content TEXT NOT NULL,
                completed BOOLEAN DEFAULT FALSE,
                date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("✅ Notes table created/verified successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Error creating table!");
            e.printStackTrace();
        }
    }

    // Add a new note to the database
    public boolean addNote(Note note) {
        String insertSQL = "INSERT INTO notes (title, content, completed) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getContent());
            pstmt.setBoolean(3, note.isCompleted());

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("✅ Note added to database!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error adding note to database!");
            e.printStackTrace();
            return false;
        }
    }

    // Get all notes from the database
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String selectSQL = "SELECT * FROM notes ORDER BY date_created DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                boolean completed = rs.getBoolean("completed");

                Note note = new Note(title, content);
                note.setCompleted(completed);
                notes.add(note);
            }

            System.out.println("✅ Loaded " + notes.size() + " notes from database!");
        } catch (SQLException e) {
            System.err.println("❌ Error loading notes from database!");
            e.printStackTrace();
        }

        return notes;
    }

    // Update note completion status
    public boolean updateNoteStatus(String title, boolean completed) {
        String updateSQL = "UPDATE notes SET completed = ? WHERE title = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setBoolean(1, completed);
            pstmt.setString(2, title);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("✅ Note status updated!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error updating note status!");
            e.printStackTrace();
            return false;
        }
    }

    // Delete a note from the database
    public boolean deleteNote(String title) {
        String deleteSQL = "DELETE FROM notes WHERE title = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setString(1, title);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("✅ Note deleted from database!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting note!");
            e.printStackTrace();
            return false;
        }
    }
}