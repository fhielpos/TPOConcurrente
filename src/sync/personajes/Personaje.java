package sync.personajes;

public abstract class Personaje extends Thread {
    public int valor;
    public Vida vida;

    public Personaje(int unValor, Vida unaVida) {
        this.valor = unValor;
        this.vida = unaVida;
    }

    public int getValor() {
        return this.valor;
    }

}
