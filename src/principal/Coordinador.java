package principal;

import java.util.List;

import dao.PersonaDao;
import ventanas.VentanaMascotas;
import ventanas.VentanaPersonas;
import vo.PersonaVo;


public class Coordinador {
	
	
	private VentanaPersonas ventanaPersonas;
	private VentanaMascotas ventanaMascotas;
	private PersonaDao miPersonaDao;
	
	
	
	
	public void setVentanaPersonas(VentanaPersonas ventanaPersonas) {
		this.ventanaPersonas = ventanaPersonas;
	}

	public void mostrarVentanaPersona() {
		ventanaPersonas.setVisible(true);
		
	}

	
	public void setVentanaMascotas(VentanaMascotas ventanaMascotas) {
		this.ventanaMascotas = ventanaMascotas;
	}

	public void mostrarVentanaMascotas() {
		ventanaMascotas.setVisible(true);
		
	}

	public String registrarPersona(PersonaVo persona) {
		return miPersonaDao.registrarPersona(persona);
		
	}

	public void setPersonaDao(PersonaDao miPersonaDao2) {
		 this.miPersonaDao = miPersonaDao2;
		
	}

	public PersonaVo consultarPersona(String documento) {
		
		return miPersonaDao.consultarPorDocumento(documento);
	}

	public String actualizarPersona(PersonaVo persona) {
	
		return miPersonaDao.actualizarPersona(persona);
	}

	public boolean eliminarPersona(PersonaVo personaObtenida) {
		
		return miPersonaDao.eliminarPersona(personaObtenida);
	}

	public List<PersonaVo> consultarListaPersonas() {
		
		return miPersonaDao.consultarListaPersonas();
	}

	
	


	



	

}
