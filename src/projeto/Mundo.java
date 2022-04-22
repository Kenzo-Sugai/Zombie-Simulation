package projeto;
import java.util.Random;
import java.util.Date;
public class Mundo {
    Date start = new Date(); // Timer start
    Random rd = new Random(); // Random
    private int X, Y, cor, safe = 0, infect = 0, zomb = 0, dead = 0; // Variaveis cordanadas e contadores
    private long sec = 0, min = 0; // Variaveis contadores de tempo
    private Pessoa[] pessoas = new Pessoa[102]; // Array de pessoas
    private int[][] mapa = new int[30][90]; // Mundo
    private int[][] mapa_original = new int[30][90]; // Copia do Mundo
    private boolean confirm = false; // booleano para não contar + de 1 minuto
    Hospital H1 = new Hospital(5, 9, 5, 13); // Hospital 1
    Hospital H2 = new Hospital(11, 15, 70, 78); // Hospital 2
    Hospital H3 = new Hospital(22, 26, 36, 44); // Hospital 3
    
    public void Timer(){ // Timer
        Date end = new Date();
        long timer = (end.getTime()-start.getTime())/1000;
        sec = timer;
        sec %= 60;
        if(sec == 59 && confirm == false){
            min++;
            confirm = true;
        }
        if(sec == 0){
            confirm = false;
        }
        System.out.println("Tempo: "+min+":"+sec);
    }
    
