package pilha;

import estruturadedados.No;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
    final int TAM = 3; // quantidade de garrafas na versão de output
    final int OX = 12; // (O)ffset (X) do mouse que o label fica quando clicado
    final int OY = -11; // (O)ffset (Y) do mouse que o label fica quando clicado
    final double FRASCO = 50; // vai servir pro tamanho das coisas
    
    // outras variáveis
    private static int x, y; // guardam a posição padrão do label arrastado
    JLabel arrastado = new JLabel(); // guarda o label que vai ser arrastado
    PilhaExemplo[] garrafas = new PilhaExemplo[TAM]; // vetor de pilhas
    Scanner scan = new Scanner(System.in); // tu sabe
    Timer arrastar; // atualiza a posição de "arrastado" numa taxa constante

    public JogoDasGarrafas() {
        // método gerado automaticamente
        initComponents();
        
        // serve para configurar o estado inicial da versão de output
        for (int i = 0; i < TAM; i++) {
            garrafas[i] = new PilhaExemplo();
            garrafas[i].empilha("verde");
            garrafas[i].empilha("amarelo");
            garrafas[i].empilha("azul");
        }

        // evento que atualiza a 144Hz a posição do objeto arrastado
        // de acordo com a posição do mouse (não sei se esse try e catch ainda
        // são necessários, já devo ter me livrado da causa do problema)
        arrastar = new Timer(1000 / 144, (ActionEvent evt1) -> {
            try {
                arrastado.setBounds(limiteX(), limiteY(), arrastado.getBounds().width, arrastado.getBounds().height);
            } catch (Exception ex) {
                System.out.println("Deu merda");
                arrastar.restart();
            }

        });
        
        // define as imagens dos 2 labels que temos por enquanto
        imagem(jLabel1, "tubo");
        imagem(jLabel2, "fundo tubo", new Color(125, 30, 10));
        
        // descomentar as 2 linhas abaixo para ativar a versão de output
        //printar();
        //gameloop();
    }
    
    // método chamado toda vez que um dos labels é clicado
    private void arrastar(JLabel label, MouseEvent evt) {
        // se o jogador conseguir magicamente clicar no botão que ele tá 
        // arrastando nada vai acontecer pq esse if nos protege
        if (arrastado.equals(label)) {
            return;
        }
        
        // se o timer estiver ativo, quer dizer que o jogador já clicou em
        // um label e está clicando no segundo agora, então o timer é 
        // desativado e o label que estava seguindo o mouse volta 
        // à sua posição original
        if (arrastar.isRunning()) {
            arrastar.stop();
            arrastado.setBounds(x, y, arrastado.getBounds().width,
                    arrastado.getBounds().height);
            arrastado = new JLabel();
        
        // caso o timer esteja desativado, quer dizer que o jogador clicou no
        // primeiro label, então o programa salva a posição dele, o define
        // como o label que vai ser arrastado e inicia o timer
        } else {
            x = label.getBounds().x;
            y = label.getBounds().y;
            arrastado = label;
            arrastar.start();
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
        if (MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x < BORDA - OX)
            return BORDA;
        if (MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x > 500 - BORDA - arrastado.getBounds().width - OX)
            return 500 - BORDA - arrastado.getBounds().width;
        return MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x + OX;
    }

    // mesma coisa que o anterior só que vertical
    private int limiteY() {
        if (MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y < BORDA - OY)
            return BORDA;
        if (MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y > 500 - BORDA - arrastado.getBounds().height - OY)
            return 500 - BORDA - arrastado.getBounds().height;
        return MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y + OY;
    }

    // põe uma imagem num label de acordo com os parâmetros, a cor é opcional
    // e pode ser adicionada num formato RGB como na linha 61
    private void imagem(JLabel label, String nome, Color cor) {
        // essas "BufferedImage"s permitem que a gente mude a cor da imagem
        // a "buff" vai pegar a imagem de acordo com o caminho dela (linha 179)
        // e "conv" vai converter "buff" pra um espectro de cores mais
        // conveniente para os nossos propósitos
        BufferedImage buff;
        BufferedImage conv;
        
        try {
            buff = ImageIO.read(new File("src/pilha/imagens/" + nome + ".png"));
            conv = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            conv.getGraphics().drawImage(buff, 0, 0, null);
        } catch (IOException ex) {
            System.out.println("Arquivo não encontrado");
            return;
        }

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
        ImageIcon img = new ImageIcon(((Image) conv).getScaledInstance(50, -1, 1));
        label.setBorder(null);
        label.setBounds(label.getBounds().x, label.getBounds().y, img.getIconWidth(), img.getIconHeight());
        label.setIcon(img);
    }

    // pra cá que o programa vem quando o parâmetro "cor" não é informado
    // por isso que o padrão é "null"
    private void imagem(JLabel label, String nome) {
        imagem(label, nome, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_principal = new javax.swing.JPanel();
        p_inicio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        p_principal.setLayout(new java.awt.CardLayout());

        p_inicio.setMinimumSize(new java.awt.Dimension(500, 500));
        p_inicio.setLayout(null);

        jLabel1.setText("Label 1");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        p_inicio.add(jLabel1);
        jLabel1.setBounds(66, 74, 60, 18);

        jLabel2.setText("Label 2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        p_inicio.add(jLabel2);
        jLabel2.setBounds(310, 80, 60, 16);

        p_principal.add(p_inicio, "inicio");

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

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        arrastar(jLabel1, evt);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        arrastar(jLabel2, evt);
    }//GEN-LAST:event_jLabel2MousePressed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel p_inicio;
    private javax.swing.JPanel p_principal;
    // End of variables declaration//GEN-END:variables
}
