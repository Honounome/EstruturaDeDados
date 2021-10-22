package lista;

import java.awt.Color;
import javax.swing.ImageIcon;

public class ListaJogoB extends javax.swing.JFrame {

    private final int ESP = 10;
    private final int IMG = 18;

    public ListaJogoB() {
        initComponents();
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
        p_jogo = new javax.swing.JPanel();

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
        l_tamanho.setText("Selecione o tamanho do grid [2 a 6]:");
        l_tamanho.setFocusable(false);
        p_menu.add(l_tamanho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 600, -1));

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
        b_jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_jogarActionPerformed(evt);
            }
        });
        p_menu.add(b_jogar, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 360, 250, -1));

        p_principal.add(p_menu, "menu");

        p_jogo.setMinimumSize(new java.awt.Dimension(616, 639));
        p_jogo.setPreferredSize(new java.awt.Dimension(616, 639));
        p_jogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_principal.add(p_jogo, "jogo");

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
    }// </editor-fold>//GEN-END:initComponents

    private void b_jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_jogarActionPerformed
        int alt = Integer.parseInt(t_altura.getText());
        int larg = Integer.parseInt(t_largura.getText());
        int y = (600 - ESP * (alt + 1)) / alt;
        int x = (600 - ESP * (larg + 1)) / larg;
        int indice;
        int[] relacao;
        int[] imagens = new int[IMG];
        boolean par = (alt * larg) % 2 == 0;

        javax.swing.JButton[] botoes;

        botoes = new javax.swing.JButton[alt * larg - (par ? 0 : 1)];
        relacao = new int[botoes.length];

        for (int i = 0; i < relacao.length; i++) {
            relacao[i] = i;
        }
        relacao = shuffle(relacao);

        for (int i = 0; i < imagens.length; i++) {
            imagens[i] = i;
        }
        imagens = shuffle(imagens);

        tela("jogo");

        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                indice = i * larg + j;
                if (!par && indice >= botoes.length / 2) {
                    if (indice == botoes.length / 2) {
                        continue;
                    }
                    indice--;
                }
                botoes[indice] = new javax.swing.JButton();
                botoes[indice].setBackground(new java.awt.Color(255, 255, 255));
                botoes[indice].setFocusable(false);
                botoes[indice].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                botoes[indice].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JButton botao = (javax.swing.JButton) evt.getSource();
                        
                        if(botao.getIcon() == null) {
                            botao.setIcon(new ImageIcon(getClass().getResource("/lista/imagens/" + imagens[encontrarBotao(botao)] + ".png")));
                        }
                        
                    }
                });
                p_jogo.add(botoes[indice], new org.netbeans.lib.awtextra.AbsoluteConstraints(ESP * (j + 1) + x * j, ESP * (i + 1) + y * i, x, y));
            }
        }

        System.out.println((x < y) ? x : -1);
        System.out.println((x > y) ? y : -1);

        for (int i = 0; i < botoes.length / 2; i++) {
            ImageIcon imgCarta = new ImageIcon(getClass().getResource("/lista/imagens/" + imagens[i] + ".png"));
            imgCarta.setImage(imgCarta.getImage().getScaledInstance(((x < y || x == y) ? x - 10 : -1), ((x > y || x == y) ? y - 10 : -1), 1));
            botoes[relacao[i*2]].setIcon(imgCarta);
            botoes[relacao[i*2+1]].setIcon(imgCarta);
        }
        
        
    }//GEN-LAST:event_b_jogarActionPerformed

    private void t_alturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_alturaKeyTyped
        if (!isNumeric("" + evt.getKeyChar()) || Integer.parseInt(t_altura.getText() + evt.getKeyChar()) > 6 || Integer.parseInt(t_altura.getText() + evt.getKeyChar()) < 2)
            evt.consume();
    }//GEN-LAST:event_t_alturaKeyTyped

    private void t_larguraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_larguraKeyTyped
        if (!isNumeric("" + evt.getKeyChar()) || Integer.parseInt(t_largura.getText() + evt.getKeyChar()) > 6 || Integer.parseInt(t_largura.getText() + evt.getKeyChar()) < 2)
            evt.consume();
    }//GEN-LAST:event_t_larguraKeyTyped
    
    // criar um método que retorne a posição do botao passado por parâmetro, dentro do vetor botoes
    private int encontrarBotao(javax.swing.JButton )
    
    private void tela(String nome) {
        java.awt.CardLayout cl = (java.awt.CardLayout) p_principal.getLayout();
        cl.show(p_principal, nome);
    }

    private static int[] shuffle(int[] vet) {
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

    private static boolean isNumeric(String strNum) {
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
            java.util.logging.Logger.getLogger(ListaJogoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaJogoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaJogoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaJogoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaJogoB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_jogar;
    private javax.swing.JLabel l_X;
    private javax.swing.JLabel l_tamanho;
    private javax.swing.JLabel l_titulo;
    private javax.swing.JPanel p_jogo;
    private javax.swing.JPanel p_menu;
    private javax.swing.JPanel p_principal;
    private javax.swing.JTextField t_altura;
    private javax.swing.JTextField t_largura;
    // End of variables declaration//GEN-END:variables
}
