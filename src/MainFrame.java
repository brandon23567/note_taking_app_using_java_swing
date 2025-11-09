import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("NoteMaster - Your Notes");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Top Navigation Bar
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(new Color(66, 133, 244));
        navBar.setPreferredSize(new Dimension(900, 70));
        navBar.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel appTitle = new JLabel("NoteMaster");
        appTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        appTitle.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(66, 133, 244));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton viewNotesBtn = new JButton("My Notes");
        styleNavButton(viewNotesBtn);
        viewNotesBtn.addActionListener(e -> showPanel("notes"));

        JButton addNoteBtn = new JButton("+ Add Note");
        styleNavButton(addNoteBtn);
        addNoteBtn.setBackground(new Color(52, 168, 83));
        addNoteBtn.addActionListener(e -> showPanel("add"));

        JButton logoutBtn = new JButton("Logout");
        styleNavButton(logoutBtn);
        logoutBtn.setBackground(new Color(234, 67, 53));
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        buttonPanel.add(viewNotesBtn);
        buttonPanel.add(addNoteBtn);
        buttonPanel.add(logoutBtn);

        navBar.add(appTitle, BorderLayout.WEST);
        navBar.add(buttonPanel, BorderLayout.EAST);

        // Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(245, 247, 250));

        contentPanel.add(new NotesPanel(this), "notes");
        contentPanel.add(new AddNotePanel(this), "add");

        add(navBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        showPanel("notes");
    }

    private void styleNavButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 103, 214));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(110, 38));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void showPanel(String panelName) {
        cardLayout.show(contentPanel, panelName);
        if (panelName.equals("notes")) {
            // Refresh the notes panel
            contentPanel.remove(0);
            contentPanel.add(new NotesPanel(this), "notes", 0);
            cardLayout.show(contentPanel, "notes");
        }
    }
}