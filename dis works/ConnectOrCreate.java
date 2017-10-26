import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

public class ConnectOrCreate extends JFrame implements ActionListener {
   
   private final Font font = new Font("Arial", Font.BOLD, 48);
   private JButton serverButton;
   private JButton advancedButton; // Used for ActionEvent

   public ConnectOrCreate() {     
      setSize(1300, 700);
      setLayout(null); 
      
      try { 
         BackgroundImage img = new BackgroundImage((BufferedImage)ImageIO.read(new File("images//background.jpg")));
	 setContentPane(img);
      } catch (Exception e) { e.printStackTrace(); };

      JLabel title = new JLabel("Welcome to OdysseyChat!");
      title.setBounds(300, 25, 900, 100);
      title.setFont(font);
      title.setForeground(new Color(225, 225, 225));
      add(title);
      
      JButton connectButton = new JButton("Connect to Chat Server");
      connectButton.setBounds(200, 150, 900, 100); 
      connectButton.setFont(font);
      connectButton.addActionListener(this);
      connectButton.setBorderPainted(false);
      connectButton.setFocusPainted(false);
      connectButton.setBackground(new Color(225, 225, 225));
      add(connectButton);

      serverButton = new JButton("Create a Group Chat");
      serverButton.setBounds(200, 300, 900, 100); 
      serverButton.setFont(font);
      serverButton.addActionListener(this);
      serverButton.setBorderPainted(false);
      serverButton.setFocusPainted(false);
      serverButton.setBackground(new Color(225, 225, 225));
      add(serverButton);

      advancedButton = new JButton("ADVANCED: Start Chat Server");
      advancedButton.setBounds(200, 450, 900, 100); 
      advancedButton.setFont(font);
      advancedButton.addActionListener(this);
      advancedButton.setBorderPainted(false);
      advancedButton.setFocusPainted(false);
      advancedButton.setBackground(new Color(225, 225, 225));
      add(advancedButton);
      
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         if (e.getSource() == serverButton) new CreateInterface();
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
      new ConnectOrCreate();
   }
}
