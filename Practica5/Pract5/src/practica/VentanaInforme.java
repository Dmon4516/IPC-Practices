package practica;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.DefaultComboBoxModel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class VentanaInforme {

	private JFrame frmInforme;
	private String portapapeles = "";
	private JTextPane tpEditor;

	// variable para detectar si se guardo la ultima version del documento
	private boolean guardado = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInforme window = new VentanaInforme();
					window.frmInforme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInforme() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInforme = new JFrame();
		frmInforme.setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imagenes/icono.png")));
		frmInforme.setTitle(VentanaPrincipal.mensajes.getString("informe_titulo"));
		frmInforme.setResizable(true);
		frmInforme.setBounds(100, 100, 760, 560);
		frmInforme.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		VentanaPrincipal.ventanaInforme = true;
		// TODO: listener para que al salir, se muestre un dialogo de confirmacion

		JMenuBar menuBar = new JMenuBar();
		frmInforme.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu(VentanaPrincipal.mensajes.getString("informe_archivo"));
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_nuevo"));
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mntmNuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nuevoActionPerformed();
			}
			
		});
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_abrir"));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_guardar"));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_guardar_como"));
		mnArchivo.add(mntmGuardarComo);

		JMenuItem mntmImprimir = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_imprimir"));
		mnArchivo.addSeparator();
		mnArchivo.add(mntmImprimir);

		JMenuItem mntmSalir = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_salir"));
		mnArchivo.addSeparator();
		mnArchivo.add(mntmSalir);

		JMenu mnEdicion = new JMenu(VentanaPrincipal.mensajes.getString("informe_edicion"));
		menuBar.add(mnEdicion);

		JMenuItem mntmCortar = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_cortar"));
		mntmCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mntmCortar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cortarActionPerformed();
			}
			
		});
		mnEdicion.add(mntmCortar);

		JMenuItem mntmCopiar = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_copiar"));
		mntmCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mntmCopiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				copiarActionPerformed();
			}
			
		});
		mnEdicion.add(mntmCopiar);

		JMenuItem mntmPegar = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_pegar"));
		mntmPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		mntmPegar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pegarActionPerformed();
			}
			
		});
		mnEdicion.add(mntmPegar);

		JMenuItem mntmBuscar = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_buscar"));
		mnEdicion.addSeparator();
		mnEdicion.add(mntmBuscar);

		JMenuItem mntmReemplazar = new JMenuItem(VentanaPrincipal.mensajes.getString("informe_reemplazar"));
		mnEdicion.add(mntmReemplazar);

		JMenu mnFormato = new JMenu(VentanaPrincipal.mensajes.getString("informe_formato"));
		menuBar.add(mnFormato);

		JMenu mnAlineacion = new JMenu(VentanaPrincipal.mensajes.getString("informe_alineacion"));
		mnFormato.add(mnAlineacion);

		JRadioButtonMenuItem rdbtnmntmCentrada = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_centrada"));
		mnAlineacion.add(rdbtnmntmCentrada);

		JRadioButtonMenuItem rdbtnmntmIzquierda = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_izquierda"));
		mnAlineacion.add(rdbtnmntmIzquierda);

		JRadioButtonMenuItem rdbtnmntmDerecha = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_derecha"));
		mnAlineacion.add(rdbtnmntmDerecha);

		JMenu mnFuente = new JMenu(VentanaPrincipal.mensajes.getString("informe_fuente"));
		mnFormato.add(mnFuente);

		JRadioButtonMenuItem rdbtnmntmArial = new JRadioButtonMenuItem("Arial");
		mnFuente.add(rdbtnmntmArial);

		JRadioButtonMenuItem rdbtnmntmCalibri = new JRadioButtonMenuItem("Calibri");
		mnFuente.add(rdbtnmntmCalibri);

		JRadioButtonMenuItem rdbtnmntmCourierNew = new JRadioButtonMenuItem("Courier New");
		mnFuente.add(rdbtnmntmCourierNew);

		JRadioButtonMenuItem rdbtnmntmHelvetica = new JRadioButtonMenuItem("Helvetica");
		mnFuente.add(rdbtnmntmHelvetica);

		JRadioButtonMenuItem rdbtnmntmTahoma = new JRadioButtonMenuItem("Tahoma");
		mnFuente.add(rdbtnmntmTahoma);

		JRadioButtonMenuItem rdbtnmntmTimesNewRoman = new JRadioButtonMenuItem("Times New Roman");
		mnFuente.add(rdbtnmntmTimesNewRoman);

		JMenu mnTamano = new JMenu(VentanaPrincipal.mensajes.getString("informe_tamanio"));
		mnFormato.add(mnTamano);

		JRadioButtonMenuItem rdbtnmntm10 = new JRadioButtonMenuItem("10");
		mnTamano.add(rdbtnmntm10);

		JRadioButtonMenuItem rdbtnmntm12 = new JRadioButtonMenuItem("12");
		mnTamano.add(rdbtnmntm12);

		JRadioButtonMenuItem rdbtnmntm14 = new JRadioButtonMenuItem("14");
		mnTamano.add(rdbtnmntm14);

		JRadioButtonMenuItem rdbtnmntm18 = new JRadioButtonMenuItem("18");
		mnTamano.add(rdbtnmntm18);

		JRadioButtonMenuItem rdbtnmntm24 = new JRadioButtonMenuItem("24");
		mnTamano.add(rdbtnmntm24);

		JRadioButtonMenuItem rdbtnmntm36 = new JRadioButtonMenuItem("36");
		mnTamano.add(rdbtnmntm36);

		JMenu mnColor = new JMenu(VentanaPrincipal.mensajes.getString("informe_color"));
		mnFormato.add(mnColor);

		JRadioButtonMenuItem rdbtnmntmNegro = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_negro"));
		mnColor.add(rdbtnmntmNegro);

		JRadioButtonMenuItem rdbtnmntmGris = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_gris"));
		mnColor.add(rdbtnmntmGris);

		JRadioButtonMenuItem rdbtnmntmBlanco = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_blanco"));
		mnColor.add(rdbtnmntmBlanco);

		JRadioButtonMenuItem rdbtnmntmRojo = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_rojo"));
		mnColor.add(rdbtnmntmRojo);

		JRadioButtonMenuItem rdbtnmntmVerde = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_verde"));
		mnColor.add(rdbtnmntmVerde);

		JRadioButtonMenuItem rdbtnmntmAzul = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_azul"));
		mnColor.add(rdbtnmntmAzul);

		JMenu mnEstilo = new JMenu(VentanaPrincipal.mensajes.getString("informe_estilo"));
		mnFormato.add(mnEstilo);

		JCheckBoxMenuItem chckbxmntmNegrita = new JCheckBoxMenuItem(VentanaPrincipal.mensajes.getString("informe_negrita"));
		mnEstilo.add(chckbxmntmNegrita);

		JCheckBoxMenuItem chckbxmntmCursiva = new JCheckBoxMenuItem(VentanaPrincipal.mensajes.getString("informe_cursiva"));
		mnEstilo.add(chckbxmntmCursiva);

		JCheckBoxMenuItem chckbxmntmSubrayado = new JCheckBoxMenuItem(VentanaPrincipal.mensajes.getString("informe_subrayado"));
		mnEstilo.add(chckbxmntmSubrayado);

		JToolBar tbBarraHerramientas = new JToolBar();
		frmInforme.getContentPane().add(tbBarraHerramientas, BorderLayout.NORTH);

		JButton btnNuevo = new JButton("");
		btnNuevo.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoNuevo.gif")));
		tbBarraHerramientas.add(btnNuevo);

		JButton btnAbrir = new JButton("");
		btnAbrir.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoAbrir.gif")));
		tbBarraHerramientas.add(btnAbrir);

		JButton btnGuardar = new JButton("");
		btnGuardar.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoGuardar.gif")));
		tbBarraHerramientas.add(btnGuardar);

		JButton btnImprimir = new JButton("");
		btnImprimir.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoImprimir.gif")));
		tbBarraHerramientas.addSeparator();
		tbBarraHerramientas.add(btnImprimir);

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoBuscar.gif")));
		tbBarraHerramientas.add(btnBuscar);

		JButton btnCortar = new JButton("");
		btnCortar.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoCortar.gif")));
		tbBarraHerramientas.addSeparator();
		tbBarraHerramientas.add(btnCortar);

		JButton btnCopiar = new JButton("");
		btnCopiar.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoCopiar.gif")));
		tbBarraHerramientas.add(btnCopiar);

		JButton btnPegar = new JButton("");
		btnPegar.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoPegar.gif")));
		tbBarraHerramientas.add(btnPegar);

		JComboBox cbFuente = new JComboBox();
		cbFuente.setModel(new DefaultComboBoxModel(
				new String[] { "Arial", "Calibri", "Courier New", "Helvetica", "Tahoma", "Times New Roman" }));
		cbFuente.setMaximumSize(new Dimension(160, 28));
		tbBarraHerramientas.addSeparator();
		tbBarraHerramientas.add(cbFuente);

		JComboBox cbTamano = new JComboBox();
		cbTamano.setModel(new DefaultComboBoxModel(new String[] { "10", "12", "14", "18", "24", "36" }));
		cbTamano.setMaximumSize(new Dimension(60, 28));
		tbBarraHerramientas.add(cbTamano);

		JButton btnNegrita = new JButton("");
		btnNegrita.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoNegrita.gif")));
		tbBarraHerramientas.addSeparator();
		tbBarraHerramientas.add(btnNegrita);

		JButton btnCursiva = new JButton("");
		btnCursiva.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoCursiva.gif")));
		tbBarraHerramientas.add(btnCursiva);

		JButton btnSubrayado = new JButton("");
		btnSubrayado.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoSubrayado.gif")));
		tbBarraHerramientas.add(btnSubrayado);

		JButton btnColor = new JButton("");
		btnColor.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoColor.gif")));
		tbBarraHerramientas.add(btnColor);

		JPopupMenu mnpopColor = new JPopupMenu();
		JRadioButtonMenuItem rdbtnpopNegro = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_negro"));
		mnpopColor.add(rdbtnpopNegro);

		JRadioButtonMenuItem rdbtnpopGris = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_gris"));
		mnpopColor.add(rdbtnpopGris);

		JRadioButtonMenuItem rdbtnpopBlanco = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_blanco"));
		mnpopColor.add(rdbtnpopBlanco);

		JRadioButtonMenuItem rdbtnpopRojo = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_rojo"));
		mnpopColor.add(rdbtnpopRojo);

		JRadioButtonMenuItem rdbtnpopVerde = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_verde"));
		mnpopColor.add(rdbtnpopVerde);

		JRadioButtonMenuItem rdbtnpopAzul = new JRadioButtonMenuItem(VentanaPrincipal.mensajes.getString("informe_azul"));
		mnpopColor.add(rdbtnpopAzul);

		JButton btnTextoIzquierda = new JButton("");
		btnTextoIzquierda.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoTextoIzquierda.gif")));
		tbBarraHerramientas.addSeparator();
		tbBarraHerramientas.add(btnTextoIzquierda);

		JButton btnTextoCentrado = new JButton("");
		btnTextoCentrado.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoTextoCentrado.gif")));
		tbBarraHerramientas.add(btnTextoCentrado);

		JButton btnTextoDerecha = new JButton("");
		btnTextoDerecha.setIcon(new ImageIcon(VentanaInforme.class.getResource("/imagenes/iconoTextoDerecha.gif")));
		tbBarraHerramientas.add(btnTextoDerecha);

		tpEditor = new JTextPane();
		frmInforme.getContentPane().add(tpEditor, BorderLayout.CENTER);

		// hacer que las barras de scroll se muestren por defecto
		JScrollPane scrollPane = new JScrollPane(tpEditor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frmInforme.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// listener para el boton de colores se comporte como un menu desplegable
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnpopColor.show(btnColor, 0, btnColor.getHeight());
			}
		});

		// creamos un listener para confirmar el cierre de la ventana
		// del editor de informe, avisando si se pierden datos sin guardar
		frmInforme.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				// mostramos un mensaje segun queden cambios sin guardar o no
				if (guardado == true) {
					int confirmar = JOptionPane.showConfirmDialog(frmInforme,
							VentanaPrincipal.mensajes.getString("informe_cierre"), VentanaPrincipal.mensajes.getString("informe_confirmar"),
							JOptionPane.YES_NO_OPTION);

					if (confirmar == JOptionPane.YES_OPTION) {
						VentanaPrincipal.ventanaInforme = false;
						frmInforme.dispose();
					}
				} else {
					int confirmar = JOptionPane.showConfirmDialog(frmInforme,
							VentanaPrincipal.mensajes.getString("informe_recierre"),
							VentanaPrincipal.mensajes.getString("informe_confirmar"), JOptionPane.YES_NO_OPTION);

					if (confirmar == JOptionPane.YES_OPTION) {
						VentanaPrincipal.ventanaInforme = false;
						frmInforme.dispose();
					}
				}
			}
		});
	}

	public void setVisible(boolean visibilidad) {
		this.frmInforme.setVisible(visibilidad);
	}
	
	private void cortarActionPerformed() {
		portapapeles = tpEditor.getSelectedText();
		String texto = tpEditor.getText();
		String principio = texto.substring(0, tpEditor.getSelectionStart()); 
		String fin = texto.substring(tpEditor.getSelectionEnd());
		tpEditor.setText(principio + fin);
	}
	
	private void copiarActionPerformed() {
		portapapeles = tpEditor.getSelectedText();
	}
	
	private void pegarActionPerformed() {
		tpEditor.setText(tpEditor.getText() + portapapeles);
	}
	
	private void nuevoActionPerformed() {
		tpEditor.setText("");
	}

}
