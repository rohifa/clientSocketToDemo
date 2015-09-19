// File Name GreetingServer.java

import java.net.*;
import java.io.*;

public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   
   public GreetingServer() throws IOException {
      serverSocket = new ServerSocket(9909);
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Server Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            Thread.sleep(15000);
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Server: 15 seconds passed. Thank you for connecting \nGoodbye!");
            server.close();
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         } catch (InterruptedException e) {
			e.printStackTrace();
		}
      }
   }
   public static void main(String [] args) {
      try
      {
         Thread t = new GreetingServer();
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}