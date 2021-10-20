package vetor;
import java.util.Scanner;

public class Vetor {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int questao;
        
        System.out.print("Insira o número da questão [1-4]: ");
        questao = scan.nextInt();
        switch(questao){
            case 1 -> VetorExemplo.questao1();
            case 2 -> VetorExemplo.questao2();
            case 3 -> VetorExemplo.questao3();
            case 4 -> VetorExemplo.questao4();
            default -> System.out.println("Questão não existente");
        }
    }
    
}
