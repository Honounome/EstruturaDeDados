package pilha;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class JogoDasGarrafas extends javax.swing.JFrame {

    // variáveis de configuração
    final double PERCENT = 10;
    final int BORDA = 5; // até quantos pixels da borda o label pode ir
    final int OX = 12; // (O)ffset (X) do mouse que o label fica quando clicado
    final int OY = -11; // (O)ffset (Y) do mouse que o label fica quando clicado
    final static int DIMENSAO = 50; // vai servir pro tamanho das coisas
    int tam = 4;

    // outras variáveis
    PilhaJogo arrastado = new PilhaJogo(); // guarda o label que vai ser arrastado
    PilhaJogo[] garrafas;
    Scanner scan = new Scanner(System.in); // tu sabe
    Timer arrastar, subir, descer, queue; // atualiza a posição de "arrastado" numa taxa constante
    Color[] cores = new Color[9];

    public JogoDasGarrafas() {
        // método gerado automaticamente
        initComponents();
        for (int i = 0; i < cores.length; i++) {
            cores[i] = Color.getHSBColor(i * 40f / 360f, 1, 1);
        }
        cores = embaralhar(cores);

        p_gameplay.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                arrastar(evt, false);
            }
        });
        // evento que atualiza a 60Hz a posição do objeto arrastado
        // de acordo com a posição do mouse (não sei se esse try e catch ainda
        // são necessários, já devo ter me livrado da causa do problema)
        arrastar = new Timer(1000 / 60, (ActionEvent evt1) -> {
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

        // define as imagens dos 2 labels que temos por enquanto
        // descomentar as 2 linhas abaixo para ativar a versão de output
        //printar();
        //gameloop();
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

                // caso o timer esteja desativado, quer dizer que o jogador clicou no
                // primeiro label, então o programa salva a posição dele, o define
                // como o label que vai ser arrastado e inicia o timer
            } else {
                arrastado = label;
                arrastar.start();
            }
        }
    }

    private void mensagem(boolean instrucoes) {
        if (instrucoes) {
            l_tituloMSG.setText("Instruções");
            l_texto.setText("<html><p align=\"justify\">Em Water Sort Puzzle você deve organizar a água colorida dentro dos frascos<br><br>"
                    + "- OBJETIVO:<br>Separar as cores para que cada cor fique em um frasco diferente<br><br>"
                    + "- MECÂNICA:<br>Clique com o botão esquerdo do mouse em um frasco para selecioná-lo, ele começará a seguir o seu mouse, clique em outro frasco e a camada superior de líquido do 1º passará para o 2º<br><br>"
                    + "- LIMITES:<br>Cada frasco suporta no máximo 4 níveis de líquido, pode tentar por mais, só um aviso: você não vai conseguir ;)<br><br>"
                    + "- SOU BURRO, CLIQUEI ERRADO:<br>Se você selecionar um frasco que não queria selecionar, simplesmente clique em qualquer lugar (dentro da janela do jogo) com o botão direito do mouse</p>");
            b_voltar.setBounds(10, 450, 120, 40);
        } else {
            l_tituloMSG.setText("Conceitos Utilizados");
            l_texto.setText("<html><p align=\"justify\">Em nosso jogo utilizamos o conceito de Pilha, no qual aquele que foi adicionado por último é o primeiro a ser retirado, esse conceito também é conhecido como LIFO (Last In, First Out).<br><br>O conceito de pilha é aplicado nos frascos do jogo, somente a camada de cima passa para outro frasco e a camada de cima é a última a ser colocada, e é exatamente assim que uma pilha funciona.<br><br>A única diferença é que no nosso jogo, se os nós do topo forem iguais eles vão juntos para a outra pilha, ou seja, se o líquido de uma certa cor no topo de um frasco ocupar mais de 1 nível, quando ele for passado para outro frasco todos os níveis do líquido serão passados</p>");
            b_voltar.setBounds(370, 450, 120, 40);
        }
        tela("mensagens");
    }

    private void jogo(boolean lm) {
        garrafas = new PilhaJogo[tam];
        if (!lm) {
            t_codigo.setVisible(false);
        }

        for (int i = 0; i < tam; i++) {
            garrafas[i] = new PilhaJogo();
            if (lm) {
                garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        arrastar(evt, true);
                    }
                });
            } else {
                garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        arrastar(evt, false);
                    }
                });
            }
            p_gameplay.add(garrafas[i]);

            garrafas[i].setBounds(500 / tam * (i + 1) - 500 / (tam * 2) - garrafas[i].getIcon().getIconWidth() / 2, 250 - garrafas[i].getIcon().getIconHeight() / 2, garrafas[i].getPreferredSize().width, garrafas[i].getPreferredSize().height);

            if (lm) {
                if (i <= (int) s_cores.getValue() - 1) {
                    for (int j = 0; j < 4; j++) {
                        garrafas[i].empilha(cores[i]);
                    }
                }
            } else {
                garrafas[i].empilha(Color.green);
                garrafas[i].empilha(Color.red);
            }

            garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    ((PilhaJogo) evt.getSource()).entrou(evt);
                }
            });
            garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    ((PilhaJogo) evt.getSource()).saiu(evt);
                }
            });
            garrafas[i].lockPos(garrafas[i].getLocation().x, garrafas[i].getLocation().y);
        }
        tela("gameplay");
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
            buff = ImageIO.read((JogoDasGarrafas.class).getResource("/pilha/imagens/" + nome + ".png"));
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
        String caracteres = "abcdefghijklmnopqrstuvwxy";
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
            codigo[i] = String.format("%04d", Integer.parseInt(new StringBuilder(codigo[i]).toString()));
            System.out.println("codigo base 5: " + codigo[i]);

            aux[0] = "" + caracteres.charAt(converter("" + codigo[i].charAt(0) + codigo[i].charAt(1), 5));
            aux[1] = "" + caracteres.charAt(converter("" + codigo[i].charAt(2) + codigo[i].charAt(3), 5));

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
        System.out.println(soma);
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
    
    private void tocarSom(String nome, boolean loop, float volume) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/lista/audio/" + nome + ".wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception ex) {
            System.out.println("Erro");
            ex.printStackTrace();
        }
    }

    private void tocarSom(String nome) {
        tocarSom(nome, false, 0.05f);
    }

    private void tocarSom(String nome, float volume) {
        tocarSom(nome, false, volume);
    }

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
        p_mensagens = new javax.swing.JPanel();
        l_tituloMSG = new javax.swing.JLabel();
        l_texto = new javax.swing.JLabel();
        b_voltar = new javax.swing.JButton();
        p_gameplay = new javax.swing.JPanel();
        t_codigo = new javax.swing.JTextField();
        p_tituloLM = new javax.swing.JPanel();
        l_tituloLM = new javax.swing.JLabel();
        s_frascos = new javax.swing.JSpinner();
        b_iniciar = new javax.swing.JButton();
        l_descricao = new javax.swing.JLabel();
        l_frascos = new javax.swing.JLabel();
        l_cores = new javax.swing.JLabel();
        s_cores = new javax.swing.JSpinner();
        b_voltarLM = new javax.swing.JButton();

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
        b_conceitos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        b_jogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_jogarActionPerformed(evt);
            }
        });
        p_titulo.add(b_jogar);
        b_jogar.setBounds(160, 280, 180, 60);

        b_instrucoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_instrucoes.setText("COMO JOGAR");
        b_instrucoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        b_criacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_criacao.setFocusable(false);
        b_criacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_criacaoActionPerformed(evt);
            }
        });
        p_titulo.add(b_criacao);
        b_criacao.setBounds(190, 450, 120, 40);

        p_principal.add(p_titulo, "titulo");

        p_mensagens.setMinimumSize(new java.awt.Dimension(500, 500));
        p_mensagens.setLayout(null);

        l_tituloMSG.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        l_tituloMSG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_tituloMSG.setText("Título");
        p_mensagens.add(l_tituloMSG);
        l_tituloMSG.setBounds(0, 20, 500, 48);

        l_texto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        l_texto.setText("<html>placeholder placeholder placeholder placeholder<br>placeholder placeholder");
        l_texto.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        p_mensagens.add(l_texto);
        l_texto.setBounds(20, 90, 460, 330);

        b_voltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_voltar.setText("VOLTAR");
        b_voltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_voltar.setFocusable(false);
        b_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_voltarActionPerformed(evt);
            }
        });
        p_mensagens.add(b_voltar);
        b_voltar.setBounds(10, 450, 120, 40);

        p_principal.add(p_mensagens, "mensagens");

        p_gameplay.setMinimumSize(new java.awt.Dimension(500, 500));
        p_gameplay.setLayout(null);

        t_codigo.setEditable(false);
        t_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_codigo.setFocusable(false);
        p_gameplay.add(t_codigo);
        t_codigo.setBounds(0, 10, 500, 30);

        p_principal.add(p_gameplay, "gameplay");

        p_tituloLM.setLayout(null);

        l_tituloLM.setFont(new java.awt.Font("Monotype Corsiva", 1, 65)); // NOI18N
        l_tituloLM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_tituloLM.setText("<html><p align=\"center\">Water Sort Puzzle<br>Level Maker</p>");
        p_tituloLM.add(l_tituloLM);
        l_tituloLM.setBounds(0, 30, 500, 150);

        s_frascos.setModel(new javax.swing.SpinnerNumberModel(3, 3, 10, 1));
        s_frascos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s_frascosStateChanged(evt);
            }
        });
        p_tituloLM.add(s_frascos);
        s_frascos.setBounds(85, 330, 80, 25);

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

        l_descricao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_descricao.setText("<html><p align=\"center\">Bem vindo ao criador de níveis do jogo!<br><br>Para iniciar basta escolher a quantidade de frascos, a quantidade de cores diferentes e clicar no botão <b>Iniciar</b></p>");
        p_tituloLM.add(l_descricao);
        l_descricao.setBounds(30, 190, 440, 60);

        l_frascos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_frascos.setText("Qnt. Frascos");
        p_tituloLM.add(l_frascos);
        l_frascos.setBounds(85, 310, 80, 16);

        l_cores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_cores.setText("Qnt. Cores");
        p_tituloLM.add(l_cores);
        l_cores.setBounds(335, 310, 80, 16);

        s_cores.setModel(new javax.swing.SpinnerNumberModel(2, 2, 2, 1));
        p_tituloLM.add(s_cores);
        s_cores.setBounds(335, 330, 80, 25);

        b_voltarLM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_voltarLM.setText("VOLTAR");
        b_voltarLM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_voltarLM.setFocusable(false);
        b_voltarLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_voltarLMActionPerformed(evt);
            }
        });
        p_tituloLM.add(b_voltarLM);
        b_voltarLM.setBounds(190, 450, 120, 40);

        p_principal.add(p_tituloLM, "tituloLM");

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
        mensagem(true);
    }//GEN-LAST:event_b_instrucoesActionPerformed

    private void b_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_voltarActionPerformed
        tela("titulo");
    }//GEN-LAST:event_b_voltarActionPerformed

    private void b_conceitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_conceitosActionPerformed
        mensagem(false);
    }//GEN-LAST:event_b_conceitosActionPerformed

    private void b_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_jogarActionPerformed
        jogo(false);
    }//GEN-LAST:event_b_jogarActionPerformed

    private void b_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_iniciarActionPerformed
        tam = (int) s_frascos.getValue();
        jogo(true);
    }//GEN-LAST:event_b_iniciarActionPerformed

    private void b_criacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_criacaoActionPerformed
        tela("tituloLM");
    }//GEN-LAST:event_b_criacaoActionPerformed

    private void b_voltarLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_voltarLMActionPerformed
        tela("titulo");
    }//GEN-LAST:event_b_voltarLMActionPerformed

    private void s_frascosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s_frascosStateChanged
        s_cores.setModel(new javax.swing.SpinnerNumberModel((int) s_frascos.getValue() == (int) s_cores.getValue() ? (int) s_cores.getValue() - 1 : (int) s_cores.getValue(), 2, (int) s_frascos.getValue() > 4 ? 4 : (int) s_frascos.getValue() - 1, 1));
    }//GEN-LAST:event_s_frascosStateChanged

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
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogoDasGarrafas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_conceitos;
    private javax.swing.JButton b_criacao;
    private javax.swing.JButton b_iniciar;
    private javax.swing.JButton b_instrucoes;
    private javax.swing.JButton b_jogar;
    private javax.swing.JButton b_voltar;
    private javax.swing.JButton b_voltarLM;
    private javax.swing.JLabel l_autores;
    private javax.swing.JLabel l_cores;
    private javax.swing.JLabel l_descricao;
    private javax.swing.JLabel l_frascos;
    private javax.swing.JLabel l_texto;
    private javax.swing.JLabel l_titulo;
    private javax.swing.JLabel l_tituloLM;
    private javax.swing.JLabel l_tituloMSG;
    private javax.swing.JPanel p_gameplay;
    private javax.swing.JPanel p_mensagens;
    private javax.swing.JPanel p_principal;
    private javax.swing.JPanel p_titulo;
    private javax.swing.JPanel p_tituloLM;
    private javax.swing.JSpinner s_cores;
    private javax.swing.JSpinner s_frascos;
    private javax.swing.JTextField t_codigo;
    // End of variables declaration//GEN-END:variables
}
