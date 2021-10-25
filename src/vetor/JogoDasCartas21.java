package vetor;

import javax.swing.ImageIcon;

public class JogoDasCartas21 extends javax.swing.JFrame {

    private int mao1, mao2;
    
    public JogoDasCartas21() {
        initComponents();
        setResizable(false);
        painel.setOpaque(false);
        imgCarta(background, "background", "2");
        iniciar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        painel = new javax.swing.JPanel();
        j1Name = new javax.swing.JTextField();
        j2Name = new javax.swing.JTextField();
        j1Label = new javax.swing.JLabel();
        j2Label = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        j1Sacar = new javax.swing.JButton();
        j1Finalizar = new javax.swing.JButton();
        j2Sacar = new javax.swing.JButton();
        j2Finalizar = new javax.swing.JButton();
        j1Text = new javax.swing.JLabel();
        j2Text = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        j1Name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        j1Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        j1Name.setText("Jogador 1");
        j1Name.setPreferredSize(new java.awt.Dimension(150, 22));
        j1Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                j1NameKeyTyped(evt);
            }
        });

        j2Name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        j2Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        j2Name.setText("Jogador 2");
        j2Name.setPreferredSize(new java.awt.Dimension(150, 22));
        j2Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                j2NameKeyTyped(evt);
            }
        });

        separador.setForeground(new java.awt.Color(255, 255, 255));
        separador.setOrientation(javax.swing.SwingConstants.VERTICAL);
        separador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        j1Sacar.setText("Sacar");
        j1Sacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        j1Sacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1SacarActionPerformed(evt);
            }
        });

        j1Finalizar.setText("Finalizar");
        j1Finalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        j1Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1FinalizarActionPerformed(evt);
            }
        });

        j2Sacar.setText("Sacar");
        j2Sacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        j2Sacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2SacarActionPerformed(evt);
            }
        });

        j2Finalizar.setText("Finalizar");
        j2Finalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        j2Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2FinalizarActionPerformed(evt);
            }
        });

        j1Text.setForeground(new java.awt.Color(204, 204, 204));
        j1Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j1Text.setText("Mão:");

        j2Text.setForeground(new java.awt.Color(204, 204, 204));
        j2Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j2Text.setText("Mão:");

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(204, 204, 204));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("WWWWWWWWWW venceu: WWWWWWWWWW passou de 21");

        reiniciar.setText("Reiniciar");
        reiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reiniciar.setPreferredSize(new java.awt.Dimension(80, 22));
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(j1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(j1Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(j1Text, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(j1Sacar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(j1Finalizar)))
                .addGap(25, 25, 25)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(j2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j2Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(j2Sacar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(j2Finalizar))
                    .addComponent(j2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(j1Name, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(j2Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(j1Text)
                            .addComponent(j2Text))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(j2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(j1Sacar)
                                .addComponent(j1Finalizar))
                            .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(j2Sacar)
                                .addComponent(j2Finalizar))))
                    .addComponent(separador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(painel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(background, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        iniciar();
    }//GEN-LAST:event_reiniciarActionPerformed

    private void j2FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2FinalizarActionPerformed
        finalizar(2);
    }//GEN-LAST:event_j2FinalizarActionPerformed

    private void j2SacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j2SacarActionPerformed
        sacar(j2Label, j2Text, 1);
    }//GEN-LAST:event_j2SacarActionPerformed

    private void j1FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1FinalizarActionPerformed
        finalizar(1);
    }//GEN-LAST:event_j1FinalizarActionPerformed

    private void j1SacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1SacarActionPerformed
        sacar(j1Label, j1Text, 2);
    }//GEN-LAST:event_j1SacarActionPerformed

    private void j1NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_j1NameKeyTyped
        if ((j1Name.getText() + evt.getKeyChar()).length() > 10)
            evt.consume();
    }//GEN-LAST:event_j1NameKeyTyped

    private void j2NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_j2NameKeyTyped
        if ((j2Name.getText() + evt.getKeyChar()).length() > 10)
            evt.consume();
    }//GEN-LAST:event_j2NameKeyTyped
        
    private void iniciar() {
        onOffBotoes(true);
        
        mao1 = 0;
        mao2 = 0;
        this.imgCarta(j1Label, "background", "1");
        this.imgCarta(j2Label, "background", "1");
        j1Text.setText("Mão: ");
        j2Text.setText("Mão: ");
        titleLabel.setText("Bem vindo ao 21!");
    }
    
    private void imgCarta(javax.swing.JLabel label, String nape, String face) {
        ImageIcon imgCarta = new ImageIcon(getClass().getResource("/vetor/jogo/CartasBaralho/" + nape + " (" + face + ").png"));
        imgCarta.setImage(imgCarta.getImage().getScaledInstance(label.getWidth(), label.getHeight(), 1));
        label.setIcon(imgCarta);
    }
    
    private String setSortCarta(javax.swing.JLabel label) {
        // Criando o vetor de String que contem os Nipes do Baralho
        String[] nps = {"Ouro", "Copas", "Espadas", "Paus"};
        // Criando o vetor de String que contem os Valores das Cartas
        String[] fcs = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
        String nape = nps[(int)(Math.random()*4)];
        String face = fcs[(int)(Math.random()*13)];
        this.imgCarta(label, nape, face);
        return face;
    }
    
    private void sacar(javax.swing.JLabel label, javax.swing.JLabel text, int adversario) {
         
        int face = Integer.parseInt(setSortCarta(label));
        int mao;
        if(face > 10)
            face = 10;
        if (adversario==2) {
            mao1 += face;
            mao = mao1;
        } else {
            mao2 += face;
            mao = mao2;
        }
        if ("Mão: ".equals(text.getText()))
            text.setText(text.getText() + face + " (" + mao + ")");
        else
            text.setText(text.getText().substring(0, text.getText().length()-(3+Integer.toString(mao-face).length())) + " +" + face + " (" + mao + ")");
        if(mao > 21){
            gameOver(adversario+2);
        }
    }
    
    private void finalizar(int jogador) {
        if (jogador == 1) {
            j1Sacar.setEnabled(false);
            j1Finalizar.setEnabled(false);
        } else {
            j2Sacar.setEnabled(false);
            j2Finalizar.setEnabled(false);
        }
        if (!j1Finalizar.isEnabled() & !j2Finalizar.isEnabled()) {
            if (mao1 > mao2)
                gameOver(1);
            else if (mao2 > mao1)
                gameOver(2);
            else
                gameOver(0);
        }
    }
    
    private void gameOver(int caso) {
        onOffBotoes(false);
        switch(caso){
            case 0 ->{
                titleLabel.setText("EMPATE: As mãos tem o mesmo valor!");
            }
            case 1 ->{
                titleLabel.setText(j1Name.getText().strip() + " venceu: A mão dele é a maior!");
            }
            case 2 ->{
                titleLabel.setText(j2Name.getText().strip() + " venceu: A mão dele é a maior!");
            }
            case 3 ->{
                titleLabel.setText(j1Name.getText().strip() + " venceu: " + j2Name.getText().strip() + " passou de 21");
            }
            case 4 ->{
                titleLabel.setText(j2Name.getText().strip() + " venceu: " + j1Name.getText().strip() + " passou de 21");
            }
        }
    }
    
    public void onOffBotoes(boolean bool) {
        j1Sacar.setEnabled(bool);
        j1Finalizar.setEnabled(bool);
        j2Sacar.setEnabled(bool);
        j2Finalizar.setEnabled(bool);
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
            java.util.logging.Logger.getLogger(JogoDasCartas21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JogoDasCartas21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JogoDasCartas21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogoDasCartas21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogoDasCartas21().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton j1Finalizar;
    private javax.swing.JLabel j1Label;
    private javax.swing.JTextField j1Name;
    private javax.swing.JButton j1Sacar;
    private javax.swing.JLabel j1Text;
    private javax.swing.JButton j2Finalizar;
    private javax.swing.JLabel j2Label;
    private javax.swing.JTextField j2Name;
    private javax.swing.JButton j2Sacar;
    private javax.swing.JLabel j2Text;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel painel;
    private javax.swing.JButton reiniciar;
    private javax.swing.JSeparator separador;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
