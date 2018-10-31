package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Crypt_Algo.*;

public class MessagePane extends JPanel implements MessageListener {

    private final ChatClient client;
    private AES a = new AES();
    private MD5 m = new MD5();
    private DigitalSignature ds = new DigitalSignature();
    private String hash1 = "";
    private String hash2 = "";
    private final String login;
    private final String secretKey = "CryptoChat";
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> messageList = new JList<>(listModel);
    private JTextField inputField = new JTextField();

    public MessagePane(ChatClient client, String login) {
        this.client = client;
        this.login = login;

        client.addMessageListener(this);

        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String t = inputField.getText();
                    hash1 = m.getMd5(t);
                    String text = a.encrypt(t, secretKey);
                    ds.signDigitally(text);
                    client.msg(login, text, hash1);
                    listModel.addElement("You: " + t);
                    inputField.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onMessage(String fromLogin, String msgBody, String hash) {
        if (login.equalsIgnoreCase(fromLogin)) {
            boolean ver = ds.verifySignature(msgBody);
            if(ver) {
                msgBody = a.decrypt(msgBody, secretKey);
                hash2 = m.getMd5(msgBody);
                if (hash2.equals(hash)) {
                    String line = fromLogin + ": " + msgBody;
                    listModel.addElement(line);
                } else {
                    JOptionPane.showMessageDialog(null, "HASH NOT MATCHING !");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"CANNOT VERIFY !");
            }
        }
    }
}
