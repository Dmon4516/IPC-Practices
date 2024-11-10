package practica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Interfaz que permite acceder a las distintas secciones del programa
 * @authors Luis Setién, Victor Descalzo, David Edmundo Montenegro, Oscar Entrecanales
 * @version Noviembre 2024
 */
public class VentanaPrincipal {
	
	private static JFrame frmPrincipal;
	public static Registro datosRegistro;
	public static Map<Integer, Object> ventanasRegistro = new HashMap<>();
	public static boolean ventanaInforme = false;
	public static Locale localizacion = null;
	public static ResourceBundle mensajes = null;
	public static JButton btnAltaProveedor;
	public static JButton btnConsultas;
	public static JButton btnInforme;
	public static JButton btnSalir;
	public static JButton btnIdioma;
	
	/**
	 * metodo principal de la clase
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * metodo constructor de la clase
	 */
	public VentanaPrincipal() {
		initialize();
	}
	
	/**
	 * metodo constructor de la clase
	 * @param localizacion: la configuracion de localizacion establecida
	 * @param mensajes: el almacen de mensajes en el idioma establecido
	 */
	public VentanaPrincipal(Locale localizacion, ResourceBundle mensajes) {
		VentanaPrincipal.localizacion = localizacion;
		VentanaPrincipal.mensajes = mensajes;
		initialize();
	}
	
	/**
	 * metodo que se encarga de inicializar el contenido de la ventana
	 */
	private void initialize() {
		
		// si no hay un idioma establecido, se utiliza el castellano
		if (localizacion == null) {
			localizacion = new Locale.Builder().setLanguage("es").setRegion("ES").build();
			mensajes = ResourceBundle.getBundle("local/Local", localizacion);
		}
		
		
		// inicializamos y establecemos los atributos basicos de la ventana
		frmPrincipal = new JFrame();
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imagenes/icono.png")));
		frmPrincipal.setTitle(mensajes.getString("principal_titulo"));
		frmPrincipal.setResizable(false);
		frmPrincipal.setBounds(100, 100, 400, 340);
		frmPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		// creamos el panel principal, estableciendo margenes y espaciado
		JPanel gridPanel = new JPanel(new GridLayout(6, 3, 10, 10));
		frmPrincipal.getContentPane().add(gridPanel, BorderLayout.CENTER);
		gridPanel.setBorder(new EmptyBorder(40, 60, 40, 60));
		
		
		// creamos los botones junto a sus manejadores de eventos
		// para lograr que al pulsarlos se produzca la accion relevante
		btnAltaProveedor = new JButton(mensajes.getString("principal_registro"));
		btnAltaProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAltaProveedorMouseClicked();
			}
		});
		gridPanel.add(btnAltaProveedor);
		
		btnInforme = new JButton(mensajes.getString("principal_informe"));
		btnInforme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnInformeMouseClicked();
			}
		});
		gridPanel.add(btnInforme);
		
		btnConsultas = new JButton(mensajes.getString("principal_consulta"));
		btnConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConsultasMouseClicked();
			}
		});
		gridPanel.add(btnConsultas);
		
		JPanel panel = new JPanel();
		gridPanel.add(panel);
		
		btnIdioma = new JButton(mensajes.getString("principal_idioma"));
		btnIdioma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnIdiomaMouseClicked();
			}
		});
		gridPanel.add(btnIdioma);
		
		btnSalir = new JButton(mensajes.getString("principal_salir"));
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cierraPrograma();
			}
		});
		gridPanel.add(btnSalir);
		
		
		// creamos un listener para evitar el cierre del programa si existe alguna
		// sub-ventana o formulario abierto, evitando asi una posible perdida de datos
		frmPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cierraPrograma();
            }
        });
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de alta de proveedor
	 */
	private void btnAltaProveedorMouseClicked() {
		// si el usuario ya tiene este formulario abierto, le avisamos
		if (!ventanasRegistro.isEmpty()) {
			String mensajeError = mensajes.getString("principal_msg_abierto");
	        JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
	        return;
		}
		
		// invocamos la ventana y la agregamos a un listado para su manejo
		VentanaRegistroPaso1 ventana = new VentanaRegistroPaso1();
		ventanasRegistro.put(1, ventana);
		datosRegistro = new Registro();
		ventana.setVisible(true);
	}
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de consultas de compraventa
	 */
	private void btnConsultasMouseClicked() {
		VentanaConsultas ventana = new VentanaConsultas();
		ventana.setVisible(true);
	}
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de informe administrativo
	 */
	private void btnInformeMouseClicked() {
		VentanaInforme ventana = new VentanaInforme();
		ventana.setVisible(true);
	}
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de cambiar idioma
	 */
	private void btnIdiomaMouseClicked() {
		if ((ventanasRegistro.isEmpty() == false) || ventanaInforme) {
        	String mensajeError = mensajes.getString("principal_msg_idioma");
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			return;
        }
		VentanaIdioma ventana = new VentanaIdioma(false, localizacion, mensajes);
		ventana.setVisible(true);
	}
	
	/**
	 * metodo que se encarga de realizar un cierre seguro del programa
	 */
	private void cierraPrograma() {
		if ((ventanasRegistro.isEmpty() == false) || ventanaInforme) {
        	String mensajeError = mensajes.getString("principal_msg_salir");
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			return;
        }
        System.exit(0);
	}
	
	/**
	 * metodo que permite modificar la visibilidad de esta ventana
	 * @param visibilidad: true para hacerla visible, false invisible
	 */
	public void setVisible(boolean visibilidad) {
		VentanaPrincipal.frmPrincipal.setVisible(visibilidad);
	}
	
	/**
	 * metodo que refresca todas las cadenas y etiquetas de texto
	 * presentes en la interfaz para actualizar el idioma mostrado
	 */
	public static void refrescarIdioma() {
		frmPrincipal.setTitle(mensajes.getString("principal_titulo"));
		btnAltaProveedor.setText(mensajes.getString("principal_registro"));
		btnConsultas.setText(mensajes.getString("principal_consulta"));
		btnInforme.setText(mensajes.getString("principal_informe"));
		btnSalir.setText(mensajes.getString("principal_salir"));
		btnIdioma.setText(mensajes.getString("principal_idioma"));
	}
}
