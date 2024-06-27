package solvo;

import static java.lang.Math.random;

import java.util.HashMap;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  final static String A = "a";
  final static String B = "b";
  static final Logger log = LoggerFactory.getLogger("Request");
  final static int MaxThread = 9;
  static final HashMap qa = new HashMap();
  static final HashMap qb = new HashMap();
  static volatile Integer lastx = null;
  static volatile String lastType = null;
  static volatile Thread lastThread = null;
  static Integer n = 1;

  static void queueMaker(String... list) {
    for (String s : list) {
//      System.out.println(s);
      String[] req = s.split("(?=\\d)", 2);
      dispatcher(req[0], Integer.parseInt(req[1]));
    }
  }

  public static void main(String[] args) {

    // test - enter anyting
    Scanner in = new Scanner(System.in);
    for (int i = 88; i > 0; i--) {
      dispatcher((random() > 0.5) ? A : B, (int) (random() * 9));
//      in.next();
    }

    // input is queue
//    queueMaker("a1", "a2", "a3", "a4", "a5", "a6","a7","a8","a9","a0");
//    queueMaker("b1", "b2", "b3", "b4", "b5", "b6","b7","b8","b9","b0");
//    queueMaker("a0", "a0", "a0", "a0", "a0", "b0","b0","b0","b0","b0");
//    queueMaker("a0", "b0", "a1", "b1", "a2", "b2","a3","b3","a4","b4");
//    queueMaker("a1", "a2", "a3", "a4", "b1", "b2", "b3", "b4");
//    queueMaker( "a5", "a6","a7","a8","a9","a0","b5", "b6","b7","b8","b9","b0");
//    queueMaker( "a9","a0","b5", "b6","b7","b8","b9","b0");

    // manual data enter
//    do {
//      String[] req = in.next().split("(?=\\d)", 2);
//      if (req.length > 1) {
//        dispatcher(req[0], Integer.parseInt(req[1]));
//      } else {
//        break;
//      }
//    } while (in.hasNext());

  }

  static void dispatcher(String type, Integer x) {

    String tx = type + x;
    String tn = "#" + (100 + n++);

    synchronized (qa) {
      synchronized (qb) {
        if (A.equals(type)) {
          qa.put(tn, tx);
        } else {
          qb.put(tn, tx);
        }
        log.info(" {}>> {} {} {}", tx, qa.values(), " ".repeat((Main.MaxThread-qa.size())*4),
            qb.values());
      }
    }

    // attribute equals
    if (x.equals(lastx)) {
      try {
        log.info(" SEQUENTAL {}/{} ", lastType + lastx, tx);
        lastThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    lastThread = new Request(type, x, (A.equals(type)) ? qa : qb);
    lastThread.setName(tn);
    lastx = x;
    lastType = type;
    lastThread.start();

    // limit queue to 5 items
    if (qa.size() >= MaxThread || qb.size() >= MaxThread) {
      try {
        lastThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
