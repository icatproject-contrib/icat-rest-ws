/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import java.util.Iterator;
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
public class InvestigationAllConverter  {

  @XmlAttribute
  private String id;
  
  @XmlElement
  private RunsInfoConverter runs;

  private static Logger log = Logger.getLogger(InvestigationAllConverter.class);

  public InvestigationAllConverter() {
  }

  public InvestigationAllConverter(Investigation inv) {
    log.info("shelly inv name: " + inv.getName());

    ArrayList<RunInfoConverter> list = new ArrayList<RunInfoConverter>();
    Iterator iter = inv.getDatasets().iterator();
    while (iter.hasNext()) {

      Dataset ds = (Dataset) iter.next();
      log.info("shelly ds name: " + ds.getName());
      RunInfoConverter runInfo = new RunInfoConverter(ds);
      list.add(runInfo);
    }

    this.id = inv.getName();
    this.runs = new RunsInfoConverter(list);
  }
}
