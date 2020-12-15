package ru.lachesis.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ScannerListener extends Thread  {
    Socket socket ;
    String source;
    ScannerListener(Socket socket, String source)
    {
        this.socket=socket;
        this.source=source;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String text;
            Scanner scanner = new Scanner(System.in);
            do  {
                text = scanner.nextLine();
                out.writeUTF(source +" write: " + text);
                System.out.println("Сообщение отправлено");
//                System.out.println(in.readUTF());
                Server.messages.add(text);
                if (text.equals("stop")) {
                    this.interrupt();
                    in.close();
                    out.close();
                    socket.close();
                    break;

                }
            } while (socket.isConnected());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            System.out.println("Сеанс завершен");
        }
        }

    }
