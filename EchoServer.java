import java.net.*;
import java.io.*;
import java.util.*;

public class EchoServer {
   private final List<ChatSocket> clientList = new ArrayList<>();

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
            ChatSocket temp = clientList.get(i);
            System.out.println("Checking: " + temp);
            System.out.println(clientList.get(i).isClosed());

            System.out.println(temp.getOutput());
         }

         try (Socket clientSocket = server.accept()) {
            ChatSocket temp = new ChatSocket(clientSocket);
            System.out.println("Success!");
            clientList.add(temp);
            System.out.println(clientList.get(0).isClosed());

         } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
               + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
         }
      }
   }
}


