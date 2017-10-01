import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.net.*;

public class ConnectInterface extends JFrame implements ActionListener {

   private final Font font = new Font("Arial", Font.BOLD, 48);
   private final int port = 12345;
   private JTextField ipField;
   private JTextField nameField;
   
   // Honestly don't know why this works or how it works but https://stackoverflow.com/questions/1028661/unhandled-exceptions-in-field-initializations
   /*static {
   
      InetAddress tempIP = null;
      
      try {
         tempIP = InetAddress.getByAddr;
      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Error", e.toString(), JOptionPane.ERROR_MESSAGE);
      } finally {
         ip = tempIP;
      }
   }*/
   
   public ConnectInterface() {
      setSize(1300, 550);
      setLayout(null); 
      
      JLabel title = new JLabel("Welcome to ChatBox!");
      title.setBounds(400, 25, 900, 100);
      title.setFont(font);
      add(title);
      
      JLabel ipLabel = new JLabel("IP:");
      ipLabel.setBounds(200, 300, 100, 100);
      ipLabel.setFont(font);
      add(ipLabel);
      
      ipField = new JTextField("");
      ipField.setBounds(275, 300, 500, 100);
      ipField.setFont(font);
      add(ipField);
      
      JLabel nameLabel = new JLabel("Username:");
      nameLabel.setBounds(200, 150, 500, 100);
      nameLabel.setFont(font);
      add(nameLabel);
      
      nameField = new JTextField("");
      nameField.setBounds(475, 150, 625, 100);
      nameField.setFont(font);
      add(nameField);
      
      JButton connectButton = new JButton("Connect");
      connectButton.setBounds(800, 300, 300, 100); 
      connectButton.setFont(font);
      connectButton.addActionListener(this);
      add(connectButton);
      
      setVisible(true);
   }

   /**
    * This overrided function paints the canvas given the
    * arrays of points.
    */
   public void paintComponent (Graphics g) {
   
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         System.out.println(connectIP());
         //this.dispose();
      } catch (Exception ex) {
         JLabel label = new JLabel("Server Not Found");
         label.setFont(font);
         ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Couldn't Connect", "Error", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public Socket connectIP() throws Exception {
      System.out.println(ipField.getText());
      Socket succ = new Socket(ipField.getText(), port);
      return succ;
   }
   
   // DELETE THIS LATER
   public static void main(String[] args) {
      new ConnectInterface();
   }
}