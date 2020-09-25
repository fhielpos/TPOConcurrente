# TP 4 -Semaforos
## Ej 6

1. OBLIGATORIO - Retome el ejercicio 3 del TP 3 (Synchronized), cómo mejoraría el ejercicio
para que no haya espera activa o no tenga sleep (xxxx)
    - Diseñar un programa en Java para mostrar las letras A, B y C. Para ello,
utilizaremos 3 hilos, pero se quiere limitar la posible salida de caracteres por
pantalla para que no se produzca de cualquier forma sino con la secuencia
predefinidas: ABBCCC, por ejemplo: ABBCCCABBCCC....
Se recomienda el uso de una variable compartida por los hilos que sirva para
asignar turnos, de manera que cada hilo imprima su letra cuando sea su turno. De
esta forma, cuando se crean los hilos, a cada uno se le asigna un identificador para
que puedan comprobar cuando es su turno y que mientras no sea su turno, no haga
nada.
    - A tener en cuenta: Cada hilo sabe la cantidad de veces que debe imprimir. Por
ejemplo: El hiloB sabe que tiene que imprimir 2 veces la letra B.