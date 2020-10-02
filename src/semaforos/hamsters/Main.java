package semaforos.hamsters;

public class Main {
    public static void main(String[] args) {
        // Definicion de objetos compartidos
        Amenity rueda = new Amenity("rueda");
        Amenity plato = new Amenity("plato");
        Amenity hamaca = new Amenity("hamaca");

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
}
