package pilha;

import estruturadedados.No;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class JogoDasGarrafas extends javax.swing.JFrame {

    // variáveis de configuração
    final int BORDA = 5; // até quantos pixels da borda o label pode ir
    final int TAM = 5; // quantidade de garrafas na versão de output
    final int OX = 12; // (O)ffset (X) do mouse que o label fica quando clicado
    final int OY = -11; // (O)ffset (Y) do mouse que o label fica quando clicado
    final static int DIMENSAO = 40; // vai servir pro tamanho das coisas

    // outras variáveis
    private static int x, y; // guardam a posição padrão do label arrastado
    JLabel arrastado = new JLabel(); // guarda o label que vai ser arrastado
    PilhaJogo[] garrafas = new PilhaJogo[TAM];
    Scanner scan = new Scanner(System.in); // tu sabe
    Timer arrastar; // atualiza a posição de "arrastado" numa taxa constante

    public JogoDasGarrafas() {
        // método gerado automaticamente
        initComponents();

        p_gameplay.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrastar(evt);
            }
        });

        // evento que atualiza a 60Hz a posição do objeto arrastado
        // de acordo com a posição do mouse (não sei se esse try e catch ainda
        // são necessários, já devo ter me livrado da causa do problema)
        arrastar = new Timer(1000 / 60, (ActionEvent evt1) -> {
            try {
                arrastado.setBounds(limiteX(), limiteY(), arrastado.getBounds().width, arrastado.getBounds().height);
            } catch (Exception ex) {
                System.out.println("Deu merda");
                arrastar.restart();
            }

        });
        
        

        for (int i = 0; i < TAM; i++) {
            garrafas[i] = new PilhaJogo();
            garrafas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    arrastar(evt);
                }
            });
            p_gameplay.add(garrafas[i]);
            garrafas[i].setBounds(500 / TAM * (i + 1) - 500 / (TAM * 2) - garrafas[i].getIcon().getIconWidth() / 2, 250 - garrafas[i].getIcon().getIconHeight() / 2, garrafas[i].getPreferredSize().width, garrafas[i].getPreferredSize().height);
        }

        // define as imagens dos 2 labels que temos por enquanto
        // descomentar as 2 linhas abaixo para ativar a versão de output
        //printar();
        //gameloop();
    }

    // método chamado toda vez que um dos labels é clicado
    private void arrastar(java.awt.event.MouseEvent evt) {
        JLabel label;
        if (evt.getButton() == 3) {
            if (arrastar.isRunning()) {
                arrastar.stop();
                arrastado.setBounds(x, y, arrastado.getBounds().width,
                        arrastado.getBounds().height);
                arrastado = new JLabel();
            }
            System.out.println("Cancelado");
            return;
        } else if (evt.getButton() == 2)
            return;
        try {
            label = (JLabel) evt.getSource();
            System.out.println("Clique no label");
        } catch (ClassCastException ex) {
            System.out.println("Clique na tela");
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
                arrastar.stop();
                arrastado.setBounds(x, y, arrastado.getBounds().width,
                        arrastado.getBounds().height);
                arrastado = new JLabel();
                System.out.println("Colocado");

                // caso o timer esteja desativado, quer dizer que o jogador clicou no
                // primeiro label, então o programa salva a posição dele, o define
                // como o label que vai ser arrastado e inicia o timer
            } else {
                x = label.getBounds().x;
                y = label.getBounds().y;
                arrastado = label;
                arrastar.start();
                System.out.println("Arrastando");
            }
        }
    }

    // comanda todo o funcionamento da versão para output
    private void gameloop() {
        int qual; // guarda a qual garrafa foi escolhida
        No atual; // percorre as garrafas

        // guarda o(s) nó(s) a serem passados para outra garrafa
        PilhaExemplo garrafa;

        // preenche o objeto "garrafa" 
        while (true) {
            garrafa = new PilhaExemplo();
            System.out.println("De qual garrafa você quer tirar? ");
            qual = scan.nextInt();
            atual = garrafas[qual - 1].get();

            // isso aqui roda até que o próximo nó não tenha mais a mesma cor
            // que o atual
            while (true) {
                garrafa.empilha(atual.getDado());
                garrafas[qual - 1].desempilha();
                if (atual.getProx() == null || !atual.getDado().equals(atual.getProx().getDado())) {
                    break;
                }
                atual = atual.getProx();
            }
            System.out.println("Em qual garrafa você quer por? ");
            qual = scan.nextInt();

            // criei um método "tam()" na classe "PilhaExemplo" pra
            // facilitar minha vida
            for (int i = 0; i < garrafa.tam(); i++) {
                garrafas[qual - 1].empilha(garrafa.get().getDado());
            }
            printar();
        }
    }

    // imprime o conteúdo de todas as pilhas do vetor "garrafas"
    private void printar() {
        No atual; // passa por cada nó de cada pilha
        for (int i = 0; i < TAM; i++) {
            atual = garrafas[i].get();
            for (int j = 0; j < garrafas[i].tam(); j++) {
                System.out.println(String.format("%10s", atual.getDado()));
                atual = atual.getProx();
            }
            System.out.println();
        }
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
            buff = ImageIO.read(new File("src/pilha/imagens/" + nome + ".png"));
        } catch (IOException ex) {
            System.out.println("Arquivo não encontrado");
            return null;
        }

        conv = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        conv.getGraphics().drawImage(buff, 0, 0, null);

        // Se o parâmetro "cor" não for informado ele 
        // recebe "null" por padrão (linha 212) e a imagem não é alterada
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
        p_mensagens = new javax.swing.JPanel();
        p_gameplay = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        p_principal.setLayout(new java.awt.CardLayout());

        p_titulo.setMinimumSize(new java.awt.Dimension(500, 500));
        p_titulo.setLayout(null);

        l_titulo.setFont(new java.awt.Font("Papyrus", 3, 50)); // NOI18N
        l_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_titulo.setText("Water Sort Puzzle");
        p_titulo.add(l_titulo);
        l_titulo.setBounds(0, 60, 500, 60);

        l_autores.setFont(new java.awt.Font("High Tower Text", 0, 18)); // NOI18N
        l_autores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_autores.setText("Entregue a vocês por Edimax, Gabriel e Wesley");
        p_titulo.add(l_autores);
        l_autores.setBounds(0, 130, 500, 22);

        b_conceitos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_conceitos.setText("CONCEITOS");
        b_conceitos.setFocusable(false);
        p_titulo.add(b_conceitos);
        b_conceitos.setBounds(370, 450, 120, 40);

        b_jogar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        b_jogar.setText("JOGAR");
        p_titulo.add(b_jogar);
        b_jogar.setBounds(160, 280, 180, 60);

        b_instrucoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        b_instrucoes.setText("COMO JOGAR");
        b_instrucoes.setFocusable(false);
        p_titulo.add(b_instrucoes);
        b_instrucoes.setBounds(10, 452, 120, 40);

        p_principal.add(p_titulo, "titulo");

        p_mensagens.setMinimumSize(new java.awt.Dimension(500, 500));
        p_mensagens.setLayout(null);
        p_principal.add(p_mensagens, "mensagens");

        p_gameplay.setMinimumSize(new java.awt.Dimension(500, 500));
        p_gameplay.setLayout(null);
        p_principal.add(p_gameplay, "gameplay");

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
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogoDasGarrafas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton b_instrucoes;
    private javax.swing.JButton b_jogar;
    private javax.swing.JLabel l_autores;
    private javax.swing.JLabel l_titulo;
    private javax.swing.JPanel p_gameplay;
    private javax.swing.JPanel p_mensagens;
    private javax.swing.JPanel p_principal;
    private javax.swing.JPanel p_titulo;
    // End of variables declaration//GEN-END:variables
}
