package tareas.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TareaTest {

    @Test
    void nuevaTareaDebeTenerTituloYNoEstarCompletada(){
        Tarea t = new Tarea("Tarea prueba");
        assertEquals("Tarea prueba", t.getTitulo());
        assertFalse(t.isCompletada());
    }

    @Test
    void marcarTareaComoCompletada(){
        Tarea t = new Tarea("Tarea para completar");
        t.marcarCompletada();
        assertTrue(t.isCompletada());
    }

    @Test
    void cambiarTituloDeTarea(){
        Tarea t = new Tarea("Tarea para cambiar título");
        t.setTitulo("Tarea con título cambiado");
        assertEquals("Tarea con título cambiado", t.getTitulo());
    }
}
