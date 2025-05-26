package tareas.gestor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tareas.modelo.Tarea;
import tareas.util.RepositorioTareas;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GestorTareasTest {

    @Test
    void testAgregarTareaValida(){
        GestorTareas gestor = new GestorTareas();
        Tarea t = new Tarea("Aprender JUnit");

        ResultadoOperacion resultado = gestor.agregarTarea(t);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertEquals(1, gestor.getTareas().size());
        assertEquals("Aprender JUnit", gestor.getTareas().get(0).getTitulo());
    }

    @Test
    void testAgregarTareaVacia(){
        GestorTareas gestor = new GestorTareas();
        Tarea t= new Tarea(" ");

        ResultadoOperacion resultado = gestor.agregarTarea(t);

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
        Tarea t = new Tarea("Tarea de prueba");
        gestor.agregarTarea(t);

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
    void editarTareaPorId(){
        GestorTareas gestor = new GestorTareas();
        Tarea t = new Tarea("Tarea a editar");
        gestor.agregarTarea(t);
        t.setId(10);

        ResultadoOperacion resultado = gestor.editarTareaPorId(10, "Tarea editada");

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertEquals("Tarea editada", t.getTitulo());
    }

    @Test
    void editarTareaPorIdInexistente(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.editarTareaPorId(10,"Tarea inexistente");

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }

    @Test
    void editarTareaPorIdTituloVacio(){
        GestorTareas gestor = new GestorTareas();
        Tarea t = new Tarea("Tarea a editar");
        gestor.agregarTarea(t);
        t.setId(5);

        ResultadoOperacion resultado = gestor.editarTareaPorId(5, " ");

        assertEquals(ResultadoOperacion.VACIA, resultado);
    }

    @Test
    void borrarTareaPorId(){
        GestorTareas gestor = new GestorTareas();
        Tarea t = new Tarea("Tarea para borrar");
        gestor.agregarTarea(t);
        t.setId(50);

        ResultadoOperacion resultado = gestor.borrarTareaPorId(50);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertTrue(gestor.getTareas().isEmpty());
    }

    @Test
    void borrarTareaPorIdInexistente(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.borrarTareaPorId(90);

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }

    @Test
    void marcarTareaCompletadaPorId(){
        GestorTareas gestor = new GestorTareas();
        Tarea t = new Tarea("Tarea para borrar");
        gestor.agregarTarea(t);
        t.setId(1);

        ResultadoOperacion resultado = gestor.marcarTareaCompletadaPorId(1);

        assertEquals(ResultadoOperacion.EXITO, resultado);
        assertTrue(t.isCompletada());
    }

    @Test
    void marcarTareaCompletadaPorIdYaCompletada(){
        GestorTareas gestor = new GestorTareas();
        Tarea t = new Tarea("Tarea ya completada");
        gestor.agregarTarea(t);
        t.setId(70);
        t.marcarCompletada();

        ResultadoOperacion resultado = gestor.marcarTareaCompletadaPorId(70);

        assertEquals(ResultadoOperacion.YA_COMPLETADA, resultado);
    }

    @Test
    void marcarTareaCompletadaPorIdInexistente(){
        GestorTareas gestor = new GestorTareas();

        ResultadoOperacion resultado = gestor.marcarTareaCompletadaPorId(12);

        assertEquals(ResultadoOperacion.NO_EXISTE, resultado);
    }
}
