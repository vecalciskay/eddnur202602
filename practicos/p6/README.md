# Práctico 6 - Calculadora con árbol binario

## Objetivo principal

Leer una expresión aritmética, construir el **árbol binario**
que la representa, **dibujarlo** y **evaluarlo**.

Ejemplo: `(4/3)*(6-(8+4.3))`.

El tema de la materia es el árbol binario y los recorridos.
Es una aplicación de escritorio donde el árbol se ve y se explica.

## Patrones de diseño que se deben aplicar

* **Observer.** Al parsear o al evaluar, el modelo
  (`Expresion` / `ArbolBinario`) notifica. El panel del
  árbol y el del resultado se refrescan solos.
* **MVC.** Modelo = árbol. Vista = dibujo + texto del
  resultado. Controlador = campo de texto + botón Evaluar.

## Idea principal para el desarrollo

1. Armar el árbol respetando los paréntesis como se hizo en el curso.
2. Cada nodo interno es un operador y tiene hijo izquierdo
   y derecho. Cada hoja es un número. No hay tercer hijo:
   es árbol **binario**.
3. Evaluar en postorden:

   ```
   valor(nodo):
     si es hoja → devolver el número
     a = valor(izquierdo)
     b = valor(derecho)
     devolver a (operador) b
   ```

   Logueen cada nodo: operador o número, valores de los
   hijos, resultado parcial.
5. Dibujar el árbol como en clases (nodo, líneas a los
   hijos). Al hacer clic en el nodo se debe poder ver el resultado de evaluar la expresión a partir de ese nodo solamente.
6. Cuando el usuario coloca una expresión que no es correcta el sistema le indica exactamente porqué no es una expresión correcta indicando la razón y el lugar en la expresión donde se detectó el error.

Interfaz mínima: campo de texto, Evaluar, panel del árbol,
label del resultado. Ejemplos para probar:

```
3+4*5
(3+4)*5
(4/3)*(6-(8+4.3))
10-4-3
```

## Elementos a revisar

Preguntas posibles en la presentación:

* Dibuje el árbol de `3+(4*5)`. 
* Evalúe a mano en postorden el árbol que dibujó. Diga
  el resultado de cada nodo.
* Escriba `evaluar(nodo)` en papel.
* Una expresión inválida: `3+*4` o `(2+3`. ¿Qué loguea
  y qué ve el usuario?
* `10-4-3` ¿da 3 o 9? ¿Su árbol es asociativo a la
  izquierda?
* ¿El Observer se dispara al parsear, al evaluar, o en
  los dos? ¿Quién es el sujeto?

