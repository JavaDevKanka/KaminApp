//package com.example.kamin;
//
//import android.os.AsyncTask;
//
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.SocketException;
//
//class NAsyncTask extends AsyncTask<Void, Void, String> {
//    volatile byte[] data = new byte[1000];
//    int udp_port = 45045;
//    private DatagramSocket socket;
//    public static String inBuf;
//
//    NAsyncTask() {
//        try {
//            this.socket = new DatagramSocket(udp_port);
//        } catch (SocketException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    protected String doInBackground(Void... voids) {
//        DatagramPacket receivingPacket = new DatagramPacket(data, data.length);
//            try {
//                socket.receive(receivingPacket);
////                socket.setSoTimeout(5000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            InetAddress address = receivingPacket.getAddress();
//            receivingPacket = new DatagramPacket(data, data.length, address, receivingPacket.getPort());
//            String recData = new String(receivingPacket.getData()).trim();
//            return recData;
//
//    }
//
//    @Override
//    protected void onPostExecute(String e) {
//        super.onPostExecute(e);
//    }
//
//}