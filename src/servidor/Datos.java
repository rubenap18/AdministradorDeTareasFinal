package servidor;

public final class Datos {
    // Almacenamiento de datos en servidor con matrices
    public static String[] comando;
    public static int[] contadores = new int[4];  // Índices: 0=perfiles, 1=equipos, 2=proyectos, 3=tareas
    public static String[][] perfiles = new String[100][3];  // ID, Nombre, Rol
    public static String[][] equipos = new String[50][2];    // ID, Nombre
    public static String[][] proyectos = new String[50][4];  // ID, Nombre, EquipoID, Estado
    public static String[][] tareas = new String[500][6];   // ID, ProyectoID, Título, Estado, AsignadoA, Porcentaje
}
