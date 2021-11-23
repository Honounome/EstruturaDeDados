package pilha;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PilhaJogo extends JLabel {

    //private final ImageIcon imagem;
    Timer subir, descer;
    private NoJogo base;
    private NoJogo topo;
    private int qnt = 0;
    public int espaco;
    private int x, y;

    public PilhaJogo() {
        espaco = defEspaco(JogoDasGarrafas.imagem(JogoDasGarrafas.DIMENSAO, this, "tubo"));
        this.setPreferredSize(new Dimension(this.getIcon().getIconWidth(), this.getIcon().getIconHeight()));
        base = topo = null;
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                entrou(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saiu(evt);
            }
        });
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
            topo.setBounds((int) (getWidth() * 19f / 256f + 0.5), (int) (getHeight() * 109f / 500f * 7f / 2f + 0.5), topo.getPreferredSize().width, topo.getPreferredSize().height);
        } else {
            topo = new NoJogo(this, "quadrado", item, topo);
            add(topo);
            topo.setBounds((int) (getWidth() * 19f / 256f + 0.5), (int) (getHeight() * 109f / 500f * (4 - qnt - 0.5) + qnt() * 1), topo.getPreferredSize().width, topo.getPreferredSize().height);
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
        for (int i = 0; i < buff.getWidth(); i++) {
            if (buff.getRGB(i, 0) != Color.black.getRGB()) {
                cont++;
            }
        }
        return cont;
    }

    public void lockPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void entrou(MouseEvent evt) {
        if (descer != null && descer.isRunning()) {
            descer.stop();
        }
        subir = new Timer(1000 / 60, (ActionEvent evt1) -> {
            PilhaJogo src = (PilhaJogo) evt.getSource();
            src.setLocation(src.getLocation().x, src.getLocation().y - 2);
            if (src.getLocation().y < src.y() - 9) {
                src.setLocation(src.getLocation().x, src.y() - 10);
                subir.stop();
            }
        });
        subir.start();
    }

    public void saiu(MouseEvent evt) {
        if (subir != null && subir.isRunning()) {
            subir.stop();
        }
        descer = new Timer(1000 / 60, (ActionEvent evt1) -> {
            PilhaJogo src = (PilhaJogo) evt.getSource();
            src.setLocation(src.getLocation().x, src.getLocation().y + 2);
            if (src.getLocation().y >= src.y()) {
                src.setLocation(src.getLocation().x, src.y());
                descer.stop();
            }
        });
        descer.start();
    }
    
    public boolean homo() {
        NoJogo aux = get();
        for (int i = 0; i < qnt; i++) {
            if(aux.getProx() != null && !(aux.getDado().equals(aux.getProx().getDado())))
                return false;
            aux = aux.getProx();
        }
        return true;
    }
}
