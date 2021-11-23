package fila;

import estruturadedados.No;

public class FilaExemplo {

    private No primeiro;
    private No ultimo;
    private String nomeDaFila;

    public FilaExemplo() {
        nomeDaFila = "Fila Teste!";
        primeiro = ultimo = null;
    }

    public FilaExemplo(String nomeDaFilaNovo) {
        nomeDaFila = nomeDaFilaNovo;
        primeiro = ultimo = null;
    }
    
    public No get() {
        return primeiro;
    }

    public boolean vazio() {
        return primeiro == null;
    }

    public void enfileira(String item) {
        if (vazio()) {
            primeiro = ultimo = new No(item);
        } else {
            ultimo.setProx(new No(item));
            ultimo = ultimo.getProx();
        }
    }

    public String desenfileira() {
        if (vazio()) {
            return null;
        } else {
            String item = primeiro.getDado();
            if (primeiro == ultimo) {
                primeiro = null;
                ultimo = null;
            } else {
                primeiro = primeiro.getProx();
            }
            return item;
        }
    }

    public void toList(String[] vet) {
        for (int i = 0; i < vet.length; i++){
            enfileira(vet[i]);
        }
    }
}
