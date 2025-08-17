package servidor;

public class MetodosPerfiles {
    public static String crearPerfil(String[] comando) {
        if (comando.length != 3) return "ERROR - EL FORMATO ES INCORRECTO"; // Debe ser 3 porque matriz tiene 3 espacios (accion, nombre, rol)
            Datos.perfiles[Datos.contadores[Constantes.PERFILES]][Constantes.PERFIL_ID] = String.valueOf(Datos.contadores[Constantes.PERFILES]); // Convertir valor a String
            Datos.perfiles[Datos.contadores[Constantes.PERFILES]][Constantes.PERFIL_NOMBRE] = comando[Constantes.PERFIL_NOMBRE];
            Datos.perfiles[Datos.contadores[Constantes.PERFILES]][Constantes.PERFIL_ROL] = comando[Constantes.PERFIL_ROL];
            Datos.contadores[Constantes.PERFILES]++;
            return "Perfil creado exitosamente.";
    }
    public static String listarPerfiles() {
        String respuesta = "";
        for (int i = 0; i < Datos.perfiles.length; i++) {
            if (Datos.perfiles[i][Constantes.PERFIL_ID] == null) {
                return "No hay perfiles registrados aun."; 
            }
            respuesta += "ID: " + Datos.perfiles[i][Constantes.PERFIL_ID] + " | " + Datos.perfiles[i][Constantes.PERFIL_NOMBRE] + "\n";
        }
        return respuesta;
    }
}
