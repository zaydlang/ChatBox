import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

import java.io.*;

public class HostInterface extends JFrame {

   private        final Font        font = new Font("Arial", Font.BOLD, 48);
   private static final InetAddress ip;
   
   public HostInterface() throws Exception {
      setSize(1300, 550);
      setLayout(null); 
      
      JLabel title = new JLabel("Welcome to ChatBox!");
      title.setBounds(400, 25, 900, 100);
      title.setFont(font);
      add(title);
      
      JLabel ipLabel = new JLabel("IP:");
      ipLabel.setBounds(200, 450, 100, 100);
      ipLabel.setFont(font);
      add(ipLabel);
      
      JTextField ipField = new JTextField("qumsieh.net");
      ipField.setBounds(275, 450, 500, 100);
      ipField.setFont(font);
      ipField.setEditable(true);
      add(ipField);

      JLabel portLabel = new JLabel("Port:");
      ipLabel.setBounds(200, 300, 100, 100);
      ipLabel.setFont(font);
      add(ipLabel);
      
      JTextField portField = new JTextField("34197");
      ipField.setBounds(325, 300, 500, 100);
      ipField.setFont(font);
      ipField.setEditable(true);
      add(ipField);
      
      JLabel nameLabel = new JLabel("Username:");
      nameLabel.setBounds(200, 150, 500, 100);
      nameLabel.setFont(font);
      add(nameLabel);
      
      JTextField nameField = new JTextField();
      nameField.setBounds(475, 150, 625, 100);
      nameField.setFont(font);
      add(nameField);
      
      JButton connectButton = new JButton("Connect");
      connectButton.setBounds(800, 300, 300, 100); 
      connectButton.setFont(font);
      add(connectButton);
      
      setVisible(true);
   }
   
   public String getIP() throws Exception {
      return ip.getHostAddress();
   }
   
   // DELETE THIS LATER
   public static void main(String[] args) throws Exception {
      new HostInterface();
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         connectIP();
         //this.dispose();
      } catch (Exception ex) {
         JLabel label = new JLabel("Server Not Found");
         label.setFont(font);
         JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public Socket connectIP() throws Exception {
      Socket succ = new Socket();
      return succ;
      //succ.accept();
   }
}
