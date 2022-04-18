package projeto;
import java.util.Date;

public class Hospital {
    private int y1, y2, x1, x2;
    Date start = new Date();
    public Hospital(int y1, int y2, int x1, int x2){
        this.y1 = y1;
        this.y2 = y2;
        this.x1 = x1;
        this.x2 = x2;
    }

    public int getTime(){
        Date end = new Date();
        long timer = (end.getTime()-start.getTime())/1000;
        return (int)timer;
    }
    
    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }
    
    public void CreateHospital(int array[][], int array_o[][]){
        for(int i = y1; i <= y2; i++){
            for(int j = x1; j <= x2; j++){
                if((i == y1+2 && j != x1 && j != x1+1 && j != x2 && j != x2-1) || (j == x1+4 && i != y1 && i != y2)){
                    array[i][j] = 6;
                    array_o[i][j] = 6;
                }
                else{
                    array[i][j] = 5;
                    array_o[i][j] = 5;  
                }
            }
        }
    }
    
    public int lotacao(int array[][]){
        int qnt = 0;
        for(int i = y1; i <= y2; i++){
            for(int j = x1; j <= x2; j++){
                if(array[i][j] == 2 || array[i][j] == 3 || array[i][j] == 4){
                    qnt++;
                }
            }
        }
        return qnt;
    }
    
    public int ocupacao(int array[][], int Y, int X){
        boolean verify = false;
        for(int i = y1; i <= y2; i++){
            for(int j = x1; j <= x2; j++){
                if(array[i][j] == array[Y][X]){
                    verify = true;
                    break;
                }
            }
        }
        if(verify == true && getTime() <= 200){
            return 1;
        }
        if(verify == true && getTime() > 200){
            return 2;
        }
        return 0;
    }
}
