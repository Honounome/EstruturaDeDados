package estruturadedados;

public class No {
    
    private String dado;
    private No prox;
    
    public No(String dadoNovo) {
        this.dado = dadoNovo;
        this.prox = null;
    }
    
    public No(String dadoNovo, No ligacao) {
        this.dado = dadoNovo;
        this.prox = ligacao;
    }
    
    public String getDado() {
        return dado;
    }
    
    public void setDado(String dado) {
        this.dado = dado;
    }
    
    public No getProx() {
        return prox;
    }
    
    public void setProx(No prox) {
        this.prox = prox;
    }
}