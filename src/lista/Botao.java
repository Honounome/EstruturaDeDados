package lista;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Botao extends JButton {

    private ImageIcon imagem = new ImageIcon();

    public Botao(ImageIcon imagem, int x, int y) {
        imagem.setImage(imagem.getImage().getScaledInstance(((x < y || x == y) ? x - 10 : -1), ((x > y || x == y) ? y - 10 : -1), 1));
        this.imagem = imagem;
        setBackground(Color.white);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void mostrarImagem() {
        setIcon(imagem);
    }

    public void esconderImagem() {
        setIcon(null);
    }

    public boolean taMostrando() {
        return (getIcon() != null);
    }

}
