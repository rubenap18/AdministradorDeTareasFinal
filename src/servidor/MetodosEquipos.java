package servidor;

public class MetodosEquipos {
    public static String crearEquipo(String[] comando) {
        if (comando.length != 2) return "ERROR - EL FORMATO ES INCORRECTO"; // Debe ser 3 porque matriz tiene 3 espacios (accion, nombre, rol)
            Datos.equipos[Datos.contadores[Constantes.EQUIPOS]][Constantes.EQUIPO_ID] = String.valueOf(Datos.contadores[Constantes.EQUIPOS]); // Convertir valor a String
            Datos.equipos[Datos.contadores[Constantes.EQUIPOS]][Constantes.EQUIPO_NOMBRE] = comando[Constantes.EQUIPO_NOMBRE];
            Datos.contadores[Constantes.EQUIPOS]++;
            return "Equipo creado exitosamente.";
    }

    public static String listarEquipos() {
        String respuesta = "";
        for (int i = 0; i < Datos.equipos.length; i++) {
            if (Datos.equipos[i][Constantes.EQUIPO_ID] == null) {
                return "Aun no hay equipos registrados";
            }
            respuesta += "ID" + Datos.equipos[i][Constantes.EQUIPO_ID] + " | " + Datos.equipos[i][Constantes.PERFIL_NOMBRE] + "\n";
        }
        return respuesta;
    }
}
