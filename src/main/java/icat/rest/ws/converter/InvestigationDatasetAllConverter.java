/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
public class InvestigationDatasetAllConverter extends InvestigationMetaConverter {

  @XmlElement
  private InvestigationRunInfoWrapperConverter runs;
  private static Logger log = Logger.getLogger(InvestigationDatasetAllConverter.class);

  public InvestigationDatasetAllConverter() {
  }

  public InvestigationDatasetAllConverter(Investigation inv) {
    super(inv);

    log.info("Begin InvestigationDatasetAllConverter inv: " + inv.getName() + ", dataset size: " + inv.getDatasets().size());
    ArrayList<InvestigationRunInfoConverter> list = new ArrayList<InvestigationRunInfoConverter>();

    Iterator iter = inv.getDatasets().iterator();
    while (iter.hasNext()) {

      Dataset ds = (Dataset) iter.next();
      if (ds.getType().getName().equalsIgnoreCase("experiment_raw")) {
        InvestigationRunInfoConverter runInfo = new InvestigationRunInfoConverter(ds);
        list.add(runInfo);
      }
    }

    Collections.sort(list);
    this.runs = new InvestigationRunInfoWrapperConverter(list);
    log.info("End InvestigationDatasetAllConverter inv: " + inv.getName());

  }
}
