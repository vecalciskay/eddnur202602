# Práctico 5 - Quadtree de una imagen

## Objetivo principal

Construir un **árbol** a partir de una foto: si una región de la
imagen no es suficientemente homogénea, se parte en **cuatro**
cuadrantes, y así en recursión. El resultado es un quadtree
(árbol donde cada nodo interno tiene exactamente 4 hijos).

Después deben **dibujar** el árbol y **reconstruir** una versión
pixelada de la imagen recorriendo las hojas.

El tema de la materia es el árbol dinámico y la recurrencia.
La imagen es la fuente de los nodos, no un filtro más.

## Patrones de diseño que se deben aplicar

* **Observer.** El modelo (`QuadTree` o la imagen + árbol)
  notifica cuando se recalcula (otro umbral, otra foto).
  Los paneles (foto original, foto reconstruida, dibujo del
  árbol) observan y se redibujan.
* **MVC.** El árbol es el modelo. Tres vistas pueden observar
  el mismo sujeto.
* **Singleton** (recomendado). Umbral por defecto, profundidad
  máxima, última ruta de archivo.

## Idea principal para el desarrollo

1. Cargar la imagen a matriz (tonos de gris solamente). Trabajar sobre un cuadrado: recorten al
   menor lado o rellenen; un quadtree se entiende mejor
   si el lado es potencia de 2, pero no es obligatorio.
2. Un nodo guarda: origen `(x, y)`, lado, color
   representativo (promedio de la región), y hasta 4 hijos
   (NO, NE, SO, SE). Hoja = sin hijos.
3. Recurrencia:

   * Caso base: el lado es 1, o la profundidad llegó al
     máximo, o la región es **homogénea**.
   * Homogénea: midan la variación (por ejemplo la diferencia
     max-min de gris, y comparen con un **umbral** que el usuario mueve.
   * Si no es homogénea: crean 4 hijos con lado/2 y llaman
     al mismo método en cada uno.
4. Color de una hoja: promedio de gris de su rectángulo.
   Al reconstruir, pintan ese rectángulo de un solo color.
   Eso es la imagen “pixelada” que produce el árbol.
5. Recorrido del árbol (preorden alcanza) para:
   * contar nodos y hojas (log `INFO` al terminar);
   * reconstruir la imagen;
   * dibujar el árbol en un panel, como se dibujó en clases
     (padre arriba, cuatro hijos abajo). Si el árbol es
     muy ancho, dibujen también los cuadrados sobre la
     foto (bordes de cada hoja).
6. Interfaz: cargar imagen, slider de umbral, checkbox
   “mostrar divisiones”, panel original / reconstruido /
   árbol. Al mover el umbral se vuelve a construir el
   árbol y se notifica.

Logs: profundidad, `(x,y,lado)`, si partió o quedó hoja,
umbral, total de nodos. Un `DEBUG` por nodo está bien en
una imagen chica; en una grande, `DEBUG` solo hasta cierta
profundidad.

## Elementos a revisar

Preguntas posibles en la presentación:

* Escriba el método recursivo `construir(x, y, lado, profundidad)`.
  ¿Cuál es el caso base?
* Un cuadrado de 4x4 con dos colores muy distintos: dibuje
  el árbol que debería salir con umbral bajo y con umbral alto.
* ¿Cómo reconstruyen la imagen a partir del árbol? ¿Qué
  recorrido usan?
* ¿Dónde está el Observer? Si muevo el umbral, ¿quién avisa
  a los tres paneles?
* Complejidad: en el peor caso (nunca homogéneo), ¿cuántas
  hojas tiene una imagen de lado `2^k`?

## Recomendaciones

* Empiecen con una imagen de 64x64 o 128x128. Un JPG de
  4000 px les arma un árbol que no se puede dibujar ni
  explicar.
* El umbral es el parámetro que hace visible la estructura:
  umbral alto → pocas hojas, bloques grandes; umbral bajo →
  casi un píxel por hoja. Prepárense para mostrarlo.
* No reutilicen el filtro de convolución del práctico 4
  como “el árbol”. Aquí el objeto principal es el nodo
  con cuatro hijos.
* Cuiden la división impar: si el lado es 5, definan qué
  hacen (recortar a 4, o lados `lado/2` y `lado - lado/2`).
* El dibujo del árbol puede limitarse a los primeros
  niveles y, en paralelo, dibujar los rectángulos sobre
  la foto. Las dos vistas se complementan.
