package tareas.app;

import tareas.gestor.ResultadoOperacion;
import tareas.gestor.GestorTareas;
import tareas.modelo.Tarea;
import tareas.util.RepositorioTareas;

import javax.xml.transform.Result;
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
                //Opción 1: Agregar Tarea
                case 1 -> {
                    String titulo = pedirTitulo(sc);
                    if (titulo.equals("0")) {
                        System.out.println("Acción cancelada. Ninguna tarea añadida.");
                    } else {
                        g1.agregarTarea(new Tarea(titulo));
                        System.out.println("✅ Tarea añadida.");
                    }
                }
                //Opción 2: Mostrar Tareas
                case 2 -> {
                    g1.mostrarListaTareas();
                }
                //Opción 3: Marcar Tarea Como Completada
                case 3 -> {
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc,"Selecciona el número de la tarea que quieres marcar como completada. Presiona 0 para volver",0,g1.getTareas().size());

                    ResultadoOperacion resultado = g1.marcarTareaCompletada(n-1);
                    switch (resultado){
                        case NO_EXISTE -> System.out.println("Esa tarea no existe.");
                        case YA_COMPLETADA -> System.out.println("Esa tarea está ya marcada como completada.");
                        case EXITO -> System.out.println("Tarea marcada como completada.");
                        default -> System.out.println("Algo salió mal.");
                    }
                }
                //Opción 4: Eliminar Tarea
                case 4 -> {
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc, "Elige el número de la tarea que quieres eliminar",0,g1.getTareas().size());
                    ResultadoOperacion resultado = g1.borrarTarea(n-1);
                    switch (resultado){
                        case EXITO -> System.out.println("Tarea borrada.");
                        case NO_EXISTE -> System.out.println("Acción cancelada, ninguna tarea borrada.");
                        default -> System.out.println("Algo salió mal.");
                    }
                }
                //Opción 5: Guardar
                case 5 -> {
                    RepositorioTareas.guardar(g1.getTareas());
                }
                //Opción 6: Editar Tarea
                case 6 ->{
                    g1.mostrarListaTareas();
                    int n = pedirNumero(sc,"Introduce el número de tarea a editar (0 para cancelar)", 0,g1.getTareas().size());
                    if  (n==0) {
                        System.out.println("Acción cancelada, ninguna tarea editada.");
                        break;
                    }
                    String nuevoTitulo = pedirTitulo(sc);
                    ResultadoOperacion resultado = g1.editarTarea(n-1, nuevoTitulo);
                    switch (resultado){
                        case EXITO -> System.out.print("Edición realizada con éxito.");
                        case VACIA -> System.out.println("Error, la tarea no puede estar vacía.");
                        case NO_EXISTE -> System.out.println("Error, la tarea no existe.");
                        default -> System.out.println("Algo salió mal.");
                    }
                }
                case 0 -> {
                    RepositorioTareas.guardar(g1.getTareas());
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
