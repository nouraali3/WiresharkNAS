
package Datatypes;
import GUI.PacketGUI;
import java.io.File;
import javax.swing.JOptionPane;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;


public class WorkingThread extends Thread {
    PacketGUI gui;
    PcapIf networkInterface2;
    MyPacket myPacket=new MyPacket();
    int packetNum=1;
    boolean running=true;
    StringBuilder errbuf=new StringBuilder();
    

    public WorkingThread(PcapIf ni,StringBuilder eb,PacketGUI pgui)
    {
        errbuf=eb;
        networkInterface2=ni;
        gui=pgui;
    }

    @Override
    public void run()
    {
        while (running)
        {
            int snaplen = 64 * 1024;           // Capture all packets, no trucation
            int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
            int timeout = 10 * 1000;           // 10 seconds in millis
            Pcap pcap =Pcap.openLive(networkInterface2.getName(), snaplen, flags, timeout, errbuf);
            
            StringBuilder errbuf = new StringBuilder();
            String fname = "tests/test-afs.pcap";
            String ofile = "captured-packets.cap";
            PcapDumper dumper = pcap.dumpOpen(ofile); // output file  

            while(pcap==null)
            {
                JOptionPane.showMessageDialog(gui, "Error while opening device for capture: "+ errbuf.toString());
            }
            
            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() 
            {
                @Override
                public void nextPacket(PcapPacket packet, String user) 
                {
                    myPacket.printToPacketGUI(gui,packet);
                }
                
                public void nextPacket(PcapHeader header, JBuffer buffer, PcapDumper dumper)
                {
                    dumper.dump(header, buffer);
                }
            };
            pcap.loop(10, jpacketHandler, "jNetPcap rocks!");
  
            File file = new File(ofile);
            System.out.printf("%s file has %d bytes in it!\n", ofile, file.length());

            dumper.close(); // Won't be able to delete without explicit close  
            pcap.close();
            
            
            
        }
        
        
    }
    
 
    
    public void terminate() 
    {
        running=false;
    }
    
}
