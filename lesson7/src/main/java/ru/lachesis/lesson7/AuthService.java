package ru.lachesis.lesson7;

import java.util.ArrayList;

public class AuthService {
    ArrayList<Client> clients = new ArrayList<>();
    AuthService(){
        clients.add(new Client("Vasily","Vasya","1111"));
        clients.add(new Client("Petr","Petya","1112"));
        clients.add(new Client("Nina","Nina","1113"));
    }
    Client auth(String login, String psw){
        if (login==null) return null;
        for (Client client:clients){
            if (client.login.equals(login)&& client.password.equals(psw)){
                return client;
            }
        }
        return null;
    }
}
