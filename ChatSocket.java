import java.net.*;
import java.io.*;
import java.util.*;

public class ChatSocket extends Socket {
   private Socket s;
   private InputStream is;
   private Scanner in;
   
   public ChatSocket(Socket socket) {
      s = socket;
      is = s.getInputStream();
      in = new Scanner(in);
   }
   
   public String getOutput() {
      if (instream.available() != 0) {
         String message = in.nextLine();
         return message;
      } else return null;
   }
   
   public InputStream getIS() {
      return is;
   }
   
   public Scanner getScan() {
      return in;
   }
}