package tareas.app;

import tareas.gestor.GestorTareas;
import tareas.modelo.Tarea;
import tareas.util.RepositorioTareas;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        RepositorioTareas.inicializarBaseDeDatos();
        ArrayList<Tarea> tareasCargadas = RepositorioTareas.cargar();

        GestorTareas gestor = new GestorTareas();
        for (Tarea t : tareasCargadas){
            gestor.agregarTarea(t);
        }

        InterfazUsuario ui = new InterfazUsuario(gestor);
        ui.iniciar();
    }
}
