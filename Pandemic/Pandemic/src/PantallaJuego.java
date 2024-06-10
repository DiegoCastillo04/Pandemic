import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PantallaJuego extends JFrame {
 ArrayList<Ciudades> ciudadesArraylist = new ArrayList<>();
 ArrayList<Vacunas> vacunasArraylist = new ArrayList<>();
 ArrayList<JButton> botonesCiudades = new ArrayList<>();
  public static int acciones = 4;
  public static int turnos = 1;
  public static int brotes = 0;
  public static JButton ciudadSeleccionado;

  
    public PantallaJuego() {
        setTitle("Mapa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth, screenHeight); // Establecer el tamaño del marco para que coincida con el tamaño de la pantalla

        // Panel central con imagen del mapa mundi
        JPanel panelCentral = new JPanel() {
        	
        	
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon mapaMundi = new ImageIcon("mapa_mundo.png");
                Image mapaOriginal = mapaMundi.getImage();
                g.drawImage(mapaOriginal, 0, 0, getWidth(), getHeight(), this);
            }
        };
       
        panelCentral.setLayout(null);
        


        // Obtener el tamaño de la imagen del mapa
        ImageIcon mapaMundi = new ImageIcon("mapa_mundo.png");
        int mapaWidth = mapaMundi.getIconWidth();
        int mapaHeight = mapaMundi.getIconHeight();
        panelCentral.setPreferredSize(new Dimension(mapaWidth, mapaHeight)); // Tamaño del panel central

        // Panel izquierdo con 4 botones y porcentaje de vacuna
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(200, mapaHeight)); // Tamaño del panel izquierdo
        
        // Panel derecho con contadores y botón de finalizar turno
        JPanel panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(new Dimension(180, mapaHeight)); // Tamaño del panel derecho
        panelDerecho.setLayout(new GridLayout(7, 1));
        JLabel labelBrotes = new JLabel("Brotes: 0");
        JLabel labelAcciones = new JLabel("Acciones: " + acciones);
        JLabel labelTurnos = new JLabel("Turnos: 1");
        JLabel labelCiudadSelec = new JLabel("Ciudad Seleccionada: Ninguna");
        JLabel labelInfeccionCiudad = new JLabel("Infeccion Ciudad: Ninguna");
        JButton botonFinalizarTurno = new JButton("Finalizar Turno");
        botonFinalizarTurno.addActionListener(e -> {
            turnos += 1;
            labelTurnos.setText("Turnos: " + turnos);
            nuevoTurno();
            acciones = 4;
            labelAcciones.setText("Acciones: " + acciones);

            // Propagar la infección a las ciudades colindantes al finalizar el turno
            for (Ciudades ciudad : ciudadesArraylist) {
                ciudad.propagarInfeccion(ciudadesArraylist);
            }

            // Verificar si todas las ciudades están infectadas al finalizar el turno
            boolean todasCiudadesInfectadas = true;
            for (Ciudades ciudad : ciudadesArraylist) {
                if (ciudad.getInfeccion() < 3) {
                    todasCiudadesInfectadas = false;
                    break;
                }
            }
            if (todasCiudadesInfectadas) {
                JOptionPane.showMessageDialog(this, "Todas las ciudades están completamente infectadas. ¡Has perdido!");
                dispose(); // Cerrar la pantalla de juego actual
                PantallaPerdedor pantallaPerdedor = new PantallaPerdedor();
                pantallaPerdedor.setVisible(true);
            }
        });
        
        panelDerecho.add(new JLabel()); // Espacio en blanco
        panelDerecho.add(labelBrotes);
        panelDerecho.add(labelAcciones);
        panelDerecho.add(labelTurnos);
        panelDerecho.add(labelCiudadSelec);
        panelDerecho.add(labelInfeccionCiudad);
        panelDerecho.add(botonFinalizarTurno);
        
        
        
        // Botón Vacuna Roja
        ImageIcon vacunaRojaIcon = new ImageIcon("vacuna1.png");
        Image vacunaRojaOriginal = vacunaRojaIcon.getImage();
        Image vacunaRojaScaled = vacunaRojaOriginal.getScaledInstance(150, -1, Image.SCALE_SMOOTH);
        JButton botonVacunaRoja = new JButton(new ImageIcon(vacunaRojaScaled));
        JLabel labelPorcentajeRojo = new JLabel("0%");
        botonVacunaRoja.addActionListener(e -> {
            int porcentajeActual = Integer.parseInt(labelPorcentajeRojo.getText().replace("%", ""));
            if (porcentajeActual < 100 && acciones >= 4) {
                int nuevoPorcentaje = porcentajeActual + 25;
                if (nuevoPorcentaje > 100) {
                    nuevoPorcentaje = 100;
                }
                vacunasArraylist.get(0).setPorcentage(nuevoPorcentaje);
                acciones = 0; // Gastar todas las acciones
                labelAcciones.setText("Acciones: " + acciones);
                labelPorcentajeRojo.setText(nuevoPorcentaje + "%");

                // Verificar si todas las vacunas están al 100%
                boolean todasVacunasCompletas = true;
                for (Vacunas vacuna : vacunasArraylist) {
                    if (vacuna.getPorcentage() < 100) {
                        todasVacunasCompletas = false;
                        break;
                    }
                }
                if (todasVacunasCompletas) {
         
                    dispose(); // Cerrar la ventana actual
                    PantallaGanador pantallaGanador = new PantallaGanador();
                    pantallaGanador.setVisible(true);
                }
            }
        });
        panelIzquierdo.add(botonVacunaRoja);
        panelIzquierdo.add(labelPorcentajeRojo);

        // Botón Vacuna Azul
        ImageIcon vacunaAzulIcon = new ImageIcon("vacuna2.png");
        Image vacunaAzulOriginal = vacunaAzulIcon.getImage();
        Image vacunaAzulScaled = vacunaAzulOriginal.getScaledInstance(150, -1, Image.SCALE_SMOOTH);
        JButton botonVacunaAzul = new JButton(new ImageIcon(vacunaAzulScaled));
        JLabel labelPorcentajeAzul = new JLabel("0%");
        botonVacunaAzul.addActionListener(e -> {
            int porcentajeActual = Integer.parseInt(labelPorcentajeAzul.getText().replace("%", ""));
            if (porcentajeActual < 100 && acciones >= 4) {
                int nuevoPorcentaje = porcentajeActual + 25;
                if (nuevoPorcentaje > 100) {
                    nuevoPorcentaje = 100;
                }
                vacunasArraylist.get(1).setPorcentage(nuevoPorcentaje);
                acciones = 0; // Gastar todas las acciones
                labelAcciones.setText("Acciones: " + acciones);
                labelPorcentajeAzul.setText(nuevoPorcentaje + "%");

                // Verificar si todas las vacunas están al 100%
                boolean todasVacunasCompletas = true;
                for (Vacunas vacuna : vacunasArraylist) {
                    if (vacuna.getPorcentage() < 100) {
                        todasVacunasCompletas = false;
                        break;
                    }
                }
                if (todasVacunasCompletas) {
                  
                    dispose(); // Cerrar la ventana actual
                    PantallaGanador pantallaGanador = new PantallaGanador();
                    pantallaGanador.setVisible(true);
                }
            }
        });
        panelIzquierdo.add(botonVacunaAzul);
        panelIzquierdo.add(labelPorcentajeAzul);

        // Botón Vacuna Amarilla
        ImageIcon vacunaAmarillaIcon = new ImageIcon("vacuna3.png");
        Image vacunaAmarillaOriginal = vacunaAmarillaIcon.getImage();
        Image vacunaAmarillaScaled = vacunaAmarillaOriginal.getScaledInstance(150, -1, Image.SCALE_SMOOTH);
        JButton botonVacunaAmarilla = new JButton(new ImageIcon(vacunaAmarillaScaled));
        JLabel labelPorcentajeAmarilla = new JLabel("0%");
        botonVacunaAmarilla.addActionListener(e -> {
            int porcentajeActual = Integer.parseInt(labelPorcentajeAmarilla.getText().replace("%", ""));
            if (porcentajeActual < 100 && acciones >= 4) {
                int nuevoPorcentaje = porcentajeActual + 25;
                if (nuevoPorcentaje > 100) {
                    nuevoPorcentaje = 100;
                }
                vacunasArraylist.get(2).setPorcentage(nuevoPorcentaje);
                acciones = 0; // Gastar todas las acciones
                labelAcciones.setText("Acciones: " + acciones);
                labelPorcentajeAmarilla.setText(nuevoPorcentaje + "%");

                // Verificar si todas las vacunas están al 100%
                boolean todasVacunasCompletas = true;
                for (Vacunas vacuna : vacunasArraylist) {
                    if (vacuna.getPorcentage() < 100) {
                        todasVacunasCompletas = false;
                        break;
                    }
                }
                if (todasVacunasCompletas) {
                  
                    dispose(); // Cerrar la ventana actual
                    PantallaGanador pantallaGanador = new PantallaGanador();
                    pantallaGanador.setVisible(true);
                }
            }
        });
        panelIzquierdo.add(botonVacunaAmarilla);
        panelIzquierdo.add(labelPorcentajeAmarilla);

        // Botón Vacuna Verde
        ImageIcon vacunaVerdeIcon = new ImageIcon("vacuna4.png");
        Image vacunaVerdeOriginal = vacunaVerdeIcon.getImage();
        Image vacunaVerdeScaled = vacunaVerdeOriginal.getScaledInstance(150, -1, Image.SCALE_SMOOTH);
        JButton botonVacunaVerde = new JButton(new ImageIcon(vacunaVerdeScaled));
        JLabel labelPorcentajeVerde = new JLabel("0%");
        botonVacunaVerde.addActionListener(e -> {
            int porcentajeActual = Integer.parseInt(labelPorcentajeVerde.getText().replace("%", ""));
            if (porcentajeActual < 100 && acciones >= 4) {
                int nuevoPorcentaje = porcentajeActual + 25;
                if (nuevoPorcentaje > 100) {
                    nuevoPorcentaje = 100;
                }
                vacunasArraylist.get(3).setPorcentage(nuevoPorcentaje);
                acciones = 0; // Gastar todas las acciones
                labelAcciones.setText("Acciones: " + acciones);
                labelPorcentajeVerde.setText(nuevoPorcentaje + "%");

                // Verificar si todas las vacunas están al 100%
                boolean todasVacunasCompletas = true;
                for (Vacunas vacuna : vacunasArraylist) {
                    if (vacuna.getPorcentage() < 100) {
                        todasVacunasCompletas = false;
                        break;
                    }
                }
                if (todasVacunasCompletas) {
                	dispose(); // Cerrar la ventana actual
                    PantallaGanador pantallaGanador = new PantallaGanador();
                    pantallaGanador.setVisible(true);
                }
            }
        });
        panelIzquierdo.add(botonVacunaVerde);
        panelIzquierdo.add(labelPorcentajeVerde);
        panelIzquierdo.add(new JLabel()); // Espacio en blanco
        panelIzquierdo.add(new JLabel());
        
     // Botones de Curar
        JButton botonCurar = new JButton("Curar");
        
        botonCurar.addActionListener(e -> {
            // Verificar si hay una ciudad seleccionada y si hay acciones disponibles
            if (ciudadSeleccionado != null && acciones > 0) {
                // Encontrar la ciudad seleccionada en la lista de ciudades
                int indiceCiudadSeleccionada = botonesCiudades.indexOf(ciudadSeleccionado);

                // Verificar que el índice sea válido
                if (indiceCiudadSeleccionada != -1) {
                    Ciudades ciudadSeleccionada = ciudadesArraylist.get(indiceCiudadSeleccionada);

                    // Verificar si la infección de la ciudad no está en 0
                    if (ciudadSeleccionada.getInfeccion() > 0) {
                        // Reducir la infección de la ciudad seleccionada en 1
                        ciudadSeleccionada.disminuirInfeccion(1);

                        // Reducir un punto de acción
                        acciones--;

                        // Actualizar la etiqueta de infección de la ciudad seleccionada
                        labelInfeccionCiudad.setText("Infeccion Ciudad: " + ciudadSeleccionada.getInfeccion());

                        // Actualizar la etiqueta de acciones
                        labelAcciones.setText("Acciones: " + acciones);
                        

                        // Si la infección llega a 0, restaurar el color original del botón de la ciudad
                        if (ciudadSeleccionada.getInfeccion() == 0) {
                            ciudadSeleccionado.setBackground(ciudadSeleccionada.getColorOriginal());
                            ciudadSeleccionado.setForeground(Color.black);
                        }
                    }
                }
            }
        });

        ImageIcon imagenBotonCurar = new ImageIcon("curar.png");
        Image imagenOriginal = imagenBotonCurar.getImage();

     // Escalar la imagen al tamaño del botón
     Image imagenEscalada = imagenOriginal.getScaledInstance(150, 150, Image.SCALE_SMOOTH); 

     // Crear un nuevo ImageIcon con la imagen escalada
     ImageIcon imagenEscaladaIcon = new ImageIcon(imagenEscalada);

     // Establecer la imagen escalada como icono del botón
     botonCurar.setIcon(imagenEscaladaIcon);

     // Establecer el tamaño y la posición del botón
     botonCurar.setBounds(40, 568, 150, 150); // Tamaño del botón

     // Agregar el botón al panel central
     panelCentral.add(botonCurar);
      
        
        // Agregar paneles al marco
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);
        getContentPane().add(panelIzquierdo, BorderLayout.WEST);
        getContentPane().add(panelDerecho, BorderLayout.EAST);
       

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    
        //Crear Botones  y array ciudades :=)
        
  
        ciudadesArraylist = txtCiudades.crearArrayCiudades();
        
        
     // Panel para los botones de las ciudades
        
        
       System.out.println(ciudadesArraylist.size());
        
        for (Ciudades ciudad : ciudadesArraylist) {
            int x = ciudad.getCoords()[0];
            int y = ciudad.getCoords()[1];

            JButton botonCiudad = new JButton(ciudad.getNombreciudad());
            botonCiudad.setBounds(x, y, 100, 25); // Establecer el tamaño y la posición del botón
            panelCentral.add(botonCiudad);
            botonesCiudades.add(botonCiudad);
            
           
            botonCiudad.setBackground(Color.GREEN); // Cambiar color de boton
            botonCiudad.setOpaque(true);
            botonCiudad.setBorderPainted(true);
            botonCiudad.addActionListener(e -> {
               ciudadSeleccionado = botonCiudad;
               int posicionCiudad = -1;
               for (int i = 0; i < botonesCiudades.size(); i++) {
				if (botonCiudad == botonesCiudades.get(i)) {
					posicionCiudad = i;
				}
			}
               
            	labelCiudadSelec.setText("Ciudad Seleccionada: " + ciudadesArraylist.get(posicionCiudad).getNombreciudad());
            	labelInfeccionCiudad.setText("Infeccion Ciudad: " + ciudadesArraylist.get(posicionCiudad).getInfeccion());
                });
        }
        
        
        //crear vacuna
        
        crearVacunas();
        parametros.LeerYPrintear();
        infectarInicio();    
        }
    
    private void crearVacunas() {
		Vacunas rojo = new Vacunas("vacuna1", "Alfa", 0);
		Vacunas azul = new Vacunas("vacuna2", "Beta", 0);
		Vacunas amarilla = new Vacunas("vacuna3", "Gama", 0);
		Vacunas verde = new Vacunas("vacuna4", "Delta", 0);
		
		vacunasArraylist.add(rojo);
		vacunasArraylist.add(azul);
		vacunasArraylist.add(amarilla);
		vacunasArraylist.add(verde);
		
	}
    
    private int generarPorcentaje() {
        Random random = new Random();
        return random.nextInt(101); // Porcentaje aleatorio entre 0 y 100
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaJuego::new);
    }
    
    public void infectarInicio() {
    	
   
    	 for (int i = 0; i < ciudadesArraylist.size(); i++) {
    	        Ciudades ciudad = ciudadesArraylist.get(i);
    	        switch (ciudad.getVirus()) {
    	            case 0:
    	                botonesCiudades.get(i).setBackground(Color.BLUE);
    	                break;
    	            case 1:
    	                botonesCiudades.get(i).setBackground(Color.RED);
    	                break;
    	            case 2:
    	                botonesCiudades.get(i).setBackground(Color.YELLOW);
    	                break;
    	            case 3:
    	                botonesCiudades.get(i).setBackground(Color.GREEN);
    	                break;
    	            default:
    	                break;
    	        }
    	    
    	        // Almacenar el color original del botón en la ciudad
    	        ciudad.setColorOriginal(botonesCiudades.get(i).getBackground());
    	    }
    
    	
    	for (int i = 0; i < parametros.numCiudadesInfectadasInicio; i++) {
             Random random = new Random();
			int ciudadesRandom = random.nextInt(ciudadesArraylist.size());
             ciudadesArraylist.get(ciudadesRandom).aumentarinfeccion(1);
    	
             botonesCiudades.get(ciudadesRandom).setBackground(Color.DARK_GRAY);
             botonesCiudades.get(ciudadesRandom).setForeground(Color.white);
             
    	}
    
    }
    
 
    	
    
    
    public void brote(Ciudades ciudad) {
        for (String nombreColindante : ciudad.getColindantes()) { // Iterar sobre los nombres de las ciudades colindantes
            // Buscar la ciudad colindante por su nombre
            for (int j = 0; j < ciudadesArraylist.size(); j++) {
                Ciudades ciudadEnLista = ciudadesArraylist.get(j);
                if (nombreColindante.equals(ciudadEnLista.getNombreciudad())) { // Comparar nombres de ciudad
                    ciudadEnLista.aumentarinfeccion(1);
                    int indiceBoton = botonesCiudades.indexOf(ciudadEnLista.getNombreciudad());
                    if (indiceBoton != -1) {
                        botonesCiudades.get(indiceBoton).setBackground(Color.DARK_GRAY);
                        botonesCiudades.get(indiceBoton).setForeground(Color.WHITE);
                    }
                    break; // Salir del bucle una vez que se haya encontrado la ciudad colindante
                }
            }
        }
    }




    
    public void nuevoTurno() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int ciudadRandom = random.nextInt(ciudadesArraylist.size());
            Ciudades ciudadActual = ciudadesArraylist.get(ciudadRandom);

            // Verificar si la ciudad está en infección 3
            if (ciudadActual.getInfeccion() < 3) {
                ciudadActual.aumentarinfeccion(1);

                // Si la ciudad ya tiene una infección, siempre aumentar una infección más
                if (ciudadActual.getInfeccion() == 1) {
                    ciudadActual.aumentarinfeccion(1);
                }

                // Actualizar la apariencia del botón de la ciudad
                botonesCiudades.get(ciudadRandom).setBackground(Color.DARK_GRAY);
                botonesCiudades.get(ciudadRandom).setForeground(Color.white);
            }
        }

       
    }

    
}


