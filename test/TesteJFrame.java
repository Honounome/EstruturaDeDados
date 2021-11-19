
/**
 * Nesse teste eu descobri que é possível adicionar um label em outro label
 * assim como que é possível conseguir o componente pai por meio do método
 * .getParent, o que facilita muitas coisas
 */

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class TesteJFrame extends javax.swing.JFrame {

    JLabel l_outer;
    JLabel l_inner;

    public TesteJFrame() {
        initComponents();
        l_outer = new JLabel();
        l_inner = new JLabel();

        p_painel.add(l_outer);
        l_outer.setBounds(100, 100, 200, 200);
        l_outer.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        l_outer.add(l_inner);
        l_inner.setBounds(l_inner.getParent().getWidth() / 2,
                l_inner.getParent().getHeight() / 2,
                l_inner.getParent().getWidth() * 2 / 10,
                l_inner.getParent().getHeight() * 3 / 10);
        l_inner.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_painel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        p_painel.setMinimumSize(new java.awt.Dimension(500, 500));
        p_painel.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout p_painelLayout = new javax.swing.GroupLayout(p_painel);
        p_painel.setLayout(p_painelLayout);
        p_painelLayout.setHorizontalGroup(
            p_painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        p_painelLayout.setVerticalGroup(
            p_painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(p_painel);
        p_painel.setBounds(0, 0, 500, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TesteJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesteJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesteJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesteJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TesteJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel p_painel;
    // End of variables declaration//GEN-END:variables
}
