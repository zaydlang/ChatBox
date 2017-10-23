import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class ConnectOrHost extends JFrame implements ActionListener {
   
   private final Font font = new Font("Arial", Font.BOLD, 48);
   private JButton serverButton; // Used for ActionEvent
   
   public ConnectOrHost() {     
      setSize(1300, 600);
      setLayout(null); 
      
      JLabel title = new JLabel("Welcome to ChatBox!");
      title.setBounds(400, 25, 900, 100);
      title.setFont(font);
      add(title);
      
      JButton connectButton = new JButton("Connect to Chat Server");
      connectButton.setBounds(200, 150, 900, 100); 
      connectButton.setFont(font);
      connectButton.addActionListener(this);
      add(connectButton);
      
      serverButton = new JButton("Host a Chat Server");
      serverButton.setBounds(200, 300, 900, 100); 
      serverButton.setFont(font);
      serverButton.addActionListener(this);
      add(serverButton);
      
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         if (e.getSource() == serverButton) new HostInterface();
         else new ConnectInterface();
         
         this.dispose();
      } catch (Exception ex) {
         JLabel label = new JLabel("No Internet Connection");
         label.setFont(font);
         JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   // DELETE THIS LATER
   public static void main(String[] args) {
      new ConnectOrHost();
   }
}