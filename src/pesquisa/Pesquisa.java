package pesquisa;
public class Pesquisa {
    public static void main(String[] args) {
        PesquisaExemplo bLinear = new PesquisaExemplo();
        int[] vetor = {2, 6, 8, 10, 23, 31};
        System.out.println("A posição do valor 10 é: " + bLinear.buscaLinear(10, vetor));
    }
}