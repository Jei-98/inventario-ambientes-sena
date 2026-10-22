# Inventario de Ambientes SENA

Aplicación de consola en Java para controlar el inventario de cada ambiente de formación del SENA y reportar faltantes, pérdidas o daños.

## Descripción

Permite a instructores y coordinadores registrar ambientes por piso, llevar el inventario de cada uno (sillas, computadores, mesas, tableros), reportar novedades y consultar reportes de faltantes para tomar decisiones de reposición.

## Product Goal

Permitir a los instructores y coordinadores del SENA controlar el inventario de cada ambiente y reportar faltantes o daños, para tomar decisiones de reposición.

## Equipo y roles Scrum

| Integrante | Rol Scrum | Rol técnico |
|---|---|---|
| María Urcuqui | Product Owner | Developer |
| Jeison Avila | Scrum Master | Developer |
| Matium Cuadros | Developer | Developer |

## Tecnologías

- Java (aplicación de consola, sin dependencias externas)
- Git y GitHub (control de versiones, ramas y Pull Requests)
- GitHub Issues y GitHub Projects (gestión Scrum)

## Requisitos

- JDK 17 o superior instalado (`java -version` y `javac -version` deben responder).
- Git.

## Cómo compilar y ejecutar

Desde la carpeta raíz del proyecto (PowerShell):

```
javac -d bin src\*.java
java -cp bin Main
```

El primer comando compila todas las clases en la carpeta `bin`. El segundo ejecuta la clase `Main`, que muestra el menú.

## Funcionalidades

| Opción | Qué hace |
|---|---|
| 1 | Registrar un ambiente (nombre y piso) |
| 2 | Agregar un elemento a un ambiente |
| 3 | Consultar el inventario de un ambiente |
| 4 | Actualizar la cantidad o el estado de un elemento |
| 5 | Reportar un faltante, pérdida o daño |
| 6 | Filtrar ambientes por piso |
| 7 | Ver las observaciones de un ambiente (todas o solo pendientes) |
| 8 | Resolver una observación |
| 9 | Reporte de faltantes agrupado por piso |
| 0 | Salir |

## Reglas de negocio principales

- El nombre de un ambiente no se repite y su piso es un entero positivo.
- Un elemento no puede repetirse por nombre dentro de un ambiente.
- Las cantidades no pueden ser negativas y el estado es BUENO, REGULAR o DAÑADO.
- Faltante = cantidad esperada − cantidad actual.
- Una pérdida o un daño descuentan la cantidad actual; un faltante no.
- Toda observación nace Pendiente y una observación resuelta no se resuelve de nuevo.
- Una operación rechazada no modifica el inventario ni cierra el programa.

## Estructura del proyecto

```
inventario-ambientes-sena/
├── README.md
├── .gitignore
├── src/
│   ├── Main.java                    Menú y entrada de datos
│   ├── GestorInventario.java        Reglas de negocio y colecciones
│   ├── Ambiente.java                Ambiente y su lista de elementos
│   ├── Elemento.java                Elemento del inventario
│   ├── Observacion.java             Reporte de faltante, pérdida o daño
│   └── DatoInvalidoException.java   Excepción propia para datos inválidos
├── pruebas/
│   └── casos-de-prueba.md           Casos de prueba documentados
└── docs/
    ├── sprint-1.md
    ├── sprint-2.md
    └── sprint-3.md
```

## Conceptos de Java aplicados

- **POO:** clases, objetos, constructores, `this`, encapsulamiento y colaboración entre clases.
- **Colecciones:** `ArrayList`, `HashMap`, `TreeMap` y `HashSet`.
- **Control de flujo:** `do while`, `switch`, `for`, `if/else`.
- **Excepciones:** `try/catch` y una excepción propia (`DatoInvalidoException`).

## Gestión del proyecto

- Repositorio: https://github.com/Jei-98/inventario-ambientes-sena
- Tablero GitHub Projects: https://github.com/users/Jei-98/projects/1
- Registro de sprints: carpeta [`docs/`](docs)
- Casos de prueba: [`pruebas/casos-de-prueba.md`](pruebas/casos-de-prueba.md)
- Informe final de Scrum: [completar con el enlace o la ruta cuando esté entregado]