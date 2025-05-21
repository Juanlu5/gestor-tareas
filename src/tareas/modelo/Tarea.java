package tareas.modelo;

public class Tarea {
    private int id;
    private String titulo;
    private boolean completada;

    public Tarea(int id, String titulo, boolean completada) {
        this.titulo = titulo;
        this.completada = completada;
        this.id = id;
    }

    public Tarea(String titulo){
        this(-1,titulo, false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void marcarCompletada(){
        this.completada = true;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada){
        this.completada = completada;
    }

}