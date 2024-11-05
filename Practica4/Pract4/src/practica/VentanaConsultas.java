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

@SuppressWarnings("serial")
public class VentanaConsultas extends JFrame {

	private JTable tabla;
	private ModeloTablaTransacciones modeloTabla;

	public VentanaConsultas() {
		initialize();
	}

	private void initialize() {
		// Configuración básica de la ventana
		setTitle("Consulta de Compraventas");
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

		// Desactivar el redimensionamiento automático para permitir scroll horizontal
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

		// Ajustar el tamaño de la ventana al contenido
		pack();
	}
	

}

@SuppressWarnings("serial")
class ModeloTablaTransacciones extends AbstractTableModel {
	private String[] columnas = { "Fecha de la transacción", "Tipo de operación", "Nombre del producto", "Cantidad",
			"Total de la operación", "Nombre del proveedor o cliente" };

	private ArrayList<Transaccion> transacciones;

	public ModeloTablaTransacciones() {
		transacciones = new ArrayList<>();
		cargarDatosFicticios();
	}

	private void cargarDatosFicticios() {
		// Datos de ejemplo
		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 15), "Compra", "Chanel N°5", 10, 150.00,
				"Perfumes Luxury S.L."));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 16), "Venta", "Light Blue D&G", 2, 180.00, "María González"));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 17), "Compra", "La Vie Est Belle", 15, 120.00,
				"Lancôme Distribuciones"));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 18), "Venta", "Chanel N°5", 1, 180.00, "Juan Pérez"));

		transacciones.add(new Transaccion(LocalDate.of(2024, 3, 19), "Venta", "La Vie Est Belle", 3, 100.00, "Ana Martínez"));
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
            case 4: return Double.class;   // Total como Double
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
			return transaccion.getTotal();
		case 5:
			return transaccion.getNombreProveedorCliente();
		default:
			return null;
		}
	}
	

}

class Transaccion {

	private LocalDate fecha;
	private String tipoOperacion;
	private String nombreProducto;
	private int cantidad;
	private double precio;
	private double total;
	private String nombreProveedorCliente;

	public Transaccion(LocalDate fecha, String tipoOperacion, String nombreProducto, int cantidad, double precio,
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
		return tipoOperacion;
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
