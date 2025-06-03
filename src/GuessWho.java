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
        frame.setLocationRelativeTo(null); // Center the frame

        // Create a custom panel with background image
        JPanel mainPanel = new JPanel() {
            private final Image bg = new ImageIcon("src/GWBG.png").getImage();
            private final Image balloonLeft = new ImageIcon("src/GWBalloons.png").getImage();
            private final Image balloonRight = new ImageIcon("src/GWBalloons.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw background image
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

                // Draw balloon images
                g.drawImage(balloonLeft, -100, getHeight() / 2 - balloonLeft.getHeight(null) / 2, this); // Left balloon
                g.drawImage(balloonRight, getWidth() - balloonRight.getWidth(null) + 100, getHeight() / 2 - balloonRight.getHeight(null) / 2, this); // Right balloon
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
            frame.dispose(); // Close the current window
            JFrame newFrame = new JFrame("Guess Who - Game");
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setSize(1400, 900); // Open a new, bigger window
            newFrame.setLocationRelativeTo(null);
            // Navy blue background panel
            JPanel gamePanel = new JPanel();
            gamePanel.setBackground(new Color(10, 20, 60)); // Navy blue
            gamePanel.setLayout(new BorderLayout());
            JLabel label = new JLabel("Game Screen (add your content here)", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 36));
            label.setForeground(Color.WHITE);
            gamePanel.add(label, BorderLayout.CENTER);
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
