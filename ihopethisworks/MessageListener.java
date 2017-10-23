import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

import java.io.*;

import java.util.*;

// Thanks Horstmann!
public class MessageListener implements Runnable {
   private Socket s;
   private Scanner in;
   private PrintWriter out;
   
   public MessageListener(Socket client) {
      s  = client;
   }
   
   public void run() {
      while (true) {
         try {
            in  = new Scanner(s.getInputStream());
            out = new PrintWriter(s.getOutputStream());
         
            queueMessage();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   
   public void queueMessage() {
      while (in.hasNext()) {
         String message = in.next();
         messageQueue.add(message);
      }
   }
}
