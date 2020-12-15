package ru.lachesis.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketListener extends Thread  {
    Socket socket ;
//    String source;
    SocketListener(Socket socket)//, String source)
    {
        this.socket=socket;
        //this.source=source;
    }

    @Override
    public void run() {
        try {
            int clientN = Server.clientCounter.getAndIncrement();
            System.out.println("Подключился клиент № " + clientN);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            do  {
                String text = in.readUTF();
                System.out.println(text);
 //               out.writeUTF(source+" " + clientN + " : " + text);
                Server.messages.add(text);
            } while (socket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Server.clientCounter.decrementAndGet();
        }

    }
}
