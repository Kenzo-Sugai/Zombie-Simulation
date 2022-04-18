package projeto;

public class Projeto {

    public static void main(String[] args) {
        int infect, zomb;
        Mundo m = new Mundo();
        m.CreateMatrix();
        m.insert_pixel();
        while(true){
            m.desenhaMundo();
            m.atualizaMundo();
            infect = m.stats(2);
            zomb = m.stats(3);
            if(infect <= 0 && zomb <= 0){
                break;
            }
            try{
                Thread.sleep(300);
            }
            catch(Exception e){
                System.out.println();
            }
        }
    }
    
}
