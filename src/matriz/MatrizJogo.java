package matriz;
/**
 * Para desativar o bot e transfomar em multiplayer deve-se:
 * - descomentar as linhas: 18, 379 a 382, 394
 * - comentar as linhas: 371, 384
 * 
 * Autores:
 * - Wesley Sousa   20192264158
 * - Gabriel Souza  20192263939
 */


import java.awt.Color;
import javax.swing.BorderFactory;

public class MatrizJogo extends javax.swing.JFrame {

    //boolean turno;
    // Cria uma matriz de JButtons
    javax.swing.JButton[][] botoes;
    
    // Cria uma matriz que vai servir para que o programa analise
    // todas as linhas, colunas e diagonais do jogo da velha
    String[][] pos = new String[8][3];
    
    // Cria uma matriz que relaciona as posiçoes da variável "pos" com as da variável "botoes"
    final int[][][] POS_LN_COL = {{{0,0}, {0,1}, {0,2}},
                                  {{1,0}, {1,1}, {1,2}},
                                  {{2,0}, {2,1}, {2,2}},
                                  {{0,0}, {1,0}, {2,0}},
                                  {{0,1}, {1,1}, {2,1}},
                                  {{0,2}, {1,2}, {2,2}},
                                  {{0,0}, {1,1}, {2,2}},
                                  {{0,2}, {1,1}, {2,0}}};

