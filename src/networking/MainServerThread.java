package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;

public class MainServerThread implements Runnable {
    Socket socket;
    ArrayList listOfClients;
    public MainServerThread(Socket socket, ArrayList listOfClients) {
        this.socket=socket;
        this.listOfClients=listOfClients;
    }

    @Override
    public void run() {
        String readMessage="";
        try {
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            do {
                readMessage= dataInputStream.readUTF();
                System.out.println("Msg :=> "+readMessage);
                if (!readMessage.equalsIgnoreCase("stop")) showInGroup(readMessage);
                else {
                    DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
                    //BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
                    dataOutputStream.writeUTF("Message Received ! ");
                    dataOutputStream.flush();
                }
            }
            while (!readMessage.equalsIgnoreCase("stop"));
        } catch (IOException e) {
            System.out.println("Something went wrong..");
            }
        }


    private void showInGroup(String readMessage) {
        Iterator iterator= listOfClients.iterator();

        while (iterator.hasNext()){
            try {
                Socket socket=(Socket) iterator.next();
                DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(readMessage);
                dataOutputStream.flush();

            }
            catch (IOException e) {
                System.out.println("Client offline..");}
            }
        }
}

