package ru.lachesis.lesson7;

import java.util.Date;

public class Message {
    Client sender;
    Client receiver;
    String text;
    String dateTime;

    public Message(Client sender, String text) {
        this.sender = sender;
        this.text = text;
        Date date = new Date();
        dateTime = date.toString();
    }
    public Message(Client sender,Client receiver, String text) {
        this.sender = sender;
        this.text = text;
        this.receiver=receiver;
        Date date = new Date();
        dateTime = date.toString();
    }
/*

    public Client getReceiver() {
        return receiver;
    }
*/
}
