package practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPrincipal {

	private JFrame frmPrincipal;

	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Administración Perfumeria");
		frmPrincipal.setResizable(false);
		frmPrincipal.setBounds(100, 100, 400, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAltaProveedor = new JButton("Alta de proveedor");
		
		JButton btnConsultas = new JButton("Consulta de compraventas");
		
		JButton btnInforme = new JButton("Informe administrativo");
		
		JButton btnSalir = new JButton("Salir");
		frmPrincipal.getContentPane().setLayout(new GridLayout(5, 3, 15, 10));
		frmPrincipal.getContentPane().add(btnAltaProveedor);
		frmPrincipal.getContentPane().add(btnConsultas);
		frmPrincipal.getContentPane().add(btnInforme);
		
		JPanel panel = new JPanel();
		frmPrincipal.getContentPane().add(panel);
		frmPrincipal.getContentPane().add(btnSalir);
		
	}
}
