package projeto;

public class PessoaSaudavel extends Pessoa implements Movable{
    public PessoaSaudavel(int y, int x, int cor){
        super(y, x, cor);
        this.cor = 2;
    }
    
    @Override
    public int MoveUp(int y){
        if(!(y-1 < 1)){
          y -= 1;  
        }
        return y;
    }
    
    @Override
    public int MoveDown(int y){
        if(!(y+1 > 28)){
          y += 1;  
        }
        return y;
    }
    
    @Override
    public int MoveLeft(int x){
        if(!(x-1 < 1)){
          x -= 1;  
        }
        return x;
    }
    
    @Override
    public int MoveRight(int x){
        if(!(x+1 > 88)){
          x += 1;  
        }
        return x;
    }
   
}
