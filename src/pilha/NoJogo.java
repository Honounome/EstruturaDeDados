package pilha;

public class NoJogo {
    private String dado;
    private NoJogo prox;
    
    public NoJogo(String dadoNovo) {
        this.dado = dadoNovo;
        this.prox = null;
    }
    
    public NoJogo(String dadoNovo, NoJogo ligacao) {
        this.dado = dadoNovo;
        this.prox = ligacao;
    }
    
    public String getDado() {
        return dado;
    }
    
    public void setDado(String dado) {
        this.dado = dado;
    }
    
    public NoJogo getProx() {
        return prox;
    }
    
    public void setProx(NoJogo prox) {
        this.prox = prox;
    }
}
