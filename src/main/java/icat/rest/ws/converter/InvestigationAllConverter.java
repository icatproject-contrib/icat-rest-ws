/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Iterator;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Dataset;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposals")
public class InvestigationAllConverter extends InvestigationMetaConverter {

  @XmlElement
  private String runRange;
  private static Logger log = Logger.getLogger(InvestigationAllConverter.class);

  public InvestigationAllConverter() {
  }

  public InvestigationAllConverter(Investigation inv) {
    super(inv);
    TreeSet set = new TreeSet();
    Iterator iter = inv.getDatasets().iterator();
    while (iter.hasNext()) {
      Dataset ds = (Dataset) iter.next();
      set.add(Integer.parseInt(ds.getName()));
    }
    this.runRange = RunRangeCalculator.getRunRange(set);
  }
}
