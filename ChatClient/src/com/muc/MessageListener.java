package com.muc;

//Interface for function when a message is received
public interface MessageListener {
    public void onMessage(String fromLogin, String msgBody, String hash);
}
