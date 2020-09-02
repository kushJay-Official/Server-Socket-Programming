package Server;

import java.io.DataOutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws  Exception{
        try{
            Socket socket=new Socket("localhost",5995);
            DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("Welcome Client to our first Server");
            dos.flush();
            dos.close();
            socket.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
