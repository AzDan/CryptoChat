# CryptoChat
Project Members

Shryesh Khandelwal - 20164126



ABOUT
CryptoChat is a multi user/server architecture chat application that has the following features:
1. Encrypted chats (End-To-End encryption with the AES algorithm)
2. Hashing (Ensuring integrity of the messages sent to other users)
3. Digital Signature (For authentication of the data sent among users)

The project is implemented using Java on the IntelliJ IDE with JDK 1.8.0_u191.

The project has 2 modules:
1. ChatServer
2. ChatClient

## ChatServer
The ChatServer module has 3 classes:
1. Server
2. ServerMain
3. ServerWorker

### Server
The program that is responsible for establishing new connections. When a new connection is made, it spawns a ServerWorker instance 
to handle the new client while the main program listens for any new connections on the port.
This class is responsible for establishing the socket before handing work to the ServerWorker instance.

### ServerMain
This program is the program that contains the main function and is responsible for starting the server.

### ServerWorker
This program is responsible for handling the clients connected to the server. It handles everything from login to the sending of the 
messages between clients. It extends the thread class so each worker is a new thread to handle a separate client.
This enables us to handle multiple clients concurrently.

## ChatClient
This module has 2 packages
1. com.muc
2. Crypt_Algo

The com.muc package contains all the clients functionalities.
The Crypt_Algo package contains all the algorithms used for encryption, hashing and digitally signing the messages.

### com.muc
It contains the following classes:
1. ChatClient
2. LoginWindow
3. MessageListener(Interface)
4. MessagePane
5. UserListPane
6. UserStatusListener(Interface)

#### ChatClient
This class handles all the clients functionalities such as handling login, message sending, reading new messages etc.
#### LoginWindow
This class is a frame that allows a user to login to message the other users.
This is the program the user executes to start his application.
#### MessageListener(Interface)
This is a simple interface for the function which can be overriden to perform actions when a new message is received by a client.
#### MessagePane
It contains the main code to perform encryption, hashing and digitally signing the data.
This window is the one from which the clients interact with each other.
#### UserListPane
This is the frame that opens after a user logins.
It shows a list of users which the client can select to chat.
#### UserStatusListener
This is a simple interface which handles online and offline status of users. The functions can be overriden.

### Crypt_Algo
Contains the three main algorithms used.
1. AES(Encryption)
2. MD5(Hashing)
3. DigitalSignature(DSA)

#### AES
Performs the encryption of the data based on the key.
Contains both the methods to encrypt and decrypt the data.

#### MD5
The Message Digest-5 algorithm for hashing

#### DigitalSignature
Implements the Digital Signature Algorithm commonly used to digitally sign data.
Contains both the methods to sign and verify a signature.

DISADVANTAGES
1. Both users need to be online and chat windows open to communicate since the server has no buffer to store the messages.
2. Lack of a good GUI.
3. Lack of many other functionalities common in other chat applications.

PRO
1. Security of the messages.
