package pilha;

import estruturadedados.No;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.Timer;

public class JogoDasGarrafas extends javax.swing.JFrame {

    private static int x, y;
    JLabel arrastado = new JLabel();
    final int TAM = 3;
    final int OX = 12;
    final int OY = -11;
    boolean click = false;
    PilhaExemplo[] garrafas = new PilhaExemplo[TAM];
    Scanner scan = new Scanner(System.in);
    Timer arrastar;

    public JogoDasGarrafas() {
        initComponents();
        for (int i = 0; i < TAM; i++) {
            garrafas[i] = new PilhaExemplo();
            garrafas[i].empilha("verde");
            garrafas[i].empilha("amarelo");
            garrafas[i].empilha("azul");
        }

        arrastar = new Timer(1000/144, (ActionEvent evt1) -> {
            try {
                System.out.println("Xm: " + (MouseInfo.getPointerInfo().getLocation().x + OX));
                System.out.println("Xt: " + getLocationOnScreen().x);
                arrastado.setBounds(limiteX(),
                                    limiteY(),
                                    arrastado.getBounds().width,
                                    arrastado.getBounds().height);
                
//                arrastado.setBounds(getMousePosition(true).x + OX,
//                                    getMousePosition(true).y + OY,
//                                    arrastado.getBounds().width,
//                                    arrastado.getBounds().height);
            } catch (Exception ex) {
                System.out.println("Erro");
                arrastar.restart();
            }
            
        });
        //printar();
        //gameloop();
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

    private void arrastar(JLabel label, MouseEvent evt) {
        if(arrastado.equals(label))
            return;
        if(arrastar.isRunning()){
            arrastar.stop();
            arrastado.setBounds(x, y, arrastado.getBounds().width,
                    arrastado.getBounds().height);
            arrastado = new JLabel();
        } else {
            x = label.getBounds().x;
            y = label.getBounds().y;
            arrastado = label;
            arrastar.start();
        }
    }

    private void gameloop() {
        int qual;
        No atual;
        PilhaExemplo garrafa;
        while (true) {
            garrafa = new PilhaExemplo();
            System.out.println("De qual garrafa você quer tirar? ");
            qual = scan.nextInt();
            atual = garrafas[qual - 1].get();
            while (true) {
                garrafa.empilha(atual.getDado());
                garrafas[qual - 1].desempilha();
                if (atual.getProx() == null
                        || !atual.getDado().equals(atual.getProx().getDado())) {
                    break;
                }
                atual = atual.getProx();
            }
            System.out.println("Em qual garrafa você quer por? ");
            qual = scan.nextInt();
            for (int i = 0; i < garrafa.tam(); i++) {
                garrafas[qual - 1].empilha(garrafa.get().getDado());
            }
            printar();
        }
    }

    private void printar() {
        No atual;
        for (int i = 0; i < TAM; i++) {
            atual = garrafas[i].get();
            for (int j = 0; j < garrafas[i].tam(); j++) {
                System.out.println(String.format("%10s", atual.getDado()));
                atual = atual.getProx();
            }
            System.out.println();
        }
    }
    
    private int limiteX() {
        if(MouseInfo.getPointerInfo().getLocation().x < getLocationOnScreen().x)
            return OX;
        if(MouseInfo.getPointerInfo().getLocation().x > getLocationOnScreen().x + 500 - arrastado.getBounds().width - 2*OX)
            return 500 - OX - arrastado.getBounds().width;
        return MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x + OX;
    }
    
    private int limiteY() {
        if(MouseInfo.getPointerInfo().getLocation().y < getLocationOnScreen().y + 31)
            return OY;
        if(MouseInfo.getPointerInfo().getLocation().y > getLocationOnScreen().y + 500 - arrastado.getBounds().height - 2*OY)
            return 500 - OY - arrastado.getBounds().height;
        return MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y + OY;
    }
}
