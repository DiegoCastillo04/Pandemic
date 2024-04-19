import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class menu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel(new BorderLayout());

        // Fondo del panel principal
        ImageIcon background = new ImageIcon("background.jpg");
        JLabel backgroundLabel = new JLabel(background);
        panel.add(backgroundLabel, BorderLayout.CENTER);
        panel.setOpaque(true);

        // Creamos el panel para los botones con un borde vacío
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Cargamos las fuentes personalizadas
        Font buttonFont = loadFont("YourFont.ttf").deriveFont(Font.PLAIN, 16f);

        // Creamos los botones con los iconos y estilo personalizado
        JButton startButton = createStyledButton("Start Game", "start_icon.png", buttonFont);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocarías el código para iniciar el juego
                JOptionPane.showMessageDialog(frame, "Starting the game!");
            }
        });

        JButton optionsButton = createStyledButton("Options", "options_icon.png", buttonFont);
        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocarías el código para abrir las opciones del juego
                JOptionPane.showMessageDialog(frame, "Opening options menu!");
            }
        });

        JButton exitButton = createStyledButton("Exit", "exit_icon.png", buttonFont);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocarías el código para salir del juego
                System.exit(0);
            }
        });

        // Añadimos los botones al panel
        buttonPanel.add(startButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(exitButton, BorderLayout.SOUTH);

        // Añadimos el panel de botones al centro del panel principal
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Añadimos el panel principal al frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para crear un botón con estilo personalizado
    private static JButton createStyledButton(String text, String iconFilename, Font font) {
        JButton button = new JButton(text, new ImageIcon(iconFilename));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(40, 40, 40));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(40, 40, 40));
            }
        });
        return button;
    }

    // Método para cargar una fuente personalizada
    private static Font loadFont(String filename) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, menu.class.getResourceAsStream(filename));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // En caso de error, se usa una fuente predeterminada
            font = new Font("Arial", Font.PLAIN, 16);
        }
        return font;
    }
}

