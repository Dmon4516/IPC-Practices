package practica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;


/**
 * Interfaz que permite seleccionar el idioma a mostrar en el programa
 * @authors Luis Setién, Victor Descalzo, David Edmundo Montenegro, Oscar Entrecanales
 * @version Noviembre 2024
 */
public class VentanaIdioma {
	
	private JFrame frmIdioma;
	private JComboBox cbIdioma;
	private Locale localizacion = null;
	private ResourceBundle mensajes = null;
	private boolean iniciador;
	
	/**
	 * metodo principal de la clase
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIdioma window = new VentanaIdioma(true);
					window.frmIdioma.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * metodo constructor de la clase
	 * @param iniciador: true si es el punto de arranque, false si no lo es
	 */
	public VentanaIdioma(boolean iniciador) {
		this.iniciador = iniciador;
		initialize();
	}
	
	/**
	 * metodo constructor de la clase
	 * @param iniciador: true si es el punto de arranque, false si no lo es
	 * @param localizacion: la configuracion de localizacion establecida
	 * @param mensajes: el almacen de mensajes en el idioma establecido
	 */
	public VentanaIdioma(boolean iniciador, Locale localizacion, ResourceBundle mensajes) {
		this.iniciador = iniciador;
		this.localizacion = localizacion;
		this.mensajes = mensajes;
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
		frmIdioma = new JFrame();
		frmIdioma.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaIdioma.class.getResource("/imagenes/icono2.png")));
		frmIdioma.setTitle(mensajes.getString("idioma_titulo"));
		frmIdioma.setResizable(false);
		frmIdioma.setBounds(100, 100, 300, 180);
		frmIdioma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		// creamos el panel principal, estableciendo margenes y espaciado
		JPanel pnlIdioma = new JPanel();
		frmIdioma.getContentPane().add(pnlIdioma, BorderLayout.CENTER);
		pnlIdioma.setLayout(new GridLayout(3, 3, 10, 10));
		pnlIdioma.setBorder(new EmptyBorder(20, 60, 30, 60));
		
		
		// creamos las etiquetas y campos desplegables 
		JLabel lblIdioma = new JLabel(mensajes.getString("idioma_subtitulo"));
		pnlIdioma.add(lblIdioma);
		
		cbIdioma = new JComboBox();
		cbIdioma.setModel(new DefaultComboBoxModel(new String[] {"Castellano", "English"}));
		pnlIdioma.add(cbIdioma);
		
		
		// creamos el boton de aceptar junto a su manejador de eventos
		// para lograr que al pulsarlo se produzca la accion relevante
		JButton btnAceptar = new JButton(mensajes.getString("idioma_aceptar"));
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAceptarMouseClicked();
			}
		});
		pnlIdioma.add(btnAceptar);
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de aceptar
	 */
	protected void btnAceptarMouseClicked() {
		
		// establecemos y configuramos el idioma seleccionado
		if (cbIdioma.getSelectedItem().equals("English")) {
			localizacion = new Locale.Builder().setLanguage("en").setRegion("US").build();
			mensajes = ResourceBundle.getBundle("local/Local", localizacion);
		} else {
			localizacion = new Locale.Builder().setLanguage("es").setRegion("ES").build();
			mensajes = ResourceBundle.getBundle("local/Local", localizacion);
		}
		
		// segun el punto de arranque del programa, se crea la ventana principal de cero
		// o bien se le pasan los datos del idioma actual y se refrescan las cadenas textuales
		if (iniciador == true) {
			VentanaPrincipal ventana = new VentanaPrincipal(localizacion, mensajes);
			ventana.setVisible(true);
		} else {
			VentanaPrincipal.localizacion = localizacion;
			VentanaPrincipal.mensajes = mensajes;
			VentanaPrincipal.refrescarIdioma();
		}
		
		// se cierra la ventana una vez configurado el idioma
		this.frmIdioma.dispose();
	}
	
	
	/**
	 * metodo que permite modificar la visibilidad de esta ventana
	 * @param visibilidad: true para hacerla visible, false invisible
	 */
	public void setVisible(boolean visibilidad) {
		this.frmIdioma.setVisible(visibilidad);
	}
}
