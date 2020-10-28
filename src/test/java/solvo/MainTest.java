package solvo;

import static java.lang.Math.random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

  @ParameterizedTest
  @ValueSource(ints = {10,20,30,40})
  void entryPointMultyTest(int  count) {
    for (int i = count; i > 0; i--) {
      String r = (random() > 0.5) ? A : B;
      Integer a = (int) (random() * 9);
      Main.dispatcher(r,a);
    }
  }

}
