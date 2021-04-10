package Server_Read_Write;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Connecting to Server...");
            Socket socket = new Socket("localhost", 5598);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connected...\nStart Conversation!");
            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            String s1 = "", s2 = "";

            while (!s1.equals("stop")) {
                s1 = b.readLine();
                dos.writeUTF(s1);
                dos.flush();
                s2 = dis.readUTF();
                System.out.println("Server:...From C2" + s2);
            }
            dos.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
