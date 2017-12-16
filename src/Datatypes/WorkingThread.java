
package Datatypes;
import GUI.PacketGUI;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import static javafx.scene.input.KeyCode.T;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import javax.swing.JOptionPane;
import org.jnetpcap.JBufferHandler;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;



public class WorkingThread extends Thread {
    PacketGUI gui;
//    NetworkInterface networkInterface;
    PcapIf networkInterface2;
    LinkedList<PcapPacket> capturedPackets=new LinkedList<>();
    MyPacket myPacket=new MyPacket();
//   JpcapCaptor captor ;
    int packetNum=1;
    Packet p;
    boolean running=true;
    StringBuilder errbuf=new StringBuilder();
    
//    public WorkingThread(NetworkInterface ni,PacketGUI pgui)
//    {
//        networkInterface=ni;
//        gui=pgui;
//    }

    public WorkingThread(PcapIf ni,StringBuilder eb,PacketGUI pgui)
    {
        errbuf=eb;
        networkInterface2=ni;
        gui=pgui;
    }
    
//    @Override
//    public void run() 
//    {
//        while(running)
//        {
//            try 
//            {
//                captor= JpcapCaptor.openDevice(networkInterface, 65535, false, 20);
//                PacketReceiver pr = new PacketReceiver() {
//                    @Override
//                    public void receivePacket(jpcap.packet.Packet packet) 
//                    {
//                        printToPacketGUI(packet);
//                        packetNum++;
//                    }
//                };
//                captor.loopPacket(1, pr);
//                captor.close();
//            } 
//            catch (Exception e)
//            {
//                System.err.println(e);
//            }
//           
//        }  
//    }

    @Override
    public void run()
    {
        while (running)
        {
//            int snaplen = 64 * 1024;           // Capture all packets, no trucation
//            int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
//            int timeout = 10 * 1000;           // 10 seconds in millis
//            Pcap pcap =Pcap.openLive(networkInterface2.getName(), snaplen, flags, timeout, errbuf);
//
//            while(pcap==null)
//            {
//                JOptionPane.showMessageDialog(gui, "Error while opening device for capture: "+ errbuf.toString());
//            }
//            
//            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() 
//            {
//                @Override
//                public void nextPacket(PcapPacket packet, String user) 
//                {
//                    capturedPackets.add(packet);
//                    long millis = System.currentTimeMillis() % 100000;
//                    printToPacketGUI(packet,millis);
//                    packetNum++;
//                }
//            };
//            pcap.loop(-1, jpacketHandler, "jNetPcap rocks!");
//            pcap.close();

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
                    capturedPackets.add(packet);
                    long time = System.currentTimeMillis() % 100000;
//                    printToPacketGUI(packet,time);
                    myPacket.printToPacketGUI(gui,packet, time);
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
    
    public LinkedList<PcapPacket> getCapturedList()
    {
        return capturedPackets;
    }
    
    public void terminate() 
    {
        running=false;
    }
    
    public void printToPacketGUI( PcapPacket p, long t)
    {
        Ethernet ethernet=new Ethernet();
        Arp arp=new Arp();
        Ip4 ip =new Ip4();
        Tcp tcp=new Tcp();
        Udp udp= new Udp();
        Http http=new Http();
        String num=(Integer.toString(packetNum));
        String len=new String();
        String info=new String();
        String src=new String(),dest=new String(),protocol=new String() ; 
        byte[] tempByte= new byte[4]; StringBuilder sb=new StringBuilder();
        JBuffer payloadBuffer = new JBuffer(4);

        String time=Long.toString(t);
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
        gui.insertInPacketTable(num,time,src,dest,protocol,len,info,payload);
        packetNum++;
    }
    
//    private void printToPacketGUI(jpcap.packet.Packet p)
//    {
//        
//        String num=(Integer.toString(packetNum));
//        String len=Integer.toString(p.len);
//        String time=(Long.toString(p.sec));
//        String src=new String(),dest=new String(),protocol=new String() ,  data1=new String(); int destPort=0,srcPort=0; 
//        byte[] tempByte; StringBuilder data=new StringBuilder();
//        
//        if (p instanceof TCPPacket)
//        {
//           System.out.println(packetNum+" "+p.toString());                        
//           TCPPacket tcpPacket=(TCPPacket) p;
//           protocol="TCP";
//           src=tcpPacket.src_ip.toString();
//           dest=tcpPacket.dst_ip.toString();
//           tempByte=p.data ; 
//            for (byte c : tempByte) {
//               data.append(String.format("%02X ", c));
//            }
//          srcPort= tcpPacket.src_port;
//          destPort= tcpPacket.src_port;
//        }
//        
//        else if (p instanceof IPPacket )
//        {
//            System.out.println(packetNum+" "+p.toString()); 
//            IPPacket ipPacket=(IPPacket) p;
//            protocol="IP";
//            src=ipPacket.src_ip.toString();
//            dest=ipPacket.dst_ip.toString();
//            tempByte=p.data ;  
//            for (byte c : tempByte) {
//               data.append(String.format("%02X ", c));
//            }
//        } 
//        
//        else if (p instanceof UDPPacket )
//        {
//            System.out.println(packetNum+" "+p.toString()); 
//            UDPPacket udpPacket=(UDPPacket) p;
//            protocol="UDP";
//            src=udpPacket.src_ip.toString();
//            dest=udpPacket.dst_ip.toString();
//            tempByte=p.data ;  
//            for (byte c : tempByte) {
//               data.append(String.format("%02X ", c));
//            }
//        } 
//        else if (p instanceof ARPPacket )
//        {
//            System.out.println(packetNum+" "+p.toString()); 
//            ARPPacket udpPacket=(ARPPacket) p;
//            protocol="ARP";
//             
//            byte[] bytes; StringBuilder sb=new StringBuilder();
//            bytes=udpPacket.sender_hardaddr;
//            for (byte c : bytes) {
//               sb.append(String.format("%02X ", c));
//            }
//            src=sb.toString();
//            
//           
//            bytes=udpPacket.target_hardaddr;
//            for (byte c : bytes) {
//               sb.append(String.format("%02X ", c));
//            }
//            dest=sb.toString();
//            
//            tempByte=p.data ;  
//            for (byte c : tempByte) {
//               data.append(String.format("%02X ", c));
//            }
//        } 
//        
//        else if (p instanceof ICMPPacket) 
//        {
//            System.out.println(packetNum+" "+p.toString()); 
//            ICMPPacket icmpPacket=(ICMPPacket) p;
//            protocol="ICMP";
//            InetAddress sender,target;
//            
//            src=icmpPacket.src_ip.toString();            
//            dest=icmpPacket.dst_ip.toString();
//            
//            tempByte=p.data ; 
//            for (byte c : tempByte) {
//               data.append(String.format("%02X ", c));
//            }
//        }
//
//        
//        String infoString=p.toString();
//        data1=data.toString();
//        MyPacket myPacket=new MyPacket(num,time,src,dest,protocol,len,infoString,data1);
//        //gui.insertInPacketTable(myPacket);
//        gui.insertInPacketTable(num,time,src,dest,protocol,len,infoString,data1);
//    }
    

}
