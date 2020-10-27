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
  static final HashMap qa = new HashMap();
  static final HashMap qb = new HashMap();
  static volatile Integer lastx = null;
  static volatile String lastType = null;
  static volatile Thread lastThread = null;
  static Integer n = 1;

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    for (int i = 88; i > 0; i--) {
      dispatcher((random() > 0.5) ? A : B, (int) (random() * 9));
//      in.next();
    }

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
        log.info(" {}>> {} {}", tx, qa.values(), qb.values());
      }
    }

    // input attribute equals
    if (x.equals(lastx)) {
      try {
        log.info(" SEQUENTAL {}/{} ", lastType + lastx, tx);
        lastThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // limit queue to 5 items
    if (qa.size() >= 5 || qb.size() >= 5) {
      try {
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
  }

}

