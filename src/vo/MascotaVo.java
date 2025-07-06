package vo;

public class MascotaVo {

	private String nombreMasc;
	private String raza;
	private String sexo;
	private String documentoPersona;
	private String nombreDueno;
	private int id;
	
	
	
	public String getNombreDueno() {
		return nombreDueno;
	}
	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreMasc() {
		return nombreMasc;
	}
	public void setNombreMasc(String nombreMasc) {
		this.nombreMasc = nombreMasc;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDocumentoPersona() {
		return documentoPersona;
	}
	public void setDocumentoPersona(String documentoPersona) {
		this.documentoPersona = documentoPersona;
	}
	}
