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
    
    public static int procurar(Botao[] btns, Botao btn) {
        for (int i = 0; i < btns.length; i++)
            if (btns[i].equals(btn))
                return i;
        return -1;
    }
    
    public static void adicionar(Botao[] btns, Botao btn) {
        Botao[] aux = new Botao[btns.length + 1];
        for (int i = 0; i < aux.length; i++)
            aux[i] = btns[i];
        aux[aux.length] = btn;
        btns = aux;
    }
    
    public static void remover(Botao[] btns, Botao btn) {
        Botao[] aux = new Botao[btns.length - 1];
        int indice = procurar(btns, btn);
        if (indice == -1) {return;}
        for (int i = 0; i < aux.length; i++)
            aux[i] = btns[i < indice ? i : i + 1];
        btns = aux;
    }

}