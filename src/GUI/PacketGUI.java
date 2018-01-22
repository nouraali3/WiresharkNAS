package GUI;

import java.awt.Color;
import javax.swing.JOptionPane;
import Datatypes.WorkingThread;
import java.io.File;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


public class PacketGUI extends javax.swing.JFrame {
    
    int index=1;
    PcapIf networkInterface2;
    WorkingThread wt;
    File capturedPacketsFile;
    boolean isCapturing;
    boolean isSaved;
    int number=1;
    
    public PacketGUI(int i,PcapIf ni,StringBuilder errbuf) {
        initComponents();
        networkInterface2=ni;
        wt=new WorkingThread(networkInterface2,errbuf,this);
        index=i;
        Integer i1=i;
        interfacetxt.setText("interface "+i1.toString());
        isCapturing=false;
        isSaved=false;
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
        filterbox = new javax.swing.JComboBox<>();
        filterbtn = new javax.swing.JButton();
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
        showbtn = new javax.swing.JButton();
        exitbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        packetstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Time", "Source", "Destination", "Protocol", "Length", "info", "data"
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

        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Filter");

        filterbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ip", "protocol", "length" }));
        filterbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterboxActionPerformed(evt);
            }
        });

        filterbtn.setText("ok");
        filterbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filterbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtertxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filterbtn)
                .addGap(91, 91, 91))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(filterbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterbtn))
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("packet info");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("packet contents");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setText("captured packets");

        loadbtn.setText("load");
        loadbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadbtnActionPerformed(evt);
            }
        });

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
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        showbtn.setText("show packet details");
        showbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showbtnActionPerformed(evt);
            }
        });

        exitbtn.setText("exit");
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showbtn)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(interfacespnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(capbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopbtn)
                        .addGap(54, 54, 54)
                        .addComponent(savebtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitbtn)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stopbtn)
                            .addComponent(savebtn)
                            .addComponent(loadbtn)
                            .addComponent(capbtn)
                            .addComponent(exitbtn)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(interfacespnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showbtn)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void capbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capbtnActionPerformed
        
        System.out.println("Started capturing");
        wt.start();
        isCapturing=true;
    }//GEN-LAST:event_capbtnActionPerformed

    public void appendProtocolsTxt(String s)
    {
        protocolstxt.append(s);
    }
     

     
    
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
        int n = JOptionPane.showConfirmDialog(this,"Are you sure you want to stop?","An Inane Question",JOptionPane.YES_NO_OPTION);
        if(n==0)
            {wt.terminate(); isCapturing=false; }
    }//GEN-LAST:event_stopbtnActionPerformed

    private void showbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showbtnActionPerformed
        packetcontenttxt.setText("");
        DefaultTableModel dtm=(DefaultTableModel) packetstbl.getModel();
        try 
        {
            int indexInTable=packetstbl.getSelectedRow();
            String packetInfo=dtm.getValueAt(indexInTable, 6).toString();
            protocolstxt.setText(packetInfo);
            String data=dtm.getValueAt(indexInTable, 7).toString();
            packetcontenttxt.setText(data); 
        } 
        catch (ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(this, "please select a packet first");
        }
 
    }//GEN-LAST:event_showbtnActionPerformed

    private void filterbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterbtnActionPerformed
        
        DefaultTableModel dtm=(DefaultTableModel) packetstbl.getModel();
        String filterChoice=(String)filterbox.getSelectedItem();
        String filterValue=filtertxt.getText();
        filtertxt.setText("");
        if (!filterValue.equals(""))
            {
                int i=0;
                if(filterChoice.equals("ip"))
                {
                    while(i < dtm.getRowCount()) 
                    {
                        String src=(String)dtm.getValueAt(i, 2);
                        String destination=(String)dtm.getValueAt(i, 3);
                        if (src.equalsIgnoreCase(filterValue ) || destination.equalsIgnoreCase(filterValue ) )
                        {
                            i++;
                            continue;
                        }
                        dtm.removeRow(i);
                    }
                }
                
                else if (filterChoice.equals("protocol"))
                {
                    while(i < dtm.getRowCount()) 
                    {
                        String protocol=(String)dtm.getValueAt(i, 4);
                        if (protocol.equalsIgnoreCase(filterValue )) //if row holds the value we want, just keep that row and go to the next one
                        {
                            i++;
                            continue;
                        }
                        else
                            dtm.removeRow(i);
                    }
                }
                
                else if (filterChoice.equals("length"))
                {
                    while(i < dtm.getRowCount()) 
                    {
                        String currentLength=(String)dtm.getValueAt(i, 5);
                        if (currentLength.equalsIgnoreCase(filterValue ))
                        {
                            i++;
                            continue;
                        }
                        dtm.removeRow(i);
                    }
                }    
            }
            else
                JOptionPane.showMessageDialog(this,"please write a value");
        
    }//GEN-LAST:event_filterbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        if(isCapturing==true)
        {
            isSaved=true;
            int n = JOptionPane.showConfirmDialog(this,"stop capturing ?","An Inane Question",JOptionPane.YES_NO_OPTION);
            if(n==0)
            {wt.terminate(); }
        }
        
        
        
    }//GEN-LAST:event_savebtnActionPerformed

    private void loadbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadbtnActionPerformed
       final JFileChooser fc= new JFileChooser();
       int returnValue=fc.showOpenDialog(this);
       String  fileName= new String();
       
       if(returnValue== JFileChooser.APPROVE_OPTION)
           fileName=fc.getSelectedFile().getName();
        System.out.println(fileName);

        StringBuilder errbuf = new StringBuilder();
        Pcap pcap = Pcap.openOffline(fileName, errbuf);
        PcapPacketHandler<String> handler = new PcapPacketHandler<String>() 
        {
            @Override
            public void nextPacket(PcapPacket packet, String user) 
                { printToPacketGUI(packet);}
        };
        pcap.loop(-1, handler, "jNetPcap rocks!");
        pcap.close();
    }//GEN-LAST:event_loadbtnActionPerformed

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
        int choice=0;
        if(isCapturing==true)  //if user still capturing, 1-ask if he wants to stop capturing or not. 2-if yes,ask if he wants to save
        {
            choice = JOptionPane.showConfirmDialog(this,"stop capturing ?","An Inane Question",JOptionPane.YES_NO_OPTION);        
            //jOptionPane returns 0=>yes  1=>no -1=>closed box
            if (choice==0)
                wt.terminate();
        }
        
        if(choice==0 )
        {
            if(!isSaved)
            {
                int choice2 = JOptionPane.showConfirmDialog(this,"Do you want to save before exit?","An Inane Question",JOptionPane.YES_NO_OPTION);        
                //jOptionPane returns 0=>yes  1=>no -1=>closed box
                if (choice2==0)
                {
                    this.setVisible(false);
                    System.exit(0);
                }
                if(choice2==1)
                {
                    this.setVisible(false);
                    capturedPacketsFile.delete();
                    //delete captured-packets file 
                    System.exit(0);
                }
            }
        }    
    }//GEN-LAST:event_exitbtnActionPerformed

    private void filterboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterboxActionPerformed
    private void setCapturedPacketsFile()
    {
        capturedPacketsFile=wt.getOutputFile();
    }
    public void printToPacketGUI(PcapPacket p)
    {
        Date date = new Date(p.getCaptureHeader().timestampInMillis());
        Ethernet ethernet=new Ethernet();
        Arp arp=new Arp();
        Ip4 ip =new Ip4();
        Tcp tcp=new Tcp();
        Udp udp= new Udp();
        Http http=new Http();
        String num=(Integer.toString(number));
        String len=new String();
        String info=new String();
        String src=new String(),dest=new String(),protocol=new String() ; 
        byte[] tempByte= new byte[4]; StringBuilder sb=new StringBuilder();
        JBuffer payloadBuffer = new JBuffer(4);
        
        String time=Long.toString(date.getTime());
        if(p.hasHeader(ethernet))
        {
            protocol="ethernet";
            info += p.getHeader(ethernet).toString();
            
            tempByte=ethernet.source();
            for (byte c : tempByte) {
               sb.append(String.format("%02X ", c));
            }
            src=sb.toString();

            tempByte=ethernet.destination();
            for (byte c : tempByte) {
               sb.append(String.format("%02X ", c));
            }
            dest=sb.toString();
            
            payloadBuffer = p.getHeader(new Payload());
            System.out.println(payloadBuffer);
        }
        info+="\n";
        
        if(p.hasHeader(arp))
        {
            protocol="ARP";
            info += p.getHeader(arp).toString();
            
            tempByte = arp.sha();
            for (byte c : tempByte) {
               sb.append(String.format("%02X ", c));
            }
            src=sb.toString();
            
            tempByte = arp.tha();
            for (byte c : tempByte) {
               sb.append(String.format("%02X ", c));
            }
            dest=sb.toString();
            
            payloadBuffer = p.getHeader(new Payload());
            System.out.println(payloadBuffer);
        }
        info+="\n"; 
        
        if(p.hasHeader(ip))
        {  
            protocol="IP";
            info +=p.getHeader(ip).toString();
            
            tempByte=ip.source();
            src= org.jnetpcap.packet.format.FormatUtils.ip(tempByte);

            tempByte=ip.destination();
            dest = org.jnetpcap.packet.format.FormatUtils.ip(tempByte);

            payloadBuffer = p.getHeader(new Payload());
            System.out.println(payloadBuffer);
        }
        info+="\n";
        
        if(p.hasHeader(tcp))
        {
            protocol="TCP";
            info+=p.getHeader(tcp);
        }
        info+="\n";
        
        if(p.hasHeader(udp))
        {
            protocol="UDP";
            info+=p.getHeader(udp);
        }
        info+="\n";
        
        if(p.hasHeader(http))
        {
            protocol ="HTTP";
            info+=p.getHeader(http).toString();
        }
        String payload =new String();
        if((p.getHeader(new Payload())!=null))
        {
            payload=p.getHeader( new Payload()).toString();
        }
        
        Integer packetSize=p.size();
        len=Integer.toString(packetSize);
        insertInPacketTable(num,time,src,dest,protocol,len,info,payload);
        number++;
    }
    
    public void insertInPacketTable(String num, String time, String src, String dest, String protocol, String len, String info, String data1) 
    {
       DefaultTableModel dtm=(DefaultTableModel) packetstbl.getModel();
       dtm.addRow(new Object[]{num,time,src,dest,protocol,len,info,data1});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton capbtn;
    private javax.swing.JButton exitbtn;
    private javax.swing.JComboBox<String> filterbox;
    private javax.swing.JButton filterbtn;
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
    private javax.swing.JTextArea packetcontenttxt;
    public static javax.swing.JTable packetstbl;
    public static javax.swing.JTextArea protocolstxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JButton showbtn;
    private javax.swing.JButton stopbtn;
    // End of variables declaration//GEN-END:variables

    

    
    
    
}
