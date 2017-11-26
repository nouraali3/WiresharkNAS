
package Datatypes;

import javax.swing.JOptionPane;
import jpcap.*;
import jpcap.packet.ARPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

class WorkingThread extends Thread 
{
    NetworkInterface networkInterface;
    public WorkingThread(NetworkInterface ni) {
        networkInterface=ni;
    }

    @Override
    public void run() {
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

                }
            };
            captor.loopPacket(-1, pr);
        } 
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
        
    
}


public class PacketOptions{
    NetworkInterface networkInterface;
    WorkingThread wt;
    public PacketOptions(NetworkInterface ni) {
        this.networkInterface = ni;
    }
    
    
    public void capture()
    {
        wt=new WorkingThread(networkInterface);
        wt.start();
    }
    public void stop()
    {
        int n = JOptionPane.showConfirmDialog(null,"Are you sure you want to close?","An Inane Question",JOptionPane.YES_NO_OPTION);
        if(n==0)
        {
            //stop capturing
        }
    }
   

    
    
}
