package procesos;

import java.util.List;

import dao.PersonaDao;
import vo.PersonaVo;

public class ProcesosPersona extends ProcesosGenerales {

    private PersonaDao personaDao = new PersonaDao();

    @Override
    public String registrar(Object obj) {
        PersonaVo persona = (PersonaVo) obj;
        return personaDao.registrarPersona(persona);
    }

    @Override
    public PersonaVo consultar(String documento) {
        return personaDao.consultarPorDocumento(documento);
    }

	@Override
	public String actualizar(Object obj) {
		  PersonaVo persona = (PersonaVo) obj;
		return personaDao.actualizarPersona(persona);
	}

	@Override
	public String eliminar(String id) {
		
		return personaDao.eliminarPersonaConMascotas(id);
	}

	@Override
	public List<?> consultarLista() {
		
		return personaDao.consultarListaPersonas();
	}
}
