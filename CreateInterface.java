import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

import java.util.*;

import java.io.*;

public class CreateInterface extends JFrame {

   private        final Font        font = new Font("Arial", Font.BOLD, 48);
   private static       String ip = "qumsieh.net"; // default
   public JTextField ipField,portField,nameField;
 
   public CreateInterface() throws Exception {
      setSize(1300, 700);
      setLayout(null); 
      
      JLabel title = new JLabel("Welcome to Odyssey Chat!");
      title.setBounds(325, 25, 900, 100);
      title.setFont(font);
      add(title);
      
      JLabel ipLabel = new JLabel("IP:");
      ipLabel.setBounds(200, 300, 100, 100);
      ipLabel.setFont(font);
      add(ipLabel);
      
      ipField = new JTextField("qumsieh.net");
      ipField.setBounds(300, 300, 800, 100);
      ipField.setFont(font);
      ipField.setEditable(true);
      add(ipField);

      JLabel portLabel = new JLabel("Port:");
      portLabel.setBounds(200, 450, 300, 100);
      portLabel.setFont(font);
      add(portLabel);
      
      portField = new JTextField("34197");
      portField.setBounds(375, 450, 425, 100);
      portField.setFont(font);
      portField.setEditable(true);
      add(portField);
      
      JLabel nameLabel = new JLabel("Username:");
      nameLabel.setBounds(200, 150, 500, 100);
      nameLabel.setFont(font);
      add(nameLabel);
      
      nameField = new JTextField();
      nameField.setBounds(525, 150, 575, 100);
      nameField.setFont(font);
      add(nameField);

      JButton connectButton = new JButton("Create");
      connectButton.setBounds(850, 450, 250, 100); 
      connectButton.setFont(font);
      add(connectButton);
      
      setVisible(true);
   }
   
   // DELETE THIS LATER
   public static void main(String[] args) throws Exception {
      new CreateInterface();
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         int id = connectIP();
         new ChatboxInterface(ipField.getText(), Integer.parseInt(portField.getText()), id, nameField.getText());
         this.dispose();
      } catch (Exception ex) {
         JLabel label = new JLabel("Server Not Found");
         label.setFont(font);
         JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public int connectIP() throws Exception {
      Socket       s = new Socket(ipField.getText(), Integer.parseInt(portField.getText()));
      InputStream  instream = s.getInputStream();
      OutputStream outstream = s.getOutputStream();
      Scanner      in = new Scanner(instream);
      PrintWriter  out = new PrintWriter(outstream);

      out.println("/gc");
      Thread.sleep(50);
      if (instream.available() == 0) throw new Exception();

      s.close();
      return in.nextInt();
   }
}
