/*package tareas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TextConexionSQLite {
    public static void main(String[] args) {
        try{
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Conexión a SQLite exitosa");

            conn.close();
        } catch (ClassNotFoundException e){
            System.out.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e){
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}*/

