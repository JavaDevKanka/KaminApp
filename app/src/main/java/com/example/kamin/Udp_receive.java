package com.example.kamin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class Udp_receive implements Runnable {
    DatagramSocket socket;
    public String buffer;
    byte[] data = new byte[1024];

    Udp_receive()  {
        try {
            socket = new DatagramSocket(45045);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized String getBuffer() {
        return buffer;
    }

    @Override
    public void run() {
        while (true) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(data, data.length, address, port);
            String asas = new String(packet.getData(), 0, packet.getLength()).trim();
            MainActivity.getFromServer = asas;
            System.out.println( new String(packet.getData(), 0, packet.getLength()).trim() + "      -------Получено");
        }
    }

}