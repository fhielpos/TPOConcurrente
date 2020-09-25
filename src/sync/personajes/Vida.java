package sync.personajes;

public class Vida {
    private int vida;

    public Vida(int valorVida) {
        this.vida = valorVida;
    }

    public int getVida() {
        return this.vida;
    }

    public synchronized void descontar(int valor) {
        this.vida = this.vida - valor;
        System.out.println(Thread.currentThread().getName() + " atacando");
    }

    public synchronized void curar(int valor) {
        this.vida = this.vida + valor;
        System.out.println(Thread.currentThread().getName() +" curando");
    }
}
