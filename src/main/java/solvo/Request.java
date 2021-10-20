package solvo;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request extends Thread {

  static final Logger log = LoggerFactory.getLogger("Execute");

  final String type;
  final Integer x;
  final HashMap<String,String> queue;

  public Request(String type, Integer x, HashMap<String,String> queue) {
    this.type = type;
    this.x = x;
    this.queue = queue;
  }

  @Override
  public void run() {
    synchronized (queue) {
      String tn = Thread.currentThread().getName();
      queue.remove(tn);
      log.info(" << {} {} {} ",
          type + x,
          type.equals(Main.B) ? " ".repeat(Main.MaxThread*4) : " ".repeat(0),
          queue.values());
    }
  }

}
