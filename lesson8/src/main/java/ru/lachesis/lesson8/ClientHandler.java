package ru.lachesis.lesson8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {
    Socket socket;
    Server server;
    Client client;
    AuthService authService;
    DataInputStream in;
    DataOutputStream out;
    boolean isAlive = true;

    final int tryCount = 3;


    public ClientHandler(Socket socket, Server server, AuthService authService) {
        this.authService = authService;
        this.socket = socket;
        this.server = server;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            if ((client = auth()) != null) {
                out.writeUTF("Вы вошли в чат!");
                server.onNewClient(this);
                messageListener(in);
            } else {
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            terminate();
        }
    }

    private void messageListener(DataInputStream in) throws IOException {
        while (true) {
            String text = in.readUTF();
            String[] messageData = text.split("\\s");
            if ("/exit".equals(messageData[0])) {
                server.onClientDisconnected(this);

                throw new IOException();
//                terminate();
            } else if ("/w".equals(messageData[0])) {
                Client receiver = null;
                String receiverName = messageData[1];
                for (int i = 0; i < server.clients.size(); i++) {
                    if (server.clients.get(i).client.name.equals(receiverName)) {
                        receiver = server.clients.get(i).client;
                        Message message = new Message(this.client, receiver, text.substring(4 + messageData[1].length()));
                        server.onNewMessage(message);
                        break;
                    }
                }
                if (receiver == null) {
                    System.out.println("пользователь " + receiverName + " не в сети");
                    sendMessage(new Message(new Client("Admin", "Admin", ""), this.client, "пользователь " + receiverName + " не в сети"));

                }

            } else {
                Message message = new Message(this.client, null, text);
                server.onNewMessage(message);
            }

        }
    }

    private void terminate() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User disconnected");

    }

    synchronized void sendMessage(Message message) {
        try {
//            System.out.println(message.toString());
            out.writeUTF("/nm " + message.toString());//message.sender.name + "(" + message.dateTime + "): " + message.text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Client auth() throws IOException {
        String login = "", psw = "";
        int i = 0;
        do {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (client == null)
                        terminate();
                }
            }, 120_000);
            //          out.writeUTF("Введите логин и пароль в формате: /auth login password");
            String[] authData = in.readUTF().split("\\s");
            i++;
            if ("/auth".equals(authData[0]) && authData.length == 3) {
                login = authData[1];
                psw = authData[2];
                client = authService.auth(login, psw);
                if (client != null) {
                    out.writeUTF("/auth ok " + client.name + " " + client.login + " " + client.password);
                    System.out.println("Login success");
                    //break;
                    return client;
                } else {
                    out.writeUTF("Неправильный логин или пароль");
                }
            } else {
                out.writeUTF("Ошибка авторизации 0");
            }
            if (i == tryCount) {
                out.writeUTF("Исчерпанно количество попыток авторизации");
                isAlive = false;
//                terminate();
                //  return null;
            }

        } while (isAlive);

        if (client == null) {
//            out.writeUTF("Исчерпанно количество попыток авторизации");
//            terminate();
            return null;
        } else return client;


    }

}
