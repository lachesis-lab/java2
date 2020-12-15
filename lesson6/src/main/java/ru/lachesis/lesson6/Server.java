package ru.lachesis.lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    static AtomicInteger clientCounter = new AtomicInteger(0);
    static Collection<String> messages = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(3030);
//            while (true) { // Работаем только с одним клиентом.
                Socket socket = server.accept();
                new SocketListener(socket).start();
                new ScannerListener(socket,"server").start();
//            } //while (socket.isConnected());
        } catch (SocketException e) {
            System.out.println("Соединение прервано");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
