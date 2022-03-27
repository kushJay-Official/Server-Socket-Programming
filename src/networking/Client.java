package networking;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public Client(){
        try {
            socket =new Socket("localhost",10);
            this.dataInputStream= new DataInputStream(socket.getInputStream());
            this.dataOutputStream =new DataOutputStream(socket.getOutputStream());

            sendMessage();

        } catch (UnknownHostException e) {
            System.out.println("Host not found !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Client();

    }

    private void sendMessage() {
        try {
            ClientThread clientThread= new ClientThread(dataInputStream);
            Thread thread=new Thread(clientThread);
            thread.start();
            System.out.println("Connected to Server...");
            BufferedReader br =new BufferedReader(
                    new InputStreamReader(System.in));
            String writeMessage="";
            do {
                System.out.print("Type Message here: => ");
                writeMessage=br.readLine();
                dataOutputStream.writeUTF(writeMessage);
                dataOutputStream.flush();
            }
            while (writeMessage.equalsIgnoreCase("stop"));
        } catch (IOException e) {
            System.out.println("I/O Exception");
        }
    }

}
