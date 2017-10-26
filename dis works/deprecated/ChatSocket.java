import java.net.*;
import java.io.*;
import java.util.*;

public class ChatSocket extends Socket {
   private Socket s;

   public ChatSocket(Socket socket) throws IOException {
      s = socket;
   }

   public String getOutput() throws IOException {
      System.out.println("Checking Input: " + s);

      BufferedWriter bw= (BufferedWriter)clients.get(i);
        bw.write(data1);
        bw.write("\r\n");
        bw.flush();
/*
      InputStream is   = s.getInputStream();
      Scanner in       = new Scanner(is);
      String returnVal = null;

      if (is.available() != 0) {
          returnVal =  in.nextLine();
      } */

      is.close();
      return returnVal;
   } 
}
