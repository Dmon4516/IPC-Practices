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


/**
 * Interfaz tipo formulario que permite introducir los datos necesarios para darse de alta
 *     esta clase en concreto representa la primera pagina, en la que se introducen los datos generales
 * @authors Luis Setién, Victor Descalzo, David Edmundo Montenegro, Oscar Entrecanales
 * @version Octubre 2024
 */
public class VentanaRegistroPaso2 {
	
	private JFrame frmAltaDeProveedor;
	
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
		frmAltaDeProveedor.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistroPaso2.class.getResource("/imagenes/icono.png")));
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
                    "¿Seguro que quieres cerrar el formulario? Los datos se perderán",
                    "Confirmar acción",
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
		lblTitulo.setText("Paso 2 de 4: Introduce informe administrativo:");
		
		// creamos el panel principal del formulario junto a los
		// elementos y campos correspondientes a esta pagina
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmAltaDeProveedor.getContentPane().add(panel, BorderLayout.CENTER);
		
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

	public void setVisible(boolean visibilidad) {
		this.frmAltaDeProveedor.setVisible(visibilidad);
	}
}
