package semaforos.hamsters;

public class Hamster implements Runnable {
    private Amenity rueda;
    private Amenity platoComida;
    private Amenity hamaca;

    public Hamster(Amenity unaRueda, Amenity unPlato, Amenity unaHamaca) {
        this.rueda = unaRueda;
        this.hamaca = unaHamaca;
        this.platoComida = unPlato;
    }

    public void run() {
        try {
            // Usar rueda
            this.rueda.usar();
            // Usar hamaca
            this.hamaca.usar();
            // Usar el plato
            this.platoComida.usar();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
