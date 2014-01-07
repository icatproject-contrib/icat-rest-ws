/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "proposals")
public class InvestigationCollectionAllConverter {

  @XmlElement
  private ArrayList<InvestigationDatasetAllConverter> proposal;
  private static Logger log = Logger.getLogger(InvestigationCollectionAllConverter.class);

  public InvestigationCollectionAllConverter() {
  }

  public InvestigationCollectionAllConverter(ArrayList<Investigation> invList) {
    ArrayList<InvestigationDatasetAllConverter> list = new ArrayList<InvestigationDatasetAllConverter>();
    log.info("shelly invList = " + invList.size());
      
    for (int i=0; i<invList.size(); i++) {

      InvestigationDatasetAllConverter invConvertor = new InvestigationDatasetAllConverter(invList.get(i));
      list.add(invConvertor);
    }
    this.proposal = list;
  }
}
