# Gestor de Tareas
Una aplicación Java para gestionar tareas, construida con el patrón MVC, SQLite para persistencia y JUnit 5 para pruebas unitarias.
Características

Añadir, editar, completar y borrar tareas.
Persistencia en base de datos SQLite.
Interfaz por consola, simple y funcional.
Pruebas unitarias con JUnit 5.

## Tecnologías

Java
SQLite
JUnit 5

## Instalación

Clona el repositorio: git clone https://github.com/Juanlu5/gestor-tareas.git
Configura SQLite (asegúrate de tener el driver JDBC).
Compila y ejecuta con: mvn clean install (o el comando de tu build tool).
Ejecuta la aplicación: java -jar target/gestor-tareas.jar

## Estructura del proyecto

tareas.modelo: Clase Tarea.
tareas.gestor: Lógica del negocio (GestorTareas).
tareas.util: Conexión a SQLite (RepositorioTareas).
tareas.app: Clase principal (Main).
src/test/java: Pruebas unitarias.

## Licencia
MIT License
