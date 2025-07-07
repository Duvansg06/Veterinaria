package procesos;

import java.util.List;

import dao.MascotaDao;
import vo.MascotaVo;

public abstract class ProcesosMascota extends ProcesosGenerales {

    private MascotaDao mascotaDao = new MascotaDao();

    @Override
    public String registrar(Object obj) {
        MascotaVo mascota = (MascotaVo) obj;
        return mascotaDao.registrarMascota(mascota);
    }

    @Override
    public MascotaVo consultar(String documentoDueno) {
        return mascotaDao.consultarMascota(documentoDueno);
    }
    @Override
    public String actualizar(Object obj) {
        MascotaVo mascota = (MascotaVo) obj;
        return mascotaDao.actualizarMascota(mascota);
    }
    
    public String eliminarPorDuenoYNombre(String documentoPersona, String nombreMasc) {
        return mascotaDao.eliminarMascotaPorDuenoYNombre(documentoPersona, nombreMasc);
    }


    @Override
    public List<MascotaVo> consultarLista() {
        return mascotaDao.consultarListaMasc();
    }
   
}
