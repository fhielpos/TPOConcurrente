# TP 3 - Synchronized

## Ej 8
## Enunciado

    8)En una tienda de mascotas están teniendo problemas para tener a todos sus hámster felices.
    Los hámster comparten una jaula en la que hay un plato con comida, una rueda para hacer
    ejercicio, y una hamaca en la que pueden descansar. Todos los hamsters quieren comer del
    plato, correr en la rueda y luego descansar en la hamaca. Pero se encuentran con el
    inconveniente de que solo 1 de ellos puede comer del plato, solo uno puede correr en la
    rueda y solo 1 puede descansar en la hamaca.
    a) Implemente un programa para simular la situación planteada, en donde todos los
    hámster puedan realizar todas las actividades.
    Nota : considere que todas las actividades consumen cierto tiempo, por lo que para
    la simulación se sugiere asignar ese tiempo con “sleep()".
    
## Solucion (main)

```java
public class Main {
    public static void main(String[] args) {
        // Definicion de objetos compartidos
        Rueda rueda = new Rueda();
        Plato plato = new Plato();
        Hamaca hamaca = new Hamaca();

        // Definicion de hilos y hamsters
        int cantHamsters = 5;
        Thread hilosHamsters[] = new Thread[cantHamsters];
        Hamster hamsters[] = new Hamster[cantHamsters];

        // Inicializar hilos y hamsters
        for (int i = 0; i < cantHamsters; i++) {
            hamsters[i] = new Hamster(rueda, plato, hamaca);
            hilosHamsters[i] = new Thread(hamsters[i], "Hamster " + i);
            hilosHamsters[i].start();
        }
    }
```
    
## Salida del programa

```log
Hamster 0 usando la rueda...
Hamster 2 usando la rueda...
Hamster 0 usando la hamaca...
Hamster 0 comiendo...
Hamster 3 usando la rueda...
Hamster 2 usando la hamaca...
Hamster 2 comiendo...
Hamster 3 usando la hamaca...
Hamster 4 usando la rueda...
Hamster 1 usando la rueda...
Hamster 3 comiendo...
Hamster 4 usando la hamaca...
Hamster 4 comiendo...
Hamster 1 usando la hamaca...
Hamster 1 comiendo...

Process finished with exit code 0

```