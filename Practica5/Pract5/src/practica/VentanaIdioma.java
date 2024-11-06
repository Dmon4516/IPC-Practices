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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class VentanaIdioma {

	private JFrame frame;
	private String locale;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIdioma window = new VentanaIdioma();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaIdioma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"English", "Espa\u00F1ol"}));
		comboBox.setBounds(78, 79, 95, 20);
		panel.add(comboBox);
		
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnOK();
				
			}
		});
		btnOk.setBounds(78, 124, 89, 23);
		panel.add(btnOk);
		
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cierraPrograma();
            }
        });
	}

	protected void cierraPrograma() {
		// TODO Auto-generated method stub
		frame.dispose();
	}

	protected void btnOK() {
		//System.out.println(comboBox.getSelectedItem());
		
		if (comboBox.getSelectedItem().equals("English")) {
			locale = "en";
			VentanaPrincipal.localizacion = new Locale.Builder().setLanguage("en").setRegion("US").build();
			VentanaPrincipal.mensajes = ResourceBundle.getBundle("local/Local", VentanaPrincipal.localizacion);
		} else {
			locale = "es";
			VentanaPrincipal.localizacion = new Locale.Builder().setLanguage("es").setRegion("ES").build();
			VentanaPrincipal.mensajes = ResourceBundle.getBundle("local/Local", VentanaPrincipal.localizacion);
		}
		System.out.println(locale);
	}

	public void setVisible(boolean visibilidad) {
		this.frame.setVisible(visibilidad);
	}
}
