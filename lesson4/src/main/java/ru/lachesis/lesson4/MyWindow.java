package ru.lachesis.lesson4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyWindow extends JFrame {
    private final ArrayList<String> messages = new ArrayList<>();
    private int sortOrder = 1;

    public MyWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        setTitle("My chat");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea targetText = new JTextArea();
        targetText.setEditable(false);
        targetText.setLineWrap(true);
        targetText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(targetText);
        add(scrollPane, BorderLayout.CENTER);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton refreshButton = new JButton("\u27F3");
        refreshButton.setToolTipText("Refresh");
        refreshButton.addActionListener(e -> refresh(targetText, 1));
        JButton sortButton = new JButton("\u21C5");
        sortButton.setToolTipText("Sort");
        JButton clearButton = new JButton("\u239A");
        sortButton.addActionListener(e -> {
            sortOrder *= -1;
            refresh(targetText, sortOrder);
        });
        clearButton.setToolTipText("Clear");
        clearButton.addActionListener(e -> {
            targetText.setText("");
            messages.clear();
        });
        toolBar.setBorderPainted(true);
        toolBar.add(refreshButton);
        toolBar.add(sortButton);
        toolBar.add(clearButton);
        add(toolBar, BorderLayout.PAGE_START);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.PAGE_END);
        JTextField sourceText = new JTextField();
        sourceText.setBackground(Color.lightGray);
        bottomPanel.add(sourceText, BorderLayout.CENTER);

        JButton button = new JButton("Send");
        bottomPanel.add(button, BorderLayout.EAST);

        button.addActionListener(e -> send(sourceText, targetText));

        sourceText.addActionListener(e -> send(sourceText, targetText));
        setVisible(true);

    }

    private void refresh(JTextArea targetText, int sortOrder) {
        targetText.setText("");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            text.append(messages.get(sortOrder == -1 ? messages.size() - i - 1 : i)).append("\n");
        }
        targetText.setText(text.toString());
    }

    private void send(JTextField sourceText, JTextArea targetText) {
        messages.add(sourceText.getText());
        targetText.setText(targetText.getText() + sourceText.getText() + "\n");
        sourceText.setText("");

    }
}
