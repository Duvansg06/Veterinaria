package principal;

import conexion.Conexion;
import dao.MascotaDao;
import dao.PersonaDao;
import ventanas.VentanaMascotas;
import ventanas.VentanaPersonas;
import ventanas.VentanaPrincipal;


public class Relaciones {

	public void iniciar() {
		
		VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
		VentanaPersonas miVentanaPersonas = new VentanaPersonas();
		VentanaMascotas miVentanaMascotas = new VentanaMascotas();
		
		
		PersonaDao miPersonaDao = new PersonaDao();
		MascotaDao miMascotaDao = new MascotaDao();
		Coordinador miCoordinador = new Coordinador();
		
		miCoordinador.setPersonaDao(miPersonaDao);
		miCoordinador.setMascotaDao(miMascotaDao);
		
		
		miVentanaPrincipal.setCoordinador(miCoordinador);
		miVentanaPersonas.setCoordinador(miCoordinador);
		miVentanaMascotas.setCoordinador(miCoordinador);
		
//		miPersonaDao.setCoordinador(miCoordinador);
	
		
		miCoordinador.setVentanaPersonas(miVentanaPersonas);
		miCoordinador.setVentanaMascotas(miVentanaMascotas);
		
		miVentanaPrincipal.setVisible(true);

	}

}
