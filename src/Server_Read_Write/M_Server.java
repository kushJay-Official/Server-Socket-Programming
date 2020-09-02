package Server_Read_Write;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class M_Server {
    public static void main(String[] args)throws Exception {
        try{
            System.out.println("Connecting...");
            ServerSocket serverSocket=new ServerSocket(5599);
            Socket socket= serverSocket.accept();

            DataInputStream dis =new DataInputStream(socket.getInputStream());
            DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
            BufferedReader b= new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected...\nStart Conversation!");
            String s1="",s2="";

            while(!s1.equals("stop")){
                s1=dis.readUTF();
                System.out.println("Client:..."+s1);

                s2=b.readLine();
                dos.writeUTF(s2);
                dos.flush();
            }

            dis.close();
            socket.close();
            serverSocket.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
