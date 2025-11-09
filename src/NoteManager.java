import java.util.List;

public class NoteManager {
    private static NoteManager instance;
    private NoteDAO noteDAO;

    private NoteManager() {
        noteDAO = new NoteDAO();
    }

    public static NoteManager getInstance() {
        if (instance == null) {
            instance = new NoteManager();
        }
        return instance;
    }

    public void addNote(Note note) {
        noteDAO.addNote(note);
    }

    public void removeNote(Note note) {
        noteDAO.deleteNote(note.getTitle());
    }

    public void updateNoteStatus(Note note) {
        noteDAO.updateNoteStatus(note.getTitle(), note.isCompleted());
    }

    public List<Note> getNotes() {
        return noteDAO.getAllNotes();
    }
}