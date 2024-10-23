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
import java.util.Map;


/**
 * Interfaz que permite acceder a las distintas secciones del programa
 * @authors Luis Setién, Victor Descalzo, David Edmundo Montenegro, Oscar Entrecanales
 * @version Octubre 2024
 */
public class VentanaPrincipal {
	
	private JFrame frmPrincipal;
	
	// atributos globales para el alta de proveedor
	public static Registro datosRegistro;
	public static Map<Integer, Object> ventanasRegistro = new HashMap<>();
	
	
	/**
	 * metodo principal de la clase
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmPrincipal.setVisible(true);
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
	 * metodo que se encarga de inicializar el contenido de la ventana
	 */
	private void initialize() {
		
		// inicializamos y establecemos los atributos basicos de la ventana
		frmPrincipal = new JFrame();
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imagenes/icono.png")));
		frmPrincipal.setTitle("Administración Perfumería");
		frmPrincipal.setResizable(false);
		frmPrincipal.setBounds(100, 100, 400, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		// creamos el panel principal, estableciendo margenes y espaciado
		JPanel gridPanel = new JPanel(new GridLayout(5, 3, 10, 10));
		frmPrincipal.getContentPane().add(gridPanel, BorderLayout.CENTER);
		gridPanel.setBorder(new EmptyBorder(40, 60, 40, 60));
		
		
		// creamos los botones junto a sus manejadores de eventos
		// para lograr que al pulsarlos se produzca la accion relevante
		JButton btnAltaProveedor = new JButton("Alta de proveedor");
		btnAltaProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAltaProveedorMouseClicked();
			}
		});
		gridPanel.add(btnAltaProveedor);
		
		JButton btnConsultas = new JButton("Consulta de compraventas");
		btnConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConsultasMouseClicked();
			}
		});
		
		JButton btnInforme = new JButton("Informe administrativo");
		btnInforme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnInformeMouseClicked();
			}
		});
		gridPanel.add(btnInforme);
		gridPanel.add(btnConsultas);
		
		JPanel panel = new JPanel();
		gridPanel.add(panel);
		
		JButton btnSalir = new JButton("Salir");
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
			String mensajeError = "Este formulario ya se encuentra abierto";
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
		ventana.setVisible();
	}
	
	/**
	 * metodo que se encarga de la accion realizada al pulsar el boton de informe administrativo
	 */
	private void btnInformeMouseClicked() {
		VentanaInforme ventana = new VentanaInforme();
		ventana.setVisible();
	}
	
	/**
	 * metodo que se encarga de realizar un cierre seguro del programa
	 */
	private void cierraPrograma() {
		if (ventanasRegistro.isEmpty() == false) {
        	String mensajeError = "Cierre todas las ventanas antes de salir del programa";
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
        	System.exit(0);
        }
	}
}
