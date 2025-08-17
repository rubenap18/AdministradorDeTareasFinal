package cliente;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        
        try (Socket socket = new Socket("localhost", 5000)){

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); // Lee datos enviados entre cliente y servidor
            out.flush(); //Enviar de una para que el servidor pueda crear sin problemas su OutputStream
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); //Envia datos al cliente
            Scanner entrada = new Scanner(System.in);

            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║                                    ║");
            System.out.println("║            BIENVENIDO A            ║");
            System.out.println("║     ADMINISTRADOR DE PROYECTOS     ║");
            System.out.println("║                                    ║");
            System.out.println("╚════════════════════════════════════╝");

            boolean salir = false;
            
            // Bucle while para menu con salir validado
            while (!salir) {
                System.out.println();
                System.out.println("┌───────────────────────────┐");
                System.out.println("│       Menú Principal      │");
                System.out.println("├───────────────────────────┤");
                System.out.println("│                           │");
                System.out.println("│       1. Perfiles         │");
                System.out.println("│                           │");
                System.out.println("│       2. Equipos          │");
                System.out.println("│                           │");
                System.out.println("│       3. Proyectos        │");
                System.out.println("│                           │");
                System.out.println("│       4. Tareas           │");
                System.out.println("│                           │");
                System.out.println("│       5. Reportes         │");
                System.out.println("│                           │");
                System.out.println("│       6. Salir            │");
                System.out.println("│                           │");
                System.out.println("└───────────────────────────┘");
                System.out.println();
                System.out.print("Ingresa una opción ▶ ");
                int opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {
                    case 1: menuPerfiles(out, in, entrada); break; // Reciben los parametros requeridos para funcionar entre cliente y servidor
                    //case 2: menuEquipos(out, in, entrada); break;
                    //case 3: menuProyectos(out, in, entrada); break;
                    //case 4: menuTareas(out, in, entrada); break;
                    //case 5: menuReportes(out, in, entrada); break;
                    case 6: 
                        String[] enviarSalida = {"SALIR"};
                        out.writeObject(enviarSalida);
                        out.flush();
                        salir = true; 
                        break;
                    //default: System.out.println("Opción no válida");
                }
            }
            // Cerrando conexión
            
            try {
                    System.out.println((String)in.readObject());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            out.close();
            in.close();
            socket.close();
            entrada.close();
            System.out.println("Gracias por utilizar el programa");

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    // ---------------------- FUNCION PARA PERFILES ----------------------
    public static void menuPerfiles(ObjectOutputStream out, ObjectInputStream in, Scanner entrada) throws IOException{
        
        System.out.println("┌───────────────────────────┐");
        System.out.println("│    Gestion de Perfiles    │");
        System.out.println("├───────────────────────────┤");
        System.out.println("│                           │");
        System.out.println("│     1. Crear Perfil       │");
        System.out.println("│                           │");
        System.out.println("│     2. Ver perfiles       │");
        System.out.println("│                           │");
        System.out.println("│     3. Menú principal     │");
        System.out.println("│                           │");
        System.out.println("└───────────────────────────┘");
        System.out.println();
        System.out.print("Ingresa una opción ▶ ");
        int opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre del perfil: ");
                String nombre = entrada.nextLine();
                System.out.print("Rol: ");
                String rol = entrada.nextLine();

                String[] datosAEnviar = {"CREAR_PERFIL",nombre,rol};

                //mandando datos al servidor
                out.writeObject(datosAEnviar);
                out.flush(); //Enjuaga el buffer haciendo que el socket mande inmediatamente los datos sin esperar a que el buffer se llene

                //imprimiendo lo recibido del servidor
                try {
                    String respuestaDelServidor = (String) in.readObject();
                    System.out.println(respuestaDelServidor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                String[] enviar = {"LISTAR_PERFILES"};
                out.writeObject(enviar);
                out.flush();

                try {
                    String respuestaDelServidor = (String) in.readObject();
                    System.out.println(respuestaDelServidor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                return;

            default:
                System.out.println("Opción no válida");
        }
    
    }
/*
    // ---------------------- FUNCION PARA EQUIPOS ----------------------
    static void menuEquipos(PrintWriter out, BufferedReader in, Scanner entrada) throws IOException {
        System.out.println("\n--- GESTIÓN DE EQUIPOS ---");
        System.out.println("1. Crear equipo");
        System.out.println("2. Listar equipos");
        System.out.println("3. Volver al menú principal");
        System.out.print("Opción: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre del equipo: ");
                String nombre = entrada.nextLine();
                out.println("CREAR_EQUIPO|" + nombre);
                System.out.println(in.readLine());
                break;

            case 2:
                out.println("LISTAR_EQUIPOS");
                String respuesta = in.readLine();

                if (respuesta.startsWith("ERROR")) {
                    System.out.println(respuesta.split("\\|")[1]);

                } else {
                    System.out.println("\n--- LISTA DE EQUIPOS ---");
                    String[] equipos = respuesta.split(";");

                    for (String equipo:equipos) { // Bucle for each para mostrar los datos del arreglo especifico
                        String[] datos = equipo.split(",");
                        System.out.println("ID: " + datos[0] + " | Nombre: " + datos[1]);
                    }
                }
                break;

            case 3:
                return;

            default:
                System.out.println("Opción no válida");
        }
    }
/*
    // ---------------------- FUNCION PARA PROYECTOS ----------------------
    static void menuProyectos(PrintWriter out, BufferedReader in, Scanner entrada) throws IOException {
        System.out.println("\n--- GESTIÓN DE PROYECTOS ---");
        System.out.println("1. Crear proyecto");
        System.out.println("2. Listar proyectos");
        System.out.println("3. Asignar equipo a proyecto");
        System.out.println("4. Volver al menú principal");
        System.out.print("Opción: ");
        int opcion = entrada.nextInt(); // Usuario digira opciones para que el switch case interactue
        entrada.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre del proyecto: ");
                String nombre = entrada.nextLine();
                
                out.println("CREAR_PROYECTO|" + nombre);
                System.out.println(in.readLine());
                break;

            case 2:
                out.println("LISTAR_PROYECTOS");
                String respuesta = in.readLine();

                if (respuesta.startsWith("ERROR")) {
                    System.out.println(respuesta.split("\\|")[1]);

                } else {
                    System.out.println("\n--- LISTA DE PROYECTOS ---");
                    String[] proyectos = respuesta.split(";");

                    for (String proyecto : proyectos) {
                        String[] datos = proyecto.split(","); // Cada que muestre los datos los separa por comas
                        System.out.println("ID: " + datos[0] + 
                                        " | Nombre: " + datos[1] + 
                                        " | Equipo: " + (datos[2].equals("null") ? "Sin asignar" : datos[2]) + 
                                        " | Estado: " + datos[3]);
                    }
                }
                break;

            case 3:
                System.out.print("ID del proyecto: ");
                int proyectoID = entrada.nextInt();

                System.out.print("ID del equipo: ");
                int equipoID = entrada.nextInt();
                entrada.nextLine();

                out.println("ASIGNAR_EQUIPO|" + proyectoID + "|" + equipoID); // Recibe los datos desde el servidor
                System.out.println(in.readLine());
                break;

            case 4:
                return;

            default:
                System.out.println("Opción no válida");
        }
    }

    // ---------------------- FUNCION PARA TAREAS ----------------------
    static void menuTareas(PrintWriter out, BufferedReader in, Scanner entrada) throws IOException {
        System.out.println("\n--- GESTIÓN DE TAREAS ---");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Cambiar estado de tarea");
        System.out.println("4. Volver al menú principal");
        System.out.print("Opción: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Título de la tarea: ");
                String titulo = entrada.nextLine();

                System.out.print("ID del proyecto: ");
                int proyectoID = entrada.nextInt();
                entrada.nextLine();

                out.println("CREAR_TAREA|" + proyectoID + "|" + titulo);
                System.out.println(in.readLine());
                break;

            case 2:
                out.println("LISTAR_TAREAS");
                String respuesta = in.readLine();

                if (respuesta.startsWith("ERROR")) {
                    System.out.println(respuesta.split("\\|")[1]);

                } else {
                    System.out.println("\n--- LISTA DE TAREAS ---");
                    String[] tareas = respuesta.split(";"); // Dividir el vector por ;
                    for (String tarea : tareas) {
                        String[] datos = tarea.split(","); // Dividir el vector por ,
                        System.out.println("ID: " + datos[0] + 
                                    " | Proyecto: " + datos[1] +
                                    " | Título: " + datos[2] + 
                                    " | Estado: " + datos[3] +
                                    " | Asignado a: " + datos[4] +
                                    " | Avance: " + datos[5] + "%");
                    }
                }
                break;

            case 3:
                System.out.print("ID de la tarea: ");
                int tareaID = entrada.nextInt();
                entrada.nextLine();

                System.out.println("1. Pendiente");
                System.out.println("2. En progreso");
                System.out.println("3. Completado");
                System.out.print("Nuevo estado: ");
                int estado = entrada.nextInt();
                entrada.nextLine();

                out.println("CAMBIAR_ESTADO_TAREA|" + tareaID + "|" + estado); // Recibe los datos desde el servidor
                System.out.println(in.readLine());
                break;

            case 4:
                return;

            default:
                System.out.println("Opción no válida");
        }
    }

    // ---------------------- FUNCION PARA REPORTES ----------------------
    static void menuReportes(PrintWriter out, BufferedReader in, Scanner entrada) throws IOException {
        System.out.println("\n--- REPORTES ---");
        System.out.println("1. Avance de proyecto");
        System.out.println("2. Tareas por estado");
        System.out.println("3. Volver al menú principal");
        System.out.print("Opción: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();

        switch(opcion){
            case 1:
                System.out.print("ID del proyecto: ");
                int proyectoID = entrada.nextInt();
                entrada.nextLine();

                out.println("REPORTE_AVANCE|" + proyectoID); // Recibe el dato desde el servidor
                System.out.println(in.readLine());
                break;

            case 2:
                out.println("REPORTE_ESTADOS");
                System.out.println(in.readLine());
                break;

            case 3:
                return;

            default:
                System.out.println("Opción no válida");
        }
    }*/
}