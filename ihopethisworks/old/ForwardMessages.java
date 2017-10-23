import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

import java.io.*;

import java.util.*;

// Thanks Horstmann!
public class ForwardMessages implements Runnable {
   private Socket s;
   private Scanner in;
   private PrintWriter out;
   private ForwardMessages fm;
   
   public ForwardMessages(Socket client, ForwardMessages inputFM) {
      fm = inputFM;
      s  = client;
   }
   
   public void run() {
      try {
         in  = new Scanner(s.getInputStream());
         out = new PrintWrite(s.getOutputStream());
         
         displayMessage();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void displayMessage() {
      while (in.hasNext()) {
         String command = in.next();
         System.out.println(command);
      }
   }
}