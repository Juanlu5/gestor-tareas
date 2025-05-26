package tareas.util;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void cerrarConexion(){
        RepositorioTareas.cerrarConexion();
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

    @Test
    void actualizarTareaExistente(){
        ArrayList<Tarea> tareas = new ArrayList<>();
        Tarea tarea = new Tarea("Tarea original");
        tareas.add(tarea);

        RepositorioTareas.guardar(tareas);
        int idAsignado = tareas.get(0).getId();

        tarea.setTitulo("Tarea actualizada");
        tarea.setCompletada(true);

        RepositorioTareas.guardar(tareas);

        ArrayList<Tarea> resultado = RepositorioTareas.cargar();

        assertEquals(1,resultado.size());
        Tarea cargada = resultado.get(0);

        assertEquals(idAsignado,cargada.getId());
        assertEquals("Tarea actualizada", cargada.getTitulo());
        assertTrue(cargada.isCompletada());

    }
    @Test
    void losIdsSeAsignanAlGuardar(){
        Tarea t1 = new Tarea("Tarea guardada 1");
        Tarea t2 = new Tarea("Tarea guardada 2");

        ArrayList<Tarea> tareas = new ArrayList<>();
        tareas.add(t1);
        tareas.add(t2);

        RepositorioTareas.guardar(tareas);

        assertNotEquals(-1, t1.getId());
        assertNotEquals(-1, t2.getId());
        assertNotEquals(t1.getId(), t2.getId());
    }

    @Test
    void editarTareasPorIdPersistida(){
        Tarea t = new Tarea("Tarea a editar");
        ArrayList<Tarea> tareas = new ArrayList<>();
        tareas.add(t);
        RepositorioTareas.guardar(tareas);

        int id = t.getId();

        t.setTitulo("Tarea editada");

        RepositorioTareas.guardar(tareas);
        tareas = RepositorioTareas.cargar();

        Tarea resultado = tareas.stream().filter(x -> x.getId()==id).findFirst().orElse(null);

        assertNotNull(resultado);
        assertEquals("Tarea editada",resultado.getTitulo());
    }
    @Test
    void marcarTareaComoCompletadaPorIdPersistida(){
        Tarea t = new Tarea("Tarea para completar");
        ArrayList<Tarea> tareas = new ArrayList<>();
        tareas.add(t);
        RepositorioTareas.guardar(tareas);

        int id = t.getId();

        t.marcarCompletada();
        RepositorioTareas.guardar(tareas);

        ArrayList<Tarea> cargada = RepositorioTareas.cargar();

        Tarea resultado = cargada.stream().filter(x -> x.getId()==id).findFirst().orElse(null);
        assertNotNull(resultado);
        assertTrue(resultado.isCompletada());
        assertEquals(id, resultado.getId());
    }

    @Test
    void eliminarTareaPorIdPersistida(){
        Tarea t = new Tarea("Tarea para eliminar");
        ArrayList<Tarea> tareas = new ArrayList<>();
        tareas.add(t);
        RepositorioTareas.guardar(tareas);

        int id = t.getId();

        RepositorioTareas.eliminarTarea(id);

        ArrayList<Tarea> resultado = RepositorioTareas.cargar();
        assertTrue(resultado.stream().noneMatch(tarea -> tarea.getId()==id));
    }
}
