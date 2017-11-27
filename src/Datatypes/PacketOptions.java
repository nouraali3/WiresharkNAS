
package Datatypes;

import GUI.PacketGUI;
import javax.swing.JOptionPane;
import jpcap.*;
import jpcap.packet.ARPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

class WorkingThread extends Thread 
{
    PacketGUI gui;
    NetworkInterface networkInterface;
    boolean running=true;
    
    public WorkingThread(NetworkInterface ni,PacketGUI pgui)
    {
        networkInterface=ni;
        gui=pgui;
    }

    @Override
    public void run() 
    {
        while(running)
        {
            try 
            {
                JpcapCaptor captor = JpcapCaptor.openDevice(networkInterface, 65535, false, 20);
                PacketReceiver pr = new PacketReceiver() {
                    @Override
                    public void receivePacket(jpcap.packet.Packet packet) {
                        if (packet instanceof TCPPacket) {
                            System.out.println("TCP :");
                        } else if (packet instanceof IPPacket) {
                            System.out.println("IP :");
                        } else if (packet instanceof UDPPacket) {
                            System.out.println("UDP :");
                        } else if (packet instanceof ARPPacket) {
                            System.out.println("ARP :");
                        }
                        System.err.println(packet.toString());
                        printToPacketGUI(packet);
                    }
                };
                captor.loopPacket(1, pr);
            } 
            catch (Exception e)
            {
                System.err.println(e);
            }
        }  
    }
    
    void terminate() 
    {
        running=false;
    }
    
    private void printToPacketGUI(jpcap.packet.Packet p)
    {
        gui.appendProtocolsTxt(p.toString()+"\n");
    }
    

    
  
}


public class PacketOptions{
    NetworkInterface networkInterface;
    PacketGUI gui;
    WorkingThread wt;
 
    
    public PacketOptions(NetworkInterface ni,PacketGUI pgui) {
        this.networkInterface = ni;
        gui=pgui;
    }
    
    
    public void capture()
    {
        wt=new WorkingThread(networkInterface,gui);
        wt.start();
    }
    public void stopCapturing()
    {
        wt.terminate();
    }
   

    
    
}
