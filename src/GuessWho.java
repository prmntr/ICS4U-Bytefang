import java.awt.*;
import javax.swing.*;

/**
 * @author Terrence Yang 2025-05-30
 */
public class GuessWho {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Guess Who");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create a custom panel with background image
        JPanel mainPanel = new JPanel() {
                        private final Image bg = new ImageIcon("src/blue-mountain.jpg").getImage();
            // If your image is actually a PNG, use "src/blue-mountain.png" instead.
            //private final Image balloonLeft = new ImageIcon("src/GWBalloons.png").getImage();
            //private final Image balloonRight = new ImageIcon("src/GWBalloons.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw background image
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

                // Draw balloon images
                //g.drawImage(balloonLeft, -100, getHeight() / 2 - balloonLeft.getHeight(null) / 2, this); // Left balloon
                //g.drawImage(balloonRight, getWidth() - balloonRight.getWidth(null) + 100, getHeight() / 2 - balloonRight.getHeight(null) / 2, this); // Right balloon
            }
        };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Load the title image
        ImageIcon imageIcon = new ImageIcon("src/GuessWhoWord.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIconChar1 = new ImageIcon("src/GWCH1.png");
        JLabel imageLabel1 = new JLabel(imageIconChar1);
        imageLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIconChar2 = new ImageIcon("src/GWCH2.png");
        JLabel imageLabel2 = new JLabel(imageIconChar2);

        // Create the START button
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 32));
        startButton.setPreferredSize(new Dimension(160, 80));
      startButton.addActionListener(evt -> {
    frame.dispose();

    JFrame newFrame = new JFrame("Guess Who - Game");
    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newFrame.setSize(1400, 900);
    newFrame.setLocationRelativeTo(null);

    // Game panel with BorderLayout
    JPanel gamePanel = new JPanel(new BorderLayout());
    gamePanel.setBackground(new Color(10, 20, 60)); // Navy blue

    // ----- CHARACTER GRID -----
    JPanel gridPanel = new JPanel(new GridLayout(4, 6, 20, 20)); // 4 rows, 6 columns
    gridPanel.setBackground(new Color(10, 20, 60)); // Same as main background
    gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); // Top, Left, Bottom, Right

    String[] names = {
        "CHANTAL", "ERIC", "ALEX", "BOB", "PAUL", "FRANK",
        "ZOE", "JOE", "BUBA", "RITA", "RICK", "ANTOINE",
        "JOHN", "CHAP", "EVELYN", "LADY", "SAMANTHA", "JENNY",
        "JAVIER", "EVAN", "MATHIAS", "MICHAEL", "HANK", "VITO"
    };

    for (String name : names) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(150, 180)); // Larger box
        card.setBackground(Color.DARK_GRAY);
        card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.CENTER);
        image.setIcon(new ImageIcon("src/characters/" + name + ".png")); // Update with correct paths

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        card.add(image, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.SOUTH);

        gridPanel.add(card);
    }

    // Add the grid to the top of the main panel (fills left to right)
// Wrap the gridPanel in another panel with BorderLayout
JPanel gridWrapper = new JPanel(new BorderLayout());
gridWrapper.setBackground(new Color(10, 20, 60));
gridWrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 150, 30)); 
// ↑↑↑ Increase bottom margin (was 10 before, now 60)

// Add gridPanel into center of wrapper
gridWrapper.add(gridPanel, BorderLayout.CENTER);