    public int Timer_sec(){
        Date end = new Date();
        long timer = (end.getTime()-start.getTime())/1000;
        int seconds = (int)timer;
        return seconds;
    }
    
    
    public void CreateMatrix(){ // Criação do mundo [dinamicamente]
        
        // Criação da Matriz (Alocação de borda e espaços vazios)
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[i].length; j++){
                if(i == 0 || i == mapa.length-1 || j == 0 || j == mapa[i].length-1){
                    mapa[i][j] = 1;
                    mapa_original[i][j] = 1;
                }
                else{
                    mapa[i][j] = 0;
                    mapa_original[i][j] = 0;
                }
            }
        }
        
        // Criação dos hospitais
        H1.CreateHospital(mapa, mapa_original);
        H2.CreateHospital(mapa, mapa_original);
        H3.CreateHospital(mapa, mapa_original);
    
    }
    public void insert_pixel(){ // Inserção dos objetos PessoaSaudavel e PessoaDoente no mundo
        
        for(int i = 0; i < 102; i++){
            Y = rd.nextInt(1, 29);
            X = rd.nextInt(1, 89);
            while(mapa[Y][X] == 2 || mapa[Y][X] == 3){
                Y = rd.nextInt(1, 29);
                X = rd.nextInt(1, 89);
            }
            if(i < 100){
                PessoaSaudavel p = new PessoaSaudavel(Y, X, 0);
                pessoas[i] = p;
            }
            else{
                PessoaDoente p = new PessoaDoente(Y, X, 0);
                pessoas[i] = p;
            } 
            Y = pessoas[i].getY(); 
            X = pessoas[i].getX();
            cor = pessoas[i].getCor();
            mapa[Y][X] = cor;
        }
    }
    public void atualizaMundo(){ // Movimentação das pessoas e troca de estado PessoaSaudavel -> PessoaDoente
        
        for(int i = 0; i < 102; i++){
            int m = rd.nextInt(1, 5);
            X = pessoas[i].getX();
            Y = pessoas[i].getY();      
            cor = pessoas[i].getCor();
            Virus v = new Virus(Y, X);
            String tipo = pessoas[i].getClass().getSimpleName();
            if(tipo.equals("PessoaDoente")){
               int p = ((PessoaDoente)pessoas[i]).getTime();
               if(p == 15){
                    pessoas[i] = new Zumbi(Y, X, 4);
               } 
            }
            if(tipo.equals("Zumbi")){
                int z = ((Zumbi)pessoas[i]).getTime();
                if(z == 60){
                    pessoas[i] = new PessoaMorta(Y, X, 7);
                    mapa_original[Y][X] = 7;
                }
            }
            mapa[Y][X] = mapa_original[Y][X];
            if(tipo.equals("PessoaSaudavel")){
                switch(m){
                    case 1: // Up
                        Y = ((PessoaSaudavel)pessoas[i]).MoveUp(Y);
                        v.setY(Y);
                        if(v.infect(mapa)){
                            pessoas[i] = new PessoaDoente(Y, X, 3);
                        }
                        pessoas[i].setY(Y);
                        mapa[Y][X] = cor;
                        break;
                    case 2: // Down
                        Y = ((PessoaSaudavel)pessoas[i]).MoveDown(Y);
                        v.setY(Y);
                        if(v.infect(mapa)){
                            pessoas[i] = new PessoaDoente(Y, X, 3);
                        }
                        pessoas[i].setY(Y);
                        mapa[Y][X] = cor;
                        break; 
                    case 3: // Left
                        X = ((PessoaSaudavel)pessoas[i]).MoveLeft(X);
                        v.setX(X);
                        if(v.infect(mapa)){
                            pessoas[i] = new PessoaDoente(Y, X, 3);
                        }
                        pessoas[i].setX(X);
                        mapa[Y][X] = cor;
                        break;
                    case 4: // Right
                        X = ((PessoaSaudavel)pessoas[i]).MoveRight(X);
                        v.setX(X);
                        if(v.infect(mapa)){
                            pessoas[i] = new PessoaDoente(Y, X, 3);
                        }
                        pessoas[i].setX(X);
                        mapa[Y][X] = cor;
                        break;
                }
            }
            if(tipo.equals("PessoaDoente")){
                if(m == 1){ // Up
                    Y = ((PessoaDoente)pessoas[i]).MoveUp(Y);
                    if(H1.ocupacao(mapa, Y, X) == 1 || H2.ocupacao(mapa, Y, X) == 1 || H3.ocupacao(mapa, Y, X) == 1 ||
                       H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                        pessoas[i] = new PessoaSaudavel(Y, X, 0);
                    }
                    pessoas[i].setY(Y);
                    mapa[Y][X] = cor;
                }
                if(m == 2){ // Down
                    Y = ((PessoaDoente)pessoas[i]).MoveDown(Y);
                    if(H1.ocupacao(mapa, Y, X) == 1 || H2.ocupacao(mapa, Y, X) == 1 || H3.ocupacao(mapa, Y, X) == 1 ||
                       H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                        pessoas[i] = new PessoaSaudavel(Y, X, 0);
                    }
                    pessoas[i].setY(Y);
                    mapa[Y][X] = cor;
                } 
                if(m == 3){ // Left
                    X = ((PessoaDoente)pessoas[i]).MoveLeft(X);
                    if(H1.ocupacao(mapa, Y, X) == 1 || H2.ocupacao(mapa, Y, X) == 1 || H3.ocupacao(mapa, Y, X) == 1 ||
                       H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                        pessoas[i] = new PessoaSaudavel(Y, X, 0);
                    }
                    pessoas[i].setX(X);
                    mapa[Y][X] = cor;
                }
                if(m == 4){ // Right
                    X = ((PessoaDoente)pessoas[i]).MoveRight(X);
                    if(H1.ocupacao(mapa, Y, X) == 1 || H2.ocupacao(mapa, Y, X) == 1 || H3.ocupacao(mapa, Y, X) == 1 ||
                       H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                        pessoas[i] = new PessoaSaudavel(Y, X, 0);
                    }
                    pessoas[i].setX(X);
                    mapa[Y][X] = cor;
                }
            }
            if(tipo.equals("Zumbi")){
                switch(m){
                    case 1:
                        Y = ((PessoaDoente)pessoas[i]).MoveUp(Y);
                        if(H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                            pessoas[i] = new PessoaSaudavel(Y, X, 0);
                        }
                        pessoas[i].setY(Y);
                        mapa[Y][X] = cor;
                        break;
                    case 2:
                        Y = ((PessoaDoente)pessoas[i]).MoveDown(Y);
                        if(H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                            pessoas[i] = new PessoaSaudavel(Y, X, 0);
                        }
                        pessoas[i].setY(Y);
                        mapa[Y][X] = cor;
                        break;
                    case 3:
                        X = ((PessoaDoente)pessoas[i]).MoveLeft(X);
                        if(H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                            pessoas[i] = new PessoaSaudavel(Y, X, 0);
                        }
                        pessoas[i].setX(X);
                        mapa[Y][X] = cor;
                        break;
                    case 4:
                        X = ((PessoaDoente)pessoas[i]).MoveRight(X);
                        if(H1.ocupacao(mapa, Y, X) == 2 || H2.ocupacao(mapa, Y, X) == 2 || H3.ocupacao(mapa, Y, X) == 2){
                            pessoas[i] = new PessoaSaudavel(Y, X, 0);
                        }
                        pessoas[i].setX(X);
                        mapa[Y][X] = cor;
                        break;
                }
                     
            }
        }
        safe = 0;
        infect = 0;
        zomb = 0;
        dead = 0;
        for(int i = 0; i < 102; i++){
            String tipo = pessoas[i].getClass().getSimpleName();
            if(tipo.equals("PessoaSaudavel")){
                safe++;
            }
            if(tipo.equals("PessoaDoente")){
                infect++;
            }
            if(tipo.equals("Zumbi")){
                zomb++;
            }
            if(tipo.equals("PessoaMorta")){
                dead++;
            }
        }
    }
    public void desenhaMundo(){ // Print do mundo no terminal
        Timer();
        System.out.println("\033[46m \033[0m Saudaveis: "+safe+" \033[44m \033[0m Doentes: "+infect+" \033[43m \033[0m Zumbis: "+zomb+" \033[41m \033[0m Mortos: "+dead);
        vacina();
        System.out.println("\033[47m \033[0m Lotacao:  Hospital 1: "+H1.lotacao(mapa)+"  Hospital 2: "+H2.lotacao(mapa)+"  Hospital 3: "+H3.lotacao(mapa));
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[i].length; j++){
                switch(mapa[i][j]){
                    case 0: // Espaço vazio
                        System.out.print(" ");
                        break;
                    case 1: // Bordas
                        System.out.print("\033[42m \033[0m");
                        break;
                    case 2: // PessoaSaudavel
                        System.out.print("\033[46m \033[0m");
                        break;
                    case 3: // PessoaDoente
                        System.out.print("\033[44m \033[0m");
                        break;
                    case 4: // Zumbi
                        System.out.print("\033[43m \033[0m");
                        break;
                    case 5: // Hospital
                        System.out.print("\033[47m \033[0m");
                        break;
                    case 6: // Hospital Cruz
                        System.out.print("\033[45m \033[0m");
                        break;
                    case 7: // Morto
                        System.out.print("\033[41m \033[0m");
                        break;
                }
            }
            System.out.println();
        }
    }
    
    public void vacina(){
        if(Timer_sec() <= 200){
            System.out.println("Vacina em: "+(200-Timer_sec()));
        }
        else{
            System.out.println("Vacina chegou!");
        }
    }
    
    public int stats(int n){
        switch(n){
            case 1:
                return safe;
            case 2:
                return infect;
            case 3:
                return zomb;
            case 4:
                return dead;
        }
        return 0;
    }
}