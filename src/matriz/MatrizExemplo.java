package matriz;
import java.util.Scanner;

public class MatrizExemplo {
    private double[][] notas = {{9.9, 5.9, 9.0}, {6.0, 4.9}};

    public double[][] getNotas() {
        return notas;
    }

    public void setNotas(double[][] notas) {
        this.notas = notas;
    }
    
    public void percorrerMatriz() {
        for(int i = 0; i < notas.length; i++) {
            for(int j = 0; j < notas[i].length; j++)
                System.out.printf("%d:%d = %.1f%n", i, j, notas[i][j]);
        }
    }
    
    public void percorrerMatriz(double valor) {
        for(int i = 0; i < notas.length; i++) {
            for(int j = 0; j < notas[i].length; j++)
                if(notas[i][j] == valor)
                    System.out.printf("A nota %.1f está na posicao (%d, %d)", notas[i][j], i, j);
        }
    }
    
    public static void questao1() {
        Scanner scan = new Scanner(System.in);
        int[][] qst1 = new int[4][4];
        int num, maior = 0;
        
        System.out.println("Insira o valor para as posições:");
        for(int i = 0; i < qst1.length; i++) {
            for(int j = 0; j < qst1[i].length; j++) {
                if(i==0 && j==0)
                    maior = qst1[i][j];
                System.out.printf("[%d,%d] -> ", i, j);
                qst1[i][j] = scan.nextInt();
                if(qst1[i][j] > maior)
                    maior = qst1[i][j];
            }
        }
        
        System.out.println("Qual numero você quer encontrar na matriz?");
        num = scan.nextInt();
        
        for(int i=0; i<qst1.length; i++) {
            for(int j=0; j<qst1[i].length; j++) {
                System.out.printf(String.format("%%%dd", Integer.toString(maior).length()) + " ", qst1[i][j]);
            }
            System.out.println();
        }
        
        System.out.println("\nO maior elemento da matriz é " + maior);
        for(int i=0; i<qst1.length; i++) {
            for(int j=0; j<qst1[i].length; j++) {
                if(num==qst1[i][j]) {
                    System.out.printf("O valor %d está na posição [%d, %d]%n", num, i, j);
                    return;
                }
            }
        }
        System.out.printf("Valor %d não encontrado na matriz%n", num);
    }
    
    public static void questao2() {
        int[][] qst2 = new int[10][10];
        String matriz = "";
        
        for(int i = 0; i < qst2.length; i++) {
            for(int j = 0; j < qst2[i].length; j++) {
                if(i<j)
                    qst2[i][j] = 2*i + 7*j - 2;
                else if(i==j)
                    qst2[i][j] = 3*i*i - 1;
                else
                    qst2[i][j] = 4*i*i*i - 5*j*j + 1;
                matriz += String.format("%4d ", qst2[i][j]);
            }
            matriz += "\n";
        }
        System.out.print(matriz);
    }
    
    public static void questao3() {
        Scanner scan = new Scanner(System.in);
        int[][] qst3 = new int[3][3];
        int som = 0, sub = 0, mut = 1;
        System.out.println("Insira o valor para as posições:");
        for(int i = 0; i < qst3.length; i++) {
            for(int j = 0; j < qst3[i].length; j++) {
                System.out.printf("[%d,%d] -> ", i, j);
                qst3[i][j] = scan.nextInt();
                if(i<j)
                    som += qst3[i][j];
                else if(i>j)
                    sub -= qst3[i][j];
                else
                    mut *= qst3[i][j];
            }
        }
        System.out.printf("Soma: %d%nSubtração: %d%nMultiplicação: %d%n", som, sub, mut);
    }
}
