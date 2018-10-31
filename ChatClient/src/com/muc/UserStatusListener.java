package com.muc;

//Interface for changing the status of the user
public interface UserStatusListener {
    public void online(String login);
    public void offline(String login);
}
