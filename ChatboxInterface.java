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
   private static JScrollPane scroll;
   private static JList<String> messageHistory;
   private static DefaultListModel listModel;

   public ChatboxInterface(String name, String ip, int port, int id) throws Exception {
      initialize(name, ip, port, id);
   }

   public void initialize(String names, String ip, int port, int id) throws Exception {
      setSize(1300, 1100);
      setLayout(null);
      //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      title = new JLabel("Chat with your friends!");
      title.setBounds(400, 25, 900, 100);
      title.setFont(titleFont);
      add(title);
/*
      chatHistory = new JTextArea();
      chatHistory.setBounds(50, 100 , 1175, 700);
      chatHistory.setEditable(false);
      chatHistory.setFont(messageFont);
      add(chatHistory);*/
      
      scroll = new JScrollPane();
      scroll.setBounds(50, 100, 1175, 700);
      //scroll.setEditable(false);
      scroll.setFont(messageFont);
      add(scroll);

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

      listModel = new DefaultListModel();
      System.out.println("Chat Made");
           
      this.name = name;
      s         = new Socket(ip, port);
      is  = s.getInputStream();
      os = s.getOutputStream();
      in        = new Scanner(is);
      out       = new PrintWriter(os); 
      this.name = names;

      out.println("/join " + id);
      out.flush();
   }

   public void actionPerformed(ActionEvent e) {
      String message = enterMessage.getText();
      appendMessage(name + ": " + message);
      out.println(name + ": " + message);
      out.flush();
   }

   public void run() throws Exception {
      new MessageFinder(s).start();
   }
   
   public static void appendMessage(String message){
      System.out.println("close");
      System.out.println(message);
      enterMessage.setText("");
      listModel.addElement('\n' + message);
      messageHistory = new JList<String>(listModel);

      scroll.setViewportView(messageHistory);
      JScrollBar vertical = scroll.getVerticalScrollBar();
      vertical.setValue(vertical.getMaximum());
   }

   public static void main(String[] args) throws Exception {
      ChatboxInterface temp = new ChatboxInterface("test", "qumsieh.net", 34197, 1);
      temp.run();
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
         //System.out.println(": Looping Thread...");         
         
         //add(ChatboxInterface.scroll);
         
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

