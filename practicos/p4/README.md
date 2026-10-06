# Práctico 4 - Turnos por socket

## Objetivo principal

Implementar una **cola** y una **pila** propias y usarlas como
núcleo de un servidor de turnos que habla un **protocolo de texto**
por red.

`PEDIR` encola un cliente. `ATENDER` saca de la cola y apila en el
historial. `DESHACER` hace pop del historial y devuelve el turno
a la cola.

El tema de la materia es pila y cola.
El socket solo transporta los comandos.

## Patrones de diseño que se deben aplicar

* **Observer.** Cada comando que cambia la cola o la pila notifica.
  El panel del servidor pinta la cola (quién espera) y el historial
  (quién ya fue atendido).
* **Strategy.** Política de atención: FIFO (sale el primero que
  llegó) o prioridad (`PEDIR Ana PRIORIDAD` se inserta delante de
  los que no tienen prioridad). La cola usa la estrategia al
  encolar; `ATENDER` siempre saca por el frente.
* **Singleton.** Puerto, clave de saludo si la agregan, y el
  contador de números de turno (o el objeto `Caja` única).

## Idea principal para el desarrollo

1. `Cola<E>` con `encolar`, `desencolar`, `verFrente`, `tamano`,
   `estaVacia`. Internamente puede ser lista enlazada propia
   (frente y fin).
2. `Pila<E>` con `push`, `pop`, `tope`, `tamano`. No usen
   `java.util.Stack`.
3. Un turno tiene: número entero, nombre, si es prioridad,
   timestamp. El Singleton (o la caja) asigna el número
   correlativo.
4. Servidor `ServerSocket`. Un cliente (telnet, netcat, Putty
   en modo raw, o un segundo JFrame) envía **una línea por
   comando**. El protocolo **no se cambia**:

   ```
   >>> HOLA
   <<< OK
   >>> PEDIR Ana
   <<< TURNO 7
   >>> PEDIR Luis PRIORIDAD
   <<< TURNO 8
   >>> ATENDER
   <<< ATIENDE 8 Luis
   >>> HISTORIAL
   <<< 1
   <<< 8 Luis
   >>> DESHACER
   <<< OK
   >>> LISTA
   <<< 1
   <<< 7 Ana
   >>> CHAU
   <<< OK
   ```

    * Sin `HOLA` primero: se cierra el socket.
    * Comando desconocido: `ERROR` y el socket **sigue** abierto.
    * `DESHACER` con historial vacío: `ERROR`.
    * `ATENDER` con cola vacía: `ERROR`.
5. `DESHACER`: pop del historial. Deben **justificar** si el
   turno vuelve al frente de la cola (recupera su lugar) o al
   final. Lo coherente es al frente: era el que se estaba
   atendiendo.
6. `LISTA` e `HISTORIAL` empiezan con la cantidad de líneas que
   siguen (igual idea que la pizarra de 2026-01, pero aquí la
   estructura es cola/pila, no figuras).
7. Cada comando se loguea: IP, puerto, texto crudo, tamaño de
   cola, tamaño de pila. `WARN` si el comando está mal formado.
   `ERROR` si falla el IO.

Pueden atender un cliente a la vez al principio. Si les alcanza
el tiempo, un thread por cliente compartiendo la misma cola
(entonces hay que cuidar la exclusión: un `synchronized` sobre
la caja alcanza).

## Elementos a revisar

Preguntas posibles en la presentación:

* Implemente en papel `encolar`, `desencolar`, `push` y `pop`.
* Dibuje la cola y la pila después de: pedir A, pedir B
  prioridad, pedir C, atender, deshacer.
* ¿Por qué BFS usa cola y DFS usa pila? (aunque este práctico
  no recorre árboles, tienen que poder decirlo).
* ¿Dónde está la Strategy? ¿Qué cambia en `encolar` cuando el
  turno es prioridad?
* ¿Por qué no usaron `ArrayList` como cola? ¿Cuál es el costo
  de sacar el primero en un arreglo vs una cola con puntero
  al frente?
* Un comando `PEDIR` sin nombre: ¿qué responde y qué loguea?
* ¿Cómo se enteró el panel de que llegó `ATENDER`? Muestre
  `notifyObservers` / `update`.
* Si dos clientes piden a la vez, ¿pueden chocar los números
  de turno?

## Recomendaciones

* Lean líneas con `BufferedReader.readLine()` y escriban con
  `PrintWriter` + `flush`. Sin `flush` telnet no ve el `OK`.
* Prueben el protocolo a mano **antes** de hacer la interfaz
  linda. Si el handshake no funciona, el resto no se evalúa.
* No copien el protocolo de la pizarra de figuras. Si mandan
  `FIGURA` o `CUADRADO` es el práctico equivocado.
* El Observer observa la **caja** (cola + pila), no el socket.
  El socket solo traduce texto a llamadas `pedir` / `atender`.
* Documenten en un comentario o en el README del proyecto
  dónde vuelve el turno al deshacer, y por qué.
