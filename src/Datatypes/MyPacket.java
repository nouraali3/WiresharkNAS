
package Datatypes;

import GUI.PacketGUI;
import java.util.Date;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public class MyPacket{
    
    int number=1;
    String time;
    String source;
    String destination;
    String protocol;
    String length;
    String info;
    String data;
    PacketGUI gui;
    

    public MyPacket( String time, String source, String destination, String protocol, String length, String info,String data) {
       
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.length = length;
        this.info=info;
        this.data = data;
    }
    

    public String getDestination() {
        return destination;
    }

    public String getData() {
        return data;
    }

    public String getLength() {
        return length;
    }

  

    public String getProtocol() {
        return protocol;
    }

    public String getSource() {
        return source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    
    public void setData(String data) {
        this.data = data;
    }

    public void setLength(String length) {
        this.length = length;
    }

  

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
    
    public MyPacket() {
        
    }
    
    public void printToPacketGUI(PacketGUI pgui, PcapPacket p)
    {
        Date date = new Date(p.getCaptureHeader().timestampInMillis());
        gui=pgui;
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
        gui.insertInPacketTable(num,time,src,dest,protocol,len,info,payload);
        number++;
    }

}
