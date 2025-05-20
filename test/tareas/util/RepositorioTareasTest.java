package tareas.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tareas.modelo.Tarea;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioTareasTest {

    @BeforeEach
    void prepararBaseDeDatos(){
        RepositorioTareas.usarBaseDeDatos("jdbc:sqlite::memory:");
        RepositorioTareas.inicializarBaseDeDatos();
    }

    @Test
    void guardarYCargarTareas(){
        ArrayList<Tarea> tareas = new ArrayList<>();
        Tarea t1 = new Tarea("Primera tarea");
        Tarea t2 = new Tarea("Segunda tarea");

        t2.marcarCompletada();

        tareas.add(t1);
        tareas.add(t2);

        RepositorioTareas.guardar(tareas);
        ArrayList<Tarea> resultado = RepositorioTareas.cargar();

        assertEquals(2, resultado.size());
        assertEquals("Primera tarea", resultado.get(0).getTitulo());
        assertFalse(resultado.get(0).isCompletada());
        assertEquals("Segunda tarea", resultado.get(1).getTitulo());
        assertTrue(resultado.get(1).isCompletada());
    }
}
