package solvo;

import static java.lang.Math.random;

import org.junit.jupiter.api.Test;

class MainTest {

  final static String A = "a";
  final static String B = "b";

  @Test
  void entryPointTest() {
    Main.dispatcher("a",1);
    Main.dispatcher("b",1);
    Main.dispatcher("b",2);
    Main.dispatcher("a",2);
    Main.dispatcher("a",2);
    Main.dispatcher("b",3);
    Main.dispatcher("b",1);
    Main.dispatcher("a",2);
  }

  @Test
  void entryPointMultyTest() {
    for (int i = 9; i > 0; i--) {
      String r = (random() > 0.5) ? A : B;
      Integer a = (int) (random() * 9);
      Main.dispatcher(r,a);
    }
  }

}