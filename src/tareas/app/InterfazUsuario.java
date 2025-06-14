package tareas.app;

import tareas.gestor.GestorTareas;
import tareas.gestor.ResultadoOperacion;
import tareas.modelo.Tarea;
import tareas.util.RepositorioTareas;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfazUsuario {
    private final GestorTareas gestor;
    private final Scanner sc;

    public InterfazUsuario(GestorTareas gestor){
        this.gestor = gestor;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do{
            mostrarMenu();
            opcion = pedirNumero("Selecciona una opción");
            if(opcion >= 0 && opcion <= 8){
                manejarOpcion(opcion);
            } else{
                System.out.println("Elige una opción válida");
            }
        } while (opcion != 0);
        RepositorioTareas.guardar(gestor.getTareas());
        System.out.println("Saliendo...");
        sc.close();
    }

    private void mostrarMenu() {
        System.out.println("""
                \n MENÚ DE TAREAS
                --------------------
                1. Añadir tareas
                2. Ver lista de tareas
                3. Marcar tarea como completada
                4. Borrar tarea
                5. Guardar tareas
                6. Editar tareas
                7. Mostrar tareas completadas
                8. Mostrar tareas pendientes
                0. Salir
                """);
    }

    private void manejarOpcion(int opcion){
        switch (opcion){
            case 1 -> agregarTarea();
            case 2 -> gestor.mostrarListaTareas();
            case 3 -> marcarCompletada();
            case 4 -> eliminarTarea();
            case 5 -> RepositorioTareas.guardar(gestor.getTareas());
            case 6 -> editarTarea();
            case 7 -> obtenerTareasCompletadas();
            case 8 -> obtenerTareasPendientes();
            }
        }

    private void agregarTarea(){
        String titulo = pedirTitulo();
            if(titulo.equals("0")) return;
            gestor.agregarTarea(new Tarea(titulo));
            RepositorioTareas.guardar(gestor.getTareas());
    }

    private void marcarCompletada(){
        gestor.mostrarListaTareas();
        int n = pedirNumero("Selecciona el id de la tarea que quieres marcar como completada.");
        if (n==0) return;
        ResultadoOperacion resultado = gestor.marcarTareaCompletadaPorId(n);
        switch (resultado){
            case NO_EXISTE -> System.out.println("Tarea no encontrada.");
            case YA_COMPLETADA -> System.out.println("Esta tarea está ya completada.");
            case EXITO -> System.out.println("Tarea marcada como completada.");
            default -> System.out.println("Algo salió mal.");
        }
    }

    private void eliminarTarea(){
        gestor.mostrarListaTareas();
        int n = pedirNumero("Selecciona el id de la tarea que quieres eliminar.");
        if(n==0) return;
        ResultadoOperacion resultado = gestor.borrarTareaPorId(n);
        switch (resultado){
            case NO_EXISTE -> System.out.println("Tarea no encontrada.");
            case EXITO -> System.out.println("Tarea eliminada.");
            default -> System.out.println("Algo salió mal.");
        }
    }

    private void editarTarea(){
        gestor.mostrarListaTareas();
        int n = pedirNumero("Selecciona el id de la tarea que quieres editar");
        if (n==0) return;
        String nuevoTitulo = pedirTitulo();
        ResultadoOperacion resultado = gestor.editarTareaPorId(n,nuevoTitulo);
        switch(resultado){
            case VACIA -> System.out.println("El título no puede estar vacío.");
            case NO_EXISTE -> System.out.println("Tarea no encontrada.");
            case EXITO -> System.out.println("Tarea editada con éxito.");
            default -> System.out.println("Algo salió mal.");
        }
    }

    private int pedirNumero(String mensaje) {
        int id;
        while(true){
            System.out.println(mensaje);
            if(sc.hasNextInt()) {
                id = sc.nextInt();
                sc.nextLine();
                return id;
            } else {
                System.out.println("❌ Entrada inválida.");
                sc.nextLine();
            }
        }
    }

    private String pedirTitulo(){
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
    private void obtenerTareasCompletadas(){
        System.out.println("TAREAS COMPLETADAS:");
        ArrayList<Tarea> completadas = gestor.obtenerCompletadas();
        for(Tarea t : completadas){
            System.out.println("("+t.getId()+") [X] " + t.getTitulo());
        }
        System.out.println();
    }

    private void obtenerTareasPendientes(){
        System.out.println("TAREAS PENDIENTES:");
        ArrayList<Tarea> pendientes = gestor.obtenerPendientes();
        for(Tarea t : pendientes){
            System.out.println("("+t.getId()+") [ ] " + t.getTitulo());
        }
        System.out.println();
    }
}
