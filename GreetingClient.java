// File Name GreetingClient.java

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class GreetingClient
{
   public static void main(String [] args)
   {
      try
      {
         System.out.println("Client Connecting to localhost on port 9909");
         Socket client = new Socket();
         client.setSoTimeout(10000);
         client.connect(new InetSocketAddress("localhost", 9909));
         System.out.println("Just connected to "  + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF("Hello from " + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         System.out.println("Server says " + in.readUTF());
         
         client.close();
      } catch(IOException e) {
         e.printStackTrace();
      } 
   }
}