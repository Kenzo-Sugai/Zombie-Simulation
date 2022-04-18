package projeto;

import java.util.Date;

public class PessoaMorta extends Zumbi {
    
    public PessoaMorta(int y, int x, int cor){
        super(y, x, cor);
        this.y = y;
        this.x = x;
        this.cor = 7;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    
    
}
