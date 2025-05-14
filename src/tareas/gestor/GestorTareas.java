package tareas.gestor;

import tareas.modelo.Tarea;

import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public ArrayList<Tarea> getTareas(){
        return tareas;
    }

    public boolean agregarTarea(Tarea t){
        if (t.getTitulo().equals("0")){
            return false;
        } else{
            tareas.add(t);
            return true;
        }
    }

    public boolean borrarTarea(int iTarea){
        if (iTarea <= 0 || iTarea > tareas.size()) {
            return false;
        } else {
            tareas.remove(iTarea-1);
            return true;
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
    public boolean marcarTareaCompletada(int iTarea){
        if (iTarea <=0|| iTarea>tareas.size()){
            return false;
        }
        Tarea t = tareas.get(iTarea-1);
        if (t.isCompletada()){
            return false;
        } else{
            t.marcarCompletada();
            return true;
        }
    }

    public boolean editarTarea(int iTarea, String nuevoTitulo){

        if (iTarea < 0 || iTarea > tareas.size()){
            return false;
        }
        if (nuevoTitulo == null || nuevoTitulo.trim().isEmpty()){
            return false;
        } else {
        tareas.get(iTarea).setTitulo(nuevoTitulo);
        return true;
        }
    }
}

