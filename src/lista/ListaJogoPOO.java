package lista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class ListaJogoPOO extends JFrame {

    private final int ESP = 10;
    private final int IMG = 18;
    long tempoEmMili;
    int[] imagens = new int[IMG];
    int[] relacao;
    int x, y, larg, alt, vitoria, index, erros;
    Timer virarCartas, mostrarCartas, terminar;
    Botao check;

    // vetor que vai servir como lista, dentro da classe dele há métodos
    // para procurar, adicionar e remover elementos, mas eles não têm
    // utilidade nesse código, por isso não os uso, todos os botões foram
    // adicionados por meio do construtor e eles não precisam ser removidos
    // ou encontrados em nenhum momento ao longo do código
    Botao[] btns;

    public ListaJogoPOO() {
        initComponents();
        tocarSom("bgm", true, 0.075f);
        iniciar();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_R) && !terminar.isRunning()) {
                    iniciar();
                }
            }
        });

        t_altura.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    jogar();
                }
            }
        });

        t_largura.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    jogar();
                }
            }
        });

        terminar = new Timer(3000, (ActionEvent evt) -> {
            tela("final");
            terminar.stop();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_principal = new javax.swing.JPanel();
        p_menu = new javax.swing.JPanel();
        l_titulo = new javax.swing.JLabel();
        l_tamanho = new javax.swing.JLabel();
        t_altura = new javax.swing.JTextField();
        t_largura = new javax.swing.JTextField();
        l_X = new javax.swing.JLabel();
        b_jogar = new javax.swing.JButton();
        l_mensagem = new javax.swing.JLabel();
        l_R1 = new javax.swing.JLabel();
        p_jogo = new javax.swing.JPanel();
        p_final = new javax.swing.JPanel();
        l_texto = new javax.swing.JLabel();
        l_tempo = new javax.swing.JLabel();
        l_erros = new javax.swing.JLabel();
        l_R2 = new javax.swing.JLabel();
        i_segundos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        p_principal.setPreferredSize(new java.awt.Dimension(616, 639));
        p_principal.setLayout(new java.awt.CardLayout());

        p_menu.setMinimumSize(new java.awt.Dimension(616, 639));
        p_menu.setPreferredSize(new java.awt.Dimension(616, 639));
        p_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_titulo.setFont(new java.awt.Font("Rockwell", 0, 70)); // NOI18N
        l_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_titulo.setText("Jogo da Memória");
        l_titulo.setFocusable(false);
        p_menu.add(l_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 600, -1));

        l_tamanho.setFont(new java.awt.Font("MS UI Gothic", 0, 24)); // NOI18N
        l_tamanho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_tamanho.setText("Selecione abaixo o tamanho do grid");
        l_tamanho.setFocusable(false);
        p_menu.add(l_tamanho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 600, -1));
        l_tamanho.getAccessibleContext().setAccessibleDescription("");

        t_altura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_altura.setText("2");
        t_altura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_alturaKeyTyped(evt);
            }
        });
        p_menu.add(t_altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 40, -1));

        t_largura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_largura.setText("2");
        t_largura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_larguraKeyTyped(evt);
            }
        });
        p_menu.add(t_largura, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 40, -1));

        l_X.setFont(new java.awt.Font("MS UI Gothic", 0, 18)); // NOI18N
        l_X.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_X.setText("X");
        l_X.setFocusable(false);
        l_X.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        p_menu.add(l_X, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 305, 20, -1));

        b_jogar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        b_jogar.setText("JOGAR");
        b_jogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_jogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_jogarMousePressed(evt);
            }
        });
        b_jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_jogarActionPerformed(evt);
            }
        });
        p_menu.add(b_jogar, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 380, 250, -1));

        l_mensagem.setFont(new java.awt.Font("MS UI Gothic", 0, 18)); // NOI18N
        l_mensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_mensagem.setText("O tamanho mínimo (altura x largura) é 2 e o máximo ");
        p_menu.add(l_mensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 600, -1));

        l_R1.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        l_R1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_R1.setText("Pressione a tecla [R] para voltar ao menu!");
        p_menu.add(l_R1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 575, 600, -1));

        p_principal.add(p_menu, "menu");

        p_jogo.setMinimumSize(new java.awt.Dimension(616, 639));
        p_jogo.setPreferredSize(new java.awt.Dimension(616, 639));
        p_jogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_principal.add(p_jogo, "jogo");

        p_final.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_texto.setFont(new java.awt.Font("Algerian", 2, 36)); // NOI18N
        l_texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_texto.setText("Seu tempo foi");
        p_final.add(l_texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 600, -1));

        l_tempo.setFont(new java.awt.Font("Segoe UI", 1, 130)); // NOI18N
        l_tempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_tempo.setText("43,235");
        p_final.add(l_tempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, 600, -1));

        l_erros.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        l_erros.setForeground(new java.awt.Color(255, 0, 0));
        l_erros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_erros.setText("Quantidade de erros: ");
        p_final.add(l_erros, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 600, -1));

        l_R2.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        l_R2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_R2.setText("Pressione a tecla [R] para voltar ao menu!");
        p_final.add(l_R2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 575, 600, -1));

        i_segundos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        i_segundos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        i_segundos.setText("segundos");
        p_final.add(i_segundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 600, -1));

        p_principal.add(p_final, "final");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_jogarActionPerformed
        jogar();
    }//GEN-LAST:event_b_jogarActionPerformed

    private void t_alturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_alturaKeyTyped
        if (!numerico("" + evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_t_alturaKeyTyped

    private void t_larguraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_larguraKeyTyped
        if (!numerico("" + evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_t_larguraKeyTyped

    private void b_jogarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_jogarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_jogarMousePressed

    private void iniciar() {
        check = null;
        erros = 0;
        tempoEmMili = 0;
        l_mensagem.setFont(new Font(l_mensagem.getFont().getFontName(), Font.PLAIN, 18));
        l_mensagem.setForeground(Color.black);
        l_mensagem.setText("O tamanho mínimo (altura x largura) é 2 e o máximo " + IMG * 2);
        tela("menu");
        for (Component c : p_jogo.getComponents()) {
            if (c instanceof JButton) {
                p_jogo.remove(c);
            }
        }
        l_erros.setText("Quantidade de erros: ");
        t_altura.requestFocus();
    }

    private void jogar() {
        if (mostrarCartas == null ? true : !mostrarCartas.isRunning()) {
            alt = Integer.parseInt(t_altura.getText());
            larg = Integer.parseInt(t_largura.getText());

            if ((alt * larg > IMG * 2) || (alt * larg < 2)) {
                l_mensagem.setForeground(Color.red);
                l_mensagem.setFont(new Font(l_mensagem.getFont().getFontName(), Font.BOLD, l_mensagem.getFont().getSize() + (l_mensagem.getFont().getSize() < 24 ? 1 : 0)));
                final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
                if (runnable != null) {
                    runnable.run();
                }
                return;
            }

            y = (600 - ESP * (alt + 1)) / alt;
            x = (600 - ESP * (larg + 1)) / larg;
            boolean par = (alt * larg) % 2 == 0;
            btns = new Botao[alt * larg - (par ? 0 : 1)];
            relacao = new int[btns.length];
            vitoria = btns.length / 2;
            for (int i = 0; i < imagens.length; i++) {
                imagens[i] = i;
            }
            imagens = embaralhar(imagens);

            for (int i = 0; i < relacao.length / 2; i++) {
                for (int j = 0; j < 2; j++) {
                    relacao[i * 2 + j] = imagens[i];
                }
            }
            relacao = embaralhar(relacao);

            tela("jogo");

            for (int i = 0; i < alt; i++) {
                for (int j = 0; j < larg; j++) {
                    index = i * larg + j;
                    if (!par && index >= btns.length / 2) {
                        if (index == btns.length / 2) {
                            continue;
                        }
                        index--;
                    }
                    btns[index] = new Botao(new ImageIcon(getClass().getResource("/lista/imagens/" + imagens[relacao[index]] + ".png")), x, y);
                    btns[index].mostrarImagem();
                    btns[index].addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            cliqueBotao(evt);
                        }
                    });
                    p_jogo.add(btns[index], new org.netbeans.lib.awtextra.AbsoluteConstraints(ESP * (j + 1) + x * j, ESP * (i + 1) + y * i, x, y));
                }
            }

            mostrarCartas = new Timer(btns.length * 150, (ActionEvent evt1) -> {
                for (Botao btn : btns) {
                    btn.esconderImagem();
                }
                mostrarCartas.stop();
            });
            mostrarCartas.start();

            requestFocus();
        }
    }

    private void cliqueBotao(java.awt.event.MouseEvent evt) {
        Botao botao = (Botao) evt.getSource();

        if (tempoEmMili == 0) {
            tempoEmMili = System.currentTimeMillis();
        }

        if (!botao.taMostrando() && (virarCartas == null ? true : !virarCartas.isRunning()) && !mostrarCartas.isRunning()) {
            tocarSom("carta");

            botao.mostrarImagem();

            if (check == null) {
                check = botao;
            } else {
                if (check.getImagem().toString().equals(botao.getImagem().toString())) {
                    tocarSom("sucesso", 0.5f);
                    vitoria--;
                    if (vitoria == 0) {
                        tocarSom("vitoria", 0.1f);
                        l_tempo.setText(String.format("%.3f", (double) (System.currentTimeMillis() - tempoEmMili) / 1000));
                        tempoEmMili = 0;
                        l_erros.setForeground(erros == 0 ? Color.green : Color.red);
                        l_erros.setText(l_erros.getText() + erros);
                        erros = 0;
                        terminar.start();
                    }
                    check = null;
                } else {
                    tocarSom("erro", 0.5f);
                    erros++;
                    virarCartas = new Timer(500, (ActionEvent evt1) -> {
                        check.esconderImagem();
                        botao.esconderImagem();
                        check = null;
                        virarCartas.stop();
                    });
                    virarCartas.start();
                }
            }
        }
    }

    private void tela(String nome) {
        CardLayout cl = (CardLayout) p_principal.getLayout();
        cl.show(p_principal, nome);
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

    private static int[] embaralhar(int[] vet) {
        int[] sort = vet;
        int[] aux;
        int[] fim = new int[vet.length];
        int pos;

        for (int i = 0; i < vet.length - 1; i++) {
            pos = (int) (Math.random() * sort.length);
            fim[i] = sort[pos];
            aux = new int[sort.length - 1];
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

    private static boolean numerico(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

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
            java.util.logging.Logger.getLogger(ListaJogoPOO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaJogoPOO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaJogoPOO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaJogoPOO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaJogoPOO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_jogar;
    private javax.swing.JLabel i_segundos;
    private javax.swing.JLabel l_R1;
    private javax.swing.JLabel l_R2;
    private javax.swing.JLabel l_X;
    private javax.swing.JLabel l_erros;
    private javax.swing.JLabel l_mensagem;
    private javax.swing.JLabel l_tamanho;
    private javax.swing.JLabel l_tempo;
    private javax.swing.JLabel l_texto;
    private javax.swing.JLabel l_titulo;
    private javax.swing.JPanel p_final;
    private javax.swing.JPanel p_jogo;
    private javax.swing.JPanel p_menu;
    private javax.swing.JPanel p_principal;
    private javax.swing.JTextField t_altura;
    private javax.swing.JTextField t_largura;
    // End of variables declaration//GEN-END:variables
}
