package sync.personajes;

public class Curandero extends Personaje{

    public Curandero(int unValor, Vida unaVida) {
        super(unValor, unaVida);

    }

    public void curar(){
        this.vida.curar(this.valor);
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            this.curar();
            try{
                sleep(250);
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
