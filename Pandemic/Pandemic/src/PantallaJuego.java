import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaJuego extends JFrame {

    private int puntosDeAccion = 4; // Contador de puntos de acción
    private JLabel pointsLabel; // Etiqueta para mostrar el contador de puntos de acción
    private int turnos = 1; // Contador de turnos
    private boolean investigando = false; // Indicador de si se está investigando una vacuna

    // Porcentajes iniciales de las vacunas
    private int porcentajeVacuna1 = 0;
    private int porcentajeVacuna2 = 0;
    private int porcentajeVacuna3 = 0;
    private int porcentajeVacuna4 = 0;

    public PantallaJuego() {
        setTitle("Pandemic - Juego");
        setSize(1920, 1080); // Establecer el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Elimina la barra de título y los bordes de la ventana

        // Crear un JLayeredPane para superponer componentes
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Calcular el tamaño del margen superior para centrar verticalmente el mapa
        int marginTop = (1080 - 780) / 2;

        // Agregar un espacio vacío en la parte superior para crear el margen
        JPanel topMarginPanel = new JPanel();
        topMarginPanel.setPreferredSize(new Dimension(1920, marginTop)); // Altura del margen
        layeredPane.add(topMarginPanel, new Integer(0));

        // Cargar la imagen de fondo
        ImageIcon backgroundImage = new ImageIcon("D:/Diego.C/Pandemic/Pandemic/Pandemic/mapa_mundo.png"); // Ruta de la imagen
        JLabel backgroundLabel = new JLabel(backgroundImage);
        // Centrar el mapa horizontalmente
        int x = (1920 - backgroundImage.getIconWidth()) / 2;
        backgroundLabel.setBounds(x, marginTop, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        layeredPane.add(backgroundLabel, new Integer(1));

        // Crear panel para los botones en la parte derecha
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 5)); // Organizar los botones verticalmente con espacio entre ellos

        // Crear botones para vacunas
        JButton vaccineButton1 = new JButton("V1 (" + porcentajeVacuna1 + "%)");
        JButton vaccineButton2 = new JButton("V2 (" + porcentajeVacuna2 + "%)");
        JButton vaccineButton3 = new JButton("V3 (" + porcentajeVacuna3 + "%)");
        JButton vaccineButton4 = new JButton("V4 (" + porcentajeVacuna4 + "%)");

        // Crear botones para investigación y curación
        JButton researchButton = new JButton("Investigación");
        JButton cureButton = new JButton("Curación");

        // Ajustar el tamaño de los botones y la fuente
        Dimension buttonSize = new Dimension(80, 30); // Reducir el tamaño de los botones
        Font buttonFont = new Font("Arial", Font.PLAIN, 12); // Ajustar la fuente
        vaccineButton1.setPreferredSize(buttonSize);
        vaccineButton2.setPreferredSize(buttonSize);
        vaccineButton3.setPreferredSize(buttonSize);
        vaccineButton4.setPreferredSize(buttonSize);
        researchButton.setPreferredSize(buttonSize);
        cureButton.setPreferredSize(buttonSize);
        vaccineButton1.setFont(buttonFont);
        vaccineButton2.setFont(buttonFont);
        vaccineButton3.setFont(buttonFont);
        vaccineButton4.setFont(buttonFont);
        researchButton.setFont(buttonFont);
        cureButton.setFont(buttonFont);

        // ActionListener para los botones de investigación de vacunas
        ActionListener researchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (puntosDeAccion > 0) {
                    investigando = true; // Indicar que se está investigando
                }
            }
        };

        // ActionListener para los botones de vacunas
        ActionListener vaccineListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (investigando && puntosDeAccion > 0) {
                    JButton button = (JButton) e.getSource();
                    String buttonText = button.getText();
                    int porcentajeActual = 0;
                    if (buttonText.startsWith("V1")) {
                        porcentajeActual = porcentajeVacuna1;
                        porcentajeVacuna1 = Math.min(porcentajeActual + 25, 100); // Incrementar hasta un máximo de 100%
                        button.setText("V1 (" + porcentajeVacuna1 + "%)");
                    } else if (buttonText.startsWith("V2")) {
                        porcentajeActual = porcentajeVacuna2;
                        porcentajeVacuna2 = Math.min(porcentajeActual + 25, 100);
                        button.setText("V2 (" + porcentajeVacuna2 + "%)");
                    } else if (buttonText.startsWith("V3")) {
                        porcentajeActual = porcentajeVacuna3;
                        porcentajeVacuna3 = Math.min(porcentajeActual + 25, 100);
                        button.setText("V3 (" + porcentajeVacuna3 + "%)");
                    } else if (buttonText.startsWith("V4")) {
                        porcentajeActual = porcentajeVacuna4;
                        porcentajeVacuna4 = Math.min(porcentajeActual + 25, 100);
                        button.setText("V4 (" + porcentajeVacuna4 + "%)");
                    }

                    // Descontar un punto de acción
                    puntosDeAccion--;
                    updatePointsLabel(); // Actualizar la etiqueta de puntos de acción
                    investigando = false; // Desactivar la investigación
                }
            }
        };

        // Agregar ActionListeners a los botones de investigación y de vacunas
        researchButton.addActionListener(researchListener);
        vaccineButton1.addActionListener(vaccineListener);
        vaccineButton2.addActionListener(vaccineListener);
        vaccineButton3.addActionListener(vaccineListener);
        vaccineButton4.addActionListener(vaccineListener);

        // Agregar botones al panel
        buttonPanel.add(vaccineButton1);
        buttonPanel.add(vaccineButton2);
        buttonPanel.add(vaccineButton3);
        buttonPanel.add(vaccineButton4);
        // Agregar espacio entre los botones de vacuna y los botones de investigación y curación
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los botones de vacuna y los de investigación/curación
        buttonPanel.add(researchButton);
        buttonPanel.add(cureButton);

        // Establecer posición y tamaño del panel de botones
        int buttonPanelWidth = 100; // Ancho del panel de botones
        int marginRight = 50; // Margen a la derecha
        int buttonPanelX = 1920 - buttonPanelWidth - marginRight; // Posición X del panel de botones (en el borde derecho con margen)
        buttonPanel.setBounds(buttonPanelX, marginTop, buttonPanelWidth, 780); // El mismo margen superior que el mapa

        // Agregar el panel de botones a la capa superior del JLayeredPane
        layeredPane.add(buttonPanel, new Integer(2));

        // Botón para cerrar la ventana
        JButton closeButton = new JButton("X");
        closeButton.setPreferredSize(new Dimension(30, 30));
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setForeground(Color.RED);
        closeButton.setBackground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
        closeButton.setBounds(1890, 10, 30, 30); // Posición del botón en la esquina superior derecha
        layeredPane.add(closeButton, new Integer(3));

        // Etiqueta para mostrar el contador de puntos de acción
        pointsLabel = new JLabel("Puntos de Acción: " + puntosDeAccion);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pointsLabel.setForeground(Color.BLACK); // Cambiar color a negro
        pointsLabel.setBounds(10, 1040, 150, 30); // Posición de la etiqueta en la esquina inferior izquierda
        layeredPane.add(pointsLabel, new Integer(4));

        // Etiqueta para mostrar el contador de turnos
        JLabel turnsLabel = new JLabel("Turno: " + turnos);
        turnsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        turnsLabel.setForeground(Color.BLACK); // Cambiar color a negro
        turnsLabel.setBounds(10, 1010, 100, 30); // Posición de la etiqueta en la esquina inferior izquierda
        layeredPane.add(turnsLabel, new Integer(5));

        // Botón para finalizar el turno
        JButton endTurnButton = new JButton("Finalizar Turno");
        endTurnButton.setPreferredSize(new Dimension(150, 30));
        endTurnButton.setFont(new Font("Arial", Font.BOLD, 12));
        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Incrementar el contador de turnos y actualizar la etiqueta
                turnos++;
                turnsLabel.setText("Turno: " + turnos);

                // Reiniciar el contador de puntos de acción
                puntosDeAccion = 4;
                updatePointsLabel(); // Actualizar la etiqueta de puntos de acción

                investigando = false; // Terminar la investigación al finalizar el turno
                // Habilitar los botones de vacuna
                vaccineButton1.setEnabled(true);
                vaccineButton2.setEnabled(true);
                vaccineButton3.setEnabled(true);
                vaccineButton4.setEnabled(true);
            }
        });
        endTurnButton.setBounds((1920 - 150) / 2, 1040, 150, 30); // Posición del botón en la parte inferior central
        layeredPane.add(endTurnButton, new Integer(6));

        setVisible(true);
    }

    private void updatePointsLabel() {
        pointsLabel.setText("Puntos de Acción: " + puntosDeAccion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PantallaJuego();
            }
        });
    }
}