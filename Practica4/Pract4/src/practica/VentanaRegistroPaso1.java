package practica;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VentanaRegistroPaso1 {

	protected JFrame frmAltaDeProveedor;
	protected JLabel lblTitulo;
	protected JButton btnSiguiente;

	/**
	 * Launch the application.
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
	 * Create the application.
	 * @param registro 
	 */
	public VentanaRegistroPaso1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaDeProveedor = new JFrame();
		frmAltaDeProveedor.setResizable(false);
		frmAltaDeProveedor.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistroPaso1.class.getResource("/imagenes/icono.png")));
		frmAltaDeProveedor.setTitle("Alta de proveedor");
		frmAltaDeProveedor.setBounds(100, 100, 600, 550);
		frmAltaDeProveedor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmAltaDeProveedor.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel pnlTitulo = new JPanel();
		frmAltaDeProveedor.getContentPane().add(pnlTitulo, BorderLayout.NORTH);
		pnlTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlTitulo.setBorder(new EmptyBorder(12, 12, 12, 12));
		
		
		lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTitulo.add(lblTitulo);
		
		
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
		//btnAnterior.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAnterior.setPreferredSize(new Dimension(100, 30));
		pnlBotonera.add(btnAnterior);
		
		btnSiguiente = new JButton("Siguiente >");
		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSiguienteMouseClicked();
			}
		});
		//btnSiguiente.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnSiguiente.setPreferredSize(new Dimension(100, 30));
		pnlBotonera.add(btnSiguiente);
		
		/**
		 * PARTE ESPECIFICA A ESTE PASO DEL FORMULARIO
		 */
		
		lblTitulo.setText("Paso 1 de 4: Introduce datos generales:");
		
	}

	protected void btnAnteriorMouseClicked() {
		// TODO Auto-generated method stub
		
	}

	protected void btnSiguienteMouseClicked() {
		VentanaRegistroPaso2 ventana = new VentanaRegistroPaso2();
		this.setVisible(false);
		ventana.setVisible(true);
	}

	public void setVisible(boolean visibilidad) {
		this.frmAltaDeProveedor.setVisible(visibilidad);
	}

}
