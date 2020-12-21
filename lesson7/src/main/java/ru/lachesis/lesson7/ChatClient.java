package ru.lachesis.lesson7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ChatClient {
    int port;
    String host;
    boolean isAlive=true;

    ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            Socket socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                Thread.currentThread().setName("socketListener");
                try {
                    while (socket.isConnected()) {
                        String messageText = in.readUTF();
                        System.out.println(messageText);
                    }
                } catch (IOException e) {
                    try {
                        in.close();
                        out.close();
                        socket.close();
                        isAlive=false;
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                    System.exit(0);
                }

            }).start();
            Scanner scanner = new Scanner(System.in);
            Thread scannerListener = new Thread(() -> {
                Thread.currentThread().setName("scannerListener");
                while (isAlive) {
                    String messageText = scanner.nextLine();
                    try {
                        out.writeUTF(messageText);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            scannerListener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 3030);
    }
}
