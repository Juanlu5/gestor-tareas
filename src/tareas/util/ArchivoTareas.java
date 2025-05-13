package tareas.util;

import tareas.modelo.Tarea;

import java.io.*;
import java.util.ArrayList;

public class ArchivoTareas {
    public static void guardar (ArrayList<Tarea> tareas, String nombreArchivo){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            for (Tarea t: tareas) {
                writer.write((t.isCompletada() ? "1": "0")+";"+ t.getTitulo());
                writer.newLine();
            }
            System.out.println("Tareas guardadas correctamente");
        } catch (IOException e){
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }
    public static ArrayList<Tarea> cargar(String nombreArchivo){
        ArrayList<Tarea> tareas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while ((linea = reader.readLine()) !=null){
                String[] partes = linea.split(";",2);
                if (partes.length ==2){
                    Tarea t = new Tarea(partes[1]);
                    if (partes[0].equals("1")){
                        t.marcarCompletada();
                    }
                    tareas.add(t);
                }
            }
            System.out.println("Tareas cargadas correctamente.");
        } catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado. Se empezará con una lista vacía");
        }
        catch (IOException e){
            System.out.println("Error al leer tareas: "+e.getMessage());
        }
        return tareas;
    }
}

