package com.example.kamin;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class Udp_receive extends Thread {
    DatagramSocket socket;
    public String buffer;
    byte[] data = new byte[128];
    Handler handler = new Handler();

    Udp_receive(Handler handler)  {
        try {
            socket = new DatagramSocket(45045);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        this.handler = handler;
    }

    public synchronized String getBuffer() {
        return buffer;
    }

    @Override
    public void run() {
        while (true) {
            Message msg;
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            for (int i = 0; i < 30; i++) {
                packet = new DatagramPacket(data, data.length, address, port);
                String asas = new String(packet.getData(), packet.getOffset(), packet.getLength()).trim();
                msg = new Message();
                msg.obj = asas;
                handler.sendMessage(msg);
            }

        }
    }

}