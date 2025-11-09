import javax.swing.*;
import java.awt.*;

public class SignupFrame extends JFrame {

    public SignupFrame() {
        setTitle("NoteMaster - Sign Up");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 168, 83));
        headerPanel.setPreferredSize(new Dimension(450, 120));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("NoteMaster");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 247, 250));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Create Account");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(51, 51, 51));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(welcomeLabel, gbc);

        JLabel subtitleLabel = new JLabel("Sign up to get started");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(128, 128, 128));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(subtitleLabel, gbc);

        // Full Name field
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nameLabel.setForeground(new Color(51, 51, 51));
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setPreferredSize(new Dimension(280, 40));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 15, 0);
        formPanel.add(nameField, gbc);

        // Username field
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        usernameLabel.setForeground(new Color(51, 51, 51));
        formPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(280, 40));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 15, 0);
        formPanel.add(usernameField, gbc);

        // Password field
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passwordLabel.setForeground(new Color(51, 51, 51));
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(280, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 25, 0);
        formPanel.add(passwordField, gbc);

        // Signup button
        JButton signupButton = new JButton("Create Account");
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signupButton.setPreferredSize(new Dimension(280, 45));
        signupButton.setBackground(new Color(52, 168, 83));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setBorderPainted(false);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(e -> {
            dispose();
            new MainFrame().setVisible(true);
        });
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 15, 0);
        formPanel.add(signupButton, gbc);

        // Login label
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(245, 247, 250));
        JLabel loginLabel = new JLabel("Already have an account? ");
        loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginLabel.setForeground(new Color(100, 100, 100));

        JLabel loginLink = new JLabel("Sign In");
        loginLink.setFont(new Font("Segoe UI", Font.BOLD, 13));
        loginLink.setForeground(new Color(52, 168, 83));
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        loginPanel.add(loginLabel);
        loginPanel.add(loginLink);

        gbc.gridy = 9;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(loginPanel, gbc);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}