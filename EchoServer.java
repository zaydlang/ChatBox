import java.net.*;
import java.io.*;
import java.util.*;

public class EchoServer {
   public static ArrayList<Socket> clientList = new ArrayList<Socket>();
   
   public static void main(String[] args) throws IOException {

      int portNumber = Integer.parseInt(args[0]);
      System.out.println("Listening...");
      ServerSocket server = new ServerSocket(portNumber);
        
      while(true) {
         for (int index = 0; index < clientList.size(); index++) {
            Socket temp = clientList.get(index);
            
            if (temp.isInputShutdown()) {
               InputStream instream = temp.getInputStream();
               Scanner in = new Scanner(instream);
               
               String message = in.nextLine();
               System.out.println(message);
            }
         }
         
         try (Socket clientSocket = server.accept()) {
            System.out.println("Success!");
            clientList.set(0, clientSocket);
         
         } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
               + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
         }
      }
   }
}
