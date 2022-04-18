package projeto;

public class Pessoa{
   protected int x, y, cor;
   
   public Pessoa(int y, int x, int cor){
       this.x = x;
       this.y = y;
       this.cor = cor;
   }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
   
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCor() {
        return cor;
    }
}
