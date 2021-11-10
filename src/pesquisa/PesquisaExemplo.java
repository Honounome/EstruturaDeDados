package pesquisa;
public class PesquisaExemplo {
    public int buscaLinear(int num, int[] pesqVetor){
        int posicao = -1;
        for(int i = 0; i < pesqVetor.length; i++){
            if(num == pesqVetor[i]){
                posicao = i;
                break;
            }
        }
        return posicao;
    }
}