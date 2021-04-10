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
            ServerSocket serverSocket2=new ServerSocket(5598);
            getConn(serverSocket);
            getConn(serverSocket2);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void getConn(ServerSocket s12)throws Exception{
        Socket ss1=s12.accept();
        DataInputStream dis =new DataInputStream(ss1.getInputStream());
        DataOutputStream dos= new DataOutputStream(ss1.getOutputStream());
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
        ss1.close();
        s12.close();


    }

}
