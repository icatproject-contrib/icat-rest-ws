/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Dataset;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposal")
public class InvestigationDatasetMetaConverter implements Comparable<InvestigationDatasetMetaConverter>{

  @XmlAttribute
  private String id;
  @XmlElement
  private String collection;
  @XmlElement
  private String title;
  @XmlElement
  private Date createTime;
  @XmlElement
  private String runRange;
  
  private static Logger log = Logger.getLogger(InvestigationDatasetMetaConverter.class);

  public InvestigationDatasetMetaConverter() {
  }

  public InvestigationDatasetMetaConverter(Investigation inv) {

    this.id = inv.getName();
    this.collection = inv.getVisitId();
    this.title = inv.getTitle();
    this.createTime = inv.getCreateTime();
    TreeSet set = new TreeSet();
    Iterator iter = inv.getDatasets().iterator();
    while (iter.hasNext()) {
      Dataset ds = (Dataset) iter.next();
      set.add(Integer.parseInt(ds.getName()));
    }
    this.runRange = getRunRange(set);
  }
  
  public int compareTo(InvestigationDatasetMetaConverter compareConverter) {
    return (this.id).compareTo(compareConverter.id);
  }

  private String getRunRange(TreeSet set) {
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
