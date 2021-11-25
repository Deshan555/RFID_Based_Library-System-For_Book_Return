/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

/**
 *
 * @author Jayashanka Deshan
 */
public class Check_Box extends javax.swing.JFrame {

    /**
     * Creates new form Log_In_Fail
     */
    public Check_Box() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 183));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(640, 183));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Animations/1021-rules-outline.gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 160, 120));

        message.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        message.setText("Please Check The Conditions ");
        jPanel1.add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 57));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/checked_30px.png"))); // NOI18N
        jLabel3.setText("GOT IT !");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 100, -1));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 190));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
        close();
    }//GEN-LAST:event_jLabel3MouseClicked

    public void close()
    {
        WindowEvent new_event;
        
        new_event = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new_event);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Check_Box().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel message;
    // End of variables declaration//GEN-END:variables
}
