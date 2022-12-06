import java.lang.reflect.Method;
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

  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("exit");
    }));
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