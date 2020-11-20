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
            System.out.println(Thread.currentThread().getName() + " donando...");
            Thread.sleep(new Random().nextInt(4000) + 2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Saliendo del centro
        System.out.println(Thread.currentThread().getName() + " saliendo");
        centro.salir();
    }
}
