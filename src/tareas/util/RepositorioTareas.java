package tareas.util;

import com.sun.source.tree.WhileLoopTree;
import tareas.modelo.Tarea;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioTareas {
    private static String url = "jdbc:sqlite:tareas.db";

    public static void usarBaseDeDatos(String nuevaUrl){
        url = nuevaUrl;
    }

    public static void inicializarBaseDeDatos(){
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS " +
                    "tarea (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " titulo TEXT NOT NULL, " +
                    "completada INTEGER NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
    public static void guardar(ArrayList<Tarea> tareas){
        try (Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement()){

            st.execute("DELETE FROM tarea");

            String sql = "INSERT INTO tarea (titulo, completada) VALUES (?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)){
                for (Tarea t : tareas){
                    pst.setString(1, t.getTitulo());
                    pst.setInt(2, t.isCompletada() ? 1 : 0);
                    pst.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar tareas "+ e.getMessage());
        }
    }

    public static ArrayList<Tarea> cargar() {
        ArrayList<Tarea> tareas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tarea")){

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                boolean completada = rs.getInt("completada")== 1;

                Tarea t = new Tarea(titulo);
                if(completada){
                    t.marcarCompletada();
                }
                tareas.add(t);
            }

        } catch (SQLException e){
            System.out.println("Error al cargar tareas: "+e.getMessage());
        }

        return tareas;
    }
}
