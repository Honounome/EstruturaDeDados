package pilha;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NoJogo extends JLabel{
    private Color dado;
    private ImageIcon imagem;
    private NoJogo prox;
    
    public NoJogo(PilhaJogo parent, String tipo, Color dadoNovo) {
        this.dado = dadoNovo;
        this.prox = null;
        JogoDasGarrafas.imagem(parent.espaco, this, tipo, dado);
    }
    
    public NoJogo(PilhaJogo parent, String tipo, Color dadoNovo, NoJogo ligacao) {
        this.dado = dadoNovo;
        this.prox = ligacao;
        JogoDasGarrafas.imagem(parent.espaco, this, tipo, dado);
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public Color getDado() {
        return dado;
    }
    
    public void setDado(Color dado) {
        this.dado = dado;
    }
    
    public NoJogo getProx() {
        return prox;
    }
    
    public void setProx(NoJogo prox) {
        this.prox = prox;
    }
}
