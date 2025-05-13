package tareas.gestor;

import tareas.modelo.Tarea;

import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public ArrayList<Tarea> getTareas(){
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void agregarTarea(Tarea t){
        if (t.titulo.equals("0")){
            System.out.println("Ninguna tarea agregada");
        } else{
            tareas.add(t);
        }
    }

    public void borrarTarea(int iTarea){
        if (iTarea == 0) {
            System.out.println("Ninguna tarea borrada");
            return;
        }
        if (iTarea < 0 || iTarea > tareas.size()){
            System.out.println("Error, la tarea número "+iTarea+" no existe");
            return;
        }
        Tarea eliminada = tareas.remove(iTarea-1);
        System.out.println("Tarea eliminada: \""+eliminada.getTitulo()+ "\"");
    }

    public void mostrarListaTareas(){
        System.out.println("---------- LISTA DE TAREAS ----------"+"\n");
        for(int i = 0; i<tareas.size(); i++){
            Tarea t = tareas.get(i);
            String estado = t.isCompletada() ? "[X]" : "[ ]";
            System.out.println((i+1)+". "+ estado + " " + t.getTitulo());
        }
    }
    public void marcarTareaCompletada(int numTarea){
        if (numTarea==0){
            System.out.println("Ninguna tarea marcada como completada, volviendo...");
            return;
        }
        if (numTarea <0|| numTarea>tareas.size()){
            System.out.println("❌ Error, el número de tarea no existe.");
            return;
        }
        Tarea t = tareas.get(numTarea-1);
        if (t.isCompletada()){
            System.out.println("La tarea está ya completada");
        } else{
            t.marcarCompletada();
            System.out.println("Tarea: \""+t.getTitulo()+"\"");
        }
    }
}

