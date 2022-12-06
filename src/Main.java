import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class Main {

  static Vector<Object> data = new Vector<>();
  static Class[] types = new Class[]{
    String.class,
    Integer.class,
    Boolean.class,
    Float.class,
    JFrame.class
  };
  static interface classConversionLambda<T> {
    T run(String json);
  }
  static classConversionLambda[] conversions = new classConversionLambda[]{
    json -> {
      String content = "";
      boolean esc = false;
      for(int i = 0; i < json.length(); i++) {
        if(json.charAt(i) == '\\') {
          esc = true;
          continue;
        }
        if(!esc && json.charAt(i) == '"') {
          esc = false;
          continue;
        }
        if(esc) esc = false;
        content += json.charAt(i);
      }
      return content;
    },
    json -> Integer.parseInt(json)
  };
  static class Double<X, Y> {
    X x;
    Y y;

    public Double(X x, Y y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(
      new InputStreamReader(System.in)
    );

    while(true) {
      String content = reader.readLine();
      String[] contentParts = content.split("\u0000");
      Vector<Double<Class, ?>> inArgs = new Vector();

      for(int i = 3; i < contentParts.length; i += 2) {
        int typeIndex = Integer.parseInt(contentParts[i]);
        inArgs.add(new Double(
          types[typeIndex],
          conversions[typeIndex].run(contentParts[i + 1])
        ));
      }

      System.out.println(Arrays.toString(contentParts));
      int typeIndex = Integer.parseInt(contentParts[0]);
      if(typeIndex == -1) {
        System.out.println("new");
        continue;
      }

      onMessage(
        types[Integer.parseInt(contentParts[0])],
        Integer.parseInt(contentParts[1]),
        contentParts[2],
        inArgs
      );
    }
  }

  static void onMessage(Class type, int id, String methodName, Vector<Double<Class, ?>> args) {
    
  }

  static void test(int number) {
    System.out.println(number);
  }

}