package projeto;
import java.util.Date;
public class Zumbi extends PessoaDoente{
    Date start = new Date();
    public Zumbi(int y, int x, int cor){
        super(y, x, cor);
        this.cor = 4;
    }

    @Override
    public int getTime(){
        Date end = new Date();
        long timer = (end.getTime()-start.getTime())/1000;
        return (int)timer;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }
    
    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getCor() {
        return cor;
    }

    @Override
    public void setCor(int cor) {
        this.cor = cor;
    }
    
    
}
