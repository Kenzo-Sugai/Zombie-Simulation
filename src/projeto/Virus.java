package projeto;

public class Virus {
    private int Y, X;
    public Virus(int Y, int X){
        this.X = X;
        this.Y = Y;
    }
    
    public boolean infect(int array[][]){
        if(array[Y][X] == 3 || array[Y+1][X] == 3 || array[Y-1][X] == 3 || array[Y][X+1] == 3 || array[Y][X-1] == 3 ||
            array[Y][X] == 4 || array[Y+1][X] == 4 || array[Y-1][X] == 4 || array[Y][X+1] == 4 || array[Y][X-1] == 4){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void setY(int Y){
        this.Y = Y;
    }
    
    public void setX(int X){
        this.X = X;
    }
    
}
