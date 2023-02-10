package com.example.kamin;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp_client extends Thread
{
    public static String inBuf;

    byte [] data = new byte[1024];
    int udp_port=45045;
    InetAddress adder;
    DatagramSocket ds;

    public Udp_client() {
        try
        {
            ds = new DatagramSocket();
            adder = InetAddress.getByName(MainActivity.ipPub);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run()  {
        while (true) {
            byte temp = MainActivity.btndn;
            String s = "" + MainActivity.direction;
            data = s.getBytes();
            if (temp == 100) {
                try {
                    DatagramPacket pack = new DatagramPacket(data, data.length, adder, udp_port);
                    ds.send(pack);
                    String sentence = new String(pack.getData(), 0, pack.getLength());
                    System.out.println(sentence + "   " + "Отправлено");
                    Thread.sleep(221);
                } catch (Exception e) {
                }
            }
        }
    }


}