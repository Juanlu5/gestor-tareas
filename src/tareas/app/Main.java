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
            opcion = pedirNumero(sc,"Selecciona una opción ",0,5);

            switch (opcion){
                case 1 -> {
                    String titulo = pedirTitulo(sc);
                    g1.agregarTarea(new Tarea(titulo));
                    System.out.println("✅ Tarea añadida");
                }
                case 2 -> {
                    g1.mostrarListaTareas();
                }
                case 3 -> {
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc,"Selecciona el número de la tara que quieres marcar como completada. Presiona 0 para volver",1,g1.getTareas().size());
                    g1.marcarTareaCompletada(n);
                    System.out.println("✅ Tarea marcada como completada");
                }
                case 4 -> {
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc, "Elige el número de la tarea que quieres eliminar",1,g1.getTareas().size());
                    g1.borrarTarea(n);
                    System.out.println("✅ Tarea borrada correctamente");
                }
                case 5 -> {
                    RepositorioTareas.guardar(g1.getTareas());
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
            System.out.println("Escribe la tarea que quieres ingresar");
            titulo = sc.nextLine().trim();
            if(titulo.isEmpty()){
                System.out.println("La tarea no puede estar vacía");
            }
        } while (titulo.isEmpty());
        return titulo;
    }
}
