package sync.personajes;

public class Main {
    public static void main(String[] args) {
        // Vida
        Vida vida = new Vida(10);

        // Curandero suma 3 de vida
        Curandero curandero = new Curandero(3, vida);
        curandero.setName("Curandero");

        // Orco quita 3 de vida
        Orco orco = new Orco(3, vida);
        orco.setName("Orco");

        curandero.start();
        orco.start();

        try {
            curandero.join();
            orco.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Vida final: " + vida.getVida());
    }

}
