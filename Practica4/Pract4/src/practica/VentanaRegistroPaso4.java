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
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Interfaz tipo formulario que permite introducir los datos necesarios para
 * darse de alta esta clase en concreto representa la primera pagina, en la que
 * se introducen los datos generales
 * 
 * @authors Luis Seti?n, Victor Descalzo, David Edmundo Montenegro, Oscar
 *          Entrecanales
 * @version Octubre 2024
 */
public class VentanaRegistroPaso4 {

	private JFrame frmAltaDeProveedor;
	private JLabel lblNombre;
	private JLabel lblNIF;
	private JLabel lblDireccion;
	private JLabel lblTelefono;
	private JLabel lblCorreo;
	private JLabel lblNombreContacto;
	private JLabel lblBancaria;
	private JLabel lblSucursal;
	private JLabel lblSWIFT;
	private JLabel lblFiscalyLegal;

	/**
	 * metodo principal de la clase
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroPaso4 window = new VentanaRegistroPaso4();
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
	public VentanaRegistroPaso4() {
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
				Toolkit.getDefaultToolkit().getImage(VentanaRegistroPaso4.class.getResource("/imagenes/icono.png")));
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
		btnSiguiente.setText("Finalizar >");
		lblTitulo.setText("Paso 4 de 4: Comprueba todos tus datos:");

		// creamos el panel principal del formulario junto a los
		// elementos y campos correspondientes a esta pagina
		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBackground(Color.WHITE);
		frmAltaDeProveedor.getContentPane().add(pnlFormulario, BorderLayout.CENTER);
		pnlFormulario.setLayout(new GridLayout(10, 1, 10, 20));
		pnlFormulario.setBorder(new EmptyBorder(60, 60, 60, 60));
		
		lblNombre = new JLabel("");
		pnlFormulario.add(lblNombre);
		
		lblNIF = new JLabel("");
		pnlFormulario.add(lblNIF);
		
		lblDireccion = new JLabel("");
		pnlFormulario.add(lblDireccion);
		
		lblTelefono = new JLabel("");
		pnlFormulario.add(lblTelefono);
		
		lblCorreo = new JLabel("");
		pnlFormulario.add(lblCorreo);
		
		lblNombreContacto = new JLabel("");
		pnlFormulario.add(lblNombreContacto);
		
		lblBancaria = new JLabel("");
		pnlFormulario.add(lblBancaria);
		
		lblSucursal = new JLabel("");
		pnlFormulario.add(lblSucursal);
		
		lblSWIFT = new JLabel("");
		pnlFormulario.add(lblSWIFT);
		
		lblFiscalyLegal = new JLabel("");
		pnlFormulario.add(lblFiscalyLegal);

	}

	private void btnAnteriorMouseClicked() {
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

	private void btnSiguienteMouseClicked() {
		// eliminamos las ventanas del formulario, pues hemos finalizado
		VentanaPrincipal.ventanasRegistro.clear();
		this.setVisible(false);
	}

	public void setVisible(boolean visibilidad) {
		this.frmAltaDeProveedor.setVisible(visibilidad);
	}
	
	public void setDatos(Registro datos) {
		lblNombre.setText("Nombre o raz�n social del proveedor: " + datos.getNombre());
		lblNIF.setText("N�mero de identificaci�n fiscal: " + datos.getNif());
		lblDireccion.setText("Direcci�n fiscal y comercial: " + datos.getDireccion());
		lblTelefono.setText("N�mero de tel�fono: " + datos.getTelefono());
		lblCorreo.setText("Correo electr�nico de contacto: " + datos.getCorreo());
		lblNombreContacto.setText("Nombre de la persona de contacto: " + datos.getNombreContacto());
		lblBancaria.setText("Cuenta bancaria (IBAN): " + datos.getIBAN());
		lblSucursal.setText("Nombre del banco y sucursal: " + datos.getBanco());
		lblSWIFT.setText("C�digo SWIFT/BIC: " + datos.getSWIFT());
		lblFiscalyLegal.setText("Informaci�n fiscal y legal: " + datos.getFiscalYLegal());
	}
}
