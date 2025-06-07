import java.awt.*;
import java.util.*;
import javax.swing.*;

/*
 * @author brian
 */
public class GuessWhoGame {
    // TODO: change frame icon setIconImage()
    public void GuessWho() {
        // Create a frame for the intro and set properties
        JFrame startFrame = new JFrame("Guess Who");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(1000, 700);
        startFrame.setLocationRelativeTo(null); // Centers the frame within the desktop window

        // Sets favicon
        // https://stackoverflow.com/questions/17815033/how-to-change-java-icon-in-a-jframe
        ImageIcon icon = new ImageIcon("src/media/GuessWhoIcon.png");
        startFrame.setIconImage(icon.getImage());

        // Create anon subclass for mainPanel
        JPanel mainPanel = new JPanel() {
            private final Image bg = new ImageIcon("src/media/blue-mountain.jpg").getImage();

            // don't touch, for background
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // sets layout as box layout; everythng defaults stacks on top of each other
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
            startFrame.dispose();

            // Instantiate the new frame and set window params
            JFrame gameFrame = new JFrame("Guess Who - Game");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // ? acts weird, this is a known bug with swing
            // https://bugs.openjdk.org/browse/JDK-8221452
            gameFrame.setSize(new Dimension(1300, 750));
            gameFrame.setMinimumSize(new Dimension(1300, 750));
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            gameFrame.setResizable(true);

            // Sets favicon
            gameFrame.setIconImage(icon.getImage());

            // Background panel
            JPanel gamePanel = new JPanel(new BorderLayout()) {
                private final Image bg = new ImageIcon("src/media/Monterey-blue-green-dark.png").getImage();

                // don't touch makes background
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (bg != null) {
                        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            // small logic to get player name and set up players
            String playerName = JOptionPane.showInputDialog(gameFrame, "Choose a character name:", "Player Name");
            if (playerName == null) {
                // User pressed Cancel, close this frame and show the start screen again
                gameFrame.dispose();
                // Re-show the start screen
                GuessWho(); // Call the method to show the start screen again
                return;
            }
            if (playerName.trim().isEmpty()) {
                playerName = "Player 1";
            }
            System.out.println(playerName); // TODO: remove when done and also fix

            // use the human's player to set up the board
            Players Human = new HumanPlayer(playerName);
            Players Computer = new ComputerPlayer("Computer");

            // Character grid; 4 rows 6 columns + adds padding
            JPanel gridPanel = new JPanel(new GridLayout(4, 6, 20, 20));
            gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 40));
            gridPanel.setOpaque(false);

            // Mini grid panel; for bot
            JPanel botGridPanel = new JPanel(new GridLayout(4, 6, 5, 5));
            botGridPanel.setOpaque(false);

            // display each box
            // TODO: attach to each bot character
            for (int i = 0; i < 24; i++) {
                JLabel botCell = new JLabel();
                Image botCellImage = new ImageIcon("src/media/placeholder.jpg").getImage()
                        .getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                botCell.setIcon(new ImageIcon(botCellImage));
                botGridPanel.add(botCell);
            }

            // Question dropdown
            ArrayList<String> questions = Human.getQuestionList();
            JComboBox<String> questionBox = new JComboBox<>(questions.toArray(new String[0]));

            // Question Label
            JLabel questionLabel = new JLabel("Ask a question:");
            questionLabel.setFont(new Font("Arial", Font.BOLD, 22));
            questionLabel.setForeground(Color.WHITE);

            // Style the question dropdown (ComboBox)
            questionBox.setFont(new Font("Arial", Font.PLAIN, 20));
            questionBox.setForeground(new Color(230, 230, 230)); // off white
            questionBox.setBackground(new Color(0, 60, 70)); // slightly deeper blue
            questionBox.setFocusable(false);
            questionBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            questionBox.setPreferredSize(new Dimension(460, 44));
            // questionBox.setMaximumRowCount(5); // Limit the number of visible options

            // Remove the ugly default arrow background
            // https://coderanch.com/t/415909/java/Change-JSpinner-JComboBox-Arrows-Color
            questionBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());

            // Style the enter button
            JButton enterButton = new JButton("Enter");
            enterButton.setFont(new Font("Arial", Font.BOLD, 20));
            enterButton.setPreferredSize(new Dimension(110, 44));
            enterButton.setForeground(new Color(230, 230, 230));
            enterButton.setBackground(new Color(1, 169, 110));
            enterButton.setBorderPainted(false);
            enterButton.setFocusPainted(false);
            enterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // TODO: figure out a way to count score (steal from real game)
            JLabel scoreLabel = new JLabel("Score:");
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
            scoreLabel.setForeground(Color.WHITE);

            ArrayList<Character> humanCharacters = Human.getBoard().getCharacterList();

            // Add character cards to grid + styling [from me :) ]
            for (Character character : humanCharacters) {
                String charName = character.getName();
                JPanel card = new JPanel(new BorderLayout());
                card.setMaximumSize(new Dimension(110, 130));
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

            // TODO: replace with generic bot vs human image
            JPanel botAndHuman = new JPanel();
            botAndHuman.setLayout(new BoxLayout(botAndHuman, BoxLayout.X_AXIS));
            botAndHuman.setOpaque(false);

            // Player image and label
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
            playerPanel.setOpaque(false);

            JLabel playerImage = new JLabel();
            Image playerImg = new ImageIcon("src/media/placeholder.jpg").getImage()
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            playerImage.setIcon(new ImageIcon(playerImg));
            playerImage.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel playerLabel = new JLabel(playerName, SwingConstants.CENTER);
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setFont(new Font("Arial", Font.BOLD, 18));
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            playerPanel.add(Box.createVerticalStrut(15));
            playerPanel.add(playerImage);
            playerPanel.add(Box.createVerticalStrut(8));
            playerPanel.add(playerLabel);

            // Computer image and label
            JPanel computerPanel = new JPanel();
            computerPanel.setLayout(new BoxLayout(computerPanel, BoxLayout.Y_AXIS));
            computerPanel.setOpaque(false);

            JLabel computerImage = new JLabel();
            Image computerImg = new ImageIcon("src/media/placeholder.jpg").getImage()
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            computerImage.setIcon(new ImageIcon(computerImg));
            computerImage.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel computerLabel = new JLabel("Computer", SwingConstants.CENTER);
            computerLabel.setForeground(Color.WHITE);
            computerLabel.setFont(new Font("Arial", Font.BOLD, 18));
            computerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            computerPanel.add(Box.createVerticalStrut(15));
            computerPanel.add(computerImage);
            computerPanel.add(Box.createVerticalStrut(8));
            computerPanel.add(computerLabel);

            // Add both panels to the main panel with spacing
            botAndHuman.add(playerPanel);
            botAndHuman.add(computerPanel);
            botAndHuman.setAlignmentX(Component.CENTER_ALIGNMENT);
            botAndHuman.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // TODO: this is where the real logic starts; start a loop blah
            // Enter button logic
            enterButton.addActionListener(_e -> {
                String selected = (String) questionBox.getSelectedItem();
                JOptionPane.showMessageDialog(gameFrame, "You asked: " + selected);
            });

            // * PUTTING EVERYTHING TOGETHER*/

            // main content Panel for character; everything except for question asker
            JPanel mainContentPanel = new JPanel();
            mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.X_AXIS));
            mainContentPanel.setOpaque(false);
            mainContentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 10, 40));

            // left side to hold user characters
            JPanel gridWrapper = new JPanel(new BorderLayout());
            gridWrapper.setOpaque(false);
            gridWrapper.add(gridPanel, BorderLayout.CENTER);
            mainContentPanel.add(gridWrapper);
            mainContentPanel.add(Box.createRigidArea(new Dimension(30, 0))); // spacing

            // right side to hold bot characters generic image and score
            JPanel rightCompositePanel = new JPanel();
            rightCompositePanel.setLayout(new BoxLayout(rightCompositePanel, BoxLayout.Y_AXIS));
            rightCompositePanel.setMaximumSize(new Dimension(500, 500));
            rightCompositePanel.setOpaque(false);

            // add bot grid panel, bot and human image, and score label
            rightCompositePanel.add(botGridPanel);
            rightCompositePanel.add(botAndHuman);
            rightCompositePanel.add(scoreLabel);
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // because boxlayout doesn't auto center
            mainContentPanel.add(rightCompositePanel);

            // question panel at the bottom
            JPanel questionPanel = new JPanel(null);

            questionPanel.setPreferredSize(new Dimension(1400, 120));
            questionPanel.setOpaque(false);

            // Use a simple layout to center everything horizontally
            questionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
            questionPanel.add(questionLabel);
            questionPanel.add(questionBox);
            questionPanel.add(enterButton);

            // add all components to the game panel
            gamePanel.setLayout(new BorderLayout());
            gamePanel.add(mainContentPanel, BorderLayout.CENTER);
            gamePanel.add(questionPanel, BorderLayout.SOUTH);

            // Show the frame
            gameFrame.setContentPane(gamePanel);
            gameFrame.setVisible(true);
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
            rulesFrame.setResizable(false);
            rulesFrame.setSize(900, 600);
            rulesFrame.setLocationRelativeTo(startFrame);
            rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Sets favicon
            rulesFrame.setIconImage(icon.getImage());

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // First rules panel
            JPanel rulesPanel1 = new JPanel() {
                @Override
                // ! terrance, why are you creating the entire panel in the paintComponent
                // method?
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
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setResizable(false);
            settingsFrame.setSize(600, 400);
            settingsFrame.setLocationRelativeTo(startFrame);
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Sets favicon
            settingsFrame.setIconImage(icon.getImage());

            JPanel settingsPanel = new JPanel();
            settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
            settingsPanel.setBackground(new Color(10, 20, 60));

            JLabel settingsLabel = new JLabel("Settings");
            settingsLabel.setFont(new Font("Arial", Font.BOLD, 36));
            settingsLabel.setForeground(Color.WHITE);
            settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add components to the panel
            settingsPanel.add(Box.createVerticalStrut(20));
            settingsPanel.add(settingsLabel);
            settingsPanel.add(Box.createVerticalStrut(20));

            settingsFrame.add(settingsPanel);
            settingsFrame.setVisible(true);
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

        startFrame.add(mainPanel);
        startFrame.setVisible(true);
    }
}
