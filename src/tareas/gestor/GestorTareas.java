package tareas.gestor;

import tareas.modelo.Tarea;

import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public ArrayList<Tarea> getTareas(){
        return tareas;
    }

    public ResultadoOperacion agregarTarea(Tarea t){
        if(t == null || t.getTitulo().trim().isEmpty()) return ResultadoOperacion.VACIA;
        tareas.add(t);
        return ResultadoOperacion.EXITO;
    }

    public ResultadoOperacion borrarTarea(int iTarea){
        if (iTarea < 0 || iTarea >= tareas.size()) {
            return ResultadoOperacion.NO_EXISTE;
        } else {
            tareas.remove(iTarea);
            return ResultadoOperacion.EXITO;
        }
    }

    public ResultadoOperacion borrarTareaPorId(int id){
        for (int i=0; i< tareas.size(); i++){
            if(tareas.get(i).getId()==id){
                tareas.remove(i);
                return ResultadoOperacion.EXITO;
            }
        }
        return ResultadoOperacion.NO_EXISTE;
    }

    public void mostrarListaTareas(){
        System.out.println("---------- LISTA DE TAREAS ----------"+"\n");
        for(int i = 0; i<tareas.size(); i++){
            Tarea t = tareas.get(i);
            String estado = t.isCompletada() ? "[X]" : "[ ]";
            System.out.println((i+1)+". ("+t.getId()+") " + estado + " " + t.getTitulo());
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

    public ResultadoOperacion marcarTareaCompletadaPorId(int id){
        for(Tarea t : tareas) {
            if (t.getId() == id) {
                if (t.isCompletada()) {
                    return ResultadoOperacion.YA_COMPLETADA;
                } else {
                    t.marcarCompletada();
                    return ResultadoOperacion.EXITO;
                }
            }
        }
        return ResultadoOperacion.NO_EXISTE;
    }

    public ArrayList<Tarea> obtenerCompletadas(){
        ArrayList<Tarea> completadas = new ArrayList<>();
        for (Tarea t : tareas){
            if(t.isCompletada()){
                completadas.add(t);
            }
        }
        return completadas;
    }

    public ArrayList<Tarea> obtenerPendientes(){
        ArrayList<Tarea> pendientes = new ArrayList<>();
        for (Tarea t : tareas){
            if (!t.isCompletada()){
                pendientes.add(t);
            }
        }
        return pendientes;
    }

    public Tarea buscarPorId(int id){
        for(Tarea t: tareas){
            if (id==t.getId()){
                return t;
            }
        }
        return null;
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

    public ResultadoOperacion editarTareaPorId(int id, String nuevoTitulo){
        if(nuevoTitulo == null || nuevoTitulo.trim().isEmpty()){
            return ResultadoOperacion.VACIA;
        }

        for(Tarea t: tareas){
            if(t.getId()==id){
                t.setTitulo(nuevoTitulo);
                return ResultadoOperacion.EXITO;
            }
        }
        return ResultadoOperacion.NO_EXISTE;
    }
}

