import javax.swing.*;
import java.awt.*;

public class Ranking extends JFrame {
    public Ranking() {
        setTitle("Ranking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        
        JLabel titleLabel = new JLabel("Ranking");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.GREEN);
        panel.add(separator, BorderLayout.CENTER);
        
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(0, 1));
        playersPanel.setBackground(Color.BLACK);
        
        JLabel player1Label = new JLabel("Nombre 1 - Puntuación: 100");
        player1Label.setForeground(Color.WHITE);
        JLabel player2Label = new JLabel("Nombre 2 - Puntuación: 90");
        player2Label.setForeground(Color.WHITE);
        
        playersPanel.add(player1Label);
        playersPanel.add(player2Label);
        // Añade más jugadores según necesites
        
        panel.add(playersPanel, BorderLayout.CENTER);
        
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ranking::new);
    }
}
