package procesos;

public abstract class ProcesosGenerales {

    public abstract String registrar(Object obj);
    public abstract Object consultar(String id);
    public abstract String actualizar(Object obj);
    public abstract String eliminar(String id);
    public abstract java.util.List<?> consultarLista();
}

