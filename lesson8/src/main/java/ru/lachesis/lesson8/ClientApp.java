package ru.lachesis.lesson8;

public class ClientApp {
    ChatApiHandler api;
    SignInWindow signInWindow;
    ChatWindow chatWindow;
    Client sender;

    ClientApp() {
        api = new ChatApiHandler("localhost", 3030, this::onAuth, this::onNewMessage);

        signInWindow = new SignInWindow(this::onLogin);
//        chatWindow = new ChatWindow(this::sendMessage, sender);
    }

    synchronized Void onLogin(SignInWindow.ClientLoginResult clientLoginResult) {
        api.auth(clientLoginResult);
        return null;
    }

    synchronized String onAuth(ChatApiHandler.ServerResponse serverResponse) {
        System.out.println("login: " + serverResponse.isSuccess);
        if (serverResponse.isSuccess) {
            sender = serverResponse.client;
            signInWindow.showError(serverResponse.serverError);
            chatWindow = new ChatWindow(this::sendMessage, sender);
            hideSignInWindow();
            showChatWindow();
        } else signInWindow.showError(serverResponse.serverError);
        return serverResponse.serverError;
    }

    synchronized Void sendMessage(Message message) {
        api.sendMessage(message);
        return null;
    }

    public Void onNewMessage(Message message) {
        synchronized (chatWindow) {
            chatWindow.onNewMessage(message);
        }
        return null;
    }

    private void showSignInWindow() {
        signInWindow.setVisible(true);
    }

    private void hideSignInWindow() {
        signInWindow.setVisible(false);
    }

    private void showChatWindow() {
        chatWindow.setVisible(true);
    }

    private void hideChatWindow() {
        chatWindow.setVisible(false);
    }


    public static void main(String[] args) {
        new ClientApp();
    }

}
