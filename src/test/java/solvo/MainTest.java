package solvo;

import static java.lang.Math.random;
import static solvo.Main.dispatcher;
import static solvo.Main.queueMaker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MainTest {

  final static String A = "a";
  final static String B = "b";

  @Test
  void entryPointTest() {
    dispatcher(A,1);
    dispatcher(B,1);
    dispatcher(B,2);
    dispatcher(A,2);
    dispatcher(A,2);
    dispatcher(B,3);
    dispatcher(A,1);
    dispatcher(A,2);
    dispatcher(A,3);
  }

  @ParameterizedTest
  @ValueSource(ints = {10,20,30,40})
  void entryPointMultyTest(int count) {
    for (int i = count; i > 0; i--) {
      String r = (random() > 0.5) ? A : B;
      Integer a = (int) (random() * 9);
      dispatcher(r,a);
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {1,2,3,4,5,1,2,3,4,5,1,2,3,4,5,1,2,3,4,5})
  void listTest(int x) {
      dispatcher(A,x);
      dispatcher(B,x);
  }

  @Test
  void queueTest1() {
    queueMaker("a1", "a2", "a3", "a4", "a5", "a6","a7","a8","a9","a0");
  }
  @Test
  void queueTest2() {
    queueMaker("b1", "b2", "b3", "b4", "b5", "b6","b7","b8","b9","b0");
  }
  @Test
  void queueTest3() {
    queueMaker("a0", "a0", "a0", "a0", "a0", "b0","b0","b0","b0","b0");
  }
  @Test
  void queueTest4() {
    queueMaker("a0", "b0", "a1", "b1", "a2", "b2","a3","b3","a4","b4","a5","b5");
  }

}
