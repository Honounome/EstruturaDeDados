package pilha;

import fila.FilaExemplo;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JogoDasGarrafas1 extends javax.swing.JFrame {

    // variáveis de configuração
    final double PERCENT = 10;
    final int BORDA = 5; // até quantos pixels da borda o label pode ir
    final int OX = 12; // (O)ffset (X) do mouse que o label fica quando clicado
    final int OY = -11; // (O)ffset (Y) do mouse que o label fica quando clicado
    final static int DIMENSAO = 40; // vai servir pro tamanho das coisas
    int tam = 1;

    int chances;
    String caracteres = "0123456789abcçdefghijklmnopqrstuvwxyzáâàãéêèíîìóôòõúûùABCÇDEFGHIJKLMNOPQRSTUVWXYZÁÂÀÃÉÊÈÍÎÌÓÔÒÕÚÛÙ .";

    // outras variáveis
    PilhaJogo arrastado = new PilhaJogo(); // guarda o label que vai ser arrastado
    PilhaJogo[] garrafas;
    Timer arrastar, vencer, tim;
    Color[] cores = new Color[9];
    FilaExemplo codigo;
    PilhaExemplo[] anteriores;
    JSpinner[] spinners;

    public JogoDasGarrafas1() {
        // método gerado automaticamente
        initComponents();
        gif(l_confete, "confetti-25");
        anteriores = new PilhaExemplo[]{new PilhaExemplo(), new PilhaExemplo(), new PilhaExemplo()};
        spinners = new JSpinner[]{s_vermelho, s_laranja, s_verdeC, s_verde, s_verzul, s_azul, s_azulE, s_roxo, s_rosa, s_vazios};
        t_codigo.setBorder(null);
        t_codigo.setOpaque(false);
        for (int i = 0; i < cores.length; i++) {
            cores[i] = Color.getHSBColor(i * 40f / 360f, 1, 0.9f);
        }
        //cores = embaralhar(cores);

        lp_gameplay.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                arrastar(evt, false);
            }
        });

        t_entrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    codigo = new FilaExemplo();
                    codigo.praFila(t_entrada.getText().split(", "));
                    jogo(false, codigo.desenfileira());
                }
            }
        });

        t_entrada.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (t_entrada.getText().length() == 0) {
                    l_insira.setText("Insira um código");
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                l_insira.setText("");
            }
        });

        // evento que atualiza a 60Hz a posição do objeto arrastado
        // de acordo com a posição do mouse (não sei se esse try e catch ainda
        // são necessários, já devo ter me livrado da causa do problema)
        arrastar = new Timer(1000 / 60, (ActionEvent evt1) -> {
            lp_gameplay.moveToFront(arrastado);
            if (arrastado.descer != null) {
                arrastado.descer.stop();
            }
            if (arrastado.subir != null) {
                arrastado.subir.stop();
            }
            try {
                arrastado.setBounds(limiteX(), limiteY(), arrastado.getBounds().width, arrastado.getBounds().height);
            } catch (Exception ex) {
                System.out.println("Deu merda");
                arrastar.restart();
            }
        });

        vencer = new Timer(500, (ActionEvent evt) -> {
            if (codigo.vazio()) {
                tela("vitoria");
                tim = new Timer(1500, (ActionEvent evt2) -> {
                    reset();
                    tela("titulo");
                    tim.stop();
                });
                tim.start();
            } else {
                jogo(false, codigo.desenfileira());
            }
            vencer.stop();
        });
    }

    private void gif(javax.swing.JLabel label, String nome) {
        ImageIcon imgCarta = new ImageIcon(getClass().getResource("/pilha/midia/" + nome + ".gif"));
        imgCarta.setImage(imgCarta.getImage().getScaledInstance(-1, label.getHeight(), 1));
        label.setIcon(imgCarta);
    }

    private void reset() {
        for (Component c : lp_gameplay.getComponents()) {
            if (c instanceof PilhaJogo) {
                lp_gameplay.remove(c);
            }
        }
        this.b_desfazer.setEnabled(true);
        arrastar.stop();
        arrastado = new PilhaJogo();
        revalidate();
        repaint();
    }

    // método chamado toda vez que um dos labels é clicado
    private void arrastar(java.awt.event.MouseEvent evt, boolean lm) {
        PilhaJogo label;
        PilhaJogo pilha = new PilhaJogo();
        NoJogo no;

        if (evt.getButton() == 3) {
            if (arrastar.isRunning()) {
                arrastar.stop();
                arrastado.setBounds(arrastado.x(), arrastado.y(), arrastado.getBounds().width,
                        arrastado.getBounds().height);
                arrastado = new PilhaJogo();
            }
            return;
        } else if (evt.getButton() == 2) {
            return;
        }

        try {
            label = (PilhaJogo) evt.getSource();
        } catch (ClassCastException ex) {
            return;
        }

        // se o jogador conseguir magicamente clicar no botão que ele tá 
        // arrastando nada vai acontecer pq esse if nos protege
        if (arrastado.equals(label)) {
            return;
        }

        if (evt.getButton() == 1) {
            // se o timer estiver ativo, quer dizer que o jogador já clicou em
            // um label e está clicando no segundo agora, então o timer é 
            // desativado e o label que estava seguindo o mouse volta 
            // à sua posição original
            if (arrastar.isRunning()) {

                if (arrastado.qnt() == 0) {
                    arrastar.stop();
                    arrastado.setBounds(arrastado.x(), arrastado.y(), arrastado.getBounds().width,
                            arrastado.getBounds().height);
                    arrastado = new PilhaJogo();
                    return;
                }

                no = arrastado.get();

                if (lm) {
                    pilha.empilha(no.getDado());
                } else {
                    while (true) {
                        pilha.empilha(no.getDado());
                        if (no.getProx() == null || !no.getDado().equals(no.getProx().getDado())) {
                            break;
                        }
                        no = no.getProx();
                    }
                }

                if (label.qnt() + pilha.qnt() > 4) {
                    return;
                }

                if (label.get() != null && anteriores[0].get() != null && lm && arrastado.get().getDado().equals(label.get().getDado()) && !(anteriores[1].get().getDado().equals(String.valueOf(java.util.Arrays.asList(garrafas).indexOf(arrastado))) && anteriores[2].get().getDado().equals(String.valueOf(java.util.Arrays.asList(garrafas).indexOf(label))))) {
                    return;
                }

                tocarSom("2agua" + label.qnt() + (label.qnt() + pilha.qnt()));

                anteriores[0].empilha(UIToCode());
                anteriores[1].empilha(String.valueOf(java.util.Arrays.asList(garrafas).indexOf(arrastado)));
                anteriores[2].empilha(String.valueOf(java.util.Arrays.asList(garrafas).indexOf(label)));

                for (int i = 0; i < pilha.qnt(); i++) {
                    arrastado.desempilha();
                    label.empilha(pilha.get().getDado());
                }

                arrastar.stop();
                arrastado.setBounds(arrastado.x(), arrastado.y(), arrastado.getBounds().width,
                        arrastado.getBounds().height);
                arrastado = new PilhaJogo();

                if (lm) {
                    t_codigo.setText(UIToCode());
                }

                if (!lm) {

                    if (check()) {
                        if (codigo.vazio()) {
                            tocarSom("vitoria");
                        } else {
                            tocarSom("prox");
                        }
                        vencer.start();
                    }
                }
            } else {
                arrastado = label;
                arrastar.start();
            }
        }
    }

    private boolean check() {
        for (PilhaJogo garrafa : garrafas) {
            if (!garrafa.homo()) {
                return false;
            }
        }
        return true;
    }

    private void mensagem(int instrucoes) {
        switch (instrucoes) {
            case 0: {
                l_tituloMSG.setText("Instruções");
                l_texto.setText("<html><p align=\"justify\">Em Water Sort Puzzle você deve organizar a água colorida dentro dos frascos<br><br>"
                        + "- OBJETIVO:<br>Separar as cores para que cada cor fique em um frasco diferente<br><br>"
                        + "- MECÂNICA:<br>Clique com o botão esquerdo do mouse em um frasco para selecioná-lo, ele começará a seguir o seu mouse, clique em outro frasco e a camada superior de líquido do 1º passará para o 2º<br><br>"
                        + "- LIMITES:<br>Cada frasco suporta no máximo 4 níveis de líquido, pode tentar por mais, só um aviso: você não vai conseguir ;)<br><br>"
                        + "- <i>OPS</i>, CLIQUEI ERRADO:<br>Se você selecionar um frasco que não queria selecionar, simplesmente clique em qualquer lugar (dentro da janela do jogo) com o botão direito do mouse<br><br>"
                        + "- DESFAZER UM MOVIMENTO<br>Caso você tenha colocado água no frasco errado, pode desfazer o movimento clicando no botão DESFAZER, que pode ser usado 5 vezes</p>");
                b_voltar.setBounds(10, 450, 120, 40);
            }
            break;
            case 1: {
                l_tituloMSG.setText("Conceitos Utilizados");
                l_texto.setText("<html><p align=\"justify\">Em nosso jogo utilizamos o conceito de Pilha, no qual aquele que foi adicionado por último é o primeiro a ser retirado, esse conceito também é conhecido como LIFO (Last In, First Out)<br><br>"
                        + "O conceito de pilha é aplicado nos frascos do jogo, somente a camada de cima passa para outro frasco e a camada de cima é a última a ser colocada, e é exatamente assim que uma pilha funciona<br><br>"
                        + "A única diferença é que no nosso jogo, se os nós do topo forem iguais eles vão juntos para a outra pilha, ou seja, se o líquido de uma certa cor no topo de um frasco ocupar mais de 1 nível, quando ele for passado para outro frasco todos os níveis do líquido serão passados<br><br>"
                        + "O conceito de pilha também foi utilizado na funcionalidade de desfazer uma ação, onde o código de cada ação é registrado em uma pilha e a cada vez que o botão DESFAZER é clicado, a pilha com os códigos fornece o último código registrado e desempilha<br><br>"
                        + "Utilizamos o conceito de fila na passagem das fases</p>");
                b_voltar.setBounds(370, 450, 120, 40);
            }
            break;
            case 2: {
                l_tituloMSG.setText("Como Criar Fases");
                l_texto.setText("<html><p align=\"justify\">As mecânicas do jogo mudam no Level Maker<br><br>"
                        + "- ESTADO INICIAL:<br>Ao iniciar a criação você vai se deparar com frascos cheios e homogêneos, óbvio, o início da criação é o estado final do jogo normal, você vai precisar embaralhar esses frascos até estar satisfeito com o resultado<br><br>"
                        + "- MOVIMENTAÇÃO DE LÍQUIDOS:<br>Diferentemente do jogo padrão, no Level Maker somente o nível superior de líquido é passado de um frasco a outro<br><br>"
                        + "- LIMITAÇÕES:<br>Para nos certificarmos que todas as fases criadas têm solução, não é possível colocar um líquido em cima de outro com a mesma cor. Você só pode empilhar líquidos de mesma cor caso venham do mesmo frasco e sejam parte da mesma sequência<br><br>"
                        + "- CÓDIGOS:<br>Quando você alterar a configuração dos frascos, o código no topo da tela atualizará, esse código representa a configuração atual dos frascos, o código é copiável e pode ser colocado na área de códigos na tela inicial para jogar a fase que ele representa. É possível jogar vários níveis seguidos se colocá-los separados por uma vírgula e um espaço</p>");
                b_voltar.setBounds(10, 450, 120, 40);
            }
            break;
            default: {
            }
            break;
        }
        tela("mensagens");
    }

    private void posicionamento(PilhaJogo[] garrafas, int i) {
        int qtd = garrafas.length;
        int max = 5;
        int linhaSup = (int) (qtd / 2f + 0.5);
        int add = ((500 - (500 / linhaSup * (linhaSup - 1 + 1) - 500 / (linhaSup * 2) - garrafas[0].getIcon().getIconWidth() / 2) - garrafas[0].getIcon().getIconWidth()) - (500 / linhaSup * 1 - 500 / (linhaSup * 2) - garrafas[0].getIcon().getIconWidth() / 2)) / 2;
        if (qtd > max) {
            if (i < linhaSup) {
                garrafas[i].setBounds(add + 500 / linhaSup * (i + 1) - 500 / (linhaSup * 2) - garrafas[i].getIcon().getIconWidth() / 2, 500 / 2 * 1 - 500 / 4 - garrafas[i].getIcon().getIconHeight() / 2 + 30, garrafas[i].getPreferredSize().width, garrafas[i].getPreferredSize().height);
            } else {
                if (qtd < linhaSup * 2) {
                    garrafas[i].setBounds(add + 500 / (qtd - linhaSup + 1) * (i - linhaSup + 1) - garrafas[i].getIcon().getIconWidth() / 2, 500 / 2 * 2 - 500 / 4 - garrafas[i].getIcon().getIconHeight() / 2 - 10, garrafas[i].getPreferredSize().width, garrafas[i].getPreferredSize().height);
                } else {
                    garrafas[i].setBounds(add + 500 / (qtd - linhaSup) * (i - linhaSup + 1) - 500 / ((qtd - linhaSup) * 2) - garrafas[i].getIcon().getIconWidth() / 2, 500 / 2 * 2 - 500 / 4 - garrafas[i].getIcon().getIconHeight() / 2 - 10, garrafas[i].getPreferredSize().width, garrafas[i].getPreferredSize().height);
                }
            }
        } else {
            garrafas[i].setBounds(500 / qtd * (i + 1) - 500 / (qtd * 2) - garrafas[i].getIcon().getIconWidth() / 2, 250 - garrafas[i].getIcon().getIconHeight() / 2, garrafas[i].getPreferredSize().width, garrafas[i].getPreferredSize().height);
        }
    }

    private void codeToUI(String fase, boolean lm) {
        String composicao;
        garrafas = new PilhaJogo[fase.length() / 2];
        for (int i = 0; i < garrafas.length; i++) {
            composicao = "";
            garrafas[i] = new PilhaJogo();
            garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    arrastar(evt, lm);
                }
            });
            lp_gameplay.add(garrafas[i]);
            posicionamento(garrafas, i);
            for (int j = 0; j < 2; j++) {
                String carac = "" + caracteres.indexOf(fase.charAt(i * 2 + j));
                composicao += (carac.length() == 1 ? "0" + carac : carac);
            }
            for (int j = 0; j < 4; j++) {
                if (composicao.charAt(j) == '0') {
                    continue;
                }
                garrafas[i].empilha(cores[Integer.parseInt("" + composicao.charAt(j)) - 1]);
            }
            garrafas[i].lockPos(garrafas[i].getLocation().x, garrafas[i].getLocation().y);
        }
        tela("gameplay");
    }

    private void jogo(boolean lm, String fase) {
        reset();

        anteriores = new PilhaExemplo[]{new PilhaExemplo(), new PilhaExemplo(), new PilhaExemplo()};
        if (lm) {
            l_codigo.setVisible(true);
            t_codigo.setVisible(true);
            chances = -1;
            garrafas = new PilhaJogo[tam];
            for (int i = 0; i < tam; i++) {
                garrafas[i] = new PilhaJogo();
                garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        arrastar(evt, true);
                    }
                });
                lp_gameplay.add(garrafas[i]);
                posicionamento(garrafas, i);
                garrafas[i].lockPos(garrafas[i].getLocation().x, garrafas[i].getLocation().y);
            }
            int cont = 0;
            for (int i = 0; i < spinners.length - 1; i++) {
                for (int j = 0; j < (int) spinners[i].getValue(); j++) {
                    for (int k = 0; k < 4; k++) {
                        garrafas[cont].empilha(cores[i]);
                    }
                    cont++;
                }
            }
            tela("gameplay");
        } else {
            chances = 5;
            l_codigo.setVisible(false);
            t_codigo.setVisible(false);
            if (fase.equals("")) {
                codigo.praFila("00lbkk, bmmmçc00, culw00uk, cvkxwé00êd, bwoê00éèûîúm, eAm0w0ìGbAHûzê, a0oazùíêCçKmJc, dúqáynècCFF0VîSk, 00pÇáCéû00IégmóúmJ, cslÙwIìÍíóGeVõú0 tÚX".split(", "));
                codeToUI(codigo.desenfileira(), false);
            } else {
                if (!(t_entrada.getText().matches("[0123456789abcçdefghijklmnopqrstuvwxyzáâàãéêèíîìóôòõúûùABCÇDEFGHIJKLMNOPQRSTUVWXYZÁÂÀÃÉÊÈÍÎÌÓÔÒÕÚÛÙ .,]+") || t_entrada.getText().equals(""))) {
                    return;
                }
                String[] check = t_entrada.getText().split(", ");
                for (String cod : check) {
                    if (cod.length() % 2 != 0) {
                        return;
                    }
                }
                codeToUI(fase, false);
            }
        }
    }

    public void tela(String nome) {
        CardLayout cl = (CardLayout) p_principal.getLayout();
        cl.show(p_principal, nome);
    }

    // define a posição horizontal mínima e máxima que o label pode ser
    // arrastado, os cálculos levam em consideração a posição do mouse na
    // tela e a posição da janela do jogo, assim como os offsets e
    // o tamanho do label
    private int limiteX() {
        if (MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x < BORDA - OX) {
            return BORDA;
        }
        if (MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x > 500 - BORDA - arrastado.getBounds().width - OX) {
            return 500 - BORDA - arrastado.getBounds().width;
        }
        return MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x + OX;
    }

    // mesma coisa que o anterior só que vertical
    private int limiteY() {
        if (MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y < BORDA - OY) {
            return BORDA;
        }
        if (MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y > 500 - BORDA - arrastado.getBounds().height - OY) {
            return 500 - BORDA - arrastado.getBounds().height;
        }
        return MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y + OY;
    }

    // põe uma imagem num label de acordo com os parâmetros, a cor é opcional
    // e pode ser adicionada num formato RGB como na linha 61
    public static Image imagem(int dimensao, JLabel label, String nome, Color cor) {
        // essas "BufferedImage"s permitem que a gente mude a cor da imagem
        // a "buff" vai pegar a imagem de acordo com o caminho dela (linha 179)
        // e "conv" vai converter "buff" pra um espectro de cores mais
        // conveniente para os nossos propósitos
        BufferedImage buff;
        BufferedImage conv;

        try {
            buff = ImageIO.read((JogoDasGarrafas1.class).getResource("/pilha/midia/" + nome + ".png"));
        } catch (IOException ex) {
            System.out.println("Arquivo não encontrado");
            return null;
        }

        conv = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        conv.getGraphics().drawImage(buff, 0, 0, null);

        // Se o parâmetro "cor" não for informado ele 
        // recebe "null" por padrão e a imagem não é alterada
        // caso ele receba alguma cor, então essa cor vai substituir o
        // preto padrão presente nas imagens do projeto
        if (cor != null) {
            for (int i = 0; i < conv.getWidth(); i++) {
                for (int j = 0; j < conv.getHeight(); j++) {
                    if (conv.getRGB(i, j) == Color.BLACK.getRGB()) {
                        conv.setRGB(i, j, cor.getRGB());
                    }
                }
            }
        }

        // o programa coloca a "BufferedImage" em um "ImageIcon" de acordo com
        // um certo tamanho, muda o tamanho do label para que a imagem não fique
        // cortada e coloca a imagem no label
        ImageIcon img = new ImageIcon(conv.getScaledInstance(dimensao, -1, 1));
        label.setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
        label.setIcon(img);

        return conv.getScaledInstance(dimensao, -1, 1);
    }

    // pra cá que o programa vem quando o parâmetro "cor" não é informado
    // por isso que o padrão é "null"
    public static Image imagem(int dimensao, JLabel label, String nome) {
        return imagem(dimensao, label, nome, null);
    }

    private String UIToCode() {
        String[] codigo = new String[garrafas.length];
        String[] aux = new String[2];
        NoJogo atual;

        for (int i = 0; i < garrafas.length; i++) {
            codigo[i] = "";
            atual = garrafas[i].get();
            if (garrafas[i].qnt() == 0) {
                codigo[i] += "0";
            }
            for (int j = 0; j < garrafas[i].qnt(); j++) {
                codigo[i] += (Arrays.asList(cores).indexOf(atual.getDado()) + 1);
                if (atual.getProx() != null) {
                    atual = atual.getProx();
                }
            }
            codigo[i] = new StringBuilder(String.format("%04d", Integer.parseInt(codigo[i]))).reverse().toString();

            aux[0] = "" + caracteres.charAt(converter("" + codigo[i].charAt(0) + codigo[i].charAt(1), (int) Math.sqrt(caracteres.length())));
            aux[1] = "" + caracteres.charAt(converter("" + codigo[i].charAt(2) + codigo[i].charAt(3), (int) Math.sqrt(caracteres.length())));

            codigo[i] = String.join("", aux);
        }
        return String.join("", codigo);
    }

    private int converter(String num, int base) {
        int soma = 0;
        String conv = String.valueOf(num);
        for (int i = 0; i < conv.length(); i++) {
            soma += Integer.parseInt("" + conv.charAt(conv.length() - 1 - i)) * Math.pow(base, i);
        }
        return soma;
    }

    private static Color[] embaralhar(Color[] vet) {
        Color[] sort = vet;
        Color[] aux;
        Color[] fim = new Color[vet.length];
        int pos;

        for (int i = 0; i < vet.length - 1; i++) {
            pos = (int) (Math.random() * sort.length);
            fim[i] = sort[pos];
            aux = new Color[sort.length - 1];
            for (int j = 0; j <= aux.length; j++) {
                if (j == pos) {
                    continue;
                }
                if (j > pos) {
                    aux[j - 1] = sort[j];
                } else {
                    aux[j] = sort[j];
                }
            }
            sort = aux;
        }
        fim[fim.length - 1] = sort[0];

        return fim;
    }

    private static void tocarSom(String nome, boolean loop, float volume, Class classe) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(classe.getResourceAsStream("/pilha/midia/" + nome + ".wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.addLineListener(new LineListener() {

                @Override
                public void update(final LineEvent event) {
                    if (event.getType().equals(javax.sound.sampled.LineEvent.Type.STOP)) {

                    }
                }
            });
        } catch (Exception ex) {
            System.out.println("Erro");
            ex.printStackTrace();
        }
    }

    private static void tocarSom(String nome) {
        tocarSom(nome, false, 0.05f, JogoDasGarrafas1.class);
    }

    private static void tocarSom(String nome, float volume) {
        tocarSom(nome, false, volume, JogoDasGarrafas1.class);
    }

    //<editor-fold defaultstate="collapsed" desc="Código gerado automaticamente e eventos">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_principal = new javax.swing.JPanel();
        p_titulo = new javax.swing.JPanel();
        l_titulo = new javax.swing.JLabel();
        l_autores = new javax.swing.JLabel();
        b_conceitos = new javax.swing.JButton();
        b_jogar = new javax.swing.JButton();
        b_instrucoes = new javax.swing.JButton();
        b_criacao = new javax.swing.JButton();
        l_insira = new javax.swing.JLabel();
        t_entrada = new javax.swing.JTextField();
        l_instrucoes = new javax.swing.JLabel();
        p_mensagens = new javax.swing.JPanel();
        l_tituloMSG = new javax.swing.JLabel();
        l_texto = new javax.swing.JLabel();
        b_voltar = new javax.swing.JButton();
        lp_gameplay = new javax.swing.JLayeredPane();
        t_codigo = new javax.swing.JTextField();
        l_codigo = new javax.swing.JLabel();
        b_menu = new javax.swing.JButton();
        b_desfazer = new javax.swing.JButton();
        p_tituloLM = new javax.swing.JPanel();
        l_tituloLM = new javax.swing.JLabel();
        l_descricao = new javax.swing.JLabel();
        b_iniciar = new javax.swing.JButton();
        b_voltarLM = new javax.swing.JButton();
        b_instrucoesLM = new javax.swing.JButton();
        l_vermelho = new javax.swing.JLabel();
        s_vermelho = new javax.swing.JSpinner();
        l_laranja = new javax.swing.JLabel();
        s_laranja = new javax.swing.JSpinner();
        l_verdeC = new javax.swing.JLabel();
        s_verdeC = new javax.swing.JSpinner();
        l_verde = new javax.swing.JLabel();
        s_verde = new javax.swing.JSpinner();
        l_verzul = new javax.swing.JLabel();
        s_verzul = new javax.swing.JSpinner();
        l_azul = new javax.swing.JLabel();
        s_azul = new javax.swing.JSpinner();
        l_azulE = new javax.swing.JLabel();
        s_azulE = new javax.swing.JSpinner();
        l_roxo = new javax.swing.JLabel();
        s_roxo = new javax.swing.JSpinner();
        l_rosa = new javax.swing.JLabel();
        s_rosa = new javax.swing.JSpinner();
        l_total = new javax.swing.JLabel();
        l_vazios = new javax.swing.JLabel();
        s_vazios = new javax.swing.JSpinner();
        p_vitoria = new javax.swing.JPanel();
        l_vitoria = new javax.swing.JLabel();
        l_confete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        p_principal.setLayout(new java.awt.CardLayout());

        p_titulo.setMinimumSize(new java.awt.Dimension(500, 500));
        p_titulo.setLayout(null);

        l_titulo.setFont(new java.awt.Font("Monotype Corsiva", 3, 65)); // NOI18N
        l_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_titulo.setText("Water Sort Puzzle");
        p_titulo.add(l_titulo);
        l_titulo.setBounds(0, 30, 500, 80);

        l_autores.setFont(new java.awt.Font("High Tower Text", 0, 18)); // NOI18N
        l_autores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_autores.setText("Entregue a vocês por Edimax, Gabriel e Wesley");
        p_titulo.add(l_autores);
        l_autores.setBounds(0, 130, 500, 22);

        b_conceitos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_conceitos.setText("CONCEITOS");
        b_conceitos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_conceitos.setFocusable(false);
        b_conceitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_conceitosActionPerformed(evt);
            }
        });
        p_titulo.add(b_conceitos);
        b_conceitos.setBounds(370, 450, 120, 40);

        b_jogar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        b_jogar.setText("JOGAR");
        b_jogar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_jogar.setFocusable(false);
        b_jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_jogarActionPerformed(evt);
            }
        });
        p_titulo.add(b_jogar);
        b_jogar.setBounds(160, 280, 180, 60);

        b_instrucoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_instrucoes.setText("COMO JOGAR");
        b_instrucoes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_instrucoes.setFocusable(false);
        b_instrucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_instrucoesActionPerformed(evt);
            }
        });
        p_titulo.add(b_instrucoes);
        b_instrucoes.setBounds(10, 450, 120, 40);

        b_criacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_criacao.setText("CRIAR");
        b_criacao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_criacao.setFocusable(false);
        b_criacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_criacaoActionPerformed(evt);
            }
        });
        p_titulo.add(b_criacao);
        b_criacao.setBounds(190, 450, 120, 40);

        l_insira.setForeground(java.awt.Color.lightGray);
        l_insira.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_insira.setText("Insira um código");
        l_insira.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        l_insira.setFocusable(false);
        p_titulo.add(l_insira);
        l_insira.setBounds(0, 250, 500, 20);

        t_entrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        p_titulo.add(t_entrada);
        t_entrada.setBounds(0, 245, 500, 30);

        l_instrucoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_instrucoes.setText("<html><p align=\"center\">No campo abaixo você pode inserir um código de fase<br>(você pode criar um código no Level Maker - experimente clicar no botão CRIAR)<br>Você também pode deixá-lo em branco e jogar 10 fases que nós fizemos</p>");
        p_titulo.add(l_instrucoes);
        l_instrucoes.setBounds(0, 180, 500, 60);

        p_principal.add(p_titulo, "titulo");

        p_mensagens.setMinimumSize(new java.awt.Dimension(500, 500));
        p_mensagens.setLayout(null);

        l_tituloMSG.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        l_tituloMSG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_tituloMSG.setText("Título");
        p_mensagens.add(l_tituloMSG);
        l_tituloMSG.setBounds(0, 20, 500, 48);

        l_texto.setText("<html>placeholder placeholder placeholder placeholder<br>placeholder placeholder");
        l_texto.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        p_mensagens.add(l_texto);
        l_texto.setBounds(10, 80, 480, 360);

        b_voltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_voltar.setText("VOLTAR");
        b_voltar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_voltar.setFocusable(false);
        b_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_voltarActionPerformed(evt);
            }
        });
        p_mensagens.add(b_voltar);
        b_voltar.setBounds(10, 450, 120, 40);

        p_principal.add(p_mensagens, "mensagens");

        t_codigo.setEditable(false);
        t_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lp_gameplay.add(t_codigo);
        t_codigo.setBounds(0, 30, 500, 30);

        l_codigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_codigo.setText("Código gerado:");
        lp_gameplay.add(l_codigo);
        l_codigo.setBounds(0, 10, 500, 20);

        b_menu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_menu.setText("MENU");
        b_menu.setFocusable(false);
        b_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_menuActionPerformed(evt);
            }
        });
        lp_gameplay.add(b_menu);
        b_menu.setBounds(370, 450, 120, 40);

        b_desfazer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_desfazer.setText("DESFAZER");
        b_desfazer.setFocusable(false);
        b_desfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_desfazerActionPerformed(evt);
            }
        });
        lp_gameplay.add(b_desfazer);
        b_desfazer.setBounds(10, 450, 120, 40);

        p_principal.add(lp_gameplay, "gameplay");

        p_tituloLM.setLayout(null);

        l_tituloLM.setFont(new java.awt.Font("Monotype Corsiva", 1, 65)); // NOI18N
        l_tituloLM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_tituloLM.setText("<html><p align=\"center\">Water Sort Puzzle<br>Level Maker</p>");
        p_tituloLM.add(l_tituloLM);
        l_tituloLM.setBounds(0, 30, 500, 150);

        l_descricao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_descricao.setText("<html><p align=\"center\">Bem vindo ao criador de níveis do jogo!<br><br>Para iniciar basta escolher a quantidade de frascos para cada cor e clicar no botão <b>Iniciar</b></p>");
        p_tituloLM.add(l_descricao);
        l_descricao.setBounds(30, 190, 440, 60);

        b_iniciar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        b_iniciar.setText("Iniciar");
        b_iniciar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_iniciarActionPerformed(evt);
            }
        });
        p_tituloLM.add(b_iniciar);
        b_iniciar.setBounds(190, 380, 120, 40);

        b_voltarLM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_voltarLM.setText("VOLTAR");
        b_voltarLM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_voltarLM.setFocusable(false);
        b_voltarLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_voltarLMActionPerformed(evt);
            }
        });
        p_tituloLM.add(b_voltarLM);
        b_voltarLM.setBounds(190, 450, 120, 40);

        b_instrucoesLM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_instrucoesLM.setText("COMO CRIAR");
        b_instrucoesLM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        b_instrucoesLM.setFocusable(false);
        b_instrucoesLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_instrucoesLMActionPerformed(evt);
            }
        });
        p_tituloLM.add(b_instrucoesLM);
        b_instrucoesLM.setBounds(10, 450, 120, 40);

        l_vermelho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_vermelho.setText("Vermelho");
        p_tituloLM.add(l_vermelho);
        l_vermelho.setBounds(10, 310, 60, 16);

        s_vermelho.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_vermelho.setFocusable(false);
        s_vermelho.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_vermelho);
        s_vermelho.setBounds(10, 330, 60, 25);

        l_laranja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_laranja.setText("Laranja");
        p_tituloLM.add(l_laranja);
        l_laranja.setBounds(80, 310, 60, 16);

        s_laranja.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_laranja.setFocusable(false);
        s_laranja.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_laranja);
        s_laranja.setBounds(80, 330, 60, 25);

        l_verdeC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_verdeC.setText("Verde C.");
        p_tituloLM.add(l_verdeC);
        l_verdeC.setBounds(150, 310, 60, 16);

        s_verdeC.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_verdeC.setFocusable(false);
        s_verdeC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_verdeC);
        s_verdeC.setBounds(150, 330, 60, 25);

        l_verde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_verde.setText("Verde");
        p_tituloLM.add(l_verde);
        l_verde.setBounds(290, 310, 60, 16);

        s_verde.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_verde.setFocusable(false);
        s_verde.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_verde);
        s_verde.setBounds(290, 330, 60, 25);

        l_verzul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_verzul.setText("Verzul");
        p_tituloLM.add(l_verzul);
        l_verzul.setBounds(360, 310, 60, 16);

        s_verzul.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_verzul.setFocusable(false);
        s_verzul.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_verzul);
        s_verzul.setBounds(360, 330, 60, 25);

        l_azul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_azul.setText("Azul");
        p_tituloLM.add(l_azul);
        l_azul.setBounds(430, 310, 60, 16);

        s_azul.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_azul.setFocusable(false);
        s_azul.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_azul);
        s_azul.setBounds(430, 330, 60, 25);

        l_azulE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_azulE.setText("Azul E.");
        p_tituloLM.add(l_azulE);
        l_azulE.setBounds(10, 370, 60, 16);

        s_azulE.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_azulE.setFocusable(false);
        s_azulE.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_azulE);
        s_azulE.setBounds(10, 390, 60, 25);

        l_roxo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_roxo.setText("Roxo");
        p_tituloLM.add(l_roxo);
        l_roxo.setBounds(80, 370, 60, 16);

        s_roxo.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_roxo.setFocusable(false);
        s_roxo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_roxo);
        s_roxo.setBounds(80, 390, 60, 25);

        l_rosa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_rosa.setText("Rosa");
        p_tituloLM.add(l_rosa);
        l_rosa.setBounds(360, 370, 60, 16);

        s_rosa.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        s_rosa.setFocusable(false);
        s_rosa.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_rosa);
        s_rosa.setBounds(360, 390, 60, 25);

        l_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_total.setText("<html><p align=\"center\">Total:<br>1</p>");
        p_tituloLM.add(l_total);
        l_total.setBounds(230, 310, 40, 32);

        l_vazios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_vazios.setText("Vazios");
        p_tituloLM.add(l_vazios);
        l_vazios.setBounds(430, 370, 60, 16);

        s_vazios.setModel(new javax.swing.SpinnerNumberModel(1, 1, 24, 1));
        s_vazios.setFocusable(false);
        s_vazios.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnersStateChanged(evt);
            }
        });
        p_tituloLM.add(s_vazios);
        s_vazios.setBounds(430, 390, 60, 25);

        p_principal.add(p_tituloLM, "tituloLM");

        p_vitoria.setLayout(null);

        l_vitoria.setFont(new java.awt.Font("Monotype Corsiva", 1, 65)); // NOI18N
        l_vitoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_vitoria.setText("Você venceu!");
        p_vitoria.add(l_vitoria);
        l_vitoria.setBounds(0, 180, 500, 140);

        l_confete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pilha/midia/confetti-25.gif"))); // NOI18N
        p_vitoria.add(l_confete);
        l_confete.setBounds(0, 0, 500, 500);

        p_principal.add(p_vitoria, "vitoria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_instrucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_instrucoesActionPerformed
        mensagem(0);
    }//GEN-LAST:event_b_instrucoesActionPerformed

    private void b_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_voltarActionPerformed
        if (l_tituloMSG.getText().equals("Como Criar Fases"))
            tela("tituloLM");
        else
            tela("titulo");
    }//GEN-LAST:event_b_voltarActionPerformed

    private void b_conceitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_conceitosActionPerformed
        mensagem(1);
    }//GEN-LAST:event_b_conceitosActionPerformed

    private void b_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_jogarActionPerformed
        codigo = new FilaExemplo();
        codigo.praFila(t_entrada.getText().split(", "));
        jogo(false, codigo.desenfileira());
    }//GEN-LAST:event_b_jogarActionPerformed

    private void b_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_iniciarActionPerformed
        //tam = (int) s_vermelho.getValue();
        jogo(true, null);
    }//GEN-LAST:event_b_iniciarActionPerformed

    private void b_criacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_criacaoActionPerformed
        tela("tituloLM");
    }//GEN-LAST:event_b_criacaoActionPerformed

    private void b_voltarLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_voltarLMActionPerformed
        tela("titulo");
    }//GEN-LAST:event_b_voltarLMActionPerformed

    private void b_desfazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_desfazerActionPerformed
        if (chances > 0 && !anteriores[0].vazia()) {
            chances--;
            reset();
            anteriores[1].desempilha();
            anteriores[2].desempilha();
            codeToUI(anteriores[0].desempilha(), false);
        }
        if (chances == 0) {
            b_desfazer.setEnabled(false);
        }
        if (chances < 0 && !anteriores[0].vazia()) {
            reset();
            anteriores[1].desempilha();
            anteriores[2].desempilha();
            codeToUI(anteriores[0].desempilha(), true);
        }
    }//GEN-LAST:event_b_desfazerActionPerformed

    private void b_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_menuActionPerformed
        reset();
        tela("titulo");
    }//GEN-LAST:event_b_menuActionPerformed

    private void spinnersStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnersStateChanged
        tam = 0;
        for (JSpinner spinner : spinners) {
            tam += (int) spinner.getValue();
        }
        l_total.setText("<html><p align=\"center\">Total:<br>" + tam + "</p>");
        for (JSpinner spinner : spinners)
            spinner.setModel(new javax.swing.SpinnerNumberModel((int) spinner.getValue(), (spinner.equals(s_vazios) ? 1 : 0), 24 - tam + (int) spinner.getValue(), 1));
    }//GEN-LAST:event_spinnersStateChanged

    private void b_instrucoesLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_instrucoesLMActionPerformed
        mensagem(2);
    }//GEN-LAST:event_b_instrucoesLMActionPerformed
