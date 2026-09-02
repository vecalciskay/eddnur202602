# Práctico 3 - Convolución de imágenes

## Fecha de Presentación
La fecha de presentación de este práctico es 19 de septiembre de 2026

## Objetivo principal

Cargar una imagen (JPG/PNG), tratarla como **matriz de píxeles**
(como en el capítulo de estructuras estáticas no lineales) y
aplicarle **convolución** con un kernel para **suavizar** o
**realzar** (el “al revés” del suavizado).

Tienen que programar el recorre-píxel y la suma ponderada.
No vale `ConvolveOp`, `Kernel` de Java2D ni un filtro de
`Graphics2D` como solución del algoritmo.

## Patrones de diseño que se deben aplicar

* **Strategy.** Cada kernel es una estrategia: suavizado caja 3x3,
  suavizado gaussiano 3x3 (o 5x5), realce (sharpen), y opcional
  un kernel que el usuario edita. El método `aplicar(imagen)`
  no conoce los números del kernel; se los pide a la Strategy.
* **Observer.** La imagen (el modelo) notifica cuando cambia.
  El panel muestra el resultado porque recibió `update`.
* **MVC.** Modelo = canales R, G, B (o una matriz de enteros).
  Vista = panel. Controlador = botones Cargar / Suavizar /
  Realzar / Restaurar.

## Idea principal para el desarrollo

1. Leer el archivo con `ImageIO` y pasar cada píxel a tres
   matrices (rojo, verde, azul) o a una sola matriz de enteros,
   como en el texto del curso. Guardar también una copia
   “original” para poder volver atrás.
2. Un kernel es una matriz chica, normalmente 3x3, de
   coeficientes. La convolución, para el píxel `(x, y)` y un
   canal, es:

   ```
   suma = 0
   para i = -r .. r
     para j = -r .. r
       suma += canal[x+i][y+j] * kernel[i+r][j+r]
   nuevo = acotar(suma, 0, 255)
   ```

   `r` es la mitad del lado (1 si el kernel es 3x3).
3. Kernels que deben implementar:

   Suavizado (media / caja):

   ```
   1/9  1/9  1/9
   1/9  1/9  1/9
   1/9  1/9  1/9
   ```

   Suavizado gaussiano (aproximado):

   ```
   1/16  2/16  1/16
   2/16  4/16  2/16
   1/16  2/16  1/16
   ```

   Realce (sharpen):

   ```
    0  -1   0
   -1   5  -1
    0  -1   0
   ```

   La idea del realce: se resta a la imagen una versión
   suavizada (o se usa un kernel que exagera el centro).
   Por eso “al revés” del suavizado: el suavizado mezcla
   vecinos; el realce acentúa la diferencia con los vecinos.
4. Bordes: no pueden leer `(-1, 0)`. Elijan **una** política
   y déjenla escrita: repetir el píxel del borde, o no
   procesar el marco de `r` píxeles, o asumir 0. Logueen
   cuál usaron.
5. La convolución se aplica **por canal**. Si suavizan solo
   el promedio de gris, pierden el color: no es lo pedido.
6. Interfaz: cargar archivo, elegir Strategy en un combo,
   aplicar, ver original y resultado (dos paneles o un
   checkbox). Observer: al terminar el filtro, el panel
   resultado se refresca.
7. Logs: archivo, ancho, alto, nombre de la Strategy, tamaño
   del kernel, tiempo, política de borde. Un `DEBUG` por
   imagen, no por píxel (millones de líneas no se pueden
   leer).

## Elementos a revisar

Preguntas posibles en la presentación:

* Escriba en papel el doble `for` de la convolución para un
  kernel 3x3. ¿Cuántas multiplicaciones hay por píxel?
  ¿Y para toda la imagen de `w * h`? Complejidad.
* Tome el píxel central de esta vecindad de rojos y el
  kernel caja. ¿Cuál es el nuevo valor?

  ```
  10 20 30
  40 50 60
  70 80 90
  ```
* ¿Por qué el kernel de realce tiene 5 en el centro y -1
  alrededor? ¿Qué pasaría si el centro fuera 1 y el resto 0?
* ¿Dónde está la Strategy? ¿Qué hay que crear para agregar
  un kernel 5x5 sin tocar el panel?
* Muestre que el algoritmo opera sobre las matrices, no
  sobre `g.drawImage` con un filtro de Java.
* ¿Qué hacen en el borde? ¿Por qué no se van de rango?
* El Observer: ¿quién llama a `setChanged` / `notifyObservers`
  y en qué momento?

## Recomendaciones

* Trabajen sobre una **copia** de las matrices. Si escriben
  el resultado encima del origen, el kernel usa píxeles ya
  filtrados y el resultado se “barre”.
* Acoten a 0..255. Un sharpen se pasa de 255 o se va a
  negativo con facilidad.
* Para probar a mano, usen una imagen chica (o un recorte).
  En la revisión pueden pedir el cálculo de un solo píxel.
* Singleton opcional: última carpeta abierta, último kernel.
* Si la IA les genera `ConvolveOp`, bórrenlo. Ese no es el
  práctico.
* El histograma del curso no es obligatorio; si les sobra
  tiempo, un histograma antes/después ayuda a ver el suavizado.