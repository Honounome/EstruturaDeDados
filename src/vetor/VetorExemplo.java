package vetor;
import java.util.Scanner;

public class VetorExemplo {
    
    public static void questao1() {
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double nPares = 0, menor, maior;

        menor = nums[0];
        maior = nums[0];
        for (double num : nums) {
            if (num % 2 == 0) {
                nPares++;
            }
            if (num > maior) {
                maior = num;
            }
            if (num < menor) {
                menor = num;
            }
        }
        System.out.printf("nPares: %.0f\nmaior: %.0f\nmenor: %.0f\n", nPares, maior, menor);
    }

    public static void questao2() {
        double[] notas = new double[5];
        Scanner scan = new Scanner(System.in);
        double soma = 0;

        for (int i = 0; i < notas.length; i++) {
            System.out.printf("%dª nota: ", i + 1);
            notas[i] = scan.nextDouble();
            soma += notas[i];
        }

        System.out.printf("Média Geral: %.2f\n", soma / notas.length);
    }

    public static void questao3() {
        double[] notas = new double[5];
        Scanner scan = new Scanner(System.in);
        int cod;

        for (int i = 0; i < notas.length; i++) {
            System.out.printf("%dª nota: ", i + 1);
            notas[i] = scan.nextDouble();
        }

        System.out.print("Código: ");
        cod = scan.nextInt();

        switch (cod) {
            case 0 -> {}
            case 1 -> {
                System.out.print("Vetor em ordem normal:");
                for (double nota : notas)
                    System.out.printf(" %.2f", nota);
            }
            case 2 -> {
                System.out.print("Vetor em ordem inversa:");
                for (int i = notas.length - 1; i >= 0; i--)
                    System.out.printf(" %.2f", notas[i]);
            }
            default -> System.out.println("Código Inválido");
        }
    }

    public static void questao4() {
        int[] nums = new int[10];
        int nImpar = 0;
        int cont = 0;
        for (int i = 0; i < 10; i++) {
            nums[i] = (int) (Math.random() * 51);
            if (nums[i] % 2 == 1) {
                nImpar++;
            }
        }
        int[] impares = new int[nImpar];
        for (int i = 0; i < 10; i++) {
            if (nums[i] % 2 == 1) {
                impares[cont] = nums[i];
                cont++;
            }
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.printf("vetorA[%d]: %d\n", i, nums[i]);
        }
        System.out.printf("\nvetorB[%d] = {%d", nImpar, impares[0]);
        for (int i = 1; i < nImpar; i++) {
            System.out.print(", " + impares[i]);
        }
        System.out.print("}");
    }

}
