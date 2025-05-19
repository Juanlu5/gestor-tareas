package tareas.gestor;

import org.junit.jupiter.api.Test;
import tareas.modelo.Tarea;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestorTareasTest {

    @Test
    public void testAgregarTareaValida(){
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = new Tarea("Aprender JUnit");

        ResultadoOperacion resultado = gestor.agregarTarea(tarea);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertEquals(1, gestor.getTareas().size());
        assertEquals("Aprender JUnit", gestor.getTareas().get(0).getTitulo());
    }

    @Test
    public void testAgregarTareaVacia(){
        GestorTareas gestor = new GestorTareas();
        Tarea tarea= new Tarea(" ");

        ResultadoOperacion resultado = gestor.agregarTarea(tarea);

        assertEquals(ResultadoOperacion.VACIA, resultado);
        assertTrue(gestor.getTareas().isEmpty());
    }

    @Test
    public void testAgregarTareaNull(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.agregarTarea(null);

        assertTrue(gestor.getTareas().isEmpty());
        assertEquals(ResultadoOperacion.VACIA, resultado);
    }

    @Test
    public void testBorrarTareaValida(){
        GestorTareas gestor = new GestorTareas();
        Tarea tarea = new Tarea("Tarea de prueba");
        gestor.agregarTarea(tarea);

        ResultadoOperacion resultado = gestor.borrarTarea(0);

        assertTrue(gestor.getTareas().isEmpty());
        assertEquals(ResultadoOperacion.EXITO, resultado);
    }

    @Test
    public void testBorrarTareaInvalida(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.borrarTarea(0);

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }

    @Test
    public void testMarcarTareaCompletadaValida(){
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Tarea para marcar completada"));

        ResultadoOperacion resultado = gestor.marcarTareaCompletada(0);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertTrue(gestor.getTareas().get(0).isCompletada());
    }

    @Test
    public void marcarTareaCompletadaYaCompletada(){
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Tarea ya completada"));
        gestor.marcarTareaCompletada(0);

        ResultadoOperacion resultado = gestor.marcarTareaCompletada(0);

        assertEquals(ResultadoOperacion.YA_COMPLETADA, resultado);
    }
}
