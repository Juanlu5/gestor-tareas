package tareas.gestor;

import tareas.modelo.Tarea;

import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public ArrayList<Tarea> getTareas(){
        return tareas;
    }

    public void agregarTarea(Tarea t){
        tareas.add(t);
    }

    public ResultadoOperacion borrarTarea(int iTarea){
        if (iTarea < 0 || iTarea >= tareas.size()) {
            return ResultadoOperacion.NO_EXISTE;
        } else {
            tareas.remove(iTarea);
            return ResultadoOperacion.EXITO;
        }
    }

    public void mostrarListaTareas(){
        System.out.println("---------- LISTA DE TAREAS ----------"+"\n");
        for(int i = 0; i<tareas.size(); i++){
            Tarea t = tareas.get(i);
            String estado = t.isCompletada() ? "[X]" : "[ ]";
            System.out.println((i+1)+". "+ estado + " " + t.getTitulo());
        }
            System.out.println();
    }
    public ResultadoOperacion marcarTareaCompletada(int iTarea){
        if (iTarea < 0|| iTarea>=tareas.size()){
            return ResultadoOperacion.NO_EXISTE;
        }
        Tarea t = tareas.get(iTarea);
        if (t.isCompletada()){
            return ResultadoOperacion.YA_COMPLETADA;
        } else{
            t.marcarCompletada();
            return ResultadoOperacion.EXITO;
        }
    }

    public ResultadoOperacion editarTarea(int iTarea, String nuevoTitulo){

        if (iTarea < 0 || iTarea >= tareas.size()){
            return ResultadoOperacion.NO_EXISTE;
        }
        if (nuevoTitulo == null || nuevoTitulo.trim().isEmpty()){
            return ResultadoOperacion.VACIA;
        } else {
        tareas.get(iTarea).setTitulo(nuevoTitulo);
        return ResultadoOperacion.EXITO;
        }
    }
}

