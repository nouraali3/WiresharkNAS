package wireshark;

import java.awt.Color;
import javax.swing.JOptionPane;
import jpcap.NetworkInterface;
import Datatypes.PacketOptions;

public class PacketGUI extends javax.swing.JFrame {
    
    int index=1;
    NetworkInterface networkInterface;
    PacketOptions p;
    boolean stop=false;
    
    public PacketGUI(int i,NetworkInterface ni) {
        initComponents();
        networkInterface=ni;
        index=i;
        Integer i1=i;
        interfacetxt.setText("interface "+i1.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        packetstbl = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        protocolstxt = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        packetcontenttxt = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        filtertxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        interfacespnl = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        interfacetxt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        loadbtn = new javax.swing.JButton();
        stopbtn = new javax.swing.JButton();
        capbtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        packetstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Time", "Source", "Destination", "Protocol", "Length", "Info"
            }
        ));
        jScrollPane2.setViewportView(packetstbl);

        protocolstxt.setEditable(false);
        protocolstxt.setColumns(20);
        protocolstxt.setRows(5);
        jScrollPane1.setViewportView(protocolstxt);

        packetcontenttxt.setEditable(false);
        packetcontenttxt.setColumns(20);
        packetcontenttxt.setRows(5);
        jScrollPane3.setViewportView(packetcontenttxt);

        jLabel1.setText("Filter");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtertxt, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        interfacespnl.setBackground(new java.awt.Color(227, 222, 250));
        interfacespnl.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(50, 2, 52)));
        interfacespnl.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                interfacespnlAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        interfacespnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                interfacespnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                interfacespnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                interfacespnlMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Capture Interfaces");

        interfacetxt.setText("show available interfaces");

        javax.swing.GroupLayout interfacespnlLayout = new javax.swing.GroupLayout(interfacespnl);
        interfacespnl.setLayout(interfacespnlLayout);
        interfacespnlLayout.setHorizontalGroup(
            interfacespnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interfacespnlLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(interfacetxt)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        interfacespnlLayout.setVerticalGroup(
            interfacespnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interfacespnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(interfacespnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(interfacetxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("packet info");

        jLabel4.setText("packet contents");

        jLabel5.setText("captured packets");

        loadbtn.setText("load");

        stopbtn.setText("stop");
        stopbtn.setBorderPainted(false);
        stopbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopbtnActionPerformed(evt);
            }
        });

        capbtn.setText("capture");
        capbtn.setBorderPainted(false);
        capbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capbtnActionPerformed(evt);
            }
        });

        savebtn.setText("save");
        savebtn.setBorderPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(capbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopbtn)
                .addGap(54, 54, 54)
                .addComponent(savebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadbtn)
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(interfacespnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(interfacespnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stopbtn)
                        .addComponent(savebtn)
                        .addComponent(loadbtn)
                        .addComponent(capbtn)))
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void capbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capbtnActionPerformed
        System.out.println("Started capturing");
        p=new PacketOptions(networkInterface);
        p.capture();
    }//GEN-LAST:event_capbtnActionPerformed

    private void interfacespnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_interfacespnlMouseEntered
        interfacespnl.setBackground(new Color(255,186,241));
    }//GEN-LAST:event_interfacespnlMouseEntered

    private void interfacespnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_interfacespnlMouseExited
        interfacespnl.setBackground(new Color(227,222,250));
    }//GEN-LAST:event_interfacespnlMouseExited

    private void interfacespnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_interfacespnlMouseClicked
        
        //jOptionPane returns 0=>yes  1=>no -1=>closed box 
        int n = JOptionPane.showConfirmDialog(this,"Are you sure you want to close?","An Inane Question",JOptionPane.YES_NO_OPTION);
        if(n==0)
        {
            new InterfacesGUI().setVisible(true);
            this.setVisible(false);
        }
           
    }//GEN-LAST:event_interfacespnlMouseClicked

    private void interfacespnlAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_interfacespnlAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_interfacespnlAncestorAdded

    private void stopbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopbtnActionPerformed
        p.stop();
    }//GEN-LAST:event_stopbtnActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton capbtn;
    private javax.swing.JTextField filtertxt;
    private javax.swing.JPanel interfacespnl;
    private javax.swing.JLabel interfacetxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loadbtn;
    public static javax.swing.JTextArea packetcontenttxt;
    public static javax.swing.JTable packetstbl;
    public static javax.swing.JTextArea protocolstxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JButton stopbtn;
    // End of variables declaration//GEN-END:variables

    
}
