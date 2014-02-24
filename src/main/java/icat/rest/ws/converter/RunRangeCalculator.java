/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Iterator;
import java.util.TreeSet;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
public class RunRangeCalculator {

  private static Logger log = Logger.getLogger(RunRangeCalculator.class);

  public static String getRunRange(TreeSet set) {
    log.info("Beginning getRunRange()");
    String rRange = "";
    int oldRunNumber = 0;

    Iterator it = set.iterator();
    boolean firstRun = true;
    boolean inIncrement = false;
    while (it.hasNext()) {
      int runNumber = (Integer) it.next();
      if (firstRun) {
        rRange = Integer.toString(runNumber);
        firstRun = false;
      } else {
        if (runNumber == oldRunNumber + 1) {
          if (!inIncrement) {
            rRange = rRange.concat("-");
            inIncrement = true;
          }
        } else {
          if (inIncrement) {
            rRange = rRange.concat(Integer.toString(oldRunNumber));
            inIncrement = false;
          }
          rRange = rRange.concat(", ").concat(Integer.toString(runNumber));
        }
      }
      oldRunNumber = runNumber;
    }

    //Be careful here, we might have exited in the middle of an incremental range. If so, tack on the last number.
    if (inIncrement) {
      rRange = rRange.concat(Integer.toString(oldRunNumber));
    }
    log.info("Ending getRunRange() run range is: " + rRange);
    return rRange;
  }
}
