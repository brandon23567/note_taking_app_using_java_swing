import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotesPanel extends JPanel {
    private MainFrame parentFrame;

    public NotesPanel(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        initComponents();
    }

    private void initComponents() {
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(245, 247, 250));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        JLabel titleLabel = new JLabel("My Notes");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));

        JLabel countLabel = new JLabel(NoteManager.getInstance().getNotes().size() + " notes");
        countLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        countLabel.setForeground(new Color(128, 128, 128));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(countLabel, BorderLayout.EAST);

        // Notes Container
        JPanel notesContainer = new JPanel();
        notesContainer.setLayout(new BoxLayout(notesContainer, BoxLayout.Y_AXIS));
        notesContainer.setBackground(new Color(245, 247, 250));
        notesContainer.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        List<Note> notes = NoteManager.getInstance().getNotes();

        if (notes.isEmpty()) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setLayout(new GridBagLayout());
            emptyPanel.setBackground(new Color(245, 247, 250));

            JLabel emptyLabel = new JLabel("No notes yet. Create your first note!");
            emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            emptyLabel.setForeground(new Color(128, 128, 128));
            emptyPanel.add(emptyLabel);

            add(emptyPanel, BorderLayout.CENTER);
        } else {
            for (Note note : notes) {
                notesContainer.add(createNoteCard(note));
                notesContainer.add(Box.createRigidArea(new Dimension(0, 15)));
            }

            JScrollPane scrollPane = new JScrollPane(notesContainer);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.setBackground(new Color(245, 247, 250));

            add(headerPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
        }
    }

    private JPanel createNoteCard(Note note) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        // Left side - Checkbox
        JCheckBox completedCheckbox = new JCheckBox();
        completedCheckbox.setSelected(note.isCompleted());
        completedCheckbox.setBackground(Color.WHITE);
        completedCheckbox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        completedCheckbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completedCheckbox.addActionListener(e -> {
            note.setCompleted(completedCheckbox.isSelected());
            NoteManager.getInstance().updateNoteStatus(note);
            parentFrame.showPanel("notes");
        });

        // Center - Note Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(note.getTitle());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(note.isCompleted() ? new Color(150, 150, 150) : new Color(51, 51, 51));

        String displayContent = note.getContent();
        if (displayContent.length() > 100) {
            displayContent = displayContent.substring(0, 100) + "...";
        }

        JLabel contentLabel = new JLabel("<html>" + displayContent + "</html>");
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        contentLabel.setForeground(new Color(100, 100, 100));

        JLabel dateLabel = new JLabel(note.getDateCreated());
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dateLabel.setForeground(new Color(150, 150, 150));

        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        contentPanel.add(contentLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        contentPanel.add(dateLabel);

        // Right side - Delete Button
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(234, 67, 53));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setPreferredSize(new Dimension(80, 32));
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this note?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                NoteManager.getInstance().removeNote(note);
                parentFrame.showPanel("notes");
            }
        });

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(deleteBtn, BorderLayout.NORTH);

        card.add(completedCheckbox, BorderLayout.WEST);
        card.add(contentPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }
}