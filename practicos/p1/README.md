# Práctico 1 - Analizador de logs

## Fecha de Presentación
La fecha de presentación de este práctico es 1ero de septiembre de 2026

## Objetivo principal

Leer un archivo de log, convertir cada línea válida en un objeto,
guardarlo en una **lista dinámica implementada por ustedes**,
poder **filtrar** con una expresión regular y **ordenar** la lista
con distintos criterios.

El tema de la materia es la lista y el ordenamiento.
El archivo y el regex son el medio, no el fin.

## Patrones de diseño que se deben aplicar

* **Observer.** La lista (o el modelo que la contiene) notifica cuando
  cambia el contenido: carga, filtro u ordenamiento. El panel se
  redibuja porque recibió `update`, no porque el botón llamó a `repaint`.
* **Strategy.** Cada criterio de ordenamiento es una estrategia
  (por fecha, por nivel, por logger, por mensaje). El combo elige la
  estrategia; el algoritmo de sort no sabe qué está comparando más
  allá del `compare`. Basta con un criterio de ordenamiento

## Idea principal para el desarrollo

1. Definir `EventoLog` con al menos: fecha/hora, nivel
   (`DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`), nombre del logger
   y mensaje. Pueden añadir hilo o clase si el formato lo trae.
2. Implementar `Lista<E>` propia (simple o doble): insertar al final,
   insertar en posición, eliminar, recorrer, tamaño. Un `Iterator`
   propio ayuda al panel y al sort.
3. Leer el archivo con `BufferedReader` (como en el capítulo de
   persistencia). Cada línea se intenta parsear con `Pattern` / `Matcher`.
   Si no hay match: `WARN` y se sigue. Si hay match: se crea el
   `EventoLog` y se inserta en la lista.
4. Un patrón de partida (ajústelo al archivo que entreguen):

   ```
   (\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2},\d{3}) \[(\w+)\] (\w+\.)+(\w+) - (.*)
   ```

   Grupos: timestamp, nivel, (paquetes), logger corto, mensaje.
   También vale un access log:

   ```
   (\S+) .* "(\w+) ([^"]+)" (\d{3})
   ```
5. El ordenamiento puede ser el que ya vieron (inserción o QuickSort)
   sobre la lista propia. La Strategy solo responde
   `int comparar(EventoLog a, EventoLog b)`.
6. Interfaz mínima: botones para generar Info, Debug, Error, Warn y un botón adicional para leer el archivo de logs, 
   campo regex de filtro,
   combo de criterio (puede no haber), panel o tabla que **dibuje** o liste los eventos
   (no hace falta JTable de Java si pintan con `drawString` en un panel).

## Elementos a revisar

Preguntas posibles en la presentación:

* ¿Qué captura cada grupo de su regex? Pruebe con una línea en el
  pizarrón y diga qué queda en `group(1)`, `group(2)`, …
* ¿Qué hace su programa con una línea que no matchea?
* ¿Dónde está el Observer? ¿Quién es el sujeto y quién el observador?
* Explique la Strategy: ¿qué hay que agregar para ordenar por longitud
  de mensaje sin tocar el sort?
* Complejidad del algoritmo de ordenamiento que usó. ¿Por qué no
  usó `Collections.sort`?
* Muestre un `DEBUG` que tenga contexto (línea, archivo, tamaño de lista)
  y un `WARN` real, no inventado.

## Recomendaciones

* Separe el parser (`LineaLogParser`) de la lista. Si cambian el
  formato del archivo, no reescriben la lista.
* No usen `e.printStackTrace()`. Un fallo de IO es `ERROR` con la
  excepción en el logger.
* Compilen el `Pattern` una vez (en el parser o en el Singleton),
  no en cada línea.
* Cuiden la fecha: parsearla a `LocalDateTime` permite ordenar de
  verdad; ordenar el `String` solo funciona si el formato es ISO.
