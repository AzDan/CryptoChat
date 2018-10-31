package com.muc;

//MAIN PROGRAM TO START THE SERVER
public class ServerMain {
    public static void main(String[] args) {
        //Specifying the server port
        int port = 8818;
        //Start the server on the given port
        Server server = new Server(port);
        server.start();
    }
}
