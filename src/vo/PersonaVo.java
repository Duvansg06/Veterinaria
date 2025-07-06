package vo;

public class PersonaVo {


	private String telefono;
	private String documento;
	private String nombre;

	

	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		return "PersonaVo [documento=" + documento + ", telefono=" + telefono + ", nombre=" + nombre + ", raza="  + "]";
	}
	
	
	
	
	

}
