import javax.swing.*;
import java.awt.*;

public class AddNotePanel extends JPanel {
    private MainFrame parentFrame;
    private JTextField titleField;
    private JTextArea contentArea;

    public AddNotePanel(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        initComponents();
    }

    private void initComponents() {
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 247, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        // Header
        JLabel headerLabel = new JLabel("Create New Note");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(new Color(51, 51, 51));
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(headerLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Title Label
        JLabel titleLabel = new JLabel("Note Title");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Title Field
        titleField = new JTextField();
        titleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        titleField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        titleField.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(titleField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Content Label
        JLabel contentLabel = new JLabel("Note Content");
        contentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentLabel.setForeground(new Color(51, 51, 51));
        contentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(contentLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Content Area
        contentArea = new JTextArea();
        contentArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setPreferredSize(new Dimension(600, 350));
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(scrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonPanel.setBackground(new Color(245, 247, 250));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelBtn.setPreferredSize(new Dimension(120, 42));
        cancelBtn.setBackground(new Color(220, 220, 220));
        cancelBtn.setForeground(new Color(51, 51, 51));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> {
            clearFields();
            parentFrame.showPanel("notes");
        });

        JButton createBtn = new JButton("Create Note");
        createBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        createBtn.setPreferredSize(new Dimension(140, 42));
        createBtn.setBackground(new Color(52, 168, 83));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFocusPainted(false);
        createBtn.setBorderPainted(false);
        createBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createBtn.addActionListener(e -> saveNote());

        buttonPanel.add(cancelBtn);
        buttonPanel.add(createBtn);

        contentPanel.add(buttonPanel);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void saveNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a title for your note.",
                    "Title Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter some content for your note.",
                    "Content Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Note newNote = new Note(title, content);
        NoteManager.getInstance().addNote(newNote);

        JOptionPane.showMessageDialog(
                this,
                "Note saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearFields();
        parentFrame.showPanel("notes");
    }

    private void clearFields() {
        titleField.setText("");
        contentArea.setText("");
    }
}