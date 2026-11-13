# Gimnasio: Inscripciones a clases con Lista, Cola y Pila

**Universidad de Cartagena - Ingeniería del software**
**Asignatura:** Estructuras de datos
**Actividad:** Unidad 2 - Actividad de aprendizaje
**Docente:** John Carlos Arrieta Arrieta
**Estudiante:** Moisés Daniel Ortega Sánchez
**Caso asignado:** 15 - Gimnasio (`InscripcionClase`)

## Descripción

Aplicación Java de consola que gestiona las inscripciones de los socios de un gimnasio a sus clases. Las estructuras de datos (Nodo, Lista, Cola y Pila) están implementadas desde cero con nodos enlazados, sin usar las colecciones de Java.

## Uso de las estructuras

| Estructura | Principio | Uso en el sistema |
|------------|-----------|-------------------|
| Lista (doblemente enlazada) | Acceso por posición o por búsqueda | Registro general de todas las inscripciones |
| Cola | FIFO | Inscripciones pendientes, atendidas en orden de llegada |
| Pila | LIFO | Historial de inscripciones procesadas y deshacer |

## Flujo del sistema

- **Registrar:** la inscripción se agrega a la lista general y se encola en pendientes.
- **Procesar:** se desencola la primera pendiente y se apila en el historial.
- **Deshacer:** se desapila la última procesada y se encola de nuevo en pendientes.
- **Cancelar:** se hace con una cola auxiliar, sin eliminar directamente del centro de la cola. La inscripción queda con estado `CANCELADA` en la lista general.

## Entidad principal: InscripcionClase

| Atributo | Descripción |
|----------|-------------|
| `codigo` | Código de inscripción (identificador principal) |
| `nombreSocio` | Nombre del socio |
| `nombreClase` | Clase a la que se inscribe |
| `horario` | Horario de la clase |
| `estado` | `PENDIENTE`, `PROCESADA` o `CANCELADA` |

`equals()` compara solo por `codigo`.

## Menú

1. Registrar inscripción
2. Ver todas las inscripciones registradas
3. Ver inscripciones pendientes
4. Procesar siguiente inscripción
5. Ver historial de inscripciones procesadas
6. Buscar inscripción por código
7. Cancelar inscripción pendiente
8. Deshacer último procesamiento
9. Ver cantidad de inscripciones
10. Salir

## Estructura del proyecto

```
gimnasio/
├── Nodo.java              # Dato y referencias izquierda/derecha
├── Lista.java             # Lista doblemente enlazada
├── Cola.java              # Cola FIFO
├── Pila.java              # Pila LIFO
├── InscripcionClase.java  # Entidad del caso
└── SistemaGimnasio.java   # Menú y lógica principal
```

## Cómo compilar y ejecutar

Requiere JDK 17 o superior.

```
cd gimnasio
javac *.java
java SistemaGimnasio
```

## Validaciones y manejo de errores

- Campos obligatorios no vacíos.
- No se permiten códigos de inscripción repetidos.
- Opción de menú no numérica o fuera de rango.
- Procesar o cancelar sin inscripciones pendientes.
- Deshacer con el historial vacío.
- Posiciones inválidas en la lista y datos nulos en las estructuras.

