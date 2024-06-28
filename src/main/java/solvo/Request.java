package solvo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request extends Thread {

  static final Logger log = LoggerFactory.getLogger("Execute");

  final String type;
  final Integer x;
  final HashQ queue;

  public Request(String type, Integer x, HashQ queue) {
    this.type = type;
    this.x = x;
    this.queue = queue;
  }

  @Override
  public void run() {
    synchronized (queue) {
      String tn = Thread.currentThread().getName();
      queue.remove(tn);
      log.info(" << {} {} {} ", type + x,
              " ".repeat(Main.MaxThread* (type.equals(Main.B) ? 4 : 0)), queue.values());
    }
  }

}
