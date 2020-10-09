package semaforos.pollos;

public class Mozo extends Empleado {

    public Mozo(Cafeteria unaCafeteria) {
        super(unaCafeteria);
    }

    @Override
    public void run() {
        while(true)
            this.cafeteria.inventar();
    }
}
