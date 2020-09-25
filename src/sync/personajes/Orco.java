package sync.personajes;

public class Orco extends Personaje {

    public Orco(int unValor, Vida unaVida){
        super(unValor, unaVida);
    }

    public void atacar(){
        this.vida.descontar(this.valor);
    }

    public void run(){
        for (int i=0;i<10;i++){
            this.atacar();
            try{
                sleep(500);
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
