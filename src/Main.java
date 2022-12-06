import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JFrame;

public class Main {

  Vector<Object> data = new Vector<>();
  Class[] types = new Class[]{
    String.class,
    Integer.class,
    Boolean.class,
    Float.class,
    JFrame.class
  };

  public static void main(String[] args) throws IOException {
      BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        // Reading data using readLine
        String name = reader.readLine();
 
        // Printing the read line
        System.out.println(name);
  }

  static void onMessage(Vector<String> content) {
    int dataIndex = Integer.parseInt(content.get(0));

    if(dataIndex == -1) {
      JFrame frame = new JFrame(content.get(1));
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      return;
    }

    int type = Integer.parseInt(content.get(1));
    String method = content.get(2);
  }

}