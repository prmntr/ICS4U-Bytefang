import java.awt.*;
import javax.swing.*;

/**
 * @author Terrence Yang, Brian Xue 2025-05-30
 */
public class GuessWho {

    public static void main(String[] args) {

        // Create a frame and set properties
        JFrame frame = new JFrame("Guess Who");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null); // Centers the frame within the desktop window

        // Create anon subclass for mainPanel
        JPanel mainPanel = new JPanel() {
            private final Image bg = new ImageIcon("src/media/blue-mountain.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Load the title image
        ImageIcon imageIcon = new ImageIcon("src/media/GuessWhoLogo.png");
        Image img = imageIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the START button and style it
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 38));
        startButton.setFocusPainted(false);
        startButton.setForeground(new Color(230, 230, 230)); // off white
        startButton.setBackground(new Color(6, 26, 62));
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(true);
        startButton.setMaximumSize(new Dimension(400, 50)); // Allow stretching

        // Real logic for Start button
        startButton.addActionListener(evt -> {
            frame.dispose();

            JFrame newFrame = new JFrame("Guess Who - Game");
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setSize(1400, 900);
            newFrame.setLocationRelativeTo(null);

            JPanel gamePanel = new JPanel(new BorderLayout()) {
                private final Image bg = new ImageIcon("src/media/Monterey-blue-green-dark.jpg").getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (bg != null) {
                        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            // ----- CHARACTER GRID -----
            JPanel gridPanel = new JPanel(new GridLayout(4, 6, 20, 20));
            gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

            String[] names = {
                    "CHANTAL", "ERIC", "ALEX", "BOB", "PAUL", "FRANK",
                    "ZOE", "JOE", "BUBA", "RITA", "RICK", "ANTOINE",
                    "JOHN", "CHAP", "EVELYN", "LADY", "SAMANTHA", "JENNY",
                    "JAVIER", "EVAN", "MATHIAS", "MICHAEL", "HANK", "VITO"
            };

            for (String name : names) {
                JPanel card = new JPanel(new BorderLayout());
                card.setMaximumSize(new Dimension(120, 140));
                card.setBackground(Color.DARK_GRAY);
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

                JLabel image = new JLabel();
                image.setHorizontalAlignment(JLabel.CENTER);
                Image scaledImg = new ImageIcon("src/media/characters/" + name + ".png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(scaledImg));

                JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

                card.add(image, BorderLayout.CENTER);
                card.add(nameLabel, BorderLayout.SOUTH);

                gridPanel.add(card);
            }

            JPanel gridWrapper = new JPanel(new BorderLayout());
            gridWrapper.add(gridPanel, BorderLayout.CENTER);

            gamePanel.add(gridWrapper, BorderLayout.CENTER);

            // ----- BOTTOM: SCORE + DROPDOWN + ENTER BUTTON + MINI GRID -----
            JComboBox<String> questionBox = new JComboBox<>(new String[] {
                    "Does your character wear glasses?",
                    "Is your character bald?",
                    "Does your character have pink hair?",
                    "Is your character male?",
                    "Does your character wear a hat?"
            });
            questionBox.setFont(new Font("Arial", Font.PLAIN, 18));
            questionBox.setPreferredSize(new Dimension(400, 40));

            JButton enterButton = new JButton("Enter");
            enterButton.setFont(new Font("Arial", Font.BOLD, 18));
            enterButton.setPreferredSize(new Dimension(100, 40));
            enterButton.addActionListener(_e -> {
                String selected = (String) questionBox.getSelectedItem();
                JOptionPane.showMessageDialog(newFrame, "You asked: " + selected);
            });

            JPanel miniGridPanel = new JPanel(new GridLayout(4, 6, 5, 5));
            miniGridPanel.setOpaque(false);
            for (int i = 0; i < 24; i++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(25, 25));
                cell.setBackground(Color.LIGHT_GRAY);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                miniGridPanel.add(cell);
            }

            JPanel bottomPanel = new JPanel(null);
            bottomPanel.setPreferredSize(new Dimension(1400, 120));
            bottomPanel.setBackground(new Color(10, 20, 60));

            JLabel scoreLabel = new JLabel("Score:");
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setBounds(40, 30, 80, 40);
            bottomPanel.add(scoreLabel);

            int dropdownWidth = 400;
            int dropdownHeight = 40;
            int dropdownX = (1400 - dropdownWidth) / 2;
            questionBox.setBounds(dropdownX, 30, dropdownWidth, dropdownHeight);
            bottomPanel.add(questionBox);

            enterButton.setBounds(dropdownX + dropdownWidth + 20, 30, 100, 40);
            bottomPanel.add(enterButton);

            int miniGridWidth = 200;
            int miniGridHeight = 120;
            miniGridPanel.setBounds(1400 - miniGridWidth - 40, 0, miniGridWidth, miniGridHeight);
            bottomPanel.add(miniGridPanel);

            gamePanel.add(bottomPanel, BorderLayout.SOUTH);

            gridPanel.setPreferredSize(new Dimension(1200, 520));
            gridWrapper.setPreferredSize(new Dimension(1200, 540));

            gridWrapper.setOpaque(false);
            gridPanel.setOpaque(false);
            bottomPanel.setOpaque(false);

            newFrame.setContentPane(gamePanel);
            newFrame.setVisible(true);
        });

        // Create the RULES button
        JButton rulesButton = new JButton("Rules");
        rulesButton.setFont(new Font("Arial", Font.PLAIN, 25));
        rulesButton.setMaximumSize(new Dimension(195, 50));
        rulesButton.setFocusPainted(false);
        rulesButton.setForeground(new Color(230, 230, 230));
        rulesButton.setBackground(new Color(6, 26, 62));
        rulesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rulesButton.setBorderPainted(false);
        rulesButton.setContentAreaFilled(true);
        rulesButton.addActionListener(e -> {
            JFrame rulesFrame = new JFrame("Rules");
            rulesFrame.setSize(500, 400);
            rulesFrame.setLocationRelativeTo(frame);
            JTextArea rulesText = new JTextArea(
                    "Guess Who Rules:\n\n" +
                            "1. Each player selects a character.\n" +
                            "2. Take turns asking yes/no questions to eliminate characters.\n" +
                            "3. First to guess the opponent's character wins!\n\n" +
                            "(Add more rules as needed.)");
            rulesText.setEditable(false);
            rulesText.setLineWrap(true);
            rulesText.setWrapStyleWord(true);
            rulesText.setFont(new Font("Arial", Font.PLAIN, 18));
            rulesFrame.add(new JScrollPane(rulesText));
            rulesFrame.setVisible(true);
        });

        JButton settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Arial", Font.PLAIN, 25));
        settingsButton.setMaximumSize(new Dimension(195, 50));
        settingsButton.setFocusPainted(false);
        settingsButton.setForeground(new Color(230, 230, 230));
        settingsButton.setBackground(new Color(6, 26, 62));
        settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(true);
        settingsButton.addActionListener(e -> {
            // stuff here
        });

        JLabel versionText = new JLabel("created by ByteFang");
        versionText.setFont(new Font("Arial", Font.PLAIN, 13));
        versionText.setForeground(new Color(230, 230, 230));

        JPanel lowerButtonPanel = new JPanel();
        lowerButtonPanel.setOpaque(false);
        lowerButtonPanel.setLayout(new BoxLayout(lowerButtonPanel, BoxLayout.X_AXIS));
        lowerButtonPanel.add(rulesButton);
        lowerButtonPanel.add(Box.createHorizontalStrut(10));
        lowerButtonPanel.add(settingsButton);
        lowerButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setMaximumSize(new Dimension(15000, 400));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        lowerButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(lowerButtonPanel);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel characterPanel = new JPanel();
        characterPanel.setOpaque(false);
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.X_AXIS));
        characterPanel.add(Box.createHorizontalGlue());
        characterPanel.add(Box.createHorizontalStrut(20));
        characterPanel.add(Box.createHorizontalGlue());
        characterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(characterPanel);
        mainPanel.add(Box.createVerticalStrut(-100));
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(60));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(versionText);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
}
