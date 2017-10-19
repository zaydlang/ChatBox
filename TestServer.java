import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

import java.io.*;

import java.util.*;

public class TestServer {
   
   public static Vector<Vector<String>> messageQueue    = new Vector<Vector<String>>();
   public static Vector<Vector<String>> messageDispatch = new Vector<Vector<String>>();
   private Socket clientSocket = null;
   public static final boolean DEBUG_MODE = true;

   public static void main(String[] args) throws Exception {
      TestServer dummy = new TestServer();
      dummy.startServer(args);
   }

   public void startServer(String[] args) throws Exception {
      if (args.length < 1) {
         System.out.println("ERROR: Not enough parameters. Usage: java TestServer <port>");
         System.exit(1);
      } 

      int portNumber = Integer.parseInt(args[0]);
      System.out.println("Listening on port " + portNumber + ".");
      ServerSocket server = new ServerSocket(portNumber);
      server.setSoTimeout(1000);

      while (true) {
         for (int i = 0; i < messageQueue.size(); i++) {
            while (messageQueue.get(i).size() != 0) {
	       String key = (messageQueue.get(i).get(0));
               System.out.println("Received Message: " + key);
               
	       for (int j = 0; j < messageQueue.size(); j++) {
                  System.out.println("Dispatching message '" + key + "' to ID " + j);
	          if (j != i) messageDispatch.get(j).add(key);
               }

               messageQueue.get(i).removeElementAt(0);
            }
         }
         
         if (DEBUG_MODE) System.out.println("Looping...");
         Thread.sleep(1000);
         if (DEBUG_MODE) System.out.println("Please Proceed.");
 
         try {
            clientSocket = server.accept();
            System.out.println("New Client: " + clientSocket);
            
	    messageQueue.add(new Vector<String>());
            messageDispatch.add(new Vector<String>());

            new MessageListener(clientSocket, messageQueue.size() - 1).start();

         } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
               + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
         }
      }
   }
}

// Thanks Horstmann!
class MessageListener extends Thread {
   private Socket s;
   private Scanner in;
   private PrintWriter out;
   private int gID;

   public MessageListener(Socket client, int groupID) {
      s   = client;
      gID = groupID;

      if (TestServer.DEBUG_MODE) System.out.println("New MessageListener created for: " + s + " with ID " + gID + ".");
   }
   
   public void run() {
      in = null;
      out = null;

      try {
         in  = new Scanner(s.getInputStream());
         out = new PrintWriter(s.getOutputStream());
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      while (true) {
         //if (TestServer.DEBUG_MODE) System.out.println(gID + ": Looping Thread...");
         
         try {
            queueMessage();
            dispatchMessage();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
   
   public void queueMessage() throws Exception {
      if (s.getInputStream().available() != 0) {
         String message = in.next();
         if (TestServer.DEBUG_MODE) System.out.println(gID + ": Queueing Message.");
         TestServer.messageQueue.get(gID).add(message);
         Thread.sleep(1000);
      }
   }
   
   public void dispatchMessage() {
      //if (TestServer.DEBUG_MODE) System.out.println(gID + ": SIZE: " + TestServer.messageDispatch.get(gID).size());
      for (int i = 0; i < TestServer.messageDispatch.get(gID).size(); i++) {
         if (TestServer.DEBUG_MODE) System.out.println(gID + ": Sending message: " +                 TestServer.messageDispatch.get(gID).get(0));
         out.println(TestServer.messageDispatch.get(gID).get(0));
         out.flush();
         TestServer.messageDispatch.get(gID).removeElementAt(0);
      }
   }
}
