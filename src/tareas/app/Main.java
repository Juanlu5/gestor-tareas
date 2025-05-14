package tareas.app;

import tareas.util.ArchivoTareas;
import tareas.gestor.GestorTareas;
import tareas.modelo.Tarea;
import tareas.util.RepositorioTareas;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorTareas g1 = new GestorTareas();
        Scanner sc = new Scanner(System.in);

        RepositorioTareas.inicializarBaseDeDatos();
        ArrayList<Tarea> tareasCargadas = RepositorioTareas.cargar();
        for(Tarea t : tareasCargadas){
            g1.agregarTarea(t);
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = pedirNumero(sc,"Selecciona una opción ",0,6);

            switch (opcion){
                case 1 -> {
                    String titulo = pedirTitulo(sc);
                    boolean agregada = g1.agregarTarea(new Tarea(titulo));
                    if (agregada){
                        System.out.println("✅ Tarea añadida.");
                    } else {
                        System.out.println("Acción cancelada, ninguna tarea agregada.");
                    }
                }
                case 2 -> {
                    g1.mostrarListaTareas();
                }
                case 3 -> {
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc,"Selecciona el número de la tara que quieres marcar como completada. Presiona 0 para volver",0,g1.getTareas().size());
                    g1.marcarTareaCompletada(n);
                }
                case 4 -> {
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc, "Elige el número de la tarea que quieres eliminar",0,g1.getTareas().size());
                    boolean borrada = g1.borrarTarea(n);
                    if (borrada){
                        System.out.println("Tarea borrada.");
                    } else {
                        System.out.println("Acción cancelada, ninguna tarea borrada.");
                    }
                }
                case 5 -> {
                    RepositorioTareas.guardar(g1.getTareas());
                }
                case 6 ->{
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc,"Introduce el número de tarea a editar (0 para cancelar)", 0,g1.getTareas().size());
                    if  (n==0) {
                        System.out.println("Acción cancelada, ninguna tarea editada.");
                        break;
                    }
                    String nuevoTitulo = pedirTitulo(sc);
                    boolean editada = g1.editarTarea(n-1, nuevoTitulo);
                    if (editada){
                        System.out.println("Edición realizada con éxito.");
                    } else {
                        System.out.println("Error, introduce un nombre válido.");
                    }
                }
                case 0 -> {
                    ArchivoTareas.guardar(g1.getTareas(), "prueba.txt");
                    System.out.println("Saliendo...");
                }
            }
        } while(opcion !=0);

        sc.close();
    }
    public static void mostrarMenu(){
        System.out.println("""
                \n MENÚ DE TAREAS
                --------------------
                1. Añadir tareas
                2. Ver lista de tareas
                3. Marcar tarea como completada
                4. Borrar tarea
                5. Guardar tareas
                6. Editar tareas
                0. Salir
                """);
    }

    public static int pedirNumero (Scanner sc, String mensaje, int min, int max){
        int numero = -1;
        boolean valido = false;
        while (!valido){
            System.out.println(mensaje);
            if(sc.hasNextInt()){
                numero = sc.nextInt();
                sc.nextLine();
                if(numero >= min && numero <= max){
                    valido = true;
                } else {
                    System.out.println("❌ El número debe estar entre "+min+ " y "+max+".");
                }
            } else{
                System.out.println("❌ Entrada inválida. Introduce un número.");
                sc.nextLine();
            }
        }
        return numero;
    }
    public static String pedirTitulo(Scanner sc){
        String titulo;
        do {
            System.out.println("Escribe la tarea: ");
            titulo = sc.nextLine().trim();
            if(titulo.isEmpty()){
                System.out.println("La tarea no puede estar vacía.");
            }
        } while (titulo.isEmpty());
        return titulo;
    }
}
