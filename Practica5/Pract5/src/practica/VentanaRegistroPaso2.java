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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 * Interfaz tipo formulario que permite introducir los datos necesarios para
 * darse de alta esta clase en concreto representa la primera pagina, en la que
 * se introducen los datos generales
 * 
 * @authors Luis Seti?n, Victor Descalzo, David Edmundo Montenegro, Oscar
 *          Entrecanales
 * @version Octubre 2024
 */
public class VentanaRegistroPaso2 {

	private JFrame frmAltaDeProveedor;
	private JTextField txtBancaria;
	private JTextField txtSucursal;
	private JTextField txtSWIFT;
	private JTextField txtFiscalyLegal;
	private JRadioButton rdbtnObligacionFiscal;
	private JRadioButton rdbtnAlta;

	private boolean[] camposErroneos = { false, false, false, false, false, false }; // 6 pero puede bajar a 4 (por
																						// rdbtns)
	private static final int MAX_LONGITUD = 256;
	private boolean semaforo = false;

	/**
	 * metodo principal de la clase
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroPaso2 window = new VentanaRegistroPaso2();
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
	public VentanaRegistroPaso2() {
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
		frmAltaDeProveedor.setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaRegistroPaso2.class.getResource("/imagenes/icono.png")));
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
				int confirmar = JOptionPane.showConfirmDialog(frmAltaDeProveedor,
						"?Seguro que quieres cerrar el formulario? Los datos se perder?n", "Confirmar acci?n",
						JOptionPane.YES_NO_OPTION);

				if (confirmar == JOptionPane.YES_OPTION) {
					VentanaPrincipal.ventanasRegistro.clear();
					VentanaPrincipal.datosRegistro = null;
					frmAltaDeProveedor.dispose();
				}
			}
		});

		// ---------------------------------------------
		// parte del formulario especifica a esta pagina
		// ---------------------------------------------

		// configuramos las propiedades especificas de esta pagina
		lblTitulo.setText("Paso 2 de 4: Introduce datos bancarios:");

		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBackground(Color.WHITE);
		frmAltaDeProveedor.getContentPane().add(pnlFormulario, BorderLayout.CENTER);
		pnlFormulario.setBorder(new EmptyBorder(60, 60, 60, 60));
		pnlFormulario.setLayout(new GridLayout(6, 1, 10, 20));

		JPanel pnlBancaria = new JPanel();
		pnlBancaria.setBorder(null);
		pnlBancaria.setBackground(Color.WHITE);
		pnlFormulario.add(pnlBancaria);
		pnlBancaria.setLayout(new GridLayout(1, 2, 10, 0));

		JLabel lblBancaria = new JLabel("Cuenta bancaria (IBAN):");
		lblBancaria.setBackground(Color.WHITE);
		lblBancaria.setFont(new Font("Dialog", Font.BOLD, 12));
		pnlBancaria.add(lblBancaria);

		txtBancaria = new JTextField();
		txtBancaria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtIBANFocusLost();
			}
		});
		pnlBancaria.add(txtBancaria);
		txtBancaria.setColumns(10);

		JPanel pnlSucursal = new JPanel();
		pnlSucursal.setBackground(Color.WHITE);
		pnlFormulario.add(pnlSucursal);
		pnlSucursal.setLayout(new GridLayout(1, 2, 10, 0));

		JLabel lblSucursal = new JLabel("Nombre del banco y sucursal:");
		lblSucursal.setBackground(Color.WHITE);
		lblSucursal.setFont(new Font("Dialog", Font.BOLD, 12));
		pnlSucursal.add(lblSucursal);

		txtSucursal = new JTextField();
		txtSucursal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtBancoFocusLost();
			}
		});
		pnlSucursal.add(txtSucursal);
		txtSucursal.setColumns(10);

		JPanel pnlSWIFT = new JPanel();
		pnlSWIFT.setBackground(Color.WHITE);
		pnlFormulario.add(pnlSWIFT);
		pnlSWIFT.setLayout(new GridLayout(1, 2, 10, 0));

		JLabel lblSWIFT = new JLabel("Código SWIFT/BIC:");
		lblSWIFT.setBackground(Color.WHITE);
		lblSWIFT.setFont(new Font("Dialog", Font.BOLD, 12));
		pnlSWIFT.add(lblSWIFT);

		txtSWIFT = new JTextField();
		txtSWIFT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtSWIFTFocusLost();
			}
		});
		pnlSWIFT.add(txtSWIFT);
		txtSWIFT.setColumns(10);

		JPanel pnlFiscalyLegal = new JPanel();
		pnlFiscalyLegal.setBackground(Color.WHITE);
		pnlFormulario.add(pnlFiscalyLegal);
		pnlFiscalyLegal.setLayout(new GridLayout(1, 2, 10, 0));

		JLabel lblFiscalyLegal = new JLabel("Información fiscal y legal:");
		lblFiscalyLegal.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFiscalyLegal.setBackground(Color.WHITE);
		pnlFiscalyLegal.add(lblFiscalyLegal);

		txtFiscalyLegal = new JTextField();
		txtFiscalyLegal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtFiscalyLegalFocusLost();
			}
		});
		pnlFiscalyLegal.add(txtFiscalyLegal);
		txtFiscalyLegal.setColumns(10);

		JPanel pnlObligacionFiscal = new JPanel();
		pnlObligacionFiscal.setBackground(Color.WHITE);
		pnlFormulario.add(pnlObligacionFiscal);
		pnlObligacionFiscal.setLayout(new GridLayout(1, 0, 0, 0));

		rdbtnObligacionFiscal = new JRadioButton("Estoy al corriente con mis obligaciones fiscales");
		rdbtnObligacionFiscal.setFont(new Font("Dialog", Font.BOLD, 12));;
		pnlObligacionFiscal.add(rdbtnObligacionFiscal);

		JPanel pnlAlta = new JPanel();
		pnlAlta.setBackground(Color.WHITE);
		pnlFormulario.add(pnlAlta);
		pnlAlta.setLayout(new GridLayout(1, 0, 0, 0));

		rdbtnAlta = new JRadioButton("Estoy de alta en el Registro de Proveedores");
		rdbtnAlta.setFont(new Font("Dialog", Font.BOLD, 12));
		pnlAlta.add(rdbtnAlta);

	}

	private void btnAnteriorMouseClicked() {
		if (VentanaPrincipal.ventanasRegistro.get(1) != null) {
			this.setVisible(false);
			((VentanaRegistroPaso1) VentanaPrincipal.ventanasRegistro.get(1)).setVisible(true);
			return;
		}

		VentanaRegistroPaso1 ventana = new VentanaRegistroPaso1();
		VentanaPrincipal.ventanasRegistro.put(1, ventana);
		this.setVisible(false);
		ventana.setVisible(true);
	}

	private void btnSiguienteMouseClicked() {
		if (comprobarDatos() == false) {
			return;
		}

		guardarDatos();

		if (VentanaPrincipal.ventanasRegistro.get(3) != null) {
			this.setVisible(false);
			((VentanaRegistroPaso3) VentanaPrincipal.ventanasRegistro.get(3)).setVisible(true);
			return;
		}

		VentanaRegistroPaso3 ventana = new VentanaRegistroPaso3();
		VentanaPrincipal.ventanasRegistro.put(3, ventana);
		this.setVisible(false);
		ventana.setVisible(true);
	}

	private void txtIBANFocusLost() {
		// txtBancaria
		String IBAN = txtBancaria.getText();

		if (semaforo) {
			return;
		} else {
			semaforo = true;
		}

		// error: el campo esta en blanco
		if (IBAN.length() == 0) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = false;
			semaforo = false;
			return;
		}

		// error: el campo no tiene la longitud correcta
		if (IBAN.length() != 24 && IBAN.length() > 0) { // Para que no choquen los dos errores
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" debe tener 24 caracteres";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}

		// error: el campo contiene letras minusculas
		if (IBAN.matches("[a-z]")) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" no debe contener letras minusculas";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}

		// error: el campo no tiene mayusculas
		if (!IBAN.matches("([A-Z]{2})(.+?{22})")) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" solo puede tener letras mayusculas en los primeros dos caracteres";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}

		// error: el campo no tiene mayusculas
		if (!IBAN.matches("(.+?{2})([0-9]{22})")) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" solo admite dígitos para los últimos 22 caracteres";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}

		// error: el campo tiene guiones
		if (IBAN.contains("-")) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" debe ser introducido sin guion";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}

		// error: el campo contiene caracteres especiales
		if (!IBAN.matches("^[a-zA-Z0-9]*$")) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"IBAN\" no debe contener caracteres especiales";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[0] = true;
			semaforo = false;
			return;
		}

		// el campo es valido
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtBancaria.getBackground().equals(new Color(255, 220, 220))) {
			txtBancaria.setBackground(new Color(255, 255, 255));
			camposErroneos[0] = false;
		}
	}

	private void txtBancoFocusLost() {
		// txtSucursal
		String banco = txtSucursal.getText();
		if (semaforo) {
			return;
		} else {
			semaforo = true;
		}

		// error: el campo esta en blanco
		if (banco.length() == 0) {
			txtSucursal.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre del banco y sucursal\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = false;
			semaforo = false;
			return;
		}

		// error: el campo no contiene letras
		if (!banco.matches("^[a-zA-Z0]*$")) {
			txtSucursal.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre del banco y sucursal\" debe contener letras.";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}

		// error: el campo contiene caracteres especiales
		if (!banco.matches("^[a-zA-Z0-9äöüÄÖÜ]*$")) {
			txtSucursal.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre del banco y sucursal\" no debe contener caracteres especiales";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}

		// error: el campo es excesivamente largo
		if (banco.length() > MAX_LONGITUD) {
			txtSucursal.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Nombre del banco y sucursal\" es demasiado largo";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[1] = true;
			semaforo = false;
			return;
		}

		// el campo es valido
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtSucursal.getBackground().equals(new Color(255, 220, 220))) {
			txtSucursal.setBackground(new Color(255, 255, 255));
			camposErroneos[1] = false;
		}
	}

	private void txtSWIFTFocusLost() {
		// txtSWIFT
		String swift = txtSWIFT.getText();
		if (semaforo) {
			return;
		} else {
			semaforo = true;
		}

		// error: el campo esta en blanco
		if (swift.length() == 0) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = false;
			semaforo = false;
			return;
		}

		// error: el campo contiene caracteres especiales
		if (!swift.matches("^[a-zA-Z0-9äöüÄÖÜ]*$")) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" no debe contener caracteres especiales";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}

		// error: el campo no debe contener letras minusculas
		if (swift.matches("[a-z]")) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" no puede contener letras minusculas";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}

		// error: el campo tiene guiones
		if (swift.contains("-")) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" debe ser introducido sin guion";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}

		// error: el campo tiene mas/menos de 11 caracteres (sin ser vacio)
		if (swift.length() != 11 && swift.length() > 0) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" debe tener exactamente 11 caracteres";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}

		// error: el campo no tiene mayusculas
		if (!swift.matches("([A-Z]{6})(.+?{4})")) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" solo puede tener letras mayusculas en los primeros seis caracteres";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}

		// error: el campo no tiene mayusculas
		if (!swift.matches("(.+?{6})([A-Z0-9]{4})")) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"codigo SWIFT\" solo pueden tener mayusculas o numeros en los cuatro ultimos caracteres";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[2] = true;
			semaforo = false;
			return;
		}

		// el campo es valido
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtSWIFT.getBackground().equals(new Color(255, 220, 220))) {
			txtSWIFT.setBackground(new Color(255, 255, 255));
			camposErroneos[2] = false;
		}
	}

	private void txtFiscalyLegalFocusLost() {
		// txtFiscalyLegal
		String fyl = txtFiscalyLegal.getText();

		// error: el campo esta en blanco
		if (fyl.length() == 0) {
			txtFiscalyLegal.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Información fiscal y legal\" es obligatorio";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[3] = false;
			semaforo = false;
			return;
		}

		// error: el campo es excesivamente largo
		if (fyl.length() > MAX_LONGITUD) {
			txtFiscalyLegal.setBackground(new Color(255, 220, 220));
			String mensajeError = "El campo \"Información fiscal y legal\" es demasiado largo";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			camposErroneos[3] = true;
			semaforo = false;
			return;
		}

		// el campo es valido
		// si fue corregido, lo volvemos a pintar de blanco
		semaforo = false;
		if (txtSWIFT.getBackground().equals(new Color(255, 220, 220))) {
			txtSWIFT.setBackground(new Color(255, 255, 255));
			camposErroneos[3] = false;
		}
	}

	private boolean comprobarDatos() {
		boolean campoErroneo = false;
		boolean campoVacio = false;
		boolean botonesNoPresionados = false;

		// comprobamos que no haya ningun campo con valor erroneo
		for (boolean erroneo : camposErroneos) {
			if (erroneo == true) {
				campoErroneo = true;
			}
		}

		// comprobamos que no quede ningun campo vacio
		// en tal caso, lo pintamos de rojo para destacarlo claramente
		if (txtBancaria.getText().length() == 0) {
			txtBancaria.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtSucursal.getText().length() == 0) {
			txtSucursal.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtSWIFT.getText().length() == 0) {
			txtSWIFT.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}
		if (txtFiscalyLegal.getText().length() == 0) {
			txtFiscalyLegal.setBackground(new Color(255, 220, 220));
			campoVacio = true;
		}

		if (!rdbtnObligacionFiscal.isSelected() || !rdbtnAlta.isSelected()) {
			botonesNoPresionados = true;
		}

		// mostramos un dialogo de error con informacion relativa al contexto
		// si alguna de las anteriores comprobaciones no pasaron
		if (campoErroneo || campoVacio || botonesNoPresionados) {
			String mensajeErroneo = "Uno o más campos contienen valores no válidos.";
			String mensajeVacio = "Uno o más campos son obligatorios pero están vacíos.";
			String mensajeAvisoFin = "Compruebe aquellos resaltados en rojo.";
			String mensajeBotones = "Es obligatorio la presión de los dos botones";
			String mensajeAviso = "";

			if (campoErroneo && campoVacio) {
				mensajeAviso = mensajeErroneo + "\n" + mensajeVacio;
			}

			if (campoErroneo && !campoVacio) {
				mensajeAviso = mensajeErroneo;
			}

			if (!campoErroneo && campoVacio) {
				mensajeAviso = mensajeVacio;
			}

			if (botonesNoPresionados) {
				if (campoErroneo || campoVacio) {
					mensajeAviso += "\n";
				}
				mensajeAviso += mensajeBotones;
			}

			if (campoErroneo || campoVacio) {
				mensajeAviso += "\n" + mensajeAvisoFin;
			}

			JOptionPane.showMessageDialog(null, mensajeAviso, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * metodo que se encarga de almacenar los datos recogidos en esta pagina del
	 * formulario
	 */
	private void guardarDatos() {
		String iban = txtBancaria.getText();
		String banco = txtSucursal.getText();
		String swift = txtSWIFT.getText();
		String fiscalYLegal = txtFiscalyLegal.getText();

		VentanaPrincipal.datosRegistro.setIBAN(iban);
		VentanaPrincipal.datosRegistro.setBanco(banco);
		VentanaPrincipal.datosRegistro.setSWIFT(swift);
		VentanaPrincipal.datosRegistro.setFiscalYLegal(fiscalYLegal);
	}

	public void setVisible(boolean visibilidad) {
		this.frmAltaDeProveedor.setVisible(visibilidad);
	}
}
