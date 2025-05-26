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

    @Test
    void nuevaTareaDebeTenerIdMenosUno(){
        Tarea t = new Tarea("Tarea para ver ID");
        assertEquals(-1, t.getId());
    }

    @Test
    void asignarIdDeTarea(){
        Tarea t = new Tarea("Tarea para asignar ID");
        t.setId(40);

        assertEquals(40, t.getId());
    }

    @Test
    void tareaConIdDiferenteSonDistintas(){
        Tarea t1 = new Tarea("Primera tarea");
        Tarea t2 = new Tarea("Segunda tarea");
        t1.setId(5);
        t2.setId(2);

        assertNotEquals(t1.getId(),t2.getId());
    }
}
