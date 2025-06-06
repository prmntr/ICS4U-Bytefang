import java.awt.*;
import java.util.*;
import javax.swing.*;

// brian terrance is a poo poo head who farts every day like a big fat boy
public class GuessWhoGame {
    public void GuessWho() {
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
            // Remove the start screen frame
            frame.dispose();

            // Instantiate the new frame and set window params
            JFrame newFrame = new JFrame("Guess Who - Game");
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setMinimumSize(new Dimension(1400, 780));
            newFrame.setLocationRelativeTo(null);
            newFrame.setResizable(false);

            // Background panel
            JPanel gamePanel = new JPanel(new BorderLayout()) {
            private final Image bg = new ImageIcon("src/media/Monterey-blue-green-dark.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
            };

            // Character grid
            JPanel gridPanel = new JPanel(new GridLayout(4, 6, 20, 20));
            gridPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 30, 40));
            gridPanel.setOpaque(false);

            // Mini grid panel
            JPanel miniGridPanel = new JPanel(new GridLayout(4, 6, 5, 5));
            miniGridPanel.setOpaque(false);
            for (int i = 0; i < 24; i++) {
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(25, 25));
            cell.setBackground(Color.LIGHT_GRAY);
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            miniGridPanel.add(cell);
            }

            // Question dropdown
            JComboBox<String> questionBox = new JComboBox<>(new String[] {
            "Does your character wear glasses?",
            "Is your character bald?",
            "Does your character have pink hair?",
            "Is your character male?",
            "Does your character wear a hat?"
            });
            questionBox.setFont(new Font("Arial", Font.PLAIN, 18));
            questionBox.setPreferredSize(new Dimension(400, 40));

            // Enter button
            JButton enterButton = new JButton("Enter");
            enterButton.setFont(new Font("Arial", Font.BOLD, 18));
            enterButton.setPreferredSize(new Dimension(100, 40));

            // Score label
            JLabel scoreLabel = new JLabel("Score:");
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
            scoreLabel.setForeground(Color.WHITE);

            // Get player name and set up players
            String playerName = JOptionPane.showInputDialog(newFrame, "Enter your name:");
            if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player 1";
            }
            Players Human = new HumanPlayer(playerName);
            Players Computer = new ComputerPlayer("Computer");
            ArrayList<Character> humanCharacters = Human.getBoard().getCharacterList();

            // Add character cards to grid
            for (Character character : humanCharacters) {
            String charName = character.getName();
            JPanel card = new JPanel(new BorderLayout());
            card.setMaximumSize(new Dimension(100, 120));
            card.setBackground(new Color(0, 0, 0, 90));
            card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));

            JLabel image = new JLabel();
            image.setHorizontalAlignment(JLabel.CENTER);
            Image scaledImg = new ImageIcon("src/media/characters/" + charName + ".png").getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(scaledImg));

            JLabel nameLabel = new JLabel(charName, SwingConstants.CENTER);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

            card.add(image, BorderLayout.CENTER);
            card.add(nameLabel, BorderLayout.SOUTH);

            gridPanel.add(card);
            }

            // Enter button logic
            enterButton.addActionListener(_e -> {
            String selected = (String) questionBox.getSelectedItem();
            JOptionPane.showMessageDialog(newFrame, "You asked: " + selected);
            });

            // Layout panels (do this at the end)
            JPanel gridWrapper = new JPanel(new BorderLayout());
            gridWrapper.setOpaque(false);
            gridWrapper.add(gridPanel, BorderLayout.CENTER);

            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setOpaque(false);
            rightPanel.setPreferredSize(new Dimension(220, 540));
            rightPanel.add(Box.createVerticalStrut(40), BorderLayout.NORTH);
            rightPanel.add(miniGridPanel, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel(null);
            bottomPanel.setPreferredSize(new Dimension(1400, 120));
            bottomPanel.setBackground(new Color(10, 20, 60));
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

            // Add everything to the main game panel
            gamePanel.add(gridWrapper, BorderLayout.CENTER);
            gamePanel.add(rightPanel, BorderLayout.EAST);
            gamePanel.add(bottomPanel, BorderLayout.SOUTH);

            // Show the frame
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
            rulesFrame.setSize(900, 600);
            rulesFrame.setLocationRelativeTo(frame);
            rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // First rules panel
            JPanel rulesPanel1 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(new Color(10, 20, 60)); // Navy blue
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 40));
                    g.drawString("RULES", 60, 50);
                    g.setFont(new Font("Arial", Font.PLAIN, 28));
                    g.drawString("1. Each player selects a character.", 60, 120);
                    g.drawString("2. Take turns asking yes/no questions.", 60, 180);
                    g.drawString("3. Eliminate characters based on answers. ", 60, 240);
                    g.drawString("4. On your turn, instead of asking a question, you ", 60, 300);
                    g.drawString("may guess the opponent’s character.", 60, 360);
                    g.drawString("      If correct: You win! ", 60, 420);
                    g.drawString("      If wrong: You lose the game. ", 60, 480);
                    g.drawString("5. First to guess the opponent's character wins!", 60, 540);
                }
            };
            rulesPanel1.setLayout(null);

            // Second rules panel
            JPanel rulesPanel2 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(new Color(10, 20, 60));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 40));
                    g.drawString("Tips and Strategies", 60, 50);
                    g.setFont(new Font("Arial", Font.PLAIN, 28));
                    g.drawString("Try to ask questions that split the remaning options in half", 60, 120);
                    g.drawString("Eliminate as many characters as possible with each question. ", 60, 180);
                    g.drawString("Think logically—don’t guess too early! ", 60, 240);
                }
            };
            rulesPanel2.setLayout(null);

            cardPanel.add(rulesPanel1, "panel1");
            cardPanel.add(rulesPanel2, "panel2");

            // Arrow buttons
            JButton leftArrow = new JButton("<");
            leftArrow.setFont(new Font("Arial", Font.BOLD, 36));
            leftArrow.setBounds(30, 250, 60, 60);
            leftArrow.setFocusable(false);
            leftArrow.setVisible(false); // Hide on first panel

            JButton rightArrow = new JButton(">");
            rightArrow.setFont(new Font("Arial", Font.BOLD, 36));
            rightArrow.setBounds(800, 250, 60, 60);
            rightArrow.setFocusable(false);

            JPanel overlayPanel = new JPanel(null);
            overlayPanel.setOpaque(false);
            overlayPanel.add(leftArrow);
            overlayPanel.add(rightArrow);
            overlayPanel.setBounds(0, 0, 900, 600);

            // Layered pane to overlay arrows on cardPanel
            JLayeredPane layeredPane = new JLayeredPane();
            cardPanel.setBounds(0, 0, 900, 600);
            overlayPanel.setBounds(0, 0, 900, 600);
            layeredPane.setPreferredSize(new Dimension(900, 600));
            layeredPane.add(cardPanel, Integer.valueOf(0));
            layeredPane.add(overlayPanel, Integer.valueOf(1));

            // Arrow button actions
            rightArrow.addActionListener(_e -> {
                cardLayout.next(cardPanel);
                rightArrow.setVisible(false);
                leftArrow.setVisible(true);
            });
            leftArrow.addActionListener(_e -> {
                cardLayout.previous(cardPanel);
                leftArrow.setVisible(false);
                rightArrow.setVisible(true);
            });

            rulesFrame.setContentPane(layeredPane);
            rulesFrame.pack();
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

        JLabel versionText = new JLabel("<html>Created by ByteFang<br>version 12345<br>Terrance sucks</html>");
        versionText.setFont(new Font("Arial", Font.PLAIN, 16));
        versionText.setForeground(new Color(230, 230, 230));
        versionText.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));

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

        mainPanel.add(Box.createVerticalStrut(170));
        mainPanel.add(imageLabel);

        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);

        mainPanel.add(Box.createVerticalGlue());

        // Place version text in bottom left corner using BorderLayout
        JPanel versionPanel = new JPanel(new BorderLayout());
        versionPanel.setOpaque(false);
        versionPanel.add(versionText, BorderLayout.WEST);
        versionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        mainPanel.add(versionPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
