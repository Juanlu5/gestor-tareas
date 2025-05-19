package tareas.gestor;

import org.junit.jupiter.api.Test;
import tareas.modelo.Tarea;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestorTareasTest {

    @Test
    void testAgregarTareaValida(){
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = new Tarea("Aprender JUnit");

        ResultadoOperacion resultado = gestor.agregarTarea(tarea);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertEquals(1, gestor.getTareas().size());
        assertEquals("Aprender JUnit", gestor.getTareas().get(0).getTitulo());
    }

    @Test
    void testAgregarTareaVacia(){
        GestorTareas gestor = new GestorTareas();
        Tarea tarea= new Tarea(" ");

        ResultadoOperacion resultado = gestor.agregarTarea(tarea);

        assertEquals(ResultadoOperacion.VACIA, resultado);
        assertTrue(gestor.getTareas().isEmpty());
    }

    @Test
    void testAgregarTareaNull(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.agregarTarea(null);

        assertTrue(gestor.getTareas().isEmpty());
        assertEquals(ResultadoOperacion.VACIA, resultado);
    }

    @Test
    void testBorrarTareaValida(){
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = new Tarea("Tarea de prueba");
        gestor.agregarTarea(tarea);

        ResultadoOperacion resultado = gestor.borrarTarea(0);

        assertTrue(gestor.getTareas().isEmpty());
        assertEquals(ResultadoOperacion.EXITO, resultado);
    }

    @Test
    void testBorrarTareaInvalida(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.borrarTarea(0);

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }

    @Test
    void testMarcarTareaCompletadaValida(){
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Tarea para marcar completada"));

        ResultadoOperacion resultado = gestor.marcarTareaCompletada(0);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertTrue(gestor.getTareas().get(0).isCompletada());
    }

    @Test
    void marcarTareaCompletadaYaCompletada(){
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Tarea ya completada"));
        gestor.marcarTareaCompletada(0);

        ResultadoOperacion resultado = gestor.marcarTareaCompletada(0);

        assertEquals(ResultadoOperacion.YA_COMPLETADA, resultado);
    }

    @Test
    void marcarTareaCompletadaInexistente(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.marcarTareaCompletada(5);

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }

    @Test
    void editarTareaCorrectamente(){
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Tarea a editar"));

        ResultadoOperacion resultado = gestor.editarTarea(0, "Tarea editada");

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertEquals("Tarea editada",gestor.getTareas().get(0).getTitulo());
    }

    @Test
    void editarTareaInexistente(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.editarTarea(5,"Tarea inexistente editada");

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }

    @Test
    void editarTareaConTituloVacio(){
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Tarea a editar"));

        ResultadoOperacion resultado = gestor.editarTarea(0,"");

        assertEquals(ResultadoOperacion.VACIA, resultado);
    }

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
