package ru.lachesis.lesson8;

import javax.swing.*;
import java.util.function.Function;


public class SignInWindow extends JFrame {

    private final JLabel error;

/*

    public void showError(ChatApiHandler.ServerError serverError) {
        error.setText(serverError.serverError);

    }
*/

    class ClientLoginResult {
        String login;
        String password;

        public ClientLoginResult(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

    SignInWindow(Function<ClientLoginResult, Void> onLogin) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 500, 300, 120);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //add(new JLabel("Пожалуйста, авторизуйтесь"));
        JTextField loginField = new JTextField();
        loginField.setName("Логин");
        JTextField passwordField = new JTextField();
        passwordField.setName("Пароль");
        JButton signInButton = new JButton("Войти");
        signInButton.addActionListener(e -> {
            onLogin.apply(new ClientLoginResult(loginField.getText(), passwordField.getText()));
        });
        loginField.addActionListener(e -> {
            onLogin.apply(new ClientLoginResult(loginField.getText(), passwordField.getText()));
        });
        passwordField.addActionListener(e -> {
            onLogin.apply(new ClientLoginResult(loginField.getText(), passwordField.getText()));
        });
        error = new JLabel();
        error.setText("Пожалуйста, авторизуйтесь");
        add(error);
        add(loginField);
        add(passwordField);
        add(signInButton);
        setVisible(true);


    }

    public void showError(String errorText) {
        error.setText(errorText);
    }

/*
    public static void main(String[] args) {
        new SignInWindow();
    }*/
}
