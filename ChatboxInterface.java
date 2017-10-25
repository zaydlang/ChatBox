//Pranay Sen
//Zayd Qumsieh
//Fall Project

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class ChatboxInterface extends JFrame implements ActionListener {
   private final Font titleFont = new Font("Arial", Font.BOLD, 48);
   private final Font messageFont = new Font("Arial", Font.PLAIN, 35);
   private JLabel title;
   private static JTextArea chatHistory;
   private static JTextField enterMessage;
   private JButton sendMessage;
   private PrintWriter out;
   public static String name;
   private Socket s;
   private InputStream is;
   private OutputStream os;
   private Scanner in;

   public ChatboxInterface(String name, String ip, int port, int id) throws Exception {
      initialize(name, ip, port, id);
   }

   public void initialize(String name, String ip, int port, int id) throws Exception {
      setSize(1300, 1100);
      setLayout(null);
      //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      title = new JLabel("Chat with your friends!");
      title.setBounds(400, 25, 900, 100);
      title.setFont(titleFont);
      add(title);

      chatHistory = new JTextArea();
      chatHistory.setBounds(50, 100 , 1175, 700);
      chatHistory.setEditable(false);
      chatHistory.setFont(messageFont);
      chatHistory.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      add(chatHistory);
      
      
      enterMessage = new JTextField();
      enterMessage.setBounds(50, 825, 975, 175);
      enterMessage.setFont(messageFont);
      add(enterMessage);
      
      ImageIcon sendIcon = new ImageIcon("send.png");
      
      sendMessage = new JButton(sendIcon);
      sendMessage.setBounds(1050, 825, 175, 175);
      sendMessage.addActionListener(this);
      add(sendMessage);
      
      this.getRootPane().setDefaultButton(sendMessage);

      setVisible(true); 

      
      System.out.println("Chat Made");
            
      this.name = name;
      s         = new Socket(ip, port);
      is  = s.getInputStream();
      os = s.getOutputStream();
      in        = new Scanner(is);
      out       = new PrintWriter(os); 
      this.name = name;

      out.println("/join " + id);
      out.flush();
   }

   public void actionPerformed(ActionEvent e) {
      String message = enterMessage.getText();
      appendMessage(message);
      out.println(message);
      out.flush();
   }

   public void run() throws Exception {
      new MessageFinder(s).start();
   }
   
   public static void appendMessage(String message){
      System.out.println("close");
      System.out.println(message);
      enterMessage.setText("");
      String messageHistory = chatHistory.getText();
      chatHistory.setText(messageHistory + "\n" + name + ": " +  message + "\n");
   }

   public static void main(String[] args) throws Exception {
      new ChatboxInterface("test", "qumsieh.net", 34197, 1);
   } 
}

class MessageFinder extends Thread {
   private Socket s;
   private Scanner in;
   private PrintWriter out;

   public MessageFinder(Socket client) {
      s = client;
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
         } catch (Exception e) {
            //e.printStackTrace();
         }
      }
   }
   
   public void queueMessage() throws Exception {
      if (s.getInputStream().available() != 0) {
         String message = in.next();
         System.out.println("Queueing Message.");
         ChatboxInterface.appendMessage(message);
         Thread.sleep(1000);
      }
   }
}

