package tareas.modelo;

public class Tarea {
    public String titulo;
    public boolean completada;

    public Tarea(String titulo) {
        this.titulo = titulo;
        this.completada = false;
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

}

