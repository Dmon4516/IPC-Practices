package practica;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


/**
 * Interfaz tipo formulario que permite introducir los datos necesarios para darse de alta
 *     esta clase en concreto representa la primera pagina, en la que se introducen los datos generales
 * @authors Luis Seti�n, Victor Descalzo, David Edmundo Montenegro, Oscar Entrecanales
 * @version Octubre 2024
 */
public class VentanaRegistroPaso1 {
	
	private JFrame frmAltaDeProveedor;
	private JTextField txtNombre;
	private JTextField txtNIF;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtNombreContacto;
	
	// anotamos aquellos campos que no son validos para comprobarlos al cambiar de pagina
	private boolean[] camposErroneos = {false, false, false, false, false, false};
	private final int MAX_LONGITUD = 256;
	
	// implementamos un semaforo para prevenir que dos alertas aparezcan a la vez en pantalla
	// por lo cual evitamos un potencial problema de usabilidad
	private boolean semaforo = false;
	
	
	/**
	 * metodo principal de la clase
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroPaso1 window = new VentanaRegistroPaso1();
					window.frmAltaDeProveedor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * metodo constructor de la clase 
	 */
	public VentanaRegistroPaso1() {
		initialize();
	}
	
	/**
	 * metodo que se encarga de inicializar el contenido de la ventana
	 */
	private void initialize() {
		
		// ----------------------------------------------
		// parte del formulario comun a todas las paginas
		// ----------------------------------------------
		
		// inicializamos y establecemos los atributos basicos de la ventana
		frmAltaDeProveedor = new JFrame();
		frmAltaDeProveedor.setResizable(false);
		frmAltaDeProveedor.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistroPaso1.class.getResource("/imagenes/icono.png")));
		frmAltaDeProveedor.setTitle("Alta de proveedor");
		frmAltaDeProveedor.setBounds(100, 100, 600, 550);
		frmAltaDeProveedor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		// creamos el panel de titulo junto al titular del formulario
		JPanel pnlTitulo = new JPanel();
		frmAltaDeProveedor.getContentPane().add(pnlTitulo, BorderLayout.NORTH);
		pnlTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlTitulo.setBorder(new EmptyBorder(12, 12, 12, 12));
		
		JLabel lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTitulo.add(lblTitulo);
		
		
		// creamos el panel de la botonera y los botones junto a sus manejadores
		// para lograr que al pulsarlos se produzca la accion relevante
		JPanel pnlBotonera = new JPanel();
		frmAltaDeProveedor.getContentPane().add(pnlBotonera, BorderLayout.SOUTH);
		pnlBotonera.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBotonera.setBorder(new EmptyBorder(12, 12, 12, 12));
		
		JButton btnAnterior = new JButton("< Anterior");
		btnAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAnteriorMouseClicked();
			}
		});
		btnAnterior.setPreferredSize(new Dimension(100, 30));
		pnlBotonera.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("Siguiente >");
		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSiguienteMouseClicked();
			}
		});
		btnSiguiente.setPreferredSize(new Dimension(100, 30));
		pnlBotonera.add(btnSiguiente);
		
		
		// creamos un listener para confirmar el cierre de la ventana
		// del formulario y consecuentemente el borrado de los datos introducidos
		frmAltaDeProveedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	semaforo = true;
                int confirmar = JOptionPane.showConfirmDialog(frmAltaDeProveedor,
                    "�Seguro que quieres cerrar el formulario? Los datos se perder�n",
                    "Confirmar acci�n",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirmar == JOptionPane.YES_OPTION) {
                	VentanaPrincipal.ventanasRegistro.clear();
                	VentanaPrincipal.datosRegistro = null;
                	frmAltaDeProveedor.dispose();
                } else {
                	semaforo = false;
                }
            }
        });
		
		
		// ---------------------------------------------
		// parte del formulario especifica a esta pagina
		// ---------------------------------------------
		
		// configuramos las propiedades especificas de esta pagina
		btnAnterior.setVisible(false);
		lblTitulo.setText("Paso 1 de 4: Introduce datos generales:");
		
		
		// creamos el panel principal del formulario junto a los
		// elementos y campos correspondientes a esta pagina
		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBackground(Color.WHITE);
		frmAltaDeProveedor.getContentPane().add(pnlFormulario, BorderLayout.CENTER);
		pnlFormulario.setLayout(new GridLayout(6, 2, 10, 20));
		pnlFormulario.setBorder(new EmptyBorder(60, 60, 60, 60));
		
		
		// creamos las etiquetas junto a los campos de texto correspondientes
		// con manejadores de eventos para poder avisar oportunamente si el campo introducido no es valido
		JLabel lblNombre = new JLabel("Nombre o raz\u00F3n social del proveedor:");
		pnlFormulario.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtNombreFocusLost();
			}
		});
		pnlFormulario.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNIF = new JLabel("N\u00FAmero de identificaci\u00F3n fiscal:");
		pnlFormulario.add(lblNIF);
		
		txtNIF = new JTextField();
		txtNIF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtNIFFocusLost();
			}
		});
		pnlFormulario.add(txtNIF);
		txtNIF.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n fiscal y comercial:");
		pnlFormulario.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtDireccionFocusLost();
			}
		});
		pnlFormulario.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("N\u00FAmero de tel\u00E9fono:");
		pnlFormulario.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtTelefonoFocusLost();
			}
		});
		pnlFormulario.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo electr\u00F3nico de contacto:");
		pnlFormulario.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtCorreoFocusLost();
			}
		});
		pnlFormulario.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblNombreContacto = new JLabel("Nombre de la persona de contacto:");
		pnlFormulario.add(lblNombreContacto);
		
		txtNombreContacto = new JTextField();
		txtNombreContacto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtNombreContactoFocusLost();
			}
		});
		pnlFormulario.add(txtNombreContacto);
		txtNombreContacto.setColumns(10);
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de anterior
	 */
	private void btnAnteriorMouseClicked() {
		// no se aplica a esta pagina en especifico
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de siguiente
	 */
	private void btnSiguienteMouseClicked() {
		// comprobamos que todos los datos introducidos sean validos
		// y todos los campos obligatorios esten rellenados correctamente
		if (comprobarDatos() == false) {
			return;
		}
		
		// guardamos los datos en el objeto correspondiente
		guardarDatos();
		
		// invocamos a la ventana que representa la pagina siguiente del formulario
		// si ya fue creada anteriormente, simplemente se vuelve a hacer visible
		if (VentanaPrincipal.ventanasRegistro.get(2) != null) {
			this.setVisible(false);
			((VentanaRegistroPaso2) VentanaPrincipal.ventanasRegistro.get(2)).setVisible(true);
			return;
		}
		
		// la creamos de cero si no se hizo antes
		VentanaRegistroPaso2 ventana = new VentanaRegistroPaso2();
		VentanaPrincipal.ventanasRegistro.put(2, ventana);
		this.setVisible(false);
		ventana.setVisible(true);
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al salir del campo del nombre
	 * comprobandose en este momento las restricciones sobre el mismo
	 */
	private void txtNombreFocusLost() {
		String nombre = txtNombre.getText();
		
		// evitamos que aparezcan dos errores al mismo tiempo
		if (semaforo == true) {
			return;
		} else {
			semaforo = true;
		}
		
		// error: el campo esta en blanco
		if (nombre.length() == 0) {
			txtNombre.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre o raz\u00F3n social del proveedor\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = false;
			semaforo = false;
			return;
		} 
		
		// error: el campo contiene numeros
		if (nombre.matches(".*[0-9].*")) {
			txtNombre.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre o raz\u00F3n social del proveedor\" no debe contener n�meros";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene caracteres especiales
		if (nombre.matches(".*[^\\p{L}\\s].*")) {
			txtNombre.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre o raz\u00F3n social del proveedor\" no debe contener caracteres especiales";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo es excesivamente largo
		if (nombre.length() > MAX_LONGITUD) {
			txtNombre.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre o raz\u00F3n social del proveedor\" es demasiado largo";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}
		
		// el campo es valido		
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtNombre.getBackground().equals(new Color(255, 220, 220))) {
			txtNombre.setBackground(new Color(255, 255, 255));
			camposErroneos[0] = false;
		}
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al salir del campo del nif
	 * comprobandose en este momento las restricciones sobre el mismo
	 */
	private void txtNIFFocusLost() {
		String nif = txtNIF.getText();
		
		// evitamos que aparezcan dos errores al mismo tiempo
		if (semaforo == true) {
			return;
		} else {
			semaforo = true;
		}
		
		// error: el campo esta en blanco
		if (nif.length() == 0) {
			txtNIF.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de identificaci\u00F3n fiscal\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = false;
			semaforo = false;
			return;
		} 
		
		// error: el campo no tiene la longitud adecuada
		if (nif.length() != 9) {
			txtNIF.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de identificaci\u00F3n fiscal\" debe tener exactamente longitud 9";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene letras minusculas
		if (nif.matches(".*[a-z].*")) {
			txtNIF.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de identificaci\u00F3n fiscal\" no debe contener letras min�sculas";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo no contiene letra
		if (!nif.matches(".*[A-Z].*")) {
			txtNIF.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de identificaci\u00F3n fiscal\" debe contener una letra";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo no contiene numeros
		if (!nif.matches(".*[0-9].*")) {
			txtNIF.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de identificaci\u00F3n fiscal\" debe contener n�meros";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene caracteres especiales
		if (nif.matches(".*[^A-Z0-9].*")) {
			txtNIF.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de identificaci\u00F3n fiscal\" no debe contener caracteres especiales";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}
		
		// el campo es valido		
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtNIF.getBackground().equals(new Color(255, 220, 220))) {
			txtNIF.setBackground(new Color(255, 255, 255));
			camposErroneos[1] = false;
		}
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al salir del campo de la direccion
	 * comprobandose en este momento las restricciones sobre el mismo
	 */
	private void txtDireccionFocusLost() {
		String direccion = txtDireccion.getText();
		
		// evitamos que aparezcan dos errores al mismo tiempo
		if (semaforo == true) {
			return;
		} else {
			semaforo = true;
		}
		
		// error: el campo esta en blanco
		if (direccion.length() == 0) {
			txtDireccion.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Direcci\u00F3n fiscal y comercial\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = false;
			semaforo = false;
			return;
		}
		
		// error: el campo es excesivamente largo
		if (direccion.length() > MAX_LONGITUD) {
			txtDireccion.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Direcci\u00F3n fiscal y comercial\" es demasiado largo";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}
		
		// el campo es valido		
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtDireccion.getBackground().equals(new Color(255, 220, 220))) {
			txtDireccion.setBackground(new Color(255, 255, 255));
			camposErroneos[2] = false;
		}
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al salir del campo del telefono
	 * comprobandose en este momento las restricciones sobre el mismo
	 */
	private void txtTelefonoFocusLost() {
		String telefono = txtTelefono.getText();
		
		// evitamos que aparezcan dos errores al mismo tiempo
		if (semaforo == true) {
			return;
		} else {
			semaforo = true;
		}
		
		// error: el campo esta en blanco
		if (telefono.length() == 0) {
			txtTelefono.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de tel\u00E9fono\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[3] = false;
			semaforo = false;
			return;
		} 
		
		// error: el campo no tiene la longitud adecuada
		if (telefono.length() != 9) {
			txtTelefono.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de tel\u00E9fono\" debe tener exactamente longitud 9";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[3] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene espacios
		if (telefono.matches(".*\\s.*")) {
			txtTelefono.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de tel\u00E9fono\" no debe contener espacios";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[3] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene letras
		if (!telefono.matches("^[0-9]+$")) {
			txtTelefono.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"N\u00FAmero de tel\u00E9fono\" solo debe contener n�meros";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[3] = true;
			semaforo = false;
			return;
		}
		
		// el campo es valido		
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtTelefono.getBackground().equals(new Color(255, 220, 220))) {
			txtTelefono.setBackground(new Color(255, 255, 255));
			camposErroneos[3] = false;
		}
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al salir del campo del correo electronico
	 * comprobandose en este momento las restricciones sobre el mismo
	 */
	private void txtCorreoFocusLost() {
		String correo = txtCorreo.getText();
		
		// evitamos que aparezcan dos errores al mismo tiempo
		if (semaforo == true) {
			return;
		} else {
			semaforo = true;
		}
		
		// error: el campo esta en blanco
		if (correo.length() == 0) {
			txtCorreo.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Correo electr\u00F3nico de contacto\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[4] = false;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene espacios
		if (correo.matches(".*\\s.*")) {
			txtCorreo.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Correo electr\u00F3nico de contacto\" no debe contener espacios";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[4] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo no sigue el formato adecuado
		if (!correo.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			txtCorreo.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Correo electr\u00F3nico de contacto\" no sigue el formato adecuado\nEjemplo: cliente@example.com";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[4] = true;
			semaforo = false;
			return;
		}
		
		// el campo es valido
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtCorreo.getBackground().equals(new Color(255, 220, 220))) {
			txtCorreo.setBackground(new Color(255, 255, 255));
			camposErroneos[4] = false;
		}
	}
	
	
	/**
	 * metodo que se encarga de la accion realizada al salir del campo del nombre de contacto
	 * comprobandose en este momento las restricciones sobre el mismo
	 */
	private void txtNombreContactoFocusLost() {
		String nombreContacto = txtNombreContacto.getText();
		
		// evitamos que aparezcan dos errores al mismo tiempo
		if (semaforo == true) {
			return;
		} else {
			semaforo = true;
		}
		
		// error: el campo esta en blanco
		if (nombreContacto.length() == 0) {
			txtNombreContacto.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre de la persona de contacto\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[5] = false;
			semaforo = false;
			return;
		} 
			
		// error: el campo contiene numeros
		if (nombreContacto.matches(".*[0-9].*")) {
			txtNombreContacto.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre de la persona de contacto\" no debe contener n�meros";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[5] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo contiene caracteres especiales
		if (nombreContacto.matches(".*[^\\p{L}\\s].*")) {
			txtNombreContacto.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre de la persona de contacto\" no debe contener caracteres especiales";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[5] = true;
			semaforo = false;
			return;
		}
		
		// error: el campo es excesivamente largo
		if (nombreContacto.length() > MAX_LONGITUD) {
			txtNombreContacto.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre de la persona de contacto\" es demasiado largo";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[5] = true;
			semaforo = false;
			return;
		}
		
		// el campo es valido
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtNombreContacto.getBackground().equals(new Color(255, 220, 220))) {
			txtNombreContacto.setBackground(new Color(255, 255, 255));
			camposErroneos[5] = false;
		}
	}
	
	
	/**
	 * metodo que comprueba que todos los campos rellenados sean validos
	 *     asi como que todos los campos obligatorios se hayan rellenado
	 * @return true: si todas las comprobaciones pasaron exitosamente
	 *         false: si algun campo es erroneo o se quedo vacio
	 */
	private boolean comprobarDatos() {
		boolean campoErroneo = false;
		boolean campoVacio = false;
		
		// comprobamos que no haya ningun campo con valor erroneo
		for (boolean erroneo: camposErroneos) {
			if (erroneo == true) {
				campoErroneo = true;
			}
		}
		
		// comprobamos que no quede ningun campo vacio
		// en tal caso, lo pintamos de rojo para destacarlo claramente
		if (txtNombre.getText().length() == 0) {
			txtNombre.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtNIF.getText().length() == 0) {
			txtNIF.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtDireccion.getText().length() == 0) {
			txtDireccion.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtTelefono.getText().length() == 0) {
			txtTelefono.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtCorreo.getText().length() == 0) {
			txtCorreo.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtNombreContacto.getText().length() == 0) {
			txtNombreContacto.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		
		// mostramos un dialogo de error con informacion relativa al contexto
		// si alguna de las anteriores comprobaciones no pasaron
		if (campoErroneo || campoVacio) {
			String mensajeErroneo = "Uno o m�s campos contienen valores no v�lidos.";
			String mensajeVacio = "Uno o m�s campos son obligatorios pero est�n vac�os.";
			String mensajeAviso = "Compruebe aquellos resaltados en rojo.";
			
			if (campoErroneo && campoVacio) {
				mensajeAviso = mensajeErroneo + "\n" + mensajeVacio + "\n" + mensajeAviso;
			}
			
			if (campoErroneo && !campoVacio) {
				mensajeAviso = mensajeErroneo + "\n" + mensajeAviso;
			}
			
			if (!campoErroneo && campoVacio) {
				mensajeAviso = mensajeVacio + "\n" + mensajeAviso;
			}
			
			JOptionPane.showMessageDialog(null, mensajeAviso, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	/**
	 * metodo que se encarga de almacenar los datos recogidos en esta pagina del formulario
	 */
	private void guardarDatos() {
		// extraemos el valor actual de los campos de texto
		String nombre = txtNombre.getText();
		String nif = txtNIF.getText();
		String direccion = txtDireccion.getText();
		String telefono = txtTelefono.getText();
		String correo = txtCorreo.getText();
		String nombreContacto = txtNombreContacto.getText();
		
		// el numero de telefono tiene sentido almacenarlo como entero
		int numTelefono = Integer.parseInt(telefono);
		
		// introducimos los datos recogidos dentro del objeto
		VentanaPrincipal.datosRegistro.setNombre(nombre);
		VentanaPrincipal.datosRegistro.setNif(nif);
		VentanaPrincipal.datosRegistro.setDireccion(direccion);
		VentanaPrincipal.datosRegistro.setTelefono(numTelefono);
		VentanaPrincipal.datosRegistro.setCorreo(correo);
		VentanaPrincipal.datosRegistro.setNombreContacto(nombreContacto);
	}
	
	
	/**
	 * metodo que permite modificar la visibilidad de esta pagina del formulario
	 * @param visibilidad: true para hacerla visible, false invisible
	 */
	public void setVisible(boolean visibilidad) {
		this.frmAltaDeProveedor.setVisible(visibilidad);
	}
}
