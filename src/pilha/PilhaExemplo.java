package pilha;

import estruturadedados.No;

public class PilhaExemplo {
    
    private No base;
    private No topo;
    private int tam = 0;
    
    public PilhaExemplo() {
        base = topo = null;
    }
    
    public No get() {
        return topo;
    }
    
    public int tam() {
        return tam;
    }
    
    public void empilha(String item) {
        if(vazia())
            base = topo = new No(item);
        else
            topo = new No(item, topo);
        tam++;
    }
    
    public String desempilha() {
        if(vazia())
            return null;
        String item = topo.getDado();
        if(base == topo)
            base = topo = null;
        else
            topo = topo.getProx();
        tam--;
        return item;
    }
    
    public boolean vazia(){
        return base == null;
    }
}
