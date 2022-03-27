package networking;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientThread implements Runnable {
    DataInputStream dataInputStream;
    public ClientThread(DataInputStream dataInputStream) {
        this.dataInputStream=dataInputStream;
    }

    @Override
    public void run() {
        String readMessage="";
        do {
            try {
                readMessage=dataInputStream.readUTF();
                System.out.println("Message : "+readMessage);
            } catch (IOException e) {
                System.out.println("Error in receiving msg");
            }
        }while (!readMessage.equalsIgnoreCase("stop"));
    }
}
