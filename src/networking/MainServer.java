package networking;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {

    ServerSocket serverSocket;
    Socket socket;

    ArrayList listOfClients= new ArrayList();

    public MainServer(){
        try {
            serverSocket = new ServerSocket(10);
            System.out.println("Server started....");
            while(true){
                System.out.println("Connecting....");
                socket=serverSocket.accept();
                listOfClients.add(socket);

                Runnable runnable=new MainServerThread(socket,listOfClients);
                Thread thread= new Thread(runnable);

                thread.start();
                System.out.println("Connected..");
            }
        } catch (IOException e) {
            System.out.println("Server Offline...");
        }
    }

    public static void main(String[] args) {
        new MainServer();
    }
}
