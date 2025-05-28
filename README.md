# Gestor de Tareas
Una aplicación Java para gestionar tareas, construida con el patrón MVC, SQLite para persistencia y JUnit 5 para pruebas unitarias.

## ¿Por qué este proyecto?

Este proyecto fue desarrollado como ejercicio práctico para aplicar conceptos clave de Java, diseño modular (MVC), persistencia con SQLite y pruebas automatizadas con JUnit. Está pensado como parte de mi portafolio profesional.

##Características

Añadir, editar, completar y borrar tareas.
Filtrado por completadas y pendientes.
Persistencia en base de datos SQLite.
Interfaz por consola, simple y funcional.
Pruebas unitarias con JUnit 5.

## Capturas de pantalla

imagenes/Captura%20Gestor.png

![Menú de tareas](/imagenes/Captura%20Gestor.png)

![Menú de tareas](/imagenes/Captura%20Gestor%202.png)


## Ejemplo de uso

```text
MENÚ DE TAREAS
--------------------
1. Añadir tareas
2. Ver lista de tareas
3. Marcar tarea como completada
4. Borrar tarea
5. Guardar tareas
6. Editar tareas
0. Salir

Selecciona una opción: 1
Escribe la tarea: Leer documentación de Java

Selecciona una opción: 2
1. [ ] Leer documentación de Java

Selecciona una opción: 3
Selecciona el ID de la tarea a completar: 1
✅ Tarea marcada como completada.
```

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

## Mejoras futuras

Agregar campo de prioridad a las tareas.
Agregar búsqueda por palabra clave.
Añadir interfaz gráfica.
Exportar tareas a .csv.

## Licencia
MIT License

