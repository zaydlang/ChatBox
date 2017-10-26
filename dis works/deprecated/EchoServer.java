import java.net.*;
import java.io.*;
import java.util.*;

public class EchoServer {
   private final Vector<Vector<Socket>> messageQueue = new Vector<Vector<Socket>>();

   public static void main(String[] args) throws IOException {
      EchoServer dummy = new EchoServer();
      dummy.startServer(args);
   }

   public void startServer(String[] args) throws IOException {
      int portNumber = Integer.parseInt(args[0]);
      System.out.println("Listening...");
      ServerSocket server = new ServerSocket(portNumber);

      while(true) {
         for (int i = 0; i < clientList.size(); i++) {
         
         }

         try (Socket clientSocket = server.accept()) {
            Socket temp = new Socket(clientSocket);
            System.out.println(temp + " has connected.");
            // thread here

         } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
               + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
         }
      }
   }
}

class MessageListener {
   Socket s;
      
   public MessageListener() {
   
   }
}