// Add the wrapper to the main game panel
gamePanel.add(gridWrapper, BorderLayout.CENTER);

    // ----- BOTTOM: SCORE + DROPDOWN + ENTER BUTTON + MINI GRID -----
    // Create question box and enter button first so they are in scope
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

    // Mini grid (24 squares)
    JPanel miniGridPanel = new JPanel(new GridLayout(4, 6, 5, 5));
    miniGridPanel.setOpaque(false);
    for (int i = 0; i < 24; i++) {
        JPanel cell = new JPanel();
        cell.setPreferredSize(new Dimension(25, 25));
        cell.setBackground(Color.LIGHT_GRAY);
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        miniGridPanel.add(cell);
    }

    // Custom bottom panel with null layout
    JPanel bottomPanel = new JPanel(null);
    bottomPanel.setPreferredSize(new Dimension(1400, 120));
    bottomPanel.setBackground(new Color(10, 20, 60));

    // Score label (keep at left)
    JLabel scoreLabel = new JLabel("Score:");
    scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
    scoreLabel.setForeground(Color.WHITE);
    scoreLabel.setBounds(40, 30, 80, 40);
    bottomPanel.add(scoreLabel);

    // Dropdown (questionBox) centered horizontally
    int dropdownWidth = 400;
    int dropdownHeight = 40;
    int dropdownX = (1400 - dropdownWidth) / 2;
    questionBox.setBounds(dropdownX, 30, dropdownWidth, dropdownHeight);
    bottomPanel.add(questionBox);

    // Enter button next to dropdown (right side)
    enterButton.setBounds(dropdownX + dropdownWidth + 20, 30, 100, 40);
    bottomPanel.add(enterButton);

    // Mini grid (24 squares) - bottom right
    int miniGridWidth = 200;
    int miniGridHeight = 120;
    miniGridPanel.setBounds(1400 - miniGridWidth - 40, 0, miniGridWidth, miniGridHeight);
    bottomPanel.add(miniGridPanel);

    // Add the custom bottomPanel to the SOUTH
    gamePanel.add(bottomPanel, BorderLayout.SOUTH);

    // Make the big grid in the center vertically bigger
    gridPanel.setPreferredSize(new Dimension(1200, 520));
    gridWrapper.setPreferredSize(new Dimension(1200, 540));

    newFrame.setContentPane(gamePanel);
    newFrame.setVisible(true);
});


        // Create the RULES button
        JButton rulesButton = new JButton("RULES");
        rulesButton.setFont(new Font("Arial", Font.BOLD, 32));
        rulesButton.setPreferredSize(new Dimension(160, 80));
        rulesButton.addActionListener(e -> {
            JFrame rulesFrame = new JFrame("Rules");
            rulesFrame.setSize(500, 400);
            rulesFrame.setLocationRelativeTo(frame);
            JTextArea rulesText = new JTextArea(
                "Guess Who Rules:\n\n" +
                "1. Each player selects a character.\n" +
                "2. Take turns asking yes/no questions to eliminate characters.\n" +
                "3. First to guess the opponent's character wins!\n\n" +
                "(Add more rules as needed.)"
            );
            rulesText.setEditable(false);
            rulesText.setLineWrap(true);
            rulesText.setWrapStyleWord(true);
            rulesText.setFont(new Font("Arial", Font.PLAIN, 18));
            rulesFrame.add(new JScrollPane(rulesText));
            rulesFrame.setVisible(true);
        });

        // Transparent wrapper panel for layout control
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(400, 100));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createHorizontalStrut(50)); // Space between buttons
        buttonPanel.add(rulesButton);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add character images at top (side by side)
        JPanel characterPanel = new JPanel();
        characterPanel.setOpaque(false); // So background shows through
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.X_AXIS));
        characterPanel.add(Box.createHorizontalGlue());
        characterPanel.add(imageLabel1);
        characterPanel.add(Box.createHorizontalStrut(20)); // spacing
        characterPanel.add(imageLabel2);
        characterPanel.add(Box.createHorizontalGlue());
        characterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(20));  // Top margin
        mainPanel.add(characterPanel);              // Character images at the top
        mainPanel.add(Box.createVerticalStrut(-100)); // Space between images
        mainPanel.add(imageLabel);                  // Guess Who title
        mainPanel.add(Box.createVerticalStrut(60)); // Space before button
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());    // Pushes everything upwards if there's space

        frame.add(mainPanel);
        frame.setVisible(true); 

    }
}
//d