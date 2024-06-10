import javax.swing.*;
import java.awt.*;

public class PantallaPerdedor extends JFrame {

    public PantallaPerdedor() {
        setTitle("¡Has perdido!");
        setUndecorated(true); // Eliminar bordes de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setResizable(false); // No redimensionable
        setAlwaysOnTop(true); // Siempre encima de otras ventanas

        ImageIcon imagenPerdedor = new ImageIcon("perdedor.png"); // Ruta de la imagen
        JLabel labelPerdedor = new JLabel(imagenPerdedor);
        labelPerdedor.setHorizontalAlignment(SwingConstants.CENTER); // Centrar la imagen en el JLabel
        getContentPane().add(labelPerdedor);

        // Botón para volver al menú
        JButton botonMenu = new JButton("Volver al Menú");
        botonMenu.addActionListener(e -> {
            dispose(); // Cerrar la ventana al hacer clic en el botón
            new menu().setVisible(true); // Mostrar la ventana del menú
        });
        getContentPane().add(botonMenu, BorderLayout.SOUTH);

        // Escuchar clics para cerrar la ventana al hacer clic en la imagen
        labelPerdedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose(); // Cerrar la ventana al hacer clic en la imagen
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PantallaPerdedor().setVisible(true));
    }
}
