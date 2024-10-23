package practica;

public class Registro {
	private String nombre;
	private String nif;
	private String direccion;
	private int telefono;
	private String correo;
	private String nombreContacto;
	
	public Registro(String nombre, String nif, String direccion,
			int telefono, String correo, String nombreContacto) {
		this.nombre = nombre;
		this.nif = nif;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.nombreContacto = nombreContacto;
	}

	public Registro() {
		// 
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	
	
}
