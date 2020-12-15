package ru.lachesis.lesson6;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",3030);
//            while (true){
                new ScannerListener(socket,"client").start();
                new SocketListener(socket).start();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