//</editor-fold>

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogoDasGarrafas1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_conceitos;
    private javax.swing.JButton b_criacao;
    private javax.swing.JButton b_desfazer;
    private javax.swing.JButton b_iniciar;
    private javax.swing.JButton b_instrucoes;
    private javax.swing.JButton b_instrucoesLM;
    private javax.swing.JButton b_jogar;
    private javax.swing.JButton b_menu;
    private javax.swing.JButton b_voltar;
    private javax.swing.JButton b_voltarLM;
    private javax.swing.JLabel l_autores;
    private javax.swing.JLabel l_azul;
    private javax.swing.JLabel l_azulE;
    private javax.swing.JLabel l_codigo;
    private javax.swing.JLabel l_confete;
    private javax.swing.JLabel l_descricao;
    private javax.swing.JLabel l_insira;
    private javax.swing.JLabel l_instrucoes;
    private javax.swing.JLabel l_laranja;
    private javax.swing.JLabel l_rosa;
    private javax.swing.JLabel l_roxo;
    private javax.swing.JLabel l_texto;
    private javax.swing.JLabel l_titulo;
    private javax.swing.JLabel l_tituloLM;
    private javax.swing.JLabel l_tituloMSG;
    private javax.swing.JLabel l_total;
    private javax.swing.JLabel l_vazios;
    private javax.swing.JLabel l_verde;
    private javax.swing.JLabel l_verdeC;
    private javax.swing.JLabel l_vermelho;
    private javax.swing.JLabel l_verzul;
    private javax.swing.JLabel l_vitoria;
    private javax.swing.JLayeredPane lp_gameplay;
    private javax.swing.JPanel p_mensagens;
    private javax.swing.JPanel p_principal;
    private javax.swing.JPanel p_titulo;
    private javax.swing.JPanel p_tituloLM;
    private javax.swing.JPanel p_vitoria;
    private javax.swing.JSpinner s_azul;
    private javax.swing.JSpinner s_azulE;
    private javax.swing.JSpinner s_laranja;
    private javax.swing.JSpinner s_rosa;
    private javax.swing.JSpinner s_roxo;
    private javax.swing.JSpinner s_vazios;
    private javax.swing.JSpinner s_verde;
    private javax.swing.JSpinner s_verdeC;
    private javax.swing.JSpinner s_vermelho;
    private javax.swing.JSpinner s_verzul;
    private javax.swing.JTextField t_codigo;
    private javax.swing.JTextField t_entrada;
    // End of variables declaration//GEN-END:variables
}
