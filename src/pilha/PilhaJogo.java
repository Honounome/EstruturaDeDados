package pilha;

public class PilhaJogo {
    private NoJogo base;
    private NoJogo topo;
    private int tam = 0;
    
    public PilhaJogo() {
        base = topo = null;
    }
    
    public NoJogo get() {
        return topo;
    }
    
    public int tam() {
        return tam;
    }
    
    public void empilha(String item) {
        if(vazia())
            base = topo = new NoJogo(item);
        else
            topo = new NoJogo(item, topo);
        tam++;
    }
    
    public void desempilha() {
        if(vazia())
            return;
        if(base == topo)
            base = topo = null;
        else
            topo = topo.getProx();
        tam--;
    }
    
    public boolean vazia(){
        return base == null;
    }
}
