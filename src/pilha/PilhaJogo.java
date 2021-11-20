package pilha;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

public class PilhaJogo extends JLabel {

    //private final ImageIcon imagem;
    private NoJogo base;
    private NoJogo topo;
    private int qnt = 0;
    public int espaco;

    public PilhaJogo(){
        espaco = defEspaco(JogoDasGarrafas.imagem(JogoDasGarrafas.DIMENSAO, this, "tubo"));
        this.setPreferredSize(new Dimension(this.getIcon().getIconWidth(), this.getIcon().getIconHeight()));
        base = topo = null;
    }

    public NoJogo get() {
        return topo;
    }

    public int qnt() {
        return qnt;
    }

    public void empilha(Color item) {
        if (vazia()) {
            base = topo = new NoJogo(this, "fundo", item);
            this.add(topo);
            topo.setBounds((int)(getWidth() * 19f/256f + 0.5), (int)(getHeight() * 109f/500f * 7f/2f + 0.5), topo.getPreferredSize().width, topo.getPreferredSize().height);
        } else {
            topo = new NoJogo(this, "quadrado", item, topo);
            add(topo);
            topo.setBounds((int)(getWidth() * 19f/256f + 0.5), (int)(getHeight() * 109f/500f * (4 - qnt - 0.5) + qnt()*1), topo.getPreferredSize().width, topo.getPreferredSize().height);
        }
        qnt++;
    }

    public void desempilha() {
        if (vazia()) {
            return;
        }
        if (base == topo) {
            remove(topo);
            base = topo = null;
        } else {
            remove(topo);
            topo = topo.getProx();
        }
        qnt--;
    }

    public boolean vazia() {
        return qnt == 0;
    }
    
    private int defEspaco(Image img) {
        BufferedImage buff = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
        buff.getGraphics().drawImage(img, 0, 0, null);
        int cont = 0;
        for(int x = 0; x < buff.getWidth(); x++)
            if(buff.getRGB(x, 0) != Color.black.getRGB())
                cont++;
        return cont;
    }
}
