package servidor;

public final class Constantes {
    private Constantes(){}//evitar que la clase se instancie
    public static final int PERFILES = 0, PERFIL_ID = 0, PERFIL_NOMBRE = 1, PERFIL_ROL = 2;
    public static final int EQUIPOS = 1, EQUIPO_ID = 0, EQUIPO_NOMBRE = 1;
    public static final int PROYECTOS = 2, PROYECTO_ID = 0, PROYECTO_NOMBRE = 1, PROYECTO_ESTADO = 2;
    public static final int TAREAS = 3, TAREA_ID = 0, TAREA_PROYECTO_ID = 1, TAREA_TITULO = 2, TAREA_ESTADO = 3, TAREA_PERFIL_ASIGNADO = 4, TAREA_PORCENTAJE = 0;
}
