package tp6.centro;

import java.util.Random;

public class Donante implements Runnable {
    private CentroHemoterapia centro;

    public Donante(CentroHemoterapia unCentro) {
        this.centro = unCentro;
    }

    @Override
    public void run() {
        // Yendo a donar
        centro.entrarSala();
        // Donando
        try {
            Thread.sleep(new Random().nextInt(4000) + 1500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
