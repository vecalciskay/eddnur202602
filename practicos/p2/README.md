# Práctico 2 - Servidor web de N-Reinas

## Fecha de Presentación
La fecha de presentación de este práctico es 11 de septiembre de 2026

## Objetivo principal

Resolver el problema de las **N-Reinas** con un método **recursivo**
(backtracking) y exponer la solución —o un paso intermedio— a través
de un **servidor HTTP mínimo** que responde con una **imagen** del
tablero.

El tema de la materia es la recurrencia.
El servidor y el PNG son el medio para ver y explicar esa recurrencia.

## Patrones de diseño que se deben aplicar

* **Observer.** El servidor (o el objeto `HiloServidor` / `NReinas`)
  notifica cambios de estado: detenido, escuchando, resolviendo,
  listo. La ventana Swing (botones Iniciar / Parar, etiqueta de
  estado) es el observador. No usen `Thread.stop()`.
* **Singleton** (recomendado). Puerto, carpeta temporal si generan
  archivo, y tamaño máximo de `n` viven en `Configuracion`.
* **MVC** sencillo: el modelo es el tablero / la solución; la vista
  es tanto el panel local (opcional) como la imagen que se envía
  por HTTP.

## Funcionamiento

1. Ejecutan el programa y tiene una interfaz gráfica
   con un menú Archivo (Tamaño, Hacer, Salir),
   Servidor (Comenzar, Parar)
2. Tamaño permite elegir el tamaño del tablero
3. Hacer ejecuta N-reinas y muestra la solución de forma gráfica
4. Comenzar hace que el servidor web comience  escuchar peticiones HTTP
5. El usuario puede navegar a una dirección y ver el resultado de forma gráfica


## Idea principal para el desarrollo

1. Modelo del tablero: arreglo `int[]` donde el índice es la fila y
   el valor es la columna de la reina, o una matriz `boolean[][]`.
   El método recursivo intenta colocar una reina en la fila `k`,
   recorre columnas, y si `esSeguro(fila, col)` avanza a `k+1`.
   Si no hay columna, retrocede.
2. Para poder pedir un **paso**, vayan registrando cada intento
   (colocar / quitar) en una lista propia de `EstadoTablero`, o
   detengan la recurrencia cuando el contador de intentos llegue
   al `paso` pedido. Logueen fila, columna, n, si avanzó o
   retrocedió.
3. Servidor: `ServerSocket` en un puerto (por ejemplo 8088), un
   thread que acepta conexiones. Leen la primera línea:

   ```
   GET /reinas?n=8 HTTP/1.1
   GET /reinas?n=4&paso=12 HTTP/1.1
   ```

   Parsean la ruta (pueden usar regex). Responden **solo** estos
   `GET`. Otro path: `404`. `n` inválido: `400`.
4. Generan una imagen (por ejemplo `BufferedImage` + `ImageIO`)
   pintando el tablero: casillas, reinas, y si quieren las celdas
   atacadas en otro tono. Luego:

   ```
   HTTP/1.0 200 OK
   Content-Type: image/png
   Content-Length: <bytes>

   <bytes del PNG>
   ```

   No usen Spring, Jetty ni un framework. El socket y el header
   los escriben ustedes.
5. Ventana: Iniciar / Parar el servidor. Un boolean `corriendo`
   para salir del `accept` con timeout o cerrando el
   `ServerSocket`. Observer actualiza el texto “escuchando en
   :8088” / “parado”.
6. Prueben con el navegador:

   ```
   http://localhost:8088/reinas?n=4
   http://localhost:8088/reinas?n=8&paso=20
   ```

## Elementos a revisar

Preguntas posibles en la presentación:

* Escriba en papel el método `colocar(fila)` y `esSeguro`.
* ¿Por qué un tablero 2x2 no tiene solución? ¿Y 3x3?
* ¿Cuántas llamadas recursivas hace, más o menos, un `n=8`?
  ¿Dónde lo logueó?
* Muestre una línea del log de un retroceso (backtrack) con
  contexto: n, fila, columna, número de intento.
* ¿Cómo arma el header HTTP? ¿Qué pasa si no pone
  `Content-Length` o se equivoca el tipo?
* ¿Cómo para el servidor sin `stop()` / `kill()`?
* ¿Quién notifica al Observer cuando llega un request?
* Si le piden `paso=12`, ¿qué hay en el tablero en ese momento?
  ¿Es una solución o un intento a medias?

## Recomendaciones

* Empiecen con `n=4` en consola (imprimir el tablero). Recién
  después armen la imagen y el socket.
* Un `n` mayor a 12 puede tardar mucho. El Singleton debe
  limitar `n` y devolver `400` + log `WARN`.
* Cierren el socket del cliente en un `finally`. Un request mal
  cerrado deja el puerto ocupado o el thread colgado.
* Si generan el PNG en memoria (`ByteArrayOutputStream`) no
  necesitan archivo temporal.
* El log del request: método, URL cruda, n, paso, bytes
  enviados, tiempo. Eso demuestra que entienden el servidor.
* Pueden usar IA para el dibujo de las casillas; el backtracking
  tienen que poder reescribirlo de memoria.
