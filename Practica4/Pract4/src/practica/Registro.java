package practica;

public class Registro {
	// Ventana Registro Paso 1
	private String nombre;
	private String nif;
	private String direccion;
	private int telefono;
	private String correo;
	private String nombreContacto;
	// Ventana Registro Paso 2
	private String iban;
	private String banco;
	private String swift;
	private String fiscalYLegal;
	//TODO: Ir añadiendo atributos (para los datos de la consulta de la ventana final)
	
	public Registro(String nombre, String nif, String direccion,
			int telefono, String correo, String nombreContacto, 
			String iban, String banco, String swift, String fiscalYLegal) {
		this.nombre = nombre;
		this.nif = nif;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.nombreContacto = nombreContacto;
		this.iban = iban;
		this.banco = banco;
		this.swift = swift;
		this.fiscalYLegal = fiscalYLegal;
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
	
	public String getIBAN() {
		return iban;
	}
	
	public void setIBAN(String iban) {
		this.iban = iban;
	}
	
	public String getBanco() {
		return banco;
	}
	
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public String getSWIFT() {
		return swift;
	}
	
	public void setSWIFT(String swift) {
		this.swift = swift;
	}
	
	public String getFiscalYLegal() {
		return fiscalYLegal;
	}
	
	public void setFiscalYLegal(String fiscalYLegal) {
		this.fiscalYLegal = fiscalYLegal;
	}
	
}
