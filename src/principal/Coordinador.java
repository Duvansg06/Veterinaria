package principal;

import java.util.List;

import dao.MascotaDao;
import dao.PersonaDao;
import ventanas.VentanaMascotas;
import ventanas.VentanaPersonas;
import vo.MascotaVo;
import vo.PersonaVo;


public class Coordinador {
	
	
	private VentanaPersonas ventanaPersonas;
	private VentanaMascotas ventanaMascotas;
	private PersonaDao miPersonaDao;
	private MascotaDao miMascotaDao;
	
	
	
	
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

//	public boolean eliminarPersona(PersonaVo personaObtenida) {
//		
//		return miPersonaDao.eliminarPersona(personaObtenida);
//	}

	public List<PersonaVo> consultarListaPersonas() {
		
		return miPersonaDao.consultarListaPersonas();
	}

	public String registrarMascota(MascotaVo mascota) {
		
		return miMascotaDao.registrarMascota(mascota);
	}

	public MascotaVo consultarMascota(String documentoMasc) {
		
		return miMascotaDao.consultarMascota(documentoMasc);
	}

	public void setMascotaDao(MascotaDao miMascotaDao2) {
		this.miMascotaDao = miMascotaDao2;
		
	}

	public String actualizarMascota(MascotaVo mascota) {
		return miMascotaDao.actualizarMascota(mascota);
	}



	public String eliminarPersonaConMascotas(String documento) {
		
		return miPersonaDao.eliminarPersonaConMascotas(documento);
	}

	public List<MascotaVo> consultarListaMasc() {
		
		return miMascotaDao.consultarListaMasc();
	}

	public String eliminarMascotaPorDuenoYNombre(String documentoPersona, String nombreMasc) {
		return miMascotaDao.eliminarMascotaPorDuenoYNombre(documentoPersona, nombreMasc);
	}

	
	
	


	



	

}
