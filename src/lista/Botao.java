package lista;

import javax.swing.*;

public class Botao {
    
    private JButton botao = new JButton();
    private ImageIcon imagem = new ImageIcon();
    private int pos;

    public Botao() {
        
    }

    public JButton getBotao() {
        return botao;
    }

    public void setBotao(JButton botao) {
        this.botao = botao;
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void mostrarImagem() {
        botao.setIcon(imagem);
    }

    public void esconderImagem() {
        botao.setIcon(null);
    }

    public boolean taMostrando() {
        return (botao.getIcon() != null);
    }

}
