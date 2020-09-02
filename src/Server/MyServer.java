package Server;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args)throws Exception {
        try{
            ServerSocket ss= new ServerSocket(5995);
            Socket s= ss.accept();

            DataInputStream dis= new DataInputStream(s.getInputStream());
            String str= (String)dis.readUTF();
            System.out.println("Message!...\n"+str);
            ss.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
