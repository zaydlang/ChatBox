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
import javax.swing.JComponent.*;

public class ChatboxInterface extends JFrame implements ActionListener {
   private final Font titleFont = new Font("Arial", Font.BOLD, 48);
   private final Font messageFont = new Font("Arial", Font.PLAIN, 35);
   private JLabel title;
   private JTextArea chatHistory;
   private JTextField enterMessage;
   private JButton sendMessage;
   private PrintWriter out;
   private String name;

   public ChatboxInterface(String ip, int port, String name, String[] args) throws Exception {
      //setContentPane(new JLabel(new ImageIcon("images\\bckgrnd.jpg")));
      Thread.sleep(10);
      Socket       s = new Socket(ip, port);
      InputStream  instream = s.getInputStream();
      OutputStream outstream = s.getOutputStream();
      Scanner      in = new Scanner(instream);
                   out = new PrintWriter(outstream); 

      this.name = name;
     
      out.println(args[0]);
      out.flush();

      setSize(1300, 1100);
      setLayout(null);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      //JLabel test = new JLabel(new ImageIcon("images//bckgrnd.jpg"));
      //test.setBounds(0, 0, 1300, 1100);
      //setComponentZOrder(test, 0);
      //add(test);
          
      title = new JLabel("Chat with your friends!");
      title.setBounds(400, 25, 900, 100);
      title.setFont(titleFont);
      add(title);

      chatHistory = new JTextArea();
      chatHistory.setBounds(50, 100 , 1175, 700);
      chatHistory.setEditable(false);
      chatHistory.setFont(messageFont);
      chatHistory.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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
      //chatHistory.setBackground(new Color(0, 0, 0, 100));
      setVisible(true);
      
      this.getRootPane().setDefaultButton(sendMessage);
      setLayout(null);
      
      while (true) {
         System.out.println(s.getInputStream().available());
         if (s.getInputStream().available() != 0) {
             String message = in.next();
             appendMessage(message);
         }
         
         TimeUnit.SECONDS.sleep(1);
      }      
   }
   
   public void actionPerformed(ActionEvent e) {
      String message = enterMessage.getText();
      appendMessage(message);
      out.println(message);
      out.flush();
   }
   
   public void appendMessage(String message) {
      if (message.equals("")) return;
      
      System.out.println("close");
      System.out.println(message);
      enterMessage.setText("");
      String messageHistory = chatHistory.getText();
      chatHistory.setText(messageHistory + "\n" + name + ": " +  message + "\n");
   }

   public static void main(String[] args) throws Exception {
      new ChatboxInterface("localhost", 34197, "Test", args);
   }
}

