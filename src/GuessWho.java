import java.awt.*;
import javax.swing.*;

public class GuessWho {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Guess Who");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create a custom panel with background image
        JPanel mainPanel = new JPanel() {
            private Image bg = new ImageIcon("src/GWBG1.jpg").getImage();
            private Image balloonLeft = new ImageIcon("src/GWBalloons.png").getImage();
            private Image balloonRight = new ImageIcon("src/GWBalloons.png").getImage();

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


        ImageIcon imageIconChar1 = new ImageIcon("src/")

        // Create the START button
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 32));
        startButton.setPreferredSize(new Dimension(160, 80));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Transparent wrapper panel for layout control
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(200, 100));
        buttonPanel.add(startButton);

        // Add everything with spacing
        mainPanel.add(Box.createVerticalStrut(80));
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
