package fila;

import estruturadedados.No;

public class FilaExemplo {
    
    private No primeiro;
    private No ultimo;
    private String nomeDaFila;
    
    public FilaExemplo(){
        this.nomeDaFila = "Fila Teste!";
        this.primeiro = null;
        this.ultimo = null;
    }
    
    public FilaExemplo(String nomeDaFilaNovo){
        this.nomeDaFila = nomeDaFilaNovo;
        this.primeiro = null;
        this.ultimo = null;
    }
    
    public boolean vazio(){
        return this.primeiro == null;
    }
    
    public void enfileira(String item){
        if(vazio()){
            this.primeiro = new No(item);
            this.ultimo = new No(item);
        }else{
            this.ultimo.setProx(new No(item));
            this.ultimo.getProx();
        }
    }
    
    public String desenfileira(){
        if(vazio()){
            return null;
        }else{
            String item = this.primeiro.getDado();
            if(this.primeiro == this.ultimo){
                this.primeiro = null;
                this.ultimo = null;
            }else{
                this.primeiro = this.primeiro.getProx();
            }
            return item;
        }
    }
}
