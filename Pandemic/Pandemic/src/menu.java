import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame {

    public menu() {
        setTitle("Menú del Juego");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30)); // Fondo gris oscuro

        // Panel para los botones con un título encima
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(new Color(30, 30, 30)); // Fondo gris oscuro
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 175, 100, 100)); // Añadir un margen interno más amplio

        // Configuración del título "PANDEMIC" encima de los botones
        JLabel titleLabel = new JLabel("PANDEMIC");
        titleLabel.setForeground(Color.WHITE); // Texto blanco
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Fuente y tamaño
        buttonsPanel.add(titleLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1; // Comenzamos desde la segunda fila para los botones
        gbc.insets = new Insets(20, 20, 20, 20); // Espaciado interior de los botones
        gbc.fill = GridBagConstraints.HORIZONTAL; // Rellenar horizontalmente

        JButton nuevaPartidaBtn = new JButton("Nueva partida");
        estilizarBoton(nuevaPartidaBtn);
        nuevaPartidaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Iniciar nueva partida...");
            }
        });
        buttonsPanel.add(nuevaPartidaBtn, gbc);

        gbc.gridy++;
        JButton cargarPartidaBtn = new JButton("Cargar partida");
        estilizarBoton(cargarPartidaBtn);
        cargarPartidaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cargar partida guardada...");
            }
        });
        buttonsPanel.add(cargarPartidaBtn, gbc);

        gbc.gridy++;
        JButton informacionBtn = new JButton("Información");
        estilizarBoton(informacionBtn);
        informacionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacion();
            }
        });
        buttonsPanel.add(informacionBtn, gbc);

        gbc.gridy++;
        JButton resumenPuntuacionesBtn = new JButton("Resumen de puntuaciones");
        estilizarBoton(resumenPuntuacionesBtn);
        resumenPuntuacionesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mostrar resumen de puntuaciones...");
            }
        });
        buttonsPanel.add(resumenPuntuacionesBtn, gbc);

        gbc.gridy++;
        JButton autoresBtn = new JButton("Autores");
        estilizarBoton(autoresBtn);
        autoresBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAutores();
            }
        });
        buttonsPanel.add(autoresBtn, gbc);

        gbc.gridy++;
        JButton versionBtn = new JButton("Versión");
        estilizarBoton(versionBtn);
        versionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Versión 1.0");
            }
        });
        buttonsPanel.add(versionBtn, gbc);

        gbc.gridy++;
        JButton salirBtn = new JButton("Salir");
        estilizarBoton(salirBtn);
        salirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonsPanel.add(salirBtn, gbc);

        mainPanel.add(buttonsPanel, BorderLayout.WEST);

        ImageIcon icon = new ImageIcon("/D:/Diego.C/Pandemic/Pandemic/Pandemic/imagenmenu.jpg"); // Cambia "ruta/a/la/imagen.jpg" por la ruta de tu imagen
        Image image = icon.getImage().getScaledInstance(1200, -1, Image.SCALE_SMOOTH); // -1 mantiene la relación de aspecto
        icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void mostrarInformacion() {
        JOptionPane.showMessageDialog(null, "Objetivo del juego:\r\n"
        		+ "El objetivo del juego es investigar y producir vacunas para todas las enfermedades presentes en el mapa antes de que se produzca un número determinado de brotes.\r\n"
        		+ "Preparación del juego:\r\n"
        		+ "Selecciona la dificultad del juego, que determinará la cantidad inicial de ciudades infectadas y el número de brotes necesarios para perder el juego.\r\n"
        		+ "Turno de juego:\r\n"
        		+ "En cada turno, el jugador puede realizar una de las siguientes acciones:\r\n"
        		+ "Investigar vacuna: El jugador puede elegir una enfermedad presente y realizar la investigación correspondiente, aumentando el porcentaje de investigacion de esta en un 25%.\r\n"
        		+ "Curar ciudad: El jugador puede elegir una ciudad con una enfermedad y eliminar la enfermedad de esa ciudad.\r\n"
        		+ "Fin del juego:\r\n"
        		+ "El juego termina si se produce un número determinado de brotes antes de que se investiguen todas las vacunas. En ese caso, el jugador pierde.\r\nSi el jugador investiga vacunas para todas las enfermedades antes de que se produzca el número de brotes establecido, gana el juego.");
    }

    private void mostrarAutores() {
        JOptionPane.showMessageDialog(null, "Autores:\nDiego Castillo Colea\nDavid Juan Antunez");
    }

    private void estilizarBoton(JButton button) {
        button.setBackground(new Color(50, 205, 50)); // Verde
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false); // Quitar efecto de enfoque
        button.setPreferredSize(new Dimension(240, 60)); // Tamaño fijo para los botones
        button.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño de fuente
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }
}
