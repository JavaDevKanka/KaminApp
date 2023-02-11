package com.example.kamin;

import android.os.Build;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Udp_client extends Thread {
    byte [] data = new byte[128];
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
            String s = MainActivity.direction;
            if (s != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    data = s.getBytes(StandardCharsets.UTF_8);
                }
            }
            if (temp != 100) {
                try {

                    for (int i = 0; i < 30; i++) {
                        DatagramPacket pack = new DatagramPacket(data, data.length, adder, udp_port);
                        ds.send(pack);
                    }
                } catch (Exception e) {
                }
            }
        }
    }


}