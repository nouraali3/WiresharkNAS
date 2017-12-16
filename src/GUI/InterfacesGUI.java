
package GUI;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jnetpcap.*;


public class InterfacesGUI extends javax.swing.JFrame {

//    public NetworkInterface[] NI; //interfaces provided by jpcap lib
    ArrayList<PcapIf> alldevs = new ArrayList<PcapIf>();//interfaces provided by jnetpcap lib
    StringBuilder errbuf = new StringBuilder(); // For any error msgs
    int index=1;
    
    public InterfacesGUI() {
        initComponents();
        DisplayInterfaces();
    }

//   public void DisplayInterfaces()
//    { 
//        DefaultTableModel dtm= (DefaultTableModel)interfacestbl.getModel();
//
//        NI=JpcapCaptor.getDeviceList();
//        for (int i=0;i<NI.length;i++)
//        {
//            Integer interfaceNumber=i;
//            String interfaceDescription= NI[i].description ;
//            String datalinkDescription=NI[i].datalink_description;
//            NetworkInterfaceAddress[] addresses = NI[i].addresses; //returns 3 addresses { ip , subnet , broadcast }
//            String IPAddress=addresses[0].address.toString();
//            dtm.addRow(new Object[]{Integer.toString(interfaceNumber),interfaceDescription,datalinkDescription,IPAddress});
//        }
//    }
    
    public void DisplayInterfaces()
    {
 
        DefaultTableModel dtm = (DefaultTableModel) interfacestbl.getModel();
        
        try 
            {int r = Pcap.findAllDevs(alldevs, errbuf);} 
        catch (Exception e) 
            { JOptionPane.showMessageDialog(this, errbuf); return; }

        if (alldevs.isEmpty()) 
        {
        }

        int i = 0;
        for (PcapIf device : alldevs) 
        {
            Integer interfaceNumber = i;
            String interfaceDescription = device.getDescription();
            String datalinkDescription = device.getName();
            ArrayList<PcapAddr> addresses = (ArrayList<PcapAddr>) device.getAddresses();
            String IPAddress = addresses.get(0).getAddr().toString();
            dtm.addRow(new Object[]{Integer.toString(interfaceNumber), interfaceDescription, datalinkDescription, IPAddress});
            i++;
        }

    }
    
                

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        interfacestbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        okbtn.setText("ok");
        okbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okbtnActionPerformed(evt);
            }
        });

        interfacestbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Device Description", "Datalink Description", "IP"
            }
        ));
        jScrollPane2.setViewportView(interfacestbl);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Interfaces");

        jLabel2.setText("Please choose one of the available interfaces");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(okbtn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okbtn))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void okbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okbtnActionPerformed
        try 
        {
            index=interfacestbl.getSelectedRow();
            this.setVisible(false);
    //            new PacketGUI(index,NI[index]).setVisible(true);
            new PacketGUI(index,alldevs.get(index),errbuf).setVisible(true);
        } 
        catch (ArrayIndexOutOfBoundsException e1) 
        {
            JOptionPane.showMessageDialog(this, "please select an interface");
            this.setVisible(true);
        }

    }//GEN-LAST:event_okbtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable interfacestbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okbtn;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        new InterfacesGUI().setVisible(true);
    }
    
}