    public MatrizJogo() {
        initComponents();
        // Preenche a matriz de JButton com 9 botões para mapear o Jogo da Velha
        // agora cada posição da matriz aponta para o jButton que ela representa
        botoes = new javax.swing.JButton[][]{{botao00, botao01, botao02},
                                             {botao10, botao11, botao12},
                                             {botao20, botao21, botao22}};
        iniciar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        painelBotoes = new javax.swing.JPanel();
        botao00 = new javax.swing.JButton();
        botao01 = new javax.swing.JButton();
        botao02 = new javax.swing.JButton();
        botao10 = new javax.swing.JButton();
        botao11 = new javax.swing.JButton();
        botao12 = new javax.swing.JButton();
        botao20 = new javax.swing.JButton();
        botao21 = new javax.swing.JButton();
        botao22 = new javax.swing.JButton();
        background = new javax.swing.JLabel();
        panelReiniciar = new javax.swing.JPanel();
        reiniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        painelBotoes.setOpaque(false);

        botao00.setBackground(new java.awt.Color(255, 255, 255));
        botao00.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao00.setContentAreaFilled(false);
        botao00.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao00.setMaximumSize(new java.awt.Dimension(126, 126));
        botao00.setMinimumSize(new java.awt.Dimension(126, 126));
        botao00.setOpaque(true);
        botao00.setPreferredSize(new java.awt.Dimension(126, 126));
        botao00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao00ActionPerformed(evt);
            }
        });

        botao01.setBackground(new java.awt.Color(255, 255, 255));
        botao01.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao01.setContentAreaFilled(false);
        botao01.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao01.setMaximumSize(new java.awt.Dimension(126, 126));
        botao01.setMinimumSize(new java.awt.Dimension(126, 126));
        botao01.setOpaque(true);
        botao01.setPreferredSize(new java.awt.Dimension(126, 126));
        botao01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao01ActionPerformed(evt);
            }
        });

        botao02.setBackground(new java.awt.Color(255, 255, 255));
        botao02.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao02.setContentAreaFilled(false);
        botao02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao02.setMaximumSize(new java.awt.Dimension(126, 126));
        botao02.setMinimumSize(new java.awt.Dimension(126, 126));
        botao02.setOpaque(true);
        botao02.setPreferredSize(new java.awt.Dimension(126, 126));
        botao02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao02ActionPerformed(evt);
            }
        });

        botao10.setBackground(new java.awt.Color(255, 255, 255));
        botao10.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao10.setContentAreaFilled(false);
        botao10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao10.setMaximumSize(new java.awt.Dimension(126, 126));
        botao10.setMinimumSize(new java.awt.Dimension(126, 126));
        botao10.setOpaque(true);
        botao10.setPreferredSize(new java.awt.Dimension(126, 126));
        botao10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao10ActionPerformed(evt);
            }
        });

        botao11.setBackground(new java.awt.Color(255, 255, 255));
        botao11.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao11.setContentAreaFilled(false);
        botao11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao11.setMaximumSize(new java.awt.Dimension(126, 126));
        botao11.setMinimumSize(new java.awt.Dimension(126, 126));
        botao11.setOpaque(true);
        botao11.setPreferredSize(new java.awt.Dimension(126, 126));
        botao11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao11ActionPerformed(evt);
            }
        });

        botao12.setBackground(new java.awt.Color(255, 255, 255));
        botao12.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao12.setContentAreaFilled(false);
        botao12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao12.setMaximumSize(new java.awt.Dimension(126, 126));
        botao12.setMinimumSize(new java.awt.Dimension(126, 126));
        botao12.setOpaque(true);
        botao12.setPreferredSize(new java.awt.Dimension(126, 126));
        botao12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao12ActionPerformed(evt);
            }
        });

        botao20.setBackground(new java.awt.Color(255, 255, 255));
        botao20.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao20.setContentAreaFilled(false);
        botao20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao20.setMaximumSize(new java.awt.Dimension(126, 126));
        botao20.setMinimumSize(new java.awt.Dimension(126, 126));
        botao20.setOpaque(true);
        botao20.setPreferredSize(new java.awt.Dimension(126, 126));
        botao20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao20ActionPerformed(evt);
            }
        });

        botao21.setBackground(new java.awt.Color(255, 255, 255));
        botao21.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao21.setContentAreaFilled(false);
        botao21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao21.setMaximumSize(new java.awt.Dimension(126, 126));
        botao21.setMinimumSize(new java.awt.Dimension(126, 126));
        botao21.setOpaque(true);
        botao21.setPreferredSize(new java.awt.Dimension(126, 126));
        botao21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao21ActionPerformed(evt);
            }
        });

        botao22.setBackground(new java.awt.Color(255, 255, 255));
        botao22.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        botao22.setContentAreaFilled(false);
        botao22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao22.setMaximumSize(new java.awt.Dimension(126, 126));
        botao22.setMinimumSize(new java.awt.Dimension(126, 126));
        botao22.setOpaque(true);
        botao22.setPreferredSize(new java.awt.Dimension(126, 126));
        botao22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botao21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao01, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botao12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        background.setOpaque(true);

        panelReiniciar.setOpaque(false);

        reiniciar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reiniciar.setText("Reiniciar");
        reiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReiniciarLayout = new javax.swing.GroupLayout(panelReiniciar);
        panelReiniciar.setLayout(panelReiniciarLayout);
        panelReiniciarLayout.setHorizontalGroup(
            panelReiniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReiniciarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reiniciar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelReiniciarLayout.setVerticalGroup(
            panelReiniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReiniciarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reiniciar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(painelBotoes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(background, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panelReiniciar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelReiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Série de métodos para os eventos dos botões
    private void botao00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao00ActionPerformed
        jogar(0, 0);
    }//GEN-LAST:event_botao00ActionPerformed

    private void botao01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao01ActionPerformed
        jogar(0, 1);
    }//GEN-LAST:event_botao01ActionPerformed

    private void botao10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao10ActionPerformed
        jogar(1, 0);
    }//GEN-LAST:event_botao10ActionPerformed

    private void botao11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao11ActionPerformed
        jogar(1, 1);
    }//GEN-LAST:event_botao11ActionPerformed

    private void botao12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao12ActionPerformed
        jogar(1, 2);
    }//GEN-LAST:event_botao12ActionPerformed

    private void botao20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao20ActionPerformed
        jogar(2, 0);
    }//GEN-LAST:event_botao20ActionPerformed

    private void botao21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao21ActionPerformed
        jogar(2, 1);
    }//GEN-LAST:event_botao21ActionPerformed

    private void botao22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao22ActionPerformed
        jogar(2, 2);
    }//GEN-LAST:event_botao22ActionPerformed

    private void botao02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao02ActionPerformed
        jogar(0, 2);
    }//GEN-LAST:event_botao02ActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        iniciar();
    }//GEN-LAST:event_reiniciarActionPerformed

    // configura as variáveis para seus respectivos valores padrão
    private void iniciar() {
        //Passando por todos os elementos da matriz "pos" e preenchendo com vazio cada elemento
        for(int i = 0; i < pos.length; i++) {
            for(int j = 0; j < pos[i].length; j++)
                pos[i][j] = "";
        }
        //Passando por todos os elementos da matriz "botoes", configurando e preenchendo com vazio cada elemento
        for(int i = 0; i < botoes.length; i++) {
            for(int j = 0; j < botoes[i].length; j++) {
                botoes[i][j].setEnabled(true);
                botoes[i][j].setText("");
                botoes[i][j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }
    
    // método utilizado nos botões
    private void jogar(int x, int y) {
        marcar(x, y);
        cpu();
    }
    
    // método que coloca o parâmetro "xo" como o texto do botão presente 
    // na linha "ln" e coluna "col" da matriz "botoes"
    private void marcar(int ln, int col, String xo) {
        botoes[ln][col].setEnabled(false);
        
//        if(turno)
//            botoes[ln][col].setText("X");
//        else
//            botoes[ln][col].setText("O");
        
        botoes[ln][col].setText(xo);
        
        // insere dentro da matriz "pos"
        pos[ln][col] = botoes[ln][col].getText();
        pos[col+3][ln] = botoes[ln][col].getText();
        if(ln == col)
            pos[6][ln] = botoes[ln][col].getText();
        if(ln + col == 2)
            pos[7][ln] = botoes[ln][col].getText();
        
        //turno = !turno;
        
        // procura por 3 valores iguais nas linhas de "pos" e finaliza o jogo
        // colocando uma borda preta no trio vencedor e desativando todos botões
        for(int i = 0; i < pos.length; i++) {
            if(pos[i][0].equals(pos[i][1]) && pos[i][1].equals(pos[i][2]) && !"".equals(pos[i][0])) {
                for(int j = 0; j < botoes.length; j++)
                    for(int k = 0; k < botoes[j].length; k++)
                        botoes[k][j].setEnabled(false);
                botoes[POS_LN_COL[i][0][0]][POS_LN_COL[i][0][1]].setBorder(BorderFactory.createLineBorder(Color.black, 3));
                botoes[POS_LN_COL[i][1][0]][POS_LN_COL[i][1][1]].setBorder(BorderFactory.createLineBorder(Color.black, 3));
                botoes[POS_LN_COL[i][2][0]][POS_LN_COL[i][2][1]].setBorder(BorderFactory.createLineBorder(Color.black, 3));
                break;
            }
        }
    }
    
    // polimorfismo
    private void marcar(int ln, int col) {
        marcar(ln, col, "X");
    }
    
    private void cpu() {
        if(disp()) {
            // matriz que guarda as posicoes vazias do tabuleiro
            int[][] posVaz = new int[8][2];
            int qntVaz = 0;
            int rdm;
            if(analise("O")) {
                if(analise("X")) {
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            if("".equals(pos[i][j])) {
                                // guarda as posições de linha e coluna disponíveis
                                posVaz[qntVaz][0] = i;
                                posVaz[qntVaz][1] = j;
                                qntVaz++;
                            }
                        }
                    }
                    // pega aleatoriamente uma das posições disponíveis
                    rdm = (int)(Math.random()*qntVaz);
                    marcar(posVaz[rdm][0], posVaz[rdm][1], "O");
                }
            }
        }
    }
    
    // analisa se uma das linhas da matriz "pos" tem dois elementos 
    // iguais à variável "xo", que pode ser "O" ou "X"
    // se ele encontrar, marca "O" na posição restante
    private boolean analise(String xo) {
        for(int i = 0; i < pos.length; i++) {
            if(xo.equals(pos[i][0]) && xo.equals(pos[i][1]) && "".equals(pos[i][2])) {
                marcar(POS_LN_COL[i][2][0],POS_LN_COL[i][2][1], "O");
                return false;
            } else if(xo.equals(pos[i][0]) && "".equals(pos[i][1]) && xo.equals(pos[i][2])) {
                marcar(POS_LN_COL[i][1][0],POS_LN_COL[i][1][1], "O");
                return false;
            } else if("".equals(pos[i][0]) && xo.equals(pos[i][1]) && xo.equals(pos[i][2])) {
                marcar(POS_LN_COL[i][0][0],POS_LN_COL[i][0][1], "O");
                return false;
            }
        }
        return true;
    }
    
    // verifica se todas as posições estão disponíveis
    private boolean disp() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(botoes[i][j].isEnabled())
                    return true;
        return false;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(MatrizJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatrizJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatrizJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatrizJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatrizJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton botao00;
    private javax.swing.JButton botao01;
    private javax.swing.JButton botao02;
    private javax.swing.JButton botao10;
    private javax.swing.JButton botao11;
    private javax.swing.JButton botao12;
    private javax.swing.JButton botao20;
    private javax.swing.JButton botao21;
    private javax.swing.JButton botao22;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel panelReiniciar;
    private javax.swing.JButton reiniciar;
    // End of variables declaration//GEN-END:variables
}
