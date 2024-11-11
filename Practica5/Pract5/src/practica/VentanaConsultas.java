package practica;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class VentanaConsultas extends JFrame {

	private JTable tabla;
	private ModeloTablaTransacciones modeloTabla;
	public ResourceBundle idioma;

	public VentanaConsultas() {
		initialize();
	}

	private void initialize() {
		// Configuraci�n b�sica de la ventana
		this.idioma = VentanaPrincipal.mensajes;
		setTitle(idioma.getString("consultas_titulo"));
		JPanel panelPrincipal = new JPanel(new BorderLayout());

		// Crear el modelo y la tabla
		modeloTabla = new ModeloTablaTransacciones();
		tabla = new JTable(modeloTabla);

		

		// Establecer un ancho preferido para cada columna
		tabla.getColumnModel().getColumn(0).setPreferredWidth(120); // Fecha
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100); // Tipo
		tabla.getColumnModel().getColumn(2).setPreferredWidth(150); // Producto
		tabla.getColumnModel().getColumn(3).setPreferredWidth(70); // Cantidad
		tabla.getColumnModel().getColumn(4).setPreferredWidth(120); // Total
		tabla.getColumnModel().getColumn(5).setPreferredWidth(180); // Proveedor/Cliente

		// Desactivar el redimensionamiento autom�tico para permitir scroll horizontal
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setFillsViewportHeight(false);
		
		// Configurar la tabla
		tabla.setFillsViewportHeight(true);
		tabla.setAutoCreateRowSorter(true);

		// Crear el ScrollPane
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Para scrollbar horizontal

		// Agregar margen alrededor de la tabla
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
		
		// Agregar el panel principal a la ventana
		setContentPane(panelPrincipal);
		pack();
	}
	

}

@SuppressWarnings("serial")
 class ModeloTablaTransacciones extends AbstractTableModel {
	private String[] columnas = {VentanaPrincipal.mensajes.getString("consultas_fecha"),
			VentanaPrincipal.mensajes.getString("consultas_tipo_operacion"),
			VentanaPrincipal.mensajes.getString("consultas_nombre_producto"),
			VentanaPrincipal.mensajes.getString("consultas_cantidad"),
			VentanaPrincipal.mensajes.getString("consultas_total_operacion"), 
			VentanaPrincipal.mensajes.getString("consultas_nombre_titular")};

	private ArrayList<Transaccion> transacciones;

	public ModeloTablaTransacciones() {
		transacciones = new ArrayList<>();
		cargarDatosFicticios();
	}

	private void cargarDatosFicticios() {
		
		// Datos de ejemplo
		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 15), 1, "Chanel N5", 10, 150.00,
				"Perfumes Luxury S.L."));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 16), 2, "Light Blue D&G", 2, 180.00, "Maria Gonzalez"));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 17), 1, "La Vie Est Belle", 15, 120.00,
				"Lancme Distribuciones"));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 18), 2, "Chanel N5", 1, 180.00, "Juan Perez"));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 19), 2, "La Vie Est Belle", 3, 100.00, "Ana Martinez"));
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return transacciones.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnas[columnIndex];
	}
	
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return LocalDate.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return Integer.class;  // Cantidad como Integer
            case 4: return String.class;   
            case 5: return String.class;
            default: return Object.class;
        }
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Transaccion transaccion = transacciones.get(rowIndex);
		switch (columnIndex) {

		case 0:
			return transaccion.getFecha();
		case 1:
			return transaccion.getTipoOperacion();
		case 2:
			return transaccion.getNombreProducto();
		case 3:
			return transaccion.getCantidad();
		case 4:
			return String.format(" %s %.2f", VentanaPrincipal.mensajes.getString("moneda"), transaccion.getTotal());
		case 5:
			return transaccion.getNombreProveedorCliente();
		default:
			return null;
		}
	}
	

}

class Transaccion {

	private LocalDate fecha;
	private int tipoOperacion;
	private String nombreProducto;
	private int cantidad;
	private double precio;
	private double total;
	private String nombreProveedorCliente;

	public Transaccion(LocalDate fecha, int tipoOperacion, String nombreProducto, int cantidad, double precio,
			String nombreProveedorCliente) {

		this.fecha = fecha;
		this.tipoOperacion = tipoOperacion;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = cantidad * precio;
		this.nombreProveedorCliente = nombreProveedorCliente;

	}

	// Getters
	public LocalDate getFecha() {
		return fecha;
	}

	public String getTipoOperacion() {
		if (tipoOperacion == 1) {
			return VentanaPrincipal.mensajes.getString("consultas_compra");
		} else {
			return VentanaPrincipal.mensajes.getString("consultas_venta");
		}
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getTotal() {
		return total;
	}

	public String getNombreProveedorCliente() {
		return nombreProveedorCliente;
	}
	
	public double getPrecio() {
		return precio;
	}

}
