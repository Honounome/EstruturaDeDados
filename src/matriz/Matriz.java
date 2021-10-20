package matriz;
import java.util.Scanner;

public class Matriz {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int questao;
        
        System.out.print("Insira o número da questão [1-3]: ");
        questao = scan.nextInt();
        switch(questao){
            case 1 -> MatrizExemplo.questao1();
            case 2 -> MatrizExemplo.questao2();
            case 3 -> MatrizExemplo.questao3();
            default -> System.out.println("Questão não existente");
        }
    }
    
}
