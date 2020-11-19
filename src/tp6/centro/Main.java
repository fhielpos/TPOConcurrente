package tp6.centro;

public class Main {
    public static void main(String[] args) {
        CentroHemoterapia unCentro = new CentroHemoterapia(12, 4, 9);

        int cantDonadores = 50;
        Thread[] hilosDonadores = new Thread[cantDonadores];

        for (int i = 0; i < cantDonadores; i++) {
            hilosDonadores[i] = new Thread(new Donante(unCentro), "DONANTE " + i);
            hilosDonadores[i].start();
        }
    }
}