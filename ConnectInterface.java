import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

import java.io.*;

import java.util.*;

public class ConnectInterface extends JFrame implements ActionListener {

   private final Font font = new Font("Arial", Font.BOLD, 48);
   private final int port = 34197;
   private JTextField ipField, idField;
   private JTextField nameField;
   
   private InputStream  instream;
   private OutputStream outstream;
   private Scanner      in;
   private PrintWriter  out;
   private Socket       s;
   private String[] arg;
   
   public ConnectInterface() {
      setSize(1300, 700);
      setLayout(null); 
      arg = new String[1];
      arg[0] = "/gc 1";
      
      JLabel title = new JLabel("Welcome to Odyssey Chat!");
      title.setBounds(300, 25, 900, 100);
      title.setFont(font);
      add(title);
      
      JLabel ipLabel = new JLabel("IP:");
      ipLabel.setBounds(200, 300, 100, 100);
      ipLabel.setFont(font);
      add(ipLabel);
      
      ipField = new JTextField("");
      ipField.setBounds(275, 300, 825, 100);
      ipField.setFont(font);
      add(ipField);

      JLabel idLabel = new JLabel("ID:");
      idLabel.setBounds(200, 450, 100, 100);
      idLabel.setFont(font);
      add(idLabel);
      
      idField = new JTextField("");
      idField.setBounds(275, 450, 500, 100);
      idField.setFont(font);
      add(idField);
      
      JLabel nameLabel = new JLabel("Username:");
      nameLabel.setBounds(200, 150, 500, 100);
      nameLabel.setFont(font);
      add(nameLabel);
      
      nameField = new JTextField("");
      nameField.setBounds(500, 150, 600, 100);
      nameField.setFont(font);
      add(nameField);
      
      JButton connectButton = new JButton("Connect");
      connectButton.setBounds(800, 450, 300, 100); 
      connectButton.setFont(font);
      connectButton.addActionListener(this);
      add(connectButton);
      
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         //connectIP();
         new ChatboxInterface(ipField.getText(), 34197, Integer.parseInt(idField.getText()), nameField.getText());
         this.dispose();
         System.out.println(s.isClosed());
         System.out.println(s);
         //this.dispose();
      } catch (Exception ex) {
         JLabel label = new JLabel("Server Not Found");
         label.setFont(font);
         ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Couldn't Connect", "Error", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public void connectIP() throws Exception {
      System.out.println(ipField.getText());
      try {
         s = new Socket(ipField.getText(), port);
         instream = s.getInputStream();
         outstream = s.getOutputStream();
         in = new Scanner(instream);
         out = new PrintWriter(outstream); 
         // Send command
   
         String command = nameField.getText();
         System.out.println(command);
         out.println(command);
         out.flush();
         //String response = in.nextLine();
         
         System.out.println(s.isClosed());
       } catch (IOException e) {
         e.printStackTrace();
       }
       System.out.println(s.isClosed());
   }
   
   // DELETE THIS LATER
   public static void main(String[] args) {
      new ConnectInterface();
   }
}
