package tareas.util;

import com.sun.source.tree.WhileLoopTree;
import tareas.modelo.Tarea;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioTareas {
    private static String URL = "jdbc:sqlite:tareas.db";
    private static Connection conexion = null;

    public static void usarBaseDeDatos(String url) {
        URL = url;
    }

    private static Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL);
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void inicializarBaseDeDatos() {
        try {
            Statement st = obtenerConexion().createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tareas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "titulo TEXT NOT NULL," +
                    "completada INTEGER NOT NULL)");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    public static void guardar(ArrayList<Tarea> tareas) {
        inicializarBaseDeDatos();
        try {
            Connection conn = obtenerConexion();

            ArrayList<Integer> idsEnBD = new ArrayList<>();
            try(Statement st =conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT id FROM tareas")){
                while (rs.next()){
                    idsEnBD.add(rs.getInt("id"));
                }
            }

            ArrayList<Integer> idsActuales = new ArrayList<>();
            for(Tarea t : tareas){
                if(t.getId()!=-1){
                    idsActuales.add(t.getId());
                }
            }

            for (int idDB : idsEnBD){
                if(!idsActuales.contains(idDB)){
                    try(PreparedStatement ps = conn.prepareStatement("DELETE FROM tareas WHERE id = ?")){
                        ps.setInt(1, idDB);
                        ps.executeUpdate();
                    }
                }
            }

            for (Tarea t : tareas) {
                if (t.getId() == -1) {
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO tareas (titulo, completada) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, t.getTitulo());
                    ps.setInt(2, t.isCompletada() ? 1 : 0);
                    ps.executeUpdate();
                    ResultSet keys = ps.getGeneratedKeys();
                    if (keys.next()) {
                        t.setId(keys.getInt(1));
                    }
                    keys.close();
                    ps.close();
                } else {
                    PreparedStatement ps = conn.prepareStatement(
                            "UPDATE tareas SET titulo = ?, completada = ? WHERE id = ?");
                    ps.setString(1, t.getTitulo());
                    ps.setInt(2, t.isCompletada() ? 1 : 0);
                    ps.setInt(3, t.getId());
                    ps.executeUpdate();
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }

    public static ArrayList<Tarea> cargar() {
        ArrayList<Tarea> resultado = new ArrayList<>();
        inicializarBaseDeDatos();
        try {
            Statement st = obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT id, titulo, completada FROM tareas");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                boolean completada = rs.getInt("completada") == 1;
                resultado.add(new Tarea(id, titulo, completada));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
        return resultado;
    }

    public static void eliminarTarea(int id){
        String sql = "DELETE FROM tareas WHERE id = ?";
        try(Connection conn = obtenerConexion();
        PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tarea: " + e.getMessage());
        }
    }
}